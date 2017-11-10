package jma.handlers.preprocess;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import catfish.base.CollectionUtils;
import catfish.base.business.common.InstalmentChannel;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PreprocessorFactory {

  private static ConcurrentHashMap<String, Class<IPreprocessor>> preprocessorMapper = new ConcurrentHashMap<>();

  private static Map<InstalmentChannel, String> channelMapper = CollectionUtils.mapOf(
      InstalmentChannel.App, "pos",
      InstalmentChannel.PayDayLoanApp, "pdl",
      InstalmentChannel.CashLoan, "cashloan");

  public static <T> IPreprocessor<T> getProcessor(InstalmentChannel channel, Class<T> modelClazz) {
    String preprocessorName = String.format("jma.handlers.preprocess.%s.%sPreprocessor",
        channelMapper.get(channel),
        modelClazz.getSimpleName().replaceFirst("PreModel", ""));

    try {
      preprocessorMapper.putIfAbsent(preprocessorName, (Class<IPreprocessor>)Class.forName(preprocessorName));
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("No preprocessor: " + preprocessorName);
    }

    try {
      return preprocessorMapper.get(preprocessorName).newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException("Cannot create preprocessor: " + preprocessorName);
    }
  }

}
