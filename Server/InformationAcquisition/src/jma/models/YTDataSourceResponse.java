package jma.models;

public class YTDataSourceResponse {
    private String x_YT_Similarity;//本次比对的相似度
    private String x_YT_SimilarityQueryDatabase;//查询照对证件照(网纹照)的相似度
    private String x_YT_SimilarityQueryIdcard;//查询照对翻拍身份证照的相似度
    private String x_YT_SimilarityIdcardDatabase;//翻拍身份证照对证件照(网纹照)的相似度
    private Boolean x_YT_IsPass;//比对是否通过（依图提供的结果）
    public String getX_YT_Similarity() {
        return x_YT_Similarity;
    }
    public void setX_YT_Similarity(String x_YT_Similarity) {
        this.x_YT_Similarity = x_YT_Similarity;
    }
    public String getX_YT_SimilarityQueryDatabase() {
        return x_YT_SimilarityQueryDatabase;
    }
    public void setX_YT_SimilarityQueryDatabase(String x_YT_SimilarityQueryDatabase) {
        this.x_YT_SimilarityQueryDatabase = x_YT_SimilarityQueryDatabase;
    }
    public String getX_YT_SimilarityQueryIdcard() {
        return x_YT_SimilarityQueryIdcard;
    }
    public void setX_YT_SimilarityQueryIdcard(String x_YT_SimilarityQueryIdcard) {
        this.x_YT_SimilarityQueryIdcard = x_YT_SimilarityQueryIdcard;
    }
    public String getX_YT_SimilarityIdcardDatabase() {
        return x_YT_SimilarityIdcardDatabase;
    }
    public void setX_YT_SimilarityIdcardDatabase(String x_YT_SimilarityIdcardDatabase) {
        this.x_YT_SimilarityIdcardDatabase = x_YT_SimilarityIdcardDatabase;
    }
    public Boolean getX_YT_IsPass() {
        return x_YT_IsPass;
    }
    public void setX_YT_IsPass(Boolean x_YT_IsPass) {
        this.x_YT_IsPass = x_YT_IsPass;
    }
}
