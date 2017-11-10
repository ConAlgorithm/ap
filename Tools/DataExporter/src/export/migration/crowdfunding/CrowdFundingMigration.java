package export.migration.crowdfunding;

import java.util.ArrayList;
import java.util.List;

import export.migration.IMigratable;

public class CrowdFundingMigration implements IMigratable{

	@Override
	public void migrate() {
		new CreateCrowdFundingProduct().migrate();
	    new RelateProductToFund().migrate();
		new RelateProductToSeller().migrate();
		new D3ProductQuota().migrate();
		new RedPackConfig().migrate();
	}
}
