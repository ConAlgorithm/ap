package jma.models.sensitive;

public class SensitiveWordResponse {
    private String sensitiveWord;//敏感词
    private String sensitiveType;//敏感词类型
    private String sensitiveDegree;//敏感词程度
    public String getSensitiveWord() {
        return sensitiveWord;
    }
    public void setSensitiveWord(String sensitiveWord) {
        this.sensitiveWord = sensitiveWord;
    }
    public String getSensitiveType() {
        return sensitiveType;
    }
    public void setSensitiveType(String sensitiveType) {
        this.sensitiveType = sensitiveType;
    }
    public String getSensitiveDegree() {
        return sensitiveDegree;
    }
    public void setSensitiveDegree(String sensitiveDegree) {
        this.sensitiveDegree = sensitiveDegree;
    }
    
} 
