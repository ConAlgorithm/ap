package jma.models;

/**
 * 百融信用评估返回字段
 *
 * @author caizhp
 */
public class BR4CreditResponseModel {

	private String code;

	private String swift_number;

	/**
	 * 稳定性评估
	 */
	private Integer flag_stability_c;

	private Integer stab_addr_num;

	private Integer stab_auth_biz_tel;

	private Integer stab_auth_cell;

	private Integer stab_auth_home_tel;

	private Integer stab_auth_id;

	private Integer stab_auth_id_name;

	private String stab_auth_key_relation;

	private Integer stab_auth_mail;

	private Integer stab_auth_name;

	private String stab_cell_firsttime;

	private Integer stab_cell_num;

	private Integer stab_id_num;

	private Integer stab_mail_num;

	private Integer stab_name_num;

	private Integer stab_tel_num;

	/**
	 * 商品消费报告
	 */
	private Integer flag_consumption_c;

	private String cons_cont;

	private Integer cons_m12_JYDQ_num;

	private Integer cons_m12_JYDQ_pay;

	private Integer cons_m12_MYYP_num;

	private Integer cons_m12_MYYP_pay;

	private Integer cons_m12_RYBH_num;

	private Integer cons_m12_RYBH_pay;

	private Integer cons_m3_JYDQ_num;

	private Integer cons_m3_JYDQ_pay;

	private Integer cons_m3_RYBH_num;

	private Integer cons_m3_RYBH_pay;

	private Integer cons_max_m12_num;

	private Integer cons_max_m12_pay;

	private String cons_max_m12_paycate;

	private Integer cons_max_m3_num;

	private Integer cons_max_m3_pay;

	private String cons_max_m3_paycate;

	private String cons_time_recent;

	private Integer cons_tot_m12_num;

	private Integer cons_tot_m12_p_catenum;

	private Integer cons_tot_m12_pay;

	private Integer cons_tot_m12_v_catenum;

	private Integer cons_tot_m12_visits;

	private Integer cons_tot_m3_num;

	private Integer cons_tot_m3_p_catenum;

	private Integer cons_tot_m3_pay;

	private Integer cons_tot_m3_v_catenum;

	private Integer cons_tot_m3_visits;

	/**
	 * 媒体阅览评估
	 */
	private Integer flag_media_c;

	private Integer media_m12_MYYE_visitdays;

	private Integer media_m12_WXYS_visitdays;

	private Integer media_m3_MYYE_visitdays;

	private Integer media_m3_WXYS_visitdays;

	private String media_max_m12_cate;

	private Integer media_max_m12_days;

	private Double media_max_m12_days_prop;

	private String media_max_m3_cate;

	private Integer media_max_m3_days;

	private Double media_max_m3_days_prop;

	private Integer media_tot_m12_catenum;

	private Integer media_tot_m12_visitdays;

	private Integer media_tot_m3_catenum;

	private Integer media_tot_m3_visitdays;

	/**
	 * 社交关系评估
	 */

	private Integer flag_societyrelation;

	private String sr_birthday;

	private String sr_blog;

	private String sr_domain;

	private Integer sr_fans_num;

	private Integer sr_follow_num;

	private String sr_gender;

	private String sr_industry;

	private String sr_int_tag;

	private String sr_level;

	private String sr_location;

	private String sr_match_type;

	private String sr_nick;

	private String sr_reg_date;

	private String sr_spread;

	private String sr_talent;

	private String sr_user_type;

	private Integer sr_weibo_num;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSwift_number() {
		return swift_number;
	}

	public void setSwift_number(String swift_number) {
		this.swift_number = swift_number;
	}

	public Integer getFlag_stability_c() {
		return flag_stability_c;
	}

	public void setFlag_stability_c(Integer flag_stability_c) {
		this.flag_stability_c = flag_stability_c;
	}

	public Integer getStab_addr_num() {
		return stab_addr_num;
	}

	public void setStab_addr_num(Integer stab_addr_num) {
		this.stab_addr_num = stab_addr_num;
	}

	public Integer getStab_auth_biz_tel() {
		return stab_auth_biz_tel;
	}

	public void setStab_auth_biz_tel(Integer stab_auth_biz_tel) {
		this.stab_auth_biz_tel = stab_auth_biz_tel;
	}

