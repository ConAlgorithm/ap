package export.exporter.jxl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import catfish.base.Logger;
import catfish.base.file.CSVWriter;
import export.Common;
import export.HandlerConfiguration;
import export.exporter.DerivativeVariablesExporter;
import export.exporter.RawDataExporter;

public class JXLDataHandler {

  private final List<DerivativeVariablesExporter> exporter = new ArrayList<DerivativeVariablesExporter>();

  private final String PERSON_FILE_NAME = "person.csv";
  private final String DATA_SOURCE_FILE_NAME = "dataSource.csv";
  private final String APPLICATION_CHECK_FILE_NAME = "applicationCheck.csv";
  private final String BEHAVIOR_CHECK_FILE_NAME = "behaviorCheck.csv";
  private final String CONTACT_REGION_FILE_NAME = "contactRegion.csv";
  private final String CONTACT_LIST_FILE_NAME = "contactList.csv";
  private final String CELL_BEHAVIOR_FILE_NAME = "cellBehavior.csv";
  private final String JXL_SEGMENTATION_FILE_NAME = "jxlSegmentation.csv";

  public JXLDataHandler() {

    exporter.add(new JXLPersonExporter(new CSVWriter(PERSON_FILE_NAME)));
    exporter.add(new JXLDataSourceExporter(new CSVWriter(DATA_SOURCE_FILE_NAME)));
    exporter.add(new JXLApplicationCheckExporter(new CSVWriter(APPLICATION_CHECK_FILE_NAME)));
    exporter.add(new JXLBehaviorCheckExporter(new CSVWriter(BEHAVIOR_CHECK_FILE_NAME)));
    exporter.add(new JXLContactRegionExporter(new CSVWriter(CONTACT_REGION_FILE_NAME)));
    exporter.add(new JXLContactListExporter(new CSVWriter(CONTACT_LIST_FILE_NAME)));
    exporter.add(new JXLCellBehaviorExporter(new CSVWriter(CELL_BEHAVIOR_FILE_NAME)));
  }

  public void handle(String inputFile) {

    if (HandlerConfiguration.getJuxinliSwitch()) {

      Iterator<String> it = Common.getAppIds(inputFile).iterator();
      RawDataExporter jxlTimeExp = new JXLSegmentationExporter(new CSVWriter(JXL_SEGMENTATION_FILE_NAME));

      while (it.hasNext()) {

        String appId = it.next();

        System.out.println("AppId: " + appId);
        try {

          jxlTimeExp.export(appId, null);

          for (DerivativeVariablesExporter e : exporter) {

            e.export(appId, null);
          }

        } catch (Exception e) {
          Logger.get().error("Export data of AppId: " + appId + " error!");
        }
      }
    }
  }
}
