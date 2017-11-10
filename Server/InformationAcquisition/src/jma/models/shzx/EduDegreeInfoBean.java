package jma.models.shzx;

public class EduDegreeInfoBean {
    private String  educationDegree ;// 学历明细(研究生、大学本科（简称“大学”）、大学专科和专科学校（简称“大专”）、中等专业学校或中等技术学校、技术学校、高中、初中、小学、文盲或半文盲、未知)
    private String  educationInfoDateTime   ;// 信息获取日期，格式为YYYY.MM.DD
    public String getEducationDegree() {
        return educationDegree;
    }
    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree;
    }
    public String getEducationInfoDateTime() {
        return educationInfoDateTime;
    }
    public void setEducationInfoDateTime(String educationInfoDateTime) {
        this.educationInfoDateTime = educationInfoDateTime;
    }

}
