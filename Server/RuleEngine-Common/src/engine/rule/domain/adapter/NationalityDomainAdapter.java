package engine.rule.domain.adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import catfish.base.StartupConfig;

import com.huateng.toprules.core.adapter.DynamicDomainAdapter;
import com.huateng.toprules.core.model.bean.DynamicDomain;
import com.huateng.toprules.core.model.bean.DynamicDomainAttribute;

public class NationalityDomainAdapter implements DynamicDomainAdapter {

	private String dbUrl;

	public NationalityDomainAdapter() {
		StartupConfig.initialize("../conf/startup.properties");
		this.dbUrl = StartupConfig.get("catfish.database.URL");
	}

	@Override
	public DynamicDomain execute(String domainName, String adapterClass,
			Object[] params) {

		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

		List<DynamicDomainAttribute> attributes = new LinkedList<DynamicDomainAttribute>();
		try {
			Class.forName(JDriver);// 动态导入数据库的驱动
			Connection conn = DriverManager.getConnection(dbUrl);// 获取数据库链接
			String query = "SELECT * FROM NationalityObjects";// 创造SQL语句
			Statement stmt = conn.createStatement();// 执行SQL语句
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				DynamicDomainAttribute attribute = new DynamicDomainAttribute(
						rs.getString(3), rs.getString(2), "" + rs.getInt(1));
				attributes.add(attribute);

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
		}
		DynamicDomain domain = new DynamicDomain(domainName, attributes,
				TYPE.NUMBER.toString(), adapterClass);
		return domain;
	}
}
