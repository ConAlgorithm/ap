package engine.rule.model.creator.app.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 〈一句话功能简述〉
 *
 * @author liucj
 * @version MobileArray.java, V1.0 2016年10月13日 下午3:18:28
 */
public class MobileArray implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -1167138272690863682L;
     private List<Mobile> contents;
    public List<Mobile> getContents() {
        return contents;
    }
    public void setContents(List<Mobile> contents) {
        this.contents = contents;
    }
    
     
}
