package catfish.data.metadata.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.google.gson.Gson;

@MappedSuperclass
public class AbstractMetaData extends AbstractLifecycleEntity{

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String innername;
    
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getInnername() {
        return innername;
    }

    public void setInnername(String innername) {
        this.innername = innername;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
