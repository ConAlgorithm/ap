package catfish.data.metadata.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
//    @Column(columnDefinition = "datetime default getdate()")
//    private Date dateadded;
//    
//    @Column(columnDefinition = "datetime default getdate()")
//    private Date lastmodified;
//    
//    @Column(columnDefinition = "varchar(255) default 'sys'")
//    private String lastmodifiedby;
    
    @Column(nullable = false)
    private Date dateadded;
    
    @Column(nullable = false)
    private Date lastmodified;
    
    @Column(nullable = false)
    private String lastmodifiedby;
    

    public Long getId() {
        return id;
    }
    
    public Date getDateadded() {
        return dateadded;
    }
    
    public void setDateadded(Date dateadded) {
        this.dateadded = dateadded;
    }
    
    public Date getLastmodified() {
        return lastmodified;
    }
    
    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }
    
    public String getLastmodifiedby() {
        return lastmodifiedby;
    }
    
    public void setLastmodifiedby(String lastmodifiedby) {
        this.lastmodifiedby = lastmodifiedby;
    }
    
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
            return false;
        }

        AbstractEntity that = (AbstractEntity) obj;

        return this.id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}

