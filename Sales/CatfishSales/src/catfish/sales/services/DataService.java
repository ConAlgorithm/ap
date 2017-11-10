package catfish.sales.services;

import java.util.ArrayList;
import java.util.List;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.dao.Dao;
import catfish.base.dao.SqlUtils;
import catfish.base.database.Database;
import catfish.framework.IServiceProvider;
import catfish.framework.database.IDatabaseService;

public abstract class DataService<T> {
	private Database db;
	private Class<T> type;
	private String IdField="Id";
	private String className;
	
	protected DataService(Class<T> type, Database db){
		this.type = type;
		this.db = db;
		this.className = this.getClass().getName();
	}
		
	public List<T> allStores(){
		Dao<T> dao = Dao.create(type, db);
		String sql = String.format("select * from %s ", dao.getTableName());
		List<T> list = dao.getMultiple(sql, null);
		return list;
	}
	
	public T get(String id){
		if(id ==null){
			Logger.get().warn(className + " - id is null");
			return null;
		}
		
		try{
			Dao<T> dao = Dao.create(type, db);
			String sql = String.format("select * from %s where %s=:%s ", dao.getTableName(), IdField, IdField);
			T result = dao.getSingle(sql, CollectionUtils.mapOf("Id", id));
			return result;
		}catch(Exception e){
			Logger.get().error(className + " - get exception, id=" + id,e);
			return null;
		}
	}
	
	public boolean update(String id, T data){
		if(data == null || id ==null){
			return false; 
		}
		
		try{
			Dao<T> dao = Dao.create(type, db);
			String tableName = dao.getTableName();
			List<String> filters = new ArrayList<String>();
			filters.add(IdField);
			
			List<String> fields = SqlUtils.getFields(data);
			fields.removeAll(filters);
			String update = SqlUtils.buildUpdate(tableName, fields, filters);
			return dao.update(update, data);
		}catch(Exception e){
			Logger.get().error(className +" - update exception, id=",e);
			return false;
		}
	}

	public boolean add(T data){
		if(data == null){
			return false; 
		}
		
		try{
			Dao<T> dao = Dao.create(type, db);
			String tableName = dao.getTableName();
			String insert = SqlUtils.buildInsert(tableName, SqlUtils.getFields(data));
			return dao.insert(insert, data);
		}catch(Exception e){
			Logger.get().error(className + " - add exception",e);
			return false;
		}
	}
}
