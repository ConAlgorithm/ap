package jma.handlers.preprocess.model;

/**
 * 
 * @ClassName: CalCulateImgSimilarityRequestModel 
 * @Description: 请求依图数据源接口RequestModel
 * @author: zhangll
 * @date: 2016年7月11日 下午8:10:40
 */
public class CalCulateImgSimilarityRequestModel {
    private String name;//姓名
    private String idNo;//身份证号
    private String mobile;//手机号
    private String idcardImage; //翻拍照的key
    private String queryImage;// 查询照的key
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIdNo() {
        return idNo;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getIdcardImage() {
        return idcardImage;
    }
    public void setIdcardImage(String idcardImage) {
        this.idcardImage = idcardImage;
    }
    public String getQueryImage() {
        return queryImage;
    }
    public void setQueryImage(String queryImage) {
        this.queryImage = queryImage;
    }
    @Override
    public String toString() {
        return "CalCulateImgSimilarityRequestModel [name=" + name + ", idNo=" + idNo + ", mobile="
               + mobile + ", idcardImage=" + idcardImage + ", queryImage=" + queryImage + "]";
    }
    
    
}
