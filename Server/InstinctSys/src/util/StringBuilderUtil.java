package util;

import catfish.base.StartupConfig;

/**
 * 拼接有分隔符的字符序列
 * @author baowzh
 *
 */
public class StringBuilderUtil {
    /**
     * 内置StringBuilder对象
     */
    private StringBuilder sb;
    
    /**
     * 分隔符
     */
    private static String delimiter = StartupConfig.get("instinct.delimiter") == null ? "|" : StartupConfig.get("instinct.delimiter");
    
    /**
     * 构造函数
     */
    public StringBuilderUtil() {
        sb = new StringBuilder();
    }
    
    /**
     * <p>首次拼接内容</p>
     * <p><b>无分隔符</b></p>
     * @param str
     * @return
     */
    public StringBuilderUtil appendFirst(String str) {
        sb.append(str);
        return this;
    }
    
    /**
     * <p>拼接内容</p>
     * <p><b>有分隔符</b></p>
     * @param str
     * @return
     */
    public StringBuilderUtil append(String str) {
        sb.append(delimiter);
        sb.append(str);
        return this;
    }
    
    /**
     * <p>拼接内容</p>
     * <p><b>无分隔符</b></p>
     * @param str
     * @return
     */
    public StringBuilderUtil appendNoDelimiter(String str) {
        sb.append(str);
        return this;
    }
    
    /**
     * 转换字符串
     */
    public String toString() {
        return sb.toString();
    }
}
