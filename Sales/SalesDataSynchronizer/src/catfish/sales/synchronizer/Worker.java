package catfish.sales.synchronizer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.object.BaseObject;
import catfish.base.dao.Dao;
import catfish.base.dao.SqlUtils;
import catfish.base.database.Database;

public class Worker {
    private Database source;
    private Database target;
    private String key;
    
    public Worker(Database source, Database target, String key){
        this.source = source;
        this.target = target;
        this.key = key;
    }
    
    public <T extends BaseObject> void run(Class<T> type, String timestamp,Map<String, String> filter,Date currentTime){
        if (type==null){
        	Logger.get().error("传入参数错误，object type 为空!");
        	return;
        }
        
        
        Dao<T> sourceDao = Dao.create(type, source);
        Dao<T> targetDao = Dao.create(type, target);
        String tableName = sourceDao.getTableName();

        //get time stamp, if time stamp is null, return the default value
        String sql = String.format("select max(Dateadded) from %s  ", tableName);
        String oldDataadded=target.querySingleStringOrDefault(sql, null, timestamp);
        if(oldDataadded==null){
        	oldDataadded=timestamp;
        }
        
        //get time stamp, if time stamp is null, return the default value
        sql = String.format("select max(LastModified) from %s  ", tableName);
        String oldLastModified=target.querySingleString(sql, null);   
        if(oldLastModified==null){
        	oldLastModified=timestamp;
        }
        Map<String, String> params=CollectionUtils.<String, String>newMapBuilder()
        		.add("oldDataadded",oldDataadded)
			    .add("oldLastModified",oldLastModified)
			    .build();
        
      //get filter sql string
        String sqlfilter="";
        if(filter!=null){
        	sqlfilter=" and (";
        	Iterator<?> it = filter.entrySet().iterator();
        	while(it.hasNext()){
	        	Entry<String, ?> entry = (Entry<String, ?>)it.next();
	        	sqlfilter+="Id in (select "+entry.getValue() + " from "+entry.getKey()+")" ;
	        	if(it.hasNext()){
	        		sqlfilter+=" OR ";
	        	}
        	}
        	sqlfilter+=" ) "; 	
        }
        
        sql = String.format("select * from %s where CONVERT(varchar, Dateadded, 120)> :oldDataadded  %s order by Dateadded ", tableName,sqlfilter);
    	Logger.get().info("to add sql: "+sql.replace(":oldDataadded", oldDataadded));        
        List<T> addList = sourceDao.getMultiple(sql, params);
        
        if(addList !=null ){
            Logger.get().info(String.format(tableName+" new count: %d", addList.size()));
            for(T data: addList){     	
            	
                String insert = SqlUtils.buildInsert(tableName, SqlUtils.getFields(data),"mysql");
                try{
                	String setsql = String.format("set names utf8mb4 ");
                    target.updateTable(setsql, null);
                    boolean success = targetDao.insert(insert, data);
                    if(!success){
                        Logger.get().error("insert sql failed:"+ insert+","+tableName+" id:"+data.Id);
                        break;
                    }
                }catch(Exception ex){
                    Logger.get().error("insert sql error:"+ insert+","+tableName+" id:"+data.Id, ex);
                    break;
                }
            }
        }
        
        
        sql = String.format("select * from %s where CONVERT(varchar, Dateadded, 120) = CONVERT(varchar, :oldDataadded, 120)  %s order by Dateadded ", tableName,sqlfilter);
    	Logger.get().info("handle margin data sql: "+sql.replace(":oldDataadded", oldDataadded));
    	addList = sourceDao.getMultiple(sql, params);
        if(addList !=null ){
            Logger.get().info(String.format(tableName+" new count: %d", addList.size()));
            for(T data: addList){     	
                String insert = SqlUtils.buildInsert(tableName, SqlUtils.getFields(data));
                try{
                	String checkDuplicateSql = String.format("select * from %s where id= :id ", tableName);
                	params.put("id", data.Id);
                	T oldItem=targetDao.getSingle(checkDuplicateSql, params);
                	if(oldItem!=null){
                		Logger.get().warn("该记录已经存在");
                		continue;
                	}
                    boolean success = targetDao.insert(insert, data);
                    if(!success){
                        Logger.get().error("insert sql failed:"+ insert+","+tableName+" id:"+data.Id);
                        break;
                    }
                }catch(Exception ex){
                    Logger.get().error("insert sql error:"+ insert+","+tableName+" id:"+data.Id, ex);
                    break;
                }
            }
        }
        

        sql = String.format("select * from %s where LastModified> :oldLastModified and (DateAdded < :oldDataadded) %s order by LastModified ", tableName,sqlfilter);
    	Logger.get().info("sql"+sql+",timestamp:"+timestamp);
    	List<T> updateList = sourceDao.getMultiple(sql, params);
        if(updateList !=null ){
            Logger.get().info(String.format(tableName+" update count: %d", updateList.size()));
            for(T data: updateList){
                List<String> filters = new ArrayList<String>();
                filters.add(key);
                List<String> fields = SqlUtils.getFields(data);
                fields.removeAll(filters);
                String update = SqlUtils.buildUpdate(tableName, fields, filters);
                try{
                    boolean success = targetDao.update(update, data);
                    if(!success){
                        Logger.get().error("update sql failed:"+ update+","+tableName+" id:"+data.Id);
                        break;
                    }
                }catch(Exception ex){
                    Logger.get().error("update sql error:"+ update+","+tableName+" id:"+data.Id, ex);
                    break;
                }
            }
        }
    }
}