	public Integer getStab_auth_cell() {
		return stab_auth_cell;
	}

	public void setStab_auth_cell(Integer stab_auth_cell) {
		this.stab_auth_cell = stab_auth_cell;
	}

	public Integer getStab_auth_home_tel() {
		return stab_auth_home_tel;
	}

	public void setStab_auth_home_tel(Integer stab_auth_home_tel) {
		this.stab_auth_home_tel = stab_auth_home_tel;
	}

	public Integer getStab_auth_id() {
		return stab_auth_id;
	}

	public void setStab_auth_id(Integer stab_auth_id) {
		this.stab_auth_id = stab_auth_id;
	}

	public Integer getStab_auth_id_name() {
		return stab_auth_id_name;
	}

	public void setStab_auth_id_name(Integer stab_auth_id_name) {
		this.stab_auth_id_name = stab_auth_id_name;
	}

	public String getStab_auth_key_relation() {
		return stab_auth_key_relation;
	}

	public void setStab_auth_key_relation(String stab_auth_key_relation) {
		this.stab_auth_key_relation = stab_auth_key_relation;
	}

	public Integer getStab_auth_mail() {
		return stab_auth_mail;
	}

	public void setStab_auth_mail(Integer stab_auth_mail) {
		this.stab_auth_mail = stab_auth_mail;
	}

	public Integer getStab_auth_name() {
		return stab_auth_name;
	}

	public void setStab_auth_name(Integer stab_auth_name) {
		this.stab_auth_name = stab_auth_name;
	}

	public String getStab_cell_firsttime() {
		return stab_cell_firsttime;
	}

	public void setStab_cell_firsttime(String stab_cell_firsttime) {
		this.stab_cell_firsttime = stab_cell_firsttime;
	}

	public Integer getStab_cell_num() {
		return stab_cell_num;
	}

	public void setStab_cell_num(Integer stab_cell_num) {
		this.stab_cell_num = stab_cell_num;
	}

	public Integer getStab_id_num() {
		return stab_id_num;
	}

	public void setStab_id_num(Integer stab_id_num) {
		this.stab_id_num = stab_id_num;
	}

	public Integer getStab_mail_num() {
		return stab_mail_num;
	}

	public void setStab_mail_num(Integer stab_mail_num) {
		this.stab_mail_num = stab_mail_num;
	}

	public Integer getStab_name_num() {
		return stab_name_num;
	}

	public void setStab_name_num(Integer stab_name_num) {
		this.stab_name_num = stab_name_num;
	}

	public Integer getStab_tel_num() {
		return stab_tel_num;
	}

	public void setStab_tel_num(Integer stab_tel_num) {
		this.stab_tel_num = stab_tel_num;
	}

	public Integer getFlag_consumption_c() {
		return flag_consumption_c;
	}

	public void setFlag_consumption_c(Integer flag_consumption_c) {
		this.flag_consumption_c = flag_consumption_c;
	}

	public String getCons_cont() {
		return cons_cont;
	}

	public void setCons_cont(String cons_cont) {
		this.cons_cont = cons_cont;
	}

	public Integer getCons_m12_JYDQ_num() {
		return cons_m12_JYDQ_num;
	}

	public void setCons_m12_JYDQ_num(Integer cons_m12_JYDQ_num) {
		this.cons_m12_JYDQ_num = cons_m12_JYDQ_num;
	}

	public Integer getCons_m12_JYDQ_pay() {
		return cons_m12_JYDQ_pay;
	}

	public void setCons_m12_JYDQ_pay(Integer cons_m12_JYDQ_pay) {
		this.cons_m12_JYDQ_pay = cons_m12_JYDQ_pay;
	}

	public Integer getCons_m12_MYYP_num() {
		return cons_m12_MYYP_num;
	}

	public void setCons_m12_MYYP_num(Integer cons_m12_MYYP_num) {
		this.cons_m12_MYYP_num = cons_m12_MYYP_num;
	}

	public Integer getCons_m12_MYYP_pay() {
		return cons_m12_MYYP_pay;
	}

	public void setCons_m12_MYYP_pay(Integer cons_m12_MYYP_pay) {
		this.cons_m12_MYYP_pay = cons_m12_MYYP_pay;
	}

	public Integer getCons_m12_RYBH_num() {
		return cons_m12_RYBH_num;
	}

