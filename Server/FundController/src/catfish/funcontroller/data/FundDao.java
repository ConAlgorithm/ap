package catfish.funcontroller.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.business.dao.AbstractDao;
import catfish.base.database.DatabaseApi;
import catfish.funcontroller.objects.FundObject;


public class FundDao extends AbstractDao<FundObject> {

	private static final String singleSql = "select * from fundobjects where id = :id";
	
	private static final String getFundByFundtagSql = "select top 1 * from fundobjects where fundtag = :fundtag";
			
	@Override
	protected String buildSingleSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, ?> buildSingleParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String buildMultipleSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, ?> buildMultipleParams() {
		// TODO Auto-generated method stub
		return null;
	}

	public FundObject getFundByFundTag(String fundTag) {
		return DatabaseApi.querySingleResult(getFundByFundtagSql, 
				CollectionUtils.<String, Object>newMapBuilder()
				  .add("fundtag", fundTag)
				  .build(),
				  new RowMapper<FundObject>() {
			@Override
			public FundObject mapRow(ResultSet resultSet, int arg1)
					throws SQLException {
				if(resultSet != null)
					return fillObject(FundObject.class, resultSet);
				return null;
			}
		});
	}

}
