package export.exporter.merchantview;

import java.util.ArrayList;
import java.util.List;

import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.base.file.IWritable;
import export.exporter.DerivativeVariablesExporter;

public class MerchantViewInfoExporter extends DerivativeVariablesExporter{

	public MerchantViewInfoExporter(IWritable writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void export(String appId, AppDerivativeVariables record) {
		String info = record.getAsString(AppDerivativeVariableNames.MERCHANT_VIEW_STATISTICS_PRINCIPALS);
		if(info == null) return;
		List<String> list = new ArrayList<>();
		list.add(appId);
		list.add(info);
		list.add(record.getAsString(AppDerivativeVariableNames.MERCHANT_VIEW_STATISTICS_PRODUCTS));
		list.add(record.getAsString(AppDerivativeVariableNames.MERCHANT_VIEW_STATISTICS_REPAYMONTHS));
		writer.write(list);
	}

}