	public void setCons_m12_RYBH_num(Integer cons_m12_RYBH_num) {
		this.cons_m12_RYBH_num = cons_m12_RYBH_num;
	}

	public Integer getCons_m12_RYBH_pay() {
		return cons_m12_RYBH_pay;
	}

	public void setCons_m12_RYBH_pay(Integer cons_m12_RYBH_pay) {
		this.cons_m12_RYBH_pay = cons_m12_RYBH_pay;
	}

	public Integer getCons_m3_JYDQ_num() {
		return cons_m3_JYDQ_num;
	}

	public void setCons_m3_JYDQ_num(Integer cons_m3_JYDQ_num) {
		this.cons_m3_JYDQ_num = cons_m3_JYDQ_num;
	}

	public Integer getCons_m3_JYDQ_pay() {
		return cons_m3_JYDQ_pay;
	}

	public void setCons_m3_JYDQ_pay(Integer cons_m3_JYDQ_pay) {
		this.cons_m3_JYDQ_pay = cons_m3_JYDQ_pay;
	}

	public Integer getCons_m3_RYBH_num() {
		return cons_m3_RYBH_num;
	}

	public void setCons_m3_RYBH_num(Integer cons_m3_RYBH_num) {
		this.cons_m3_RYBH_num = cons_m3_RYBH_num;
	}

	public Integer getCons_m3_RYBH_pay() {
		return cons_m3_RYBH_pay;
	}

	public void setCons_m3_RYBH_pay(Integer cons_m3_RYBH_pay) {
		this.cons_m3_RYBH_pay = cons_m3_RYBH_pay;
	}

	public Integer getCons_max_m12_num() {
		return cons_max_m12_num;
	}

	public void setCons_max_m12_num(Integer cons_max_m12_num) {
		this.cons_max_m12_num = cons_max_m12_num;
	}

	public Integer getCons_max_m12_pay() {
		return cons_max_m12_pay;
	}

	public void setCons_max_m12_pay(Integer cons_max_m12_pay) {
		this.cons_max_m12_pay = cons_max_m12_pay;
	}

	public String getCons_max_m12_paycate() {
		return cons_max_m12_paycate;
	}

	public void setCons_max_m12_paycate(String cons_max_m12_paycate) {
		this.cons_max_m12_paycate = cons_max_m12_paycate;
	}

	public Integer getCons_max_m3_num() {
		return cons_max_m3_num;
	}

	public void setCons_max_m3_num(Integer cons_max_m3_num) {
		this.cons_max_m3_num = cons_max_m3_num;
	}

	public Integer getCons_max_m3_pay() {
		return cons_max_m3_pay;
	}

	public void setCons_max_m3_pay(Integer cons_max_m3_pay) {
		this.cons_max_m3_pay = cons_max_m3_pay;
	}

	public String getCons_max_m3_paycate() {
		return cons_max_m3_paycate;
	}

	public void setCons_max_m3_paycate(String cons_max_m3_paycate) {
		this.cons_max_m3_paycate = cons_max_m3_paycate;
	}

	public String getCons_time_recent() {
		return cons_time_recent;
	}

	public void setCons_time_recent(String cons_time_recent) {
		this.cons_time_recent = cons_time_recent;
	}

	public Integer getCons_tot_m12_num() {
		return cons_tot_m12_num;
	}

	public void setCons_tot_m12_num(Integer cons_tot_m12_num) {
		this.cons_tot_m12_num = cons_tot_m12_num;
	}

	public Integer getCons_tot_m12_p_catenum() {
		return cons_tot_m12_p_catenum;
	}

	public void setCons_tot_m12_p_catenum(Integer cons_tot_m12_p_catenum) {
		this.cons_tot_m12_p_catenum = cons_tot_m12_p_catenum;
	}

	public Integer getCons_tot_m12_pay() {
		return cons_tot_m12_pay;
	}

	public void setCons_tot_m12_pay(Integer cons_tot_m12_pay) {
		this.cons_tot_m12_pay = cons_tot_m12_pay;
	}

	public Integer getCons_tot_m12_v_catenum() {
		return cons_tot_m12_v_catenum;
	}

	public void setCons_tot_m12_v_catenum(Integer cons_tot_m12_v_catenum) {
		this.cons_tot_m12_v_catenum = cons_tot_m12_v_catenum;
	}

