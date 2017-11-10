package jma.models;

public class FuShuBlackListInfo {

    private String hitCondition;
    
    private boolean inBlackList;
    
    private String type;
    
    private BlackDetails details;

    public String getHitCondition() {
        return hitCondition;
    }

    public void setHitCondition(String hitCondition) {
        this.hitCondition = hitCondition;
    }

    public boolean isInBlackList() {
        return inBlackList;
    }

    public void setInBlackList(boolean inBlackList) {
        this.inBlackList = inBlackList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BlackDetails getDetails() {
        return details;
    }

    public void setDetails(BlackDetails details) {
        this.details = details;
    }
}
