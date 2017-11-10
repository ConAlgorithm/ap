package catfish.data.metadata.domain;

import java.util.Date;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractLifecycleEntity extends AbstractEntity{
    
    private Date starttime;
    
    private Date endtime;

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
    
    
}