	public Integer getCons_tot_m12_visits() {
		return cons_tot_m12_visits;
	}

	public void setCons_tot_m12_visits(Integer cons_tot_m12_visits) {
		this.cons_tot_m12_visits = cons_tot_m12_visits;
	}

	public Integer getCons_tot_m3_num() {
		return cons_tot_m3_num;
	}

	public void setCons_tot_m3_num(Integer cons_tot_m3_num) {
		this.cons_tot_m3_num = cons_tot_m3_num;
	}

	public Integer getCons_tot_m3_p_catenum() {
		return cons_tot_m3_p_catenum;
	}

	public void setCons_tot_m3_p_catenum(Integer cons_tot_m3_p_catenum) {
		this.cons_tot_m3_p_catenum = cons_tot_m3_p_catenum;
	}

	public Integer getCons_tot_m3_pay() {
		return cons_tot_m3_pay;
	}

	public void setCons_tot_m3_pay(Integer cons_tot_m3_pay) {
		this.cons_tot_m3_pay = cons_tot_m3_pay;
	}

	public Integer getCons_tot_m3_v_catenum() {
		return cons_tot_m3_v_catenum;
	}

	public void setCons_tot_m3_v_catenum(Integer cons_tot_m3_v_catenum) {
		this.cons_tot_m3_v_catenum = cons_tot_m3_v_catenum;
	}

	public Integer getCons_tot_m3_visits() {
		return cons_tot_m3_visits;
	}

	public void setCons_tot_m3_visits(Integer cons_tot_m3_visits) {
		this.cons_tot_m3_visits = cons_tot_m3_visits;
	}

	public Integer getFlag_media_c() {
		return flag_media_c;
	}

	public void setFlag_media_c(Integer flag_media_c) {
		this.flag_media_c = flag_media_c;
	}

	public Integer getMedia_m12_MYYE_visitdays() {
		return media_m12_MYYE_visitdays;
	}

	public void setMedia_m12_MYYE_visitdays(Integer media_m12_MYYE_visitdays) {
		this.media_m12_MYYE_visitdays = media_m12_MYYE_visitdays;
	}

	public Integer getMedia_m12_WXYS_visitdays() {
		return media_m12_WXYS_visitdays;
	}

	public void setMedia_m12_WXYS_visitdays(Integer media_m12_WXYS_visitdays) {
		this.media_m12_WXYS_visitdays = media_m12_WXYS_visitdays;
	}

	public Integer getMedia_m3_MYYE_visitdays() {
		return media_m3_MYYE_visitdays;
	}

	public void setMedia_m3_MYYE_visitdays(Integer media_m3_MYYE_visitdays) {
		this.media_m3_MYYE_visitdays = media_m3_MYYE_visitdays;
	}

	public Integer getMedia_m3_WXYS_visitdays() {
		return media_m3_WXYS_visitdays;
	}

	public void setMedia_m3_WXYS_visitdays(Integer media_m3_WXYS_visitdays) {
		this.media_m3_WXYS_visitdays = media_m3_WXYS_visitdays;
	}

	public String getMedia_max_m12_cate() {
		return media_max_m12_cate;
	}

	public void setMedia_max_m12_cate(String media_max_m12_cate) {
		this.media_max_m12_cate = media_max_m12_cate;
	}

	public Integer getMedia_max_m12_days() {
		return media_max_m12_days;
	}

	public void setMedia_max_m12_days(Integer media_max_m12_days) {
		this.media_max_m12_days = media_max_m12_days;
	}

	public Double getMedia_max_m12_days_prop() {
		return media_max_m12_days_prop;
	}

	public void setMedia_max_m12_days_prop(Double media_max_m12_days_prop) {
		this.media_max_m12_days_prop = media_max_m12_days_prop;
	}

	public String getMedia_max_m3_cate() {
		return media_max_m3_cate;
	}

	public void setMedia_max_m3_cate(String media_max_m3_cate) {
		this.media_max_m3_cate = media_max_m3_cate;
	}

	public Integer getMedia_max_m3_days() {
		return media_max_m3_days;
	}

	public void setMedia_max_m3_days(Integer media_max_m3_days) {
		this.media_max_m3_days = media_max_m3_days;
	}

	public Double getMedia_max_m3_days_prop() {
		return media_max_m3_days_prop;
	}

