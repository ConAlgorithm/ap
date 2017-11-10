package jma.models;

import java.util.List;

public class BlackDetails {

    private List<Court> court;
    
    private List<Finance> finance;

    public List<Court> getCourt() {
        return court;
    }

    public void setCourt(List<Court> court) {
        this.court = court;
    }

    public List<Finance> getFinance() {
        return finance;
    }

    public void setFinance(List<Finance> finance) {
        this.finance = finance;
    }
    
}
