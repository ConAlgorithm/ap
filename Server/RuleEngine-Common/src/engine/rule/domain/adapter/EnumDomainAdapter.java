package engine.rule.domain.adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.antlr.grammar.v3.ANTLRv3Parser.id_return;
import org.apache.commons.logging.LogFactory;

import catfish.base.StartupConfig;

import com.alibaba.fastjson.asm.Label;
import com.huateng.toprules.core.adapter.DynamicDomainAdapter;
import com.huateng.toprules.core.model.bean.DynamicDomain;
import com.huateng.toprules.core.model.bean.DynamicDomainAttribute;

public class EnumDomainAdapter implements DynamicDomainAdapter {

	private String dbUrl;

	public EnumDomainAdapter() {
		if(!StartupConfig.initialize("../conf/startup.properties"))
		{
			if(!StartupConfig.initialize("./conf/startup.properties"))
				StartupConfig.initialize("startup.properties");
		}
		this.dbUrl = StartupConfig.get("catfish.database.URL");
	}

	@Override
	public DynamicDomain execute(String domainName, String adapterClass,
			Object[] params) {

		List<DynamicDomainAttribute> attributes = new LinkedList<DynamicDomainAttribute>();

		if (params == null || params.length < 2)
			return new DynamicDomain(domainName, attributes,
					TYPE.NUMBER.toString(), adapterClass);

		org.apache.commons.logging.Log log = LogFactory.getLog("root");
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

		String enumTypeName = (String) params[0];
		String namespaceOfModel = (String) params[1];
		try {
			Class.forName(JDriver);// 动态导入数据库的驱动
			Connection conn = DriverManager.getConnection(this.dbUrl);// 获取数据库链接
			String query = "SELECT EnumValueObjects.Value"
					+ ",EnumValueObjects.ValueDescription"
					+ ",EnumValueObjects.IsValueObsolete"
					+ ",EnumValueObjects.[Key] "
					+ "FROM EnumTypeObjects, EnumValueObjects, DerivativeVariableObjects "
					+ "where EnumValueObjects.EnumTypeId=EnumTypeObjects.Id and DerivativeVariableObjects.EnumTypeId=EnumTypeObjects.Id and DerivativeVariableObjects.IsVariableObsolete=0 "
					//+ "and NameSpaceOfModel='" + namespaceOfModel
					//+ "' and DerivativeVariableObjects.VariableName='"
					+ " and DerivativeVariableObjects.VariableName='"
					+ enumTypeName + "'";

			log.info(query);
			Set<Integer> distinctSet = new HashSet<>();
			Statement stmt = conn.createStatement();// 执行SQL语句
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				log.info(rs.getInt(1));
				log.info(rs.getString(2));
				log.info(rs.getBoolean(3));
				log.info(rs.getString(4));
				if (rs.getBoolean(3))
					continue;
				
				if(distinctSet.contains(rs.getInt(1)))
					continue;
				
				distinctSet.add(rs.getInt(1));
				/*DynamicDomainAttribute attribute = new DynamicDomainAttribute(
						rs.getString(4), rs.getString(2), "" + rs.getInt(1));*/
				DynamicDomainAttribute attribute = new DynamicDomainAttribute(
						String.valueOf(rs.getInt(1)), rs.getString(2), "" + rs.getInt(1));
				attributes.add(attribute);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			log.error(e);
		}

		//If the attributes doesn't have the default value(0), we create one here.
		int i = 0;
		for (; i < attributes.size(); i++) {
			if(attributes.get(i).getValue().equalsIgnoreCase("0"))
				break;
		}
		if(i == attributes.size()){
			log.info("Create a default DynamicDomainAttribute.");
			
			//保证default的“无”不会跟问题中的真实选项“无”冲突
			String label = "无";
			for (int j = 0; j < attributes.size(); j++) {
				DynamicDomainAttribute attr = attributes.get(j);
				if(!attr.getLabel().equalsIgnoreCase("无"))
					label = "";
			}
			
			attributes.add(new DynamicDomainAttribute("None", label, "0"));
		}

		return new DynamicDomain(domainName, attributes,
				TYPE.NUMBER.toString(), adapterClass);
	}
}