	public void setMedia_max_m3_days_prop(Double media_max_m3_days_prop) {
		this.media_max_m3_days_prop = media_max_m3_days_prop;
	}

	public Integer getMedia_tot_m12_catenum() {
		return media_tot_m12_catenum;
	}

	public void setMedia_tot_m12_catenum(Integer media_tot_m12_catenum) {
		this.media_tot_m12_catenum = media_tot_m12_catenum;
	}

	public Integer getMedia_tot_m12_visitdays() {
		return media_tot_m12_visitdays;
	}

	public void setMedia_tot_m12_visitdays(Integer media_tot_m12_visitdays) {
		this.media_tot_m12_visitdays = media_tot_m12_visitdays;
	}

	public Integer getMedia_tot_m3_catenum() {
		return media_tot_m3_catenum;
	}

	public void setMedia_tot_m3_catenum(Integer media_tot_m3_catenum) {
		this.media_tot_m3_catenum = media_tot_m3_catenum;
	}

	public Integer getMedia_tot_m3_visitdays() {
		return media_tot_m3_visitdays;
	}

	public void setMedia_tot_m3_visitdays(Integer media_tot_m3_visitdays) {
		this.media_tot_m3_visitdays = media_tot_m3_visitdays;
	}

	public Integer getFlag_societyrelation() {
		return flag_societyrelation;
	}

	public void setFlag_societyrelation(Integer flag_societyrelation) {
		this.flag_societyrelation = flag_societyrelation;
	}

	public String getSr_birthday() {
		return sr_birthday;
	}

	public void setSr_birthday(String sr_birthday) {
		this.sr_birthday = sr_birthday;
	}

	public String getSr_blog() {
		return sr_blog;
	}

	public void setSr_blog(String sr_blog) {
		this.sr_blog = sr_blog;
	}

	public String getSr_domain() {
		return sr_domain;
	}

	public void setSr_domain(String sr_domain) {
		this.sr_domain = sr_domain;
	}

	public Integer getSr_fans_num() {
		return sr_fans_num;
	}

	public void setSr_fans_num(Integer sr_fans_num) {
		this.sr_fans_num = sr_fans_num;
	}

	public Integer getSr_follow_num() {
		return sr_follow_num;
	}

	public void setSr_follow_num(Integer sr_follow_num) {
		this.sr_follow_num = sr_follow_num;
	}

	public String getSr_gender() {
		return sr_gender;
	}

	public void setSr_gender(String sr_gender) {
		this.sr_gender = sr_gender;
	}

	public String getSr_industry() {
		return sr_industry;
	}

	public void setSr_industry(String sr_industry) {
		this.sr_industry = sr_industry;
	}

	public String getSr_int_tag() {
		return sr_int_tag;
	}

	public void setSr_int_tag(String sr_int_tag) {
		this.sr_int_tag = sr_int_tag;
	}

	public String getSr_level() {
		return sr_level;
	}

	public void setSr_level(String sr_level) {
		this.sr_level = sr_level;
	}

	public String getSr_location() {
		return sr_location;
	}

	public void setSr_location(String sr_location) {
		this.sr_location = sr_location;
	}

	public String getSr_match_type() {
		return sr_match_type;
	}

	public void setSr_match_type(String sr_match_type) {
		this.sr_match_type = sr_match_type;
	}

	public String getSr_nick() {
		return sr_nick;
	}

	public void setSr_nick(String sr_nick) {
		this.sr_nick = sr_nick;
	}

	public String getSr_reg_date() {
		return sr_reg_date;
	}

	public void setSr_reg_date(String sr_reg_date) {
		this.sr_reg_date = sr_reg_date;
	}

	public String getSr_spread() {
		return sr_spread;
	}

	public void setSr_spread(String sr_spread) {
		this.sr_spread = sr_spread;
	}

	public String getSr_talent() {
		return sr_talent;
	}

	public void setSr_talent(String sr_talent) {
		this.sr_talent = sr_talent;
	}

	public String getSr_user_type() {
		return sr_user_type;
	}

	public void setSr_user_type(String sr_user_type) {
		this.sr_user_type = sr_user_type;
	}

	public Integer getSr_weibo_num() {
		return sr_weibo_num;
	}

	public void setSr_weibo_num(Integer sr_weibo_num) {
		this.sr_weibo_num = sr_weibo_num;
	}
}
