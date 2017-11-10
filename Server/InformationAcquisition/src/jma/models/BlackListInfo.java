package jma.models;

import java.util.Date;

public class BlackListInfo {
	
	

	private Integer id;

    private String category;

    private String reason;

    private String content;

    private Integer type;

    private String name;

    private String source;

    private String product;

    private Date create_time;

    private Date update_time;

    private String update_by;

    private Byte delete_flag;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReason() {
        return reason;
    }


    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }


    public Integer getType() {
        return type;
    }


    public void setType(Integer type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public String getSource() {
        return source;
    }


    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }


    public String getProduct() {
        return product;
    }


    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }


    public Date getCreate_time() {
        return create_time;
    }


    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }


    public Date getUpdate_time() {
        return update_time;
    }


    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }


    public String getUpdate_by() {
        return update_by;
    }


    public void setUpdate_by(String update_by) {
        this.update_by = update_by == null ? null : update_by.trim();
    }


    public Byte getDelete_flag() {
        return delete_flag;
    }


    public void setDelete_flag(Byte delete_flag) {
        this.delete_flag = delete_flag;
    }
}