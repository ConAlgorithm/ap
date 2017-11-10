package engine.rule.model.creator.app.domain;

import java.io.Serializable;

/**
 * 
 * 〈一句话功能简述〉
 *
 * @author liucj
 * @version Mobile.java, V1.0 2016年10月13日 下午3:18:17
 */
public class Mobile implements Serializable{
    
    private static final long serialVersionUID = 769231699755046409L;

    private String mobile;
     
     private String name;
     
     private NoNum _id;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NoNum get_id() {
        return _id;
    }

    public void set_id(NoNum _id) {
        this._id = _id;
    }

     
}
