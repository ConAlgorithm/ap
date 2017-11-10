/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models;

/**
 * 〈百融特殊接口〉
 *
 * @author dengw
 * @version BR4ApplyVerificationResponseModel.java, V1.0 2017年5月5日 下午4:13:28
 */
public class BR4ApplyVerificationResponseModel {
	private String code;

	private String swift_number;

	/**
	 * 特殊名单核查产品输出标识 1(输出成功),0(未匹配上无输出),98(用户输入信息不足),99(系统异常)
	 */
	private Integer flag_specialList_c;

	private Integer sl_id_abnormal;

	private Integer sl_id_phone_overdue;

	private Integer sl_id_court_bad;

	private Integer sl_id_court_executed;

	private Integer sl_id_bank_bad;

	private Integer sl_id_bank_overdue;

	private Integer sl_id_bank_fraud;

	private Integer sl_id_bank_lost;

	private Integer sl_id_bank_refuse;

	private Integer sl_id_p2p_bad;

	private Integer sl_id_p2p_overdue;

	private Integer sl_id_p2p_fraud;

	private Integer sl_id_p2p_lost;

	private Integer sl_id_p2p_refuse;

	private Integer sl_id_nbank_p2p_bad;

	private Integer sl_id_nbank_p2p_overdue;

	private Integer sl_id_nbank_p2p_fraud;

	private Integer sl_id_nbank_p2p_lost;

	private Integer sl_id_nbank_p2p_refuse;

	private Integer sl_id_nbank_mc_bad;

	private Integer sl_id_nbank_mc_overdue;

	private Integer sl_id_nbank_mc_fraud;

	private Integer sl_id_nbank_mc_lost;

	private Integer sl_id_nbank_mc_refuse;

	private Integer sl_id_nbank_ca_bad;

	private Integer sl_id_nbank_ca_overdue;

	private Integer sl_id_nbank_ca_fraud;

	private Integer sl_id_nbank_ca_lost;

	private Integer sl_id_nbank_ca_refuse;

	private Integer sl_id_nbank_com_bad;

	private Integer sl_id_nbank_com_overdue;

	private Integer sl_id_nbank_com_fraud;

	private Integer sl_id_nbank_com_lost;

	private Integer sl_id_nbank_com_refuse;

	private Integer sl_id_nbank_cf_bad;

	private Integer sl_id_nbank_cf_overdue;

	private Integer sl_id_nbank_cf_fraud;

	private Integer sl_id_nbank_cf_lost;

	private Integer sl_id_nbank_cf_refuse;

	private Integer sl_id_nbank_other_bad;

	private Integer sl_id_nbank_other_overdue;

	private Integer sl_id_nbank_other_fraud;

	private Integer sl_id_nbank_other_lost;

	private Integer sl_id_nbank_other_refuse;

	//通过手机号查询
	private Integer sl_cell_abnormal;

	private Integer sl_cell_phone_overdue;

	private Integer sl_cell_bank_bad;

	private Integer sl_cell_bank_overdue;

	private Integer sl_cell_bank_fraud;

	private Integer sl_cell_bank_lost;

	private Integer sl_cell_bank_refuse;

	private Integer sl_cell_p2p_bad;

	private Integer sl_cell_p2p_overdue;

	private Integer sl_cell_p2p_fraud;

	private Integer sl_cell_p2p_lost;

	private Integer sl_cell_p2p_refuse;

	private Integer sl_cell_nbank_p2p_bad;

	private Integer sl_cell_nbank_p2p_overdue;

	private Integer sl_cell_nbank_p2p_fraud;

	private Integer sl_cell_nbank_p2p_lost;

	private Integer sl_cell_nbank_p2p_refuse;

	private Integer sl_cell_nbank_mc_bad;

	private Integer sl_cell_nbank_mc_overdue;

	private Integer sl_cell_nbank_mc_fraud;

	private Integer sl_cell_nbank_mc_lost;

	private Integer sl_cell_nbank_mc_refuse;

	private Integer sl_cell_nbank_ca_bad;

	private Integer sl_cell_nbank_ca_overdue;

	private Integer sl_cell_nbank_ca_fraud;

	private Integer sl_cell_nbank_ca_lost;

	private Integer sl_cell_nbank_ca_refuse;

	private Integer sl_cell_nbank_com_bad;

	private Integer sl_cell_nbank_com_overdue;

	private Integer sl_cell_nbank_com_fraud;

	private Integer sl_cell_nbank_com_lost;

	private Integer sl_cell_nbank_com_refuse;

	private Integer sl_cell_nbank_cf_bad;

	private Integer sl_cell_nbank_cf_overdue;

	private Integer sl_cell_nbank_cf_fraud;

	private Integer sl_cell_nbank_cf_lost;

	private Integer sl_cell_nbank_cf_refuse;

	private Integer sl_cell_nbank_other_bad;

	private Integer sl_cell_nbank_other_overdue;

	private Integer sl_cell_nbank_other_fraud;

	private Integer sl_cell_nbank_other_lost;

	private Integer sl_cell_nbank_other_refuse;

	//通过联系人手机号查询
	private Integer sl_lm_cell_abnormal;

	private Integer sl_lm_cell_phone_overdue;

	private Integer sl_lm_cell_bank_bad;

	private Integer sl_lm_cell_bank_overdue;

	private Integer sl_lm_cell_bank_fraud;

	private Integer sl_lm_cell_bank_lost;

	private Integer sl_lm_cell_bank_refuse;

	private Integer sl_lm_cell_nbank_p2p_bad;

	private Integer sl_lm_cell_nbank_p2p_overdue;

	private Integer sl_lm_cell_nbank_p2p_fraud;

	private Integer sl_lm_cell_nbank_p2p_lost;

	private Integer sl_lm_cell_nbank_p2p_refuse;

	private Integer sl_lm_cell_nbank_mc_bad;

	private Integer sl_lm_cell_nbank_mc_overdue;

	private Integer sl_lm_cell_nbank_mc_fraud;

	private Integer sl_lm_cell_nbank_mc_lost;

	private Integer sl_lm_cell_nbank_mc_refuse;

	private Integer sl_lm_cell_nbank_ca_bad;

	private Integer sl_lm_cell_nbank_ca_overdue;

	private Integer sl_lm_cell_nbank_ca_fraud;

	private Integer sl_lm_cell_nbank_ca_lost;

	private Integer sl_lm_cell_nbank_ca_refuse;

	private Integer sl_lm_cell_nbank_com_bad;

	private Integer sl_lm_cell_nbank_com_overdue;

	private Integer sl_lm_cell_nbank_com_fraud;

	private Integer sl_lm_cell_nbank_com_lost;

	private Integer sl_lm_cell_nbank_com_refuse;

	private Integer sl_lm_cell_nbank_cf_bad;

	private Integer sl_lm_cell_nbank_cf_overdue;

	private Integer sl_lm_cell_nbank_cf_fraud;

	private Integer sl_lm_cell_nbank_cf_lost;

	private Integer sl_lm_cell_nbank_cf_refuse;

	private Integer sl_lm_cell_nbank_other_bad;

	private Integer sl_lm_cell_nbank_other_overdue;

	private Integer sl_lm_cell_nbank_other_fraud;

	private Integer sl_lm_cell_nbank_other_lost;

	private Integer sl_lm_cell_nbank_other_refuse;
	
	//通过GID查询
	private Integer sl_gid_phone_overdue;

	private Integer sl_gid_bank_bad;

	private Integer sl_gid_bank_overdue;

	private Integer sl_gid_bank_fraud;

	private Integer sl_gid_bank_lost;

	private Integer sl_gid_bank_refuse;

	private Integer sl_gid_p2p_bad;

	private Integer sl_gid_p2p_overdue;

	private Integer sl_gid_p2p_fraud;

	private Integer sl_gid_p2p_lost;

	private Integer sl_gid_p2p_refuse;

	private Integer sl_gid_nbank_p2p_bad;

	private Integer sl_gid_nbank_p2p_overdue;

	private Integer sl_gid_nbank_p2p_fraud;

	private Integer sl_gid_nbank_p2p_lost;

	private Integer sl_gid_nbank_p2p_refuse;

	private Integer sl_gid_nbank_mc_bad;

	private Integer sl_gid_nbank_mc_overdue;

	private Integer sl_gid_nbank_mc_fraud;

	private Integer sl_gid_nbank_mc_lost;

	private Integer sl_gid_nbank_mc_refuse;

	private Integer sl_gid_nbank_ca_bad;

	private Integer sl_gid_nbank_ca_overdue;

	private Integer sl_gid_nbank_ca_fraud;

	private Integer sl_gid_nbank_ca_lost;

	private Integer sl_gid_nbank_ca_refuse;

	private Integer sl_gid_nbank_com_bad;

	private Integer sl_gid_nbank_com_overdue;

	private Integer sl_gid_nbank_com_fraud;

	private Integer sl_gid_nbank_com_lost;

	private Integer sl_gid_nbank_com_refuse;

	private Integer sl_gid_nbank_cf_bad;

	private Integer sl_gid_nbank_cf_overdue;

	private Integer sl_gid_nbank_cf_fraud;

	private Integer sl_gid_nbank_cf_lost;

	private Integer sl_gid_nbank_cf_refuse;

	private Integer sl_gid_nbank_other_bad;

	private Integer sl_gid_nbank_other_overdue;

	private Integer sl_gid_nbank_other_fraud;

	private Integer sl_gid_nbank_other_lost;

	private Integer sl_gid_nbank_other_refuse;

	//多次申请核查V2产品输出标识   1(输出成功),0(未匹配上无输出),98(用户输入信息不足),99(系统异常)
	private Integer flag_applyloanstr;

	//近7天申请次数和机构数
	private Integer als_d7_id_bank_selfnum;

	private Integer als_d7_id_bank_allnum;

	private Integer als_d7_id_bank_orgnum;

	private Integer als_d7_id_nbank_selfnum;

	private Integer als_d7_id_nbank_allnum;

	private Integer als_d7_id_nbank_p2p_allnum;

	private Integer als_d7_id_nbank_mc_allnum;

	private Integer als_d7_id_nbank_ca_allnum;

	private Integer als_d7_id_nbank_cf_allnum;

	private Integer als_d7_id_nbank_com_allnum;

	private Integer als_d7_id_nbank_oth_allnum;

	private Integer als_d7_id_nbank_orgnum;

	private Integer als_d7_id_nbank_p2p_orgnum;

	private Integer als_d7_id_nbank_mc_orgnum;

	private Integer als_d7_id_nbank_ca_orgnum;

	private Integer als_d7_id_nbank_cf_orgnum;

	private Integer als_d7_id_nbank_com_orgnum;

	private Integer als_d7_id_nbank_oth_orgnum;

	private Integer als_d7_cell_bank_selfnum;

	private Integer als_d7_cell_bank_allnum;

	private Integer als_d7_cell_bank_orgnum;

	private Integer als_d7_cell_nbank_selfnum;

	private Integer als_d7_cell_nbank_allnum;

	private Integer als_d7_cell_nbank_p2p_allnum;

	private Integer als_d7_cell_nbank_mc_allnum;

	private Integer als_d7_cell_nbank_ca_allnum;

	private Integer als_d7_cell_nbank_cf_allnum;

	private Integer als_d7_cell_nbank_com_allnum;

	private Integer als_d7_cell_nbank_oth_allnum;

	private Integer als_d7_cell_nbank_orgnum;

	private Integer als_d7_cell_nbank_p2p_orgnum;

	private Integer als_d7_cell_nbank_mc_orgnum;

	private Integer als_d7_cell_nbank_ca_orgnum;

	private Integer als_d7_cell_nbank_cf_orgnum;

	private Integer als_d7_cell_nbank_com_orgnum;

	private Integer als_d7_cell_nbank_oth_orgnum;
	
	//近15天申请次数和机构数
	private Integer als_d15_id_bank_selfnum;

	private Integer als_d15_id_bank_allnum;

	private Integer als_d15_id_bank_orgnum;

	private Integer als_d15_id_nbank_selfnum;

	private Integer als_d15_id_nbank_allnum;

	private Integer als_d15_id_nbank_p2p_allnum;

	private Integer als_d15_id_nbank_mc_allnum;

	private Integer als_d15_id_nbank_ca_allnum;

	private Integer als_d15_id_nbank_cf_allnum;

	private Integer als_d15_id_nbank_com_allnum;

	private Integer als_d15_id_nbank_oth_allnum;

	private Integer als_d15_id_nbank_orgnum;

	private Integer als_d15_id_nbank_p2p_orgnum;

	private Integer als_d15_id_nbank_mc_orgnum;

	private Integer als_d15_id_nbank_ca_orgnum;

	private Integer als_d15_id_nbank_cf_orgnum;

	private Integer als_d15_id_nbank_com_orgnum;

	private Integer als_d15_id_nbank_oth_orgnum;

	private Integer als_d15_cell_bank_selfnum;

	private Integer als_d15_cell_bank_allnum;

	private Integer als_d15_cell_bank_orgnum;

	private Integer als_d15_cell_nbank_selfnum;

	private Integer als_d15_cell_nbank_allnum;

	private Integer als_d15_cell_nbank_p2p_allnum;

	private Integer als_d15_cell_nbank_mc_allnum;

	private Integer als_d15_cell_nbank_ca_allnum;

	private Integer als_d15_cell_nbank_cf_allnum;

	private Integer als_d15_cell_nbank_com_allnum;

	private Integer als_d15_cell_nbank_oth_allnum;

	private Integer als_d15_cell_nbank_orgnum;

	private Integer als_d15_cell_nbank_p2p_orgnum;

	private Integer als_d15_cell_nbank_mc_orgnum;

	private Integer als_d15_cell_nbank_ca_orgnum;

	private Integer als_d15_cell_nbank_cf_orgnum;

	private Integer als_d15_cell_nbank_com_orgnum;

	private Integer als_d15_cell_nbank_oth_orgnum;
	
	//近1个月申请次数和机构数
	private Integer als_m1_id_bank_selfnum;

	private Integer als_m1_id_bank_allnum;

	private Integer als_m1_id_bank_orgnum;

	private Integer als_m1_id_nbank_selfnum;

	private Integer als_m1_id_nbank_allnum;

	private Integer als_m1_id_nbank_p2p_allnum;

	private Integer als_m1_id_nbank_mc_allnum;

	private Integer als_m1_id_nbank_ca_allnum;

	private Integer als_m1_id_nbank_cf_allnum;

	private Integer als_m1_id_nbank_com_allnum;

	private Integer als_m1_id_nbank_oth_allnum;

	private Integer als_m1_id_nbank_orgnum;

	private Integer als_m1_id_nbank_p2p_orgnum;

	private Integer als_m1_id_nbank_mc_orgnum;

	private Integer als_m1_id_nbank_ca_orgnum;

	private Integer als_m1_id_nbank_cf_orgnum;

	private Integer als_m1_id_nbank_com_orgnum;

	private Integer als_m1_id_nbank_oth_orgnum;

	private Integer als_m1_cell_bank_selfnum;

	private Integer als_m1_cell_bank_allnum;

	private Integer als_m1_cell_bank_orgnum;

	private Integer als_m1_cell_nbank_selfnum;

	private Integer als_m1_cell_nbank_allnum;

	private Integer als_m1_cell_nbank_p2p_allnum;

	private Integer als_m1_cell_nbank_mc_allnum;

	private Integer als_m1_cell_nbank_ca_allnum;

	private Integer als_m1_cell_nbank_cf_allnum;

	private Integer als_m1_cell_nbank_com_allnum;

	private Integer als_m1_cell_nbank_oth_allnum;

	private Integer als_m1_cell_nbank_orgnum;

	private Integer als_m1_cell_nbank_p2p_orgnum;

	private Integer als_m1_cell_nbank_mc_orgnum;

	private Integer als_m1_cell_nbank_ca_orgnum;

	private Integer als_m1_cell_nbank_cf_orgnum;

	private Integer als_m1_cell_nbank_com_orgnum;

	private Integer als_m1_cell_nbank_oth_orgnum;
	
	//近3个月申请次数和机构数
	private Integer als_m3_id_bank_selfnum;

	private Integer als_m3_id_bank_allnum;

	private Integer als_m3_id_bank_orgnum;

	private Integer als_m3_id_nbank_selfnum;

	private Integer als_m3_id_nbank_allnum;

	private Integer als_m3_id_nbank_p2p_allnum;

	private Integer als_m3_id_nbank_mc_allnum;

	private Integer als_m3_id_nbank_ca_allnum;

	private Integer als_m3_id_nbank_cf_allnum;

	private Integer als_m3_id_nbank_com_allnum;

	private Integer als_m3_id_nbank_oth_allnum;

	private Integer als_m3_id_nbank_orgnum;

	private Integer als_m3_id_nbank_p2p_orgnum;

	private Integer als_m3_id_nbank_mc_orgnum;

	private Integer als_m3_id_nbank_ca_orgnum;

	private Integer als_m3_id_nbank_cf_orgnum;

	private Integer als_m3_id_nbank_com_orgnum;

	private Integer als_m3_id_nbank_oth_orgnum;

	private Integer als_m3_cell_bank_selfnum;

	private Integer als_m3_cell_bank_allnum;

	private Integer als_m3_cell_bank_orgnum;

	private Integer als_m3_cell_nbank_selfnum;

	private Integer als_m3_cell_nbank_allnum;

	private Integer als_m3_cell_nbank_p2p_allnum;

	private Integer als_m3_cell_nbank_mc_allnum;

	private Integer als_m3_cell_nbank_ca_allnum;

	private Integer als_m3_cell_nbank_cf_allnum;

	private Integer als_m3_cell_nbank_com_allnum;

	private Integer als_m3_cell_nbank_oth_allnum;

	private Integer als_m3_cell_nbank_orgnum;

	private Integer als_m3_cell_nbank_p2p_orgnum;

	private Integer als_m3_cell_nbank_mc_orgnum;

	private Integer als_m3_cell_nbank_ca_orgnum;

	private Integer als_m3_cell_nbank_cf_orgnum;

	private Integer als_m3_cell_nbank_com_orgnum;

	private Integer als_m3_cell_nbank_oth_orgnum;
	
	//近6个月申请记录汇总
	private Integer als_m6_id_tot_mons;

	private Double als_m6_id_avg_monnum;

	private Integer als_m6_id_max_monnum;

	private Integer als_m6_id_min_monnum;

	private Integer als_m6_id_max_inteday;

	private Integer als_m6_id_min_inteday;

	private Integer als_m6_cell_tot_mons;

	private Double als_m6_cell_avg_monnum;

	private Integer als_m6_cell_max_monnum;

	private Integer als_m6_cell_min_monnum;

	private Integer als_m6_cell_max_inteday;

	private Integer als_m6_cell_min_inteday;
	
	//最近最早申请记录
	private Integer als_fst_id_bank_inteday;

	private Integer als_fst_id_nbank_inteday;

	private Integer als_fst_cell_bank_inteday;

	private Integer als_fst_cell_nbank_inteday;

	private Integer als_lst_id_bank_inteday;

	private Integer als_lst_id_bank_consnum;

	private Integer als_lst_id_bank_csinteday;

	private Integer als_lst_id_nbank_inteday;

	private Integer als_lst_id_nbank_consnum;

	private Integer als_lst_id_nbank_csinteday;

	private Integer als_lst_cell_bank_inteday;

	private Integer als_lst_cell_bank_consnum;

	private Integer als_lst_cell_bank_csinteday;

	private Integer als_lst_cell_nbank_inteday;

	private Integer als_lst_cell_nbank_consnum;

	private Integer als_lst_cell_nbank_csinteday;
	
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

	public Integer getFlag_specialList_c() {
		return flag_specialList_c;
	}

	public void setFlag_specialList_c(Integer flag_specialList_c) {
		this.flag_specialList_c = flag_specialList_c;
	}

	public Integer getSl_id_abnormal() {
		return sl_id_abnormal;
	}

	public void setSl_id_abnormal(Integer sl_id_abnormal) {
		this.sl_id_abnormal = sl_id_abnormal;
	}

	public Integer getSl_id_phone_overdue() {
		return sl_id_phone_overdue;
	}

	public void setSl_id_phone_overdue(Integer sl_id_phone_overdue) {
		this.sl_id_phone_overdue = sl_id_phone_overdue;
	}

	public Integer getSl_id_court_bad() {
		return sl_id_court_bad;
	}

	public void setSl_id_court_bad(Integer sl_id_court_bad) {
		this.sl_id_court_bad = sl_id_court_bad;
	}

	public Integer getSl_id_court_executed() {
		return sl_id_court_executed;
	}

	public void setSl_id_court_executed(Integer sl_id_court_executed) {
		this.sl_id_court_executed = sl_id_court_executed;
	}

	public Integer getSl_id_bank_bad() {
		return sl_id_bank_bad;
	}

	public void setSl_id_bank_bad(Integer sl_id_bank_bad) {
		this.sl_id_bank_bad = sl_id_bank_bad;
	}

	public Integer getSl_id_bank_overdue() {
		return sl_id_bank_overdue;
	}

	public void setSl_id_bank_overdue(Integer sl_id_bank_overdue) {
		this.sl_id_bank_overdue = sl_id_bank_overdue;
	}

	public Integer getSl_id_bank_fraud() {
		return sl_id_bank_fraud;
	}

	public void setSl_id_bank_fraud(Integer sl_id_bank_fraud) {
		this.sl_id_bank_fraud = sl_id_bank_fraud;
	}

	public Integer getSl_id_bank_lost() {
		return sl_id_bank_lost;
	}

	public void setSl_id_bank_lost(Integer sl_id_bank_lost) {
		this.sl_id_bank_lost = sl_id_bank_lost;
	}

	public Integer getSl_id_bank_refuse() {
		return sl_id_bank_refuse;
	}

	public void setSl_id_bank_refuse(Integer sl_id_bank_refuse) {
		this.sl_id_bank_refuse = sl_id_bank_refuse;
	}

	public Integer getSl_id_p2p_bad() {
		return sl_id_p2p_bad;
	}

	public void setSl_id_p2p_bad(Integer sl_id_p2p_bad) {
		this.sl_id_p2p_bad = sl_id_p2p_bad;
	}

	public Integer getSl_id_p2p_overdue() {
		return sl_id_p2p_overdue;
	}

	public void setSl_id_p2p_overdue(Integer sl_id_p2p_overdue) {
		this.sl_id_p2p_overdue = sl_id_p2p_overdue;
	}

	public Integer getSl_id_p2p_fraud() {
		return sl_id_p2p_fraud;
	}

	public void setSl_id_p2p_fraud(Integer sl_id_p2p_fraud) {
		this.sl_id_p2p_fraud = sl_id_p2p_fraud;
	}

	public Integer getSl_id_p2p_lost() {
		return sl_id_p2p_lost;
	}

	public void setSl_id_p2p_lost(Integer sl_id_p2p_lost) {
		this.sl_id_p2p_lost = sl_id_p2p_lost;
	}

	public Integer getSl_id_p2p_refuse() {
		return sl_id_p2p_refuse;
	}

	public void setSl_id_p2p_refuse(Integer sl_id_p2p_refuse) {
		this.sl_id_p2p_refuse = sl_id_p2p_refuse;
	}

	public Integer getSl_id_nbank_p2p_bad() {
		return sl_id_nbank_p2p_bad;
	}

	public void setSl_id_nbank_p2p_bad(Integer sl_id_nbank_p2p_bad) {
		this.sl_id_nbank_p2p_bad = sl_id_nbank_p2p_bad;
	}

	public Integer getSl_id_nbank_p2p_overdue() {
		return sl_id_nbank_p2p_overdue;
	}

	public void setSl_id_nbank_p2p_overdue(Integer sl_id_nbank_p2p_overdue) {
		this.sl_id_nbank_p2p_overdue = sl_id_nbank_p2p_overdue;
	}

	public Integer getSl_id_nbank_p2p_fraud() {
		return sl_id_nbank_p2p_fraud;
	}

	public void setSl_id_nbank_p2p_fraud(Integer sl_id_nbank_p2p_fraud) {
		this.sl_id_nbank_p2p_fraud = sl_id_nbank_p2p_fraud;
	}

	public Integer getSl_id_nbank_p2p_lost() {
		return sl_id_nbank_p2p_lost;
	}

	public void setSl_id_nbank_p2p_lost(Integer sl_id_nbank_p2p_lost) {
		this.sl_id_nbank_p2p_lost = sl_id_nbank_p2p_lost;
	}

	public Integer getSl_id_nbank_p2p_refuse() {
		return sl_id_nbank_p2p_refuse;
	}

	public void setSl_id_nbank_p2p_refuse(Integer sl_id_nbank_p2p_refuse) {
		this.sl_id_nbank_p2p_refuse = sl_id_nbank_p2p_refuse;
	}

	public Integer getSl_id_nbank_mc_bad() {
		return sl_id_nbank_mc_bad;
	}

	public void setSl_id_nbank_mc_bad(Integer sl_id_nbank_mc_bad) {
		this.sl_id_nbank_mc_bad = sl_id_nbank_mc_bad;
	}

	public Integer getSl_id_nbank_mc_overdue() {
		return sl_id_nbank_mc_overdue;
	}

	public void setSl_id_nbank_mc_overdue(Integer sl_id_nbank_mc_overdue) {
		this.sl_id_nbank_mc_overdue = sl_id_nbank_mc_overdue;
	}

	public Integer getSl_id_nbank_mc_fraud() {
		return sl_id_nbank_mc_fraud;
	}

	public void setSl_id_nbank_mc_fraud(Integer sl_id_nbank_mc_fraud) {
		this.sl_id_nbank_mc_fraud = sl_id_nbank_mc_fraud;
	}

	public Integer getSl_id_nbank_mc_lost() {
		return sl_id_nbank_mc_lost;
	}

	public void setSl_id_nbank_mc_lost(Integer sl_id_nbank_mc_lost) {
		this.sl_id_nbank_mc_lost = sl_id_nbank_mc_lost;
	}

	public Integer getSl_id_nbank_mc_refuse() {
		return sl_id_nbank_mc_refuse;
	}

	public void setSl_id_nbank_mc_refuse(Integer sl_id_nbank_mc_refuse) {
		this.sl_id_nbank_mc_refuse = sl_id_nbank_mc_refuse;
	}

	public Integer getSl_id_nbank_ca_bad() {
		return sl_id_nbank_ca_bad;
	}

	public void setSl_id_nbank_ca_bad(Integer sl_id_nbank_ca_bad) {
		this.sl_id_nbank_ca_bad = sl_id_nbank_ca_bad;
	}

	public Integer getSl_id_nbank_ca_overdue() {
		return sl_id_nbank_ca_overdue;
	}

	public void setSl_id_nbank_ca_overdue(Integer sl_id_nbank_ca_overdue) {
		this.sl_id_nbank_ca_overdue = sl_id_nbank_ca_overdue;
	}

	public Integer getSl_id_nbank_ca_fraud() {
		return sl_id_nbank_ca_fraud;
	}

	public void setSl_id_nbank_ca_fraud(Integer sl_id_nbank_ca_fraud) {
		this.sl_id_nbank_ca_fraud = sl_id_nbank_ca_fraud;
	}

	public Integer getSl_id_nbank_ca_lost() {
		return sl_id_nbank_ca_lost;
	}

	public void setSl_id_nbank_ca_lost(Integer sl_id_nbank_ca_lost) {
		this.sl_id_nbank_ca_lost = sl_id_nbank_ca_lost;
	}

	public Integer getSl_id_nbank_ca_refuse() {
		return sl_id_nbank_ca_refuse;
	}

	public void setSl_id_nbank_ca_refuse(Integer sl_id_nbank_ca_refuse) {
		this.sl_id_nbank_ca_refuse = sl_id_nbank_ca_refuse;
	}

	public Integer getSl_id_nbank_com_bad() {
		return sl_id_nbank_com_bad;
	}

	public void setSl_id_nbank_com_bad(Integer sl_id_nbank_com_bad) {
		this.sl_id_nbank_com_bad = sl_id_nbank_com_bad;
	}

	public Integer getSl_id_nbank_com_overdue() {
		return sl_id_nbank_com_overdue;
	}

	public void setSl_id_nbank_com_overdue(Integer sl_id_nbank_com_overdue) {
		this.sl_id_nbank_com_overdue = sl_id_nbank_com_overdue;
	}

	public Integer getSl_id_nbank_com_fraud() {
		return sl_id_nbank_com_fraud;
	}

	public void setSl_id_nbank_com_fraud(Integer sl_id_nbank_com_fraud) {
		this.sl_id_nbank_com_fraud = sl_id_nbank_com_fraud;
	}

	public Integer getSl_id_nbank_com_lost() {
		return sl_id_nbank_com_lost;
	}

	public void setSl_id_nbank_com_lost(Integer sl_id_nbank_com_lost) {
		this.sl_id_nbank_com_lost = sl_id_nbank_com_lost;
	}

	public Integer getSl_id_nbank_com_refuse() {
		return sl_id_nbank_com_refuse;
	}

	public void setSl_id_nbank_com_refuse(Integer sl_id_nbank_com_refuse) {
		this.sl_id_nbank_com_refuse = sl_id_nbank_com_refuse;
	}

	public Integer getSl_id_nbank_cf_bad() {
		return sl_id_nbank_cf_bad;
	}

	public void setSl_id_nbank_cf_bad(Integer sl_id_nbank_cf_bad) {
		this.sl_id_nbank_cf_bad = sl_id_nbank_cf_bad;
	}

	public Integer getSl_id_nbank_cf_overdue() {
		return sl_id_nbank_cf_overdue;
	}

	public void setSl_id_nbank_cf_overdue(Integer sl_id_nbank_cf_overdue) {
		this.sl_id_nbank_cf_overdue = sl_id_nbank_cf_overdue;
	}

	public Integer getSl_id_nbank_cf_fraud() {
		return sl_id_nbank_cf_fraud;
	}

	public void setSl_id_nbank_cf_fraud(Integer sl_id_nbank_cf_fraud) {
		this.sl_id_nbank_cf_fraud = sl_id_nbank_cf_fraud;
	}

	public Integer getSl_id_nbank_cf_lost() {
		return sl_id_nbank_cf_lost;
	}

	public void setSl_id_nbank_cf_lost(Integer sl_id_nbank_cf_lost) {
		this.sl_id_nbank_cf_lost = sl_id_nbank_cf_lost;
	}

	public Integer getSl_id_nbank_cf_refuse() {
		return sl_id_nbank_cf_refuse;
	}

	public void setSl_id_nbank_cf_refuse(Integer sl_id_nbank_cf_refuse) {
		this.sl_id_nbank_cf_refuse = sl_id_nbank_cf_refuse;
	}

	public Integer getSl_id_nbank_other_bad() {
		return sl_id_nbank_other_bad;
	}

	public void setSl_id_nbank_other_bad(Integer sl_id_nbank_other_bad) {
		this.sl_id_nbank_other_bad = sl_id_nbank_other_bad;
	}

	public Integer getSl_id_nbank_other_overdue() {
		return sl_id_nbank_other_overdue;
	}

	public void setSl_id_nbank_other_overdue(Integer sl_id_nbank_other_overdue) {
		this.sl_id_nbank_other_overdue = sl_id_nbank_other_overdue;
	}

	public Integer getSl_id_nbank_other_fraud() {
		return sl_id_nbank_other_fraud;
	}

	public void setSl_id_nbank_other_fraud(Integer sl_id_nbank_other_fraud) {
		this.sl_id_nbank_other_fraud = sl_id_nbank_other_fraud;
	}

	public Integer getSl_id_nbank_other_lost() {
		return sl_id_nbank_other_lost;
	}

	public void setSl_id_nbank_other_lost(Integer sl_id_nbank_other_lost) {
		this.sl_id_nbank_other_lost = sl_id_nbank_other_lost;
	}

	public Integer getSl_id_nbank_other_refuse() {
		return sl_id_nbank_other_refuse;
	}

	public void setSl_id_nbank_other_refuse(Integer sl_id_nbank_other_refuse) {
		this.sl_id_nbank_other_refuse = sl_id_nbank_other_refuse;
	}

	public Integer getSl_cell_abnormal() {
		return sl_cell_abnormal;
	}

	public void setSl_cell_abnormal(Integer sl_cell_abnormal) {
		this.sl_cell_abnormal = sl_cell_abnormal;
	}

	public Integer getSl_cell_phone_overdue() {
		return sl_cell_phone_overdue;
	}

	public void setSl_cell_phone_overdue(Integer sl_cell_phone_overdue) {
		this.sl_cell_phone_overdue = sl_cell_phone_overdue;
	}

	public Integer getSl_cell_bank_bad() {
		return sl_cell_bank_bad;
	}

	public void setSl_cell_bank_bad(Integer sl_cell_bank_bad) {
		this.sl_cell_bank_bad = sl_cell_bank_bad;
	}

	public Integer getSl_cell_bank_overdue() {
		return sl_cell_bank_overdue;
	}

	public void setSl_cell_bank_overdue(Integer sl_cell_bank_overdue) {
		this.sl_cell_bank_overdue = sl_cell_bank_overdue;
	}

	public Integer getSl_cell_bank_fraud() {
		return sl_cell_bank_fraud;
	}

	public void setSl_cell_bank_fraud(Integer sl_cell_bank_fraud) {
		this.sl_cell_bank_fraud = sl_cell_bank_fraud;
	}

	public Integer getSl_cell_bank_lost() {
		return sl_cell_bank_lost;
	}

	public void setSl_cell_bank_lost(Integer sl_cell_bank_lost) {
		this.sl_cell_bank_lost = sl_cell_bank_lost;
	}

	public Integer getSl_cell_bank_refuse() {
		return sl_cell_bank_refuse;
	}

	public void setSl_cell_bank_refuse(Integer sl_cell_bank_refuse) {
		this.sl_cell_bank_refuse = sl_cell_bank_refuse;
	}

	public Integer getSl_cell_p2p_bad() {
		return sl_cell_p2p_bad;
	}

	public void setSl_cell_p2p_bad(Integer sl_cell_p2p_bad) {
		this.sl_cell_p2p_bad = sl_cell_p2p_bad;
	}

	public Integer getSl_cell_p2p_overdue() {
		return sl_cell_p2p_overdue;
	}

	public void setSl_cell_p2p_overdue(Integer sl_cell_p2p_overdue) {
		this.sl_cell_p2p_overdue = sl_cell_p2p_overdue;
	}

	public Integer getSl_cell_p2p_fraud() {
		return sl_cell_p2p_fraud;
	}

	public void setSl_cell_p2p_fraud(Integer sl_cell_p2p_fraud) {
		this.sl_cell_p2p_fraud = sl_cell_p2p_fraud;
	}

	public Integer getSl_cell_p2p_lost() {
		return sl_cell_p2p_lost;
	}

	public void setSl_cell_p2p_lost(Integer sl_cell_p2p_lost) {
		this.sl_cell_p2p_lost = sl_cell_p2p_lost;
	}

	public Integer getSl_cell_p2p_refuse() {
		return sl_cell_p2p_refuse;
	}

	public void setSl_cell_p2p_refuse(Integer sl_cell_p2p_refuse) {
		this.sl_cell_p2p_refuse = sl_cell_p2p_refuse;
	}

	public Integer getSl_cell_nbank_p2p_bad() {
		return sl_cell_nbank_p2p_bad;
	}

	public void setSl_cell_nbank_p2p_bad(Integer sl_cell_nbank_p2p_bad) {
		this.sl_cell_nbank_p2p_bad = sl_cell_nbank_p2p_bad;
	}

	public Integer getSl_cell_nbank_p2p_overdue() {
		return sl_cell_nbank_p2p_overdue;
	}

	public void setSl_cell_nbank_p2p_overdue(Integer sl_cell_nbank_p2p_overdue) {
		this.sl_cell_nbank_p2p_overdue = sl_cell_nbank_p2p_overdue;
	}

	public Integer getSl_cell_nbank_p2p_fraud() {
		return sl_cell_nbank_p2p_fraud;
	}

	public void setSl_cell_nbank_p2p_fraud(Integer sl_cell_nbank_p2p_fraud) {
		this.sl_cell_nbank_p2p_fraud = sl_cell_nbank_p2p_fraud;
	}

	public Integer getSl_cell_nbank_p2p_lost() {
		return sl_cell_nbank_p2p_lost;
	}

	public void setSl_cell_nbank_p2p_lost(Integer sl_cell_nbank_p2p_lost) {
		this.sl_cell_nbank_p2p_lost = sl_cell_nbank_p2p_lost;
	}

	public Integer getSl_cell_nbank_p2p_refuse() {
		return sl_cell_nbank_p2p_refuse;
	}

	public void setSl_cell_nbank_p2p_refuse(Integer sl_cell_nbank_p2p_refuse) {
		this.sl_cell_nbank_p2p_refuse = sl_cell_nbank_p2p_refuse;
	}

	public Integer getSl_cell_nbank_mc_bad() {
		return sl_cell_nbank_mc_bad;
	}

	public void setSl_cell_nbank_mc_bad(Integer sl_cell_nbank_mc_bad) {
		this.sl_cell_nbank_mc_bad = sl_cell_nbank_mc_bad;
	}

	public Integer getSl_cell_nbank_mc_overdue() {
		return sl_cell_nbank_mc_overdue;
	}

	public void setSl_cell_nbank_mc_overdue(Integer sl_cell_nbank_mc_overdue) {
		this.sl_cell_nbank_mc_overdue = sl_cell_nbank_mc_overdue;
	}

	public Integer getSl_cell_nbank_mc_fraud() {
		return sl_cell_nbank_mc_fraud;
	}

	public void setSl_cell_nbank_mc_fraud(Integer sl_cell_nbank_mc_fraud) {
		this.sl_cell_nbank_mc_fraud = sl_cell_nbank_mc_fraud;
	}

	public Integer getSl_cell_nbank_mc_lost() {
		return sl_cell_nbank_mc_lost;
	}

	public void setSl_cell_nbank_mc_lost(Integer sl_cell_nbank_mc_lost) {
		this.sl_cell_nbank_mc_lost = sl_cell_nbank_mc_lost;
	}

	public Integer getSl_cell_nbank_mc_refuse() {
		return sl_cell_nbank_mc_refuse;
	}

	public void setSl_cell_nbank_mc_refuse(Integer sl_cell_nbank_mc_refuse) {
		this.sl_cell_nbank_mc_refuse = sl_cell_nbank_mc_refuse;
	}

	public Integer getSl_cell_nbank_ca_bad() {
		return sl_cell_nbank_ca_bad;
	}

	public void setSl_cell_nbank_ca_bad(Integer sl_cell_nbank_ca_bad) {
		this.sl_cell_nbank_ca_bad = sl_cell_nbank_ca_bad;
	}

	public Integer getSl_cell_nbank_ca_overdue() {
		return sl_cell_nbank_ca_overdue;
	}

	public void setSl_cell_nbank_ca_overdue(Integer sl_cell_nbank_ca_overdue) {
		this.sl_cell_nbank_ca_overdue = sl_cell_nbank_ca_overdue;
	}

	public Integer getSl_cell_nbank_ca_fraud() {
		return sl_cell_nbank_ca_fraud;
	}

	public void setSl_cell_nbank_ca_fraud(Integer sl_cell_nbank_ca_fraud) {
		this.sl_cell_nbank_ca_fraud = sl_cell_nbank_ca_fraud;
	}

	public Integer getSl_cell_nbank_ca_lost() {
		return sl_cell_nbank_ca_lost;
	}

	public void setSl_cell_nbank_ca_lost(Integer sl_cell_nbank_ca_lost) {
		this.sl_cell_nbank_ca_lost = sl_cell_nbank_ca_lost;
	}

	public Integer getSl_cell_nbank_ca_refuse() {
		return sl_cell_nbank_ca_refuse;
	}

	public void setSl_cell_nbank_ca_refuse(Integer sl_cell_nbank_ca_refuse) {
		this.sl_cell_nbank_ca_refuse = sl_cell_nbank_ca_refuse;
	}

	public Integer getSl_cell_nbank_com_bad() {
		return sl_cell_nbank_com_bad;
	}

	public void setSl_cell_nbank_com_bad(Integer sl_cell_nbank_com_bad) {
		this.sl_cell_nbank_com_bad = sl_cell_nbank_com_bad;
	}

	public Integer getSl_cell_nbank_com_overdue() {
		return sl_cell_nbank_com_overdue;
	}

	public void setSl_cell_nbank_com_overdue(Integer sl_cell_nbank_com_overdue) {
		this.sl_cell_nbank_com_overdue = sl_cell_nbank_com_overdue;
	}

	public Integer getSl_cell_nbank_com_fraud() {
		return sl_cell_nbank_com_fraud;
	}

	public void setSl_cell_nbank_com_fraud(Integer sl_cell_nbank_com_fraud) {
		this.sl_cell_nbank_com_fraud = sl_cell_nbank_com_fraud;
	}

	public Integer getSl_cell_nbank_com_lost() {
		return sl_cell_nbank_com_lost;
	}

	public void setSl_cell_nbank_com_lost(Integer sl_cell_nbank_com_lost) {
		this.sl_cell_nbank_com_lost = sl_cell_nbank_com_lost;
	}

	public Integer getSl_cell_nbank_com_refuse() {
		return sl_cell_nbank_com_refuse;
	}

	public void setSl_cell_nbank_com_refuse(Integer sl_cell_nbank_com_refuse) {
		this.sl_cell_nbank_com_refuse = sl_cell_nbank_com_refuse;
	}

	public Integer getSl_cell_nbank_cf_bad() {
		return sl_cell_nbank_cf_bad;
	}

	public void setSl_cell_nbank_cf_bad(Integer sl_cell_nbank_cf_bad) {
		this.sl_cell_nbank_cf_bad = sl_cell_nbank_cf_bad;
	}

	public Integer getSl_cell_nbank_cf_overdue() {
		return sl_cell_nbank_cf_overdue;
	}

	public void setSl_cell_nbank_cf_overdue(Integer sl_cell_nbank_cf_overdue) {
		this.sl_cell_nbank_cf_overdue = sl_cell_nbank_cf_overdue;
	}

	public Integer getSl_cell_nbank_cf_fraud() {
		return sl_cell_nbank_cf_fraud;
	}

	public void setSl_cell_nbank_cf_fraud(Integer sl_cell_nbank_cf_fraud) {
		this.sl_cell_nbank_cf_fraud = sl_cell_nbank_cf_fraud;
	}

	public Integer getSl_cell_nbank_cf_lost() {
		return sl_cell_nbank_cf_lost;
	}

	public void setSl_cell_nbank_cf_lost(Integer sl_cell_nbank_cf_lost) {
		this.sl_cell_nbank_cf_lost = sl_cell_nbank_cf_lost;
	}

	public Integer getSl_cell_nbank_cf_refuse() {
		return sl_cell_nbank_cf_refuse;
	}

	public void setSl_cell_nbank_cf_refuse(Integer sl_cell_nbank_cf_refuse) {
		this.sl_cell_nbank_cf_refuse = sl_cell_nbank_cf_refuse;
	}

	public Integer getSl_cell_nbank_other_bad() {
		return sl_cell_nbank_other_bad;
	}

	public void setSl_cell_nbank_other_bad(Integer sl_cell_nbank_other_bad) {
		this.sl_cell_nbank_other_bad = sl_cell_nbank_other_bad;
	}

	public Integer getSl_cell_nbank_other_overdue() {
		return sl_cell_nbank_other_overdue;
	}

	public void setSl_cell_nbank_other_overdue(Integer sl_cell_nbank_other_overdue) {
		this.sl_cell_nbank_other_overdue = sl_cell_nbank_other_overdue;
	}

	public Integer getSl_cell_nbank_other_fraud() {
		return sl_cell_nbank_other_fraud;
	}

	public void setSl_cell_nbank_other_fraud(Integer sl_cell_nbank_other_fraud) {
		this.sl_cell_nbank_other_fraud = sl_cell_nbank_other_fraud;
	}

	public Integer getSl_cell_nbank_other_lost() {
		return sl_cell_nbank_other_lost;
	}

	public void setSl_cell_nbank_other_lost(Integer sl_cell_nbank_other_lost) {
		this.sl_cell_nbank_other_lost = sl_cell_nbank_other_lost;
	}

	public Integer getSl_cell_nbank_other_refuse() {
		return sl_cell_nbank_other_refuse;
	}

	public void setSl_cell_nbank_other_refuse(Integer sl_cell_nbank_other_refuse) {
		this.sl_cell_nbank_other_refuse = sl_cell_nbank_other_refuse;
	}

	public Integer getSl_lm_cell_abnormal() {
		return sl_lm_cell_abnormal;
	}

	public void setSl_lm_cell_abnormal(Integer sl_lm_cell_abnormal) {
		this.sl_lm_cell_abnormal = sl_lm_cell_abnormal;
	}

	public Integer getSl_lm_cell_phone_overdue() {
		return sl_lm_cell_phone_overdue;
	}

	public void setSl_lm_cell_phone_overdue(Integer sl_lm_cell_phone_overdue) {
		this.sl_lm_cell_phone_overdue = sl_lm_cell_phone_overdue;
	}

	public Integer getSl_lm_cell_bank_bad() {
		return sl_lm_cell_bank_bad;
	}

	public void setSl_lm_cell_bank_bad(Integer sl_lm_cell_bank_bad) {
		this.sl_lm_cell_bank_bad = sl_lm_cell_bank_bad;
	}

	public Integer getSl_lm_cell_bank_overdue() {
		return sl_lm_cell_bank_overdue;
	}

	public void setSl_lm_cell_bank_overdue(Integer sl_lm_cell_bank_overdue) {
		this.sl_lm_cell_bank_overdue = sl_lm_cell_bank_overdue;
	}

	public Integer getSl_lm_cell_bank_fraud() {
		return sl_lm_cell_bank_fraud;
	}

	public void setSl_lm_cell_bank_fraud(Integer sl_lm_cell_bank_fraud) {
		this.sl_lm_cell_bank_fraud = sl_lm_cell_bank_fraud;
	}

	public Integer getSl_lm_cell_bank_lost() {
		return sl_lm_cell_bank_lost;
	}

	public void setSl_lm_cell_bank_lost(Integer sl_lm_cell_bank_lost) {
		this.sl_lm_cell_bank_lost = sl_lm_cell_bank_lost;
	}

	public Integer getSl_lm_cell_bank_refuse() {
		return sl_lm_cell_bank_refuse;
	}

	public void setSl_lm_cell_bank_refuse(Integer sl_lm_cell_bank_refuse) {
		this.sl_lm_cell_bank_refuse = sl_lm_cell_bank_refuse;
	}

	public Integer getSl_lm_cell_nbank_p2p_bad() {
		return sl_lm_cell_nbank_p2p_bad;
	}

	public void setSl_lm_cell_nbank_p2p_bad(Integer sl_lm_cell_nbank_p2p_bad) {
		this.sl_lm_cell_nbank_p2p_bad = sl_lm_cell_nbank_p2p_bad;
	}

	public Integer getSl_lm_cell_nbank_p2p_overdue() {
		return sl_lm_cell_nbank_p2p_overdue;
	}

	public void setSl_lm_cell_nbank_p2p_overdue(Integer sl_lm_cell_nbank_p2p_overdue) {
		this.sl_lm_cell_nbank_p2p_overdue = sl_lm_cell_nbank_p2p_overdue;
	}

	public Integer getSl_lm_cell_nbank_p2p_fraud() {
		return sl_lm_cell_nbank_p2p_fraud;
	}

	public void setSl_lm_cell_nbank_p2p_fraud(Integer sl_lm_cell_nbank_p2p_fraud) {
		this.sl_lm_cell_nbank_p2p_fraud = sl_lm_cell_nbank_p2p_fraud;
	}

	public Integer getSl_lm_cell_nbank_p2p_lost() {
		return sl_lm_cell_nbank_p2p_lost;
	}

	public void setSl_lm_cell_nbank_p2p_lost(Integer sl_lm_cell_nbank_p2p_lost) {
		this.sl_lm_cell_nbank_p2p_lost = sl_lm_cell_nbank_p2p_lost;
	}

	public Integer getSl_lm_cell_nbank_p2p_refuse() {
		return sl_lm_cell_nbank_p2p_refuse;
	}

	public void setSl_lm_cell_nbank_p2p_refuse(Integer sl_lm_cell_nbank_p2p_refuse) {
		this.sl_lm_cell_nbank_p2p_refuse = sl_lm_cell_nbank_p2p_refuse;
	}

	public Integer getSl_lm_cell_nbank_mc_bad() {
		return sl_lm_cell_nbank_mc_bad;
	}

	public void setSl_lm_cell_nbank_mc_bad(Integer sl_lm_cell_nbank_mc_bad) {
		this.sl_lm_cell_nbank_mc_bad = sl_lm_cell_nbank_mc_bad;
	}

	public Integer getSl_lm_cell_nbank_mc_overdue() {
		return sl_lm_cell_nbank_mc_overdue;
	}

	public void setSl_lm_cell_nbank_mc_overdue(Integer sl_lm_cell_nbank_mc_overdue) {
		this.sl_lm_cell_nbank_mc_overdue = sl_lm_cell_nbank_mc_overdue;
	}

	public Integer getSl_lm_cell_nbank_mc_fraud() {
		return sl_lm_cell_nbank_mc_fraud;
	}

	public void setSl_lm_cell_nbank_mc_fraud(Integer sl_lm_cell_nbank_mc_fraud) {
		this.sl_lm_cell_nbank_mc_fraud = sl_lm_cell_nbank_mc_fraud;
	}

	public Integer getSl_lm_cell_nbank_mc_lost() {
		return sl_lm_cell_nbank_mc_lost;
	}

	public void setSl_lm_cell_nbank_mc_lost(Integer sl_lm_cell_nbank_mc_lost) {
		this.sl_lm_cell_nbank_mc_lost = sl_lm_cell_nbank_mc_lost;
	}

	public Integer getSl_lm_cell_nbank_mc_refuse() {
		return sl_lm_cell_nbank_mc_refuse;
	}

	public void setSl_lm_cell_nbank_mc_refuse(Integer sl_lm_cell_nbank_mc_refuse) {
		this.sl_lm_cell_nbank_mc_refuse = sl_lm_cell_nbank_mc_refuse;
	}

	public Integer getSl_lm_cell_nbank_ca_bad() {
		return sl_lm_cell_nbank_ca_bad;
	}

	public void setSl_lm_cell_nbank_ca_bad(Integer sl_lm_cell_nbank_ca_bad) {
		this.sl_lm_cell_nbank_ca_bad = sl_lm_cell_nbank_ca_bad;
	}

	public Integer getSl_lm_cell_nbank_ca_overdue() {
		return sl_lm_cell_nbank_ca_overdue;
	}

	public void setSl_lm_cell_nbank_ca_overdue(Integer sl_lm_cell_nbank_ca_overdue) {
		this.sl_lm_cell_nbank_ca_overdue = sl_lm_cell_nbank_ca_overdue;
	}

	public Integer getSl_lm_cell_nbank_ca_fraud() {
		return sl_lm_cell_nbank_ca_fraud;
	}

	public void setSl_lm_cell_nbank_ca_fraud(Integer sl_lm_cell_nbank_ca_fraud) {
		this.sl_lm_cell_nbank_ca_fraud = sl_lm_cell_nbank_ca_fraud;
	}

	public Integer getSl_lm_cell_nbank_ca_lost() {
		return sl_lm_cell_nbank_ca_lost;
	}

	public void setSl_lm_cell_nbank_ca_lost(Integer sl_lm_cell_nbank_ca_lost) {
		this.sl_lm_cell_nbank_ca_lost = sl_lm_cell_nbank_ca_lost;
	}

	public Integer getSl_lm_cell_nbank_ca_refuse() {
		return sl_lm_cell_nbank_ca_refuse;
	}

	public void setSl_lm_cell_nbank_ca_refuse(Integer sl_lm_cell_nbank_ca_refuse) {
		this.sl_lm_cell_nbank_ca_refuse = sl_lm_cell_nbank_ca_refuse;
	}

	public Integer getSl_lm_cell_nbank_com_bad() {
		return sl_lm_cell_nbank_com_bad;
	}

	public void setSl_lm_cell_nbank_com_bad(Integer sl_lm_cell_nbank_com_bad) {
		this.sl_lm_cell_nbank_com_bad = sl_lm_cell_nbank_com_bad;
	}

	public Integer getSl_lm_cell_nbank_com_overdue() {
		return sl_lm_cell_nbank_com_overdue;
	}

	public void setSl_lm_cell_nbank_com_overdue(Integer sl_lm_cell_nbank_com_overdue) {
		this.sl_lm_cell_nbank_com_overdue = sl_lm_cell_nbank_com_overdue;
	}

	public Integer getSl_lm_cell_nbank_com_fraud() {
		return sl_lm_cell_nbank_com_fraud;
	}

	public void setSl_lm_cell_nbank_com_fraud(Integer sl_lm_cell_nbank_com_fraud) {
		this.sl_lm_cell_nbank_com_fraud = sl_lm_cell_nbank_com_fraud;
	}

	public Integer getSl_lm_cell_nbank_com_lost() {
		return sl_lm_cell_nbank_com_lost;
	}

	public void setSl_lm_cell_nbank_com_lost(Integer sl_lm_cell_nbank_com_lost) {
		this.sl_lm_cell_nbank_com_lost = sl_lm_cell_nbank_com_lost;
	}

	public Integer getSl_lm_cell_nbank_com_refuse() {
		return sl_lm_cell_nbank_com_refuse;
	}

	public void setSl_lm_cell_nbank_com_refuse(Integer sl_lm_cell_nbank_com_refuse) {
		this.sl_lm_cell_nbank_com_refuse = sl_lm_cell_nbank_com_refuse;
	}

	public Integer getSl_lm_cell_nbank_cf_bad() {
		return sl_lm_cell_nbank_cf_bad;
	}

	public void setSl_lm_cell_nbank_cf_bad(Integer sl_lm_cell_nbank_cf_bad) {
		this.sl_lm_cell_nbank_cf_bad = sl_lm_cell_nbank_cf_bad;
	}

	public Integer getSl_lm_cell_nbank_cf_overdue() {
		return sl_lm_cell_nbank_cf_overdue;
	}

	public void setSl_lm_cell_nbank_cf_overdue(Integer sl_lm_cell_nbank_cf_overdue) {
		this.sl_lm_cell_nbank_cf_overdue = sl_lm_cell_nbank_cf_overdue;
	}

	public Integer getSl_lm_cell_nbank_cf_fraud() {
		return sl_lm_cell_nbank_cf_fraud;
	}

	public void setSl_lm_cell_nbank_cf_fraud(Integer sl_lm_cell_nbank_cf_fraud) {
		this.sl_lm_cell_nbank_cf_fraud = sl_lm_cell_nbank_cf_fraud;
	}

	public Integer getSl_lm_cell_nbank_cf_lost() {
		return sl_lm_cell_nbank_cf_lost;
	}

	public void setSl_lm_cell_nbank_cf_lost(Integer sl_lm_cell_nbank_cf_lost) {
		this.sl_lm_cell_nbank_cf_lost = sl_lm_cell_nbank_cf_lost;
	}

	public Integer getSl_lm_cell_nbank_cf_refuse() {
		return sl_lm_cell_nbank_cf_refuse;
	}

	public void setSl_lm_cell_nbank_cf_refuse(Integer sl_lm_cell_nbank_cf_refuse) {
		this.sl_lm_cell_nbank_cf_refuse = sl_lm_cell_nbank_cf_refuse;
	}

	public Integer getSl_lm_cell_nbank_other_bad() {
		return sl_lm_cell_nbank_other_bad;
	}

	public void setSl_lm_cell_nbank_other_bad(Integer sl_lm_cell_nbank_other_bad) {
		this.sl_lm_cell_nbank_other_bad = sl_lm_cell_nbank_other_bad;
	}

	public Integer getSl_lm_cell_nbank_other_overdue() {
		return sl_lm_cell_nbank_other_overdue;
	}

	public void setSl_lm_cell_nbank_other_overdue(Integer sl_lm_cell_nbank_other_overdue) {
		this.sl_lm_cell_nbank_other_overdue = sl_lm_cell_nbank_other_overdue;
	}

	public Integer getSl_lm_cell_nbank_other_fraud() {
		return sl_lm_cell_nbank_other_fraud;
	}

	public void setSl_lm_cell_nbank_other_fraud(Integer sl_lm_cell_nbank_other_fraud) {
		this.sl_lm_cell_nbank_other_fraud = sl_lm_cell_nbank_other_fraud;
	}

	public Integer getSl_lm_cell_nbank_other_lost() {
		return sl_lm_cell_nbank_other_lost;
	}

	public void setSl_lm_cell_nbank_other_lost(Integer sl_lm_cell_nbank_other_lost) {
		this.sl_lm_cell_nbank_other_lost = sl_lm_cell_nbank_other_lost;
	}

	public Integer getSl_lm_cell_nbank_other_refuse() {
		return sl_lm_cell_nbank_other_refuse;
	}

	public void setSl_lm_cell_nbank_other_refuse(Integer sl_lm_cell_nbank_other_refuse) {
		this.sl_lm_cell_nbank_other_refuse = sl_lm_cell_nbank_other_refuse;
	}

	public Integer getSl_gid_phone_overdue() {
		return sl_gid_phone_overdue;
	}

	public void setSl_gid_phone_overdue(Integer sl_gid_phone_overdue) {
		this.sl_gid_phone_overdue = sl_gid_phone_overdue;
	}

	public Integer getSl_gid_bank_bad() {
		return sl_gid_bank_bad;
	}

	public void setSl_gid_bank_bad(Integer sl_gid_bank_bad) {
		this.sl_gid_bank_bad = sl_gid_bank_bad;
	}

	public Integer getSl_gid_bank_overdue() {
		return sl_gid_bank_overdue;
	}

	public void setSl_gid_bank_overdue(Integer sl_gid_bank_overdue) {
		this.sl_gid_bank_overdue = sl_gid_bank_overdue;
	}

	public Integer getSl_gid_bank_fraud() {
		return sl_gid_bank_fraud;
	}

	public void setSl_gid_bank_fraud(Integer sl_gid_bank_fraud) {
		this.sl_gid_bank_fraud = sl_gid_bank_fraud;
	}

	public Integer getSl_gid_bank_lost() {
		return sl_gid_bank_lost;
	}

	public void setSl_gid_bank_lost(Integer sl_gid_bank_lost) {
		this.sl_gid_bank_lost = sl_gid_bank_lost;
	}

	public Integer getSl_gid_bank_refuse() {
		return sl_gid_bank_refuse;
	}

	public void setSl_gid_bank_refuse(Integer sl_gid_bank_refuse) {
		this.sl_gid_bank_refuse = sl_gid_bank_refuse;
	}

	public Integer getSl_gid_p2p_bad() {
		return sl_gid_p2p_bad;
	}

	public void setSl_gid_p2p_bad(Integer sl_gid_p2p_bad) {
		this.sl_gid_p2p_bad = sl_gid_p2p_bad;
	}

	public Integer getSl_gid_p2p_overdue() {
		return sl_gid_p2p_overdue;
	}

	public void setSl_gid_p2p_overdue(Integer sl_gid_p2p_overdue) {
		this.sl_gid_p2p_overdue = sl_gid_p2p_overdue;
	}

	public Integer getSl_gid_p2p_fraud() {
		return sl_gid_p2p_fraud;
	}

	public void setSl_gid_p2p_fraud(Integer sl_gid_p2p_fraud) {
		this.sl_gid_p2p_fraud = sl_gid_p2p_fraud;
	}

	public Integer getSl_gid_p2p_lost() {
		return sl_gid_p2p_lost;
	}

	public void setSl_gid_p2p_lost(Integer sl_gid_p2p_lost) {
		this.sl_gid_p2p_lost = sl_gid_p2p_lost;
	}

	public Integer getSl_gid_p2p_refuse() {
		return sl_gid_p2p_refuse;
	}

	public void setSl_gid_p2p_refuse(Integer sl_gid_p2p_refuse) {
		this.sl_gid_p2p_refuse = sl_gid_p2p_refuse;
	}

	public Integer getSl_gid_nbank_p2p_bad() {
		return sl_gid_nbank_p2p_bad;
	}

	public void setSl_gid_nbank_p2p_bad(Integer sl_gid_nbank_p2p_bad) {
		this.sl_gid_nbank_p2p_bad = sl_gid_nbank_p2p_bad;
	}

	public Integer getSl_gid_nbank_p2p_overdue() {
		return sl_gid_nbank_p2p_overdue;
	}

	public void setSl_gid_nbank_p2p_overdue(Integer sl_gid_nbank_p2p_overdue) {
		this.sl_gid_nbank_p2p_overdue = sl_gid_nbank_p2p_overdue;
	}

	public Integer getSl_gid_nbank_p2p_fraud() {
		return sl_gid_nbank_p2p_fraud;
	}

	public void setSl_gid_nbank_p2p_fraud(Integer sl_gid_nbank_p2p_fraud) {
		this.sl_gid_nbank_p2p_fraud = sl_gid_nbank_p2p_fraud;
	}

	public Integer getSl_gid_nbank_p2p_lost() {
		return sl_gid_nbank_p2p_lost;
	}

	public void setSl_gid_nbank_p2p_lost(Integer sl_gid_nbank_p2p_lost) {
		this.sl_gid_nbank_p2p_lost = sl_gid_nbank_p2p_lost;
	}

	public Integer getSl_gid_nbank_p2p_refuse() {
		return sl_gid_nbank_p2p_refuse;
	}

	public void setSl_gid_nbank_p2p_refuse(Integer sl_gid_nbank_p2p_refuse) {
		this.sl_gid_nbank_p2p_refuse = sl_gid_nbank_p2p_refuse;
	}

	public Integer getSl_gid_nbank_mc_bad() {
		return sl_gid_nbank_mc_bad;
	}

	public void setSl_gid_nbank_mc_bad(Integer sl_gid_nbank_mc_bad) {
		this.sl_gid_nbank_mc_bad = sl_gid_nbank_mc_bad;
	}

	public Integer getSl_gid_nbank_mc_overdue() {
		return sl_gid_nbank_mc_overdue;
	}

	public void setSl_gid_nbank_mc_overdue(Integer sl_gid_nbank_mc_overdue) {
		this.sl_gid_nbank_mc_overdue = sl_gid_nbank_mc_overdue;
	}

	public Integer getSl_gid_nbank_mc_fraud() {
		return sl_gid_nbank_mc_fraud;
	}

	public void setSl_gid_nbank_mc_fraud(Integer sl_gid_nbank_mc_fraud) {
		this.sl_gid_nbank_mc_fraud = sl_gid_nbank_mc_fraud;
	}

	public Integer getSl_gid_nbank_mc_lost() {
		return sl_gid_nbank_mc_lost;
	}

	public void setSl_gid_nbank_mc_lost(Integer sl_gid_nbank_mc_lost) {
		this.sl_gid_nbank_mc_lost = sl_gid_nbank_mc_lost;
	}

	public Integer getSl_gid_nbank_mc_refuse() {
		return sl_gid_nbank_mc_refuse;
	}

	public void setSl_gid_nbank_mc_refuse(Integer sl_gid_nbank_mc_refuse) {
		this.sl_gid_nbank_mc_refuse = sl_gid_nbank_mc_refuse;
	}

	public Integer getSl_gid_nbank_ca_bad() {
		return sl_gid_nbank_ca_bad;
	}

	public void setSl_gid_nbank_ca_bad(Integer sl_gid_nbank_ca_bad) {
		this.sl_gid_nbank_ca_bad = sl_gid_nbank_ca_bad;
	}

	public Integer getSl_gid_nbank_ca_overdue() {
		return sl_gid_nbank_ca_overdue;
	}

	public void setSl_gid_nbank_ca_overdue(Integer sl_gid_nbank_ca_overdue) {
		this.sl_gid_nbank_ca_overdue = sl_gid_nbank_ca_overdue;
	}

	public Integer getSl_gid_nbank_ca_fraud() {
		return sl_gid_nbank_ca_fraud;
	}

	public void setSl_gid_nbank_ca_fraud(Integer sl_gid_nbank_ca_fraud) {
		this.sl_gid_nbank_ca_fraud = sl_gid_nbank_ca_fraud;
	}

	public Integer getSl_gid_nbank_ca_lost() {
		return sl_gid_nbank_ca_lost;
	}

	public void setSl_gid_nbank_ca_lost(Integer sl_gid_nbank_ca_lost) {
		this.sl_gid_nbank_ca_lost = sl_gid_nbank_ca_lost;
	}

	public Integer getSl_gid_nbank_ca_refuse() {
		return sl_gid_nbank_ca_refuse;
	}

	public void setSl_gid_nbank_ca_refuse(Integer sl_gid_nbank_ca_refuse) {
		this.sl_gid_nbank_ca_refuse = sl_gid_nbank_ca_refuse;
	}

	public Integer getSl_gid_nbank_com_bad() {
		return sl_gid_nbank_com_bad;
	}

	public void setSl_gid_nbank_com_bad(Integer sl_gid_nbank_com_bad) {
		this.sl_gid_nbank_com_bad = sl_gid_nbank_com_bad;
	}

	public Integer getSl_gid_nbank_com_overdue() {
		return sl_gid_nbank_com_overdue;
	}

	public void setSl_gid_nbank_com_overdue(Integer sl_gid_nbank_com_overdue) {
		this.sl_gid_nbank_com_overdue = sl_gid_nbank_com_overdue;
	}

	public Integer getSl_gid_nbank_com_fraud() {
		return sl_gid_nbank_com_fraud;
	}

	public void setSl_gid_nbank_com_fraud(Integer sl_gid_nbank_com_fraud) {
		this.sl_gid_nbank_com_fraud = sl_gid_nbank_com_fraud;
	}

	public Integer getSl_gid_nbank_com_lost() {
		return sl_gid_nbank_com_lost;
	}

	public void setSl_gid_nbank_com_lost(Integer sl_gid_nbank_com_lost) {
		this.sl_gid_nbank_com_lost = sl_gid_nbank_com_lost;
	}

	public Integer getSl_gid_nbank_com_refuse() {
		return sl_gid_nbank_com_refuse;
	}

	public void setSl_gid_nbank_com_refuse(Integer sl_gid_nbank_com_refuse) {
		this.sl_gid_nbank_com_refuse = sl_gid_nbank_com_refuse;
	}

	public Integer getSl_gid_nbank_cf_bad() {
		return sl_gid_nbank_cf_bad;
	}

	public void setSl_gid_nbank_cf_bad(Integer sl_gid_nbank_cf_bad) {
		this.sl_gid_nbank_cf_bad = sl_gid_nbank_cf_bad;
	}

	public Integer getSl_gid_nbank_cf_overdue() {
		return sl_gid_nbank_cf_overdue;
	}

	public void setSl_gid_nbank_cf_overdue(Integer sl_gid_nbank_cf_overdue) {
		this.sl_gid_nbank_cf_overdue = sl_gid_nbank_cf_overdue;
	}

	public Integer getSl_gid_nbank_cf_fraud() {
		return sl_gid_nbank_cf_fraud;
	}

	public void setSl_gid_nbank_cf_fraud(Integer sl_gid_nbank_cf_fraud) {
		this.sl_gid_nbank_cf_fraud = sl_gid_nbank_cf_fraud;
	}

	public Integer getSl_gid_nbank_cf_lost() {
		return sl_gid_nbank_cf_lost;
	}

	public void setSl_gid_nbank_cf_lost(Integer sl_gid_nbank_cf_lost) {
		this.sl_gid_nbank_cf_lost = sl_gid_nbank_cf_lost;
	}

	public Integer getSl_gid_nbank_cf_refuse() {
		return sl_gid_nbank_cf_refuse;
	}

	public void setSl_gid_nbank_cf_refuse(Integer sl_gid_nbank_cf_refuse) {
		this.sl_gid_nbank_cf_refuse = sl_gid_nbank_cf_refuse;
	}

	public Integer getSl_gid_nbank_other_bad() {
		return sl_gid_nbank_other_bad;
	}

	public void setSl_gid_nbank_other_bad(Integer sl_gid_nbank_other_bad) {
		this.sl_gid_nbank_other_bad = sl_gid_nbank_other_bad;
	}

	public Integer getSl_gid_nbank_other_overdue() {
		return sl_gid_nbank_other_overdue;
	}

	public void setSl_gid_nbank_other_overdue(Integer sl_gid_nbank_other_overdue) {
		this.sl_gid_nbank_other_overdue = sl_gid_nbank_other_overdue;
	}

	public Integer getSl_gid_nbank_other_fraud() {
		return sl_gid_nbank_other_fraud;
	}

	public void setSl_gid_nbank_other_fraud(Integer sl_gid_nbank_other_fraud) {
		this.sl_gid_nbank_other_fraud = sl_gid_nbank_other_fraud;
	}

	public Integer getSl_gid_nbank_other_lost() {
		return sl_gid_nbank_other_lost;
	}

	public void setSl_gid_nbank_other_lost(Integer sl_gid_nbank_other_lost) {
		this.sl_gid_nbank_other_lost = sl_gid_nbank_other_lost;
	}

	public Integer getSl_gid_nbank_other_refuse() {
		return sl_gid_nbank_other_refuse;
	}

	public void setSl_gid_nbank_other_refuse(Integer sl_gid_nbank_other_refuse) {
		this.sl_gid_nbank_other_refuse = sl_gid_nbank_other_refuse;
	}

	public Integer getFlag_applyloanstr() {
		return flag_applyloanstr;
	}

	public void setFlag_applyloanstr(Integer flag_applyloanstr) {
		this.flag_applyloanstr = flag_applyloanstr;
	}

	public Integer getAls_d7_id_bank_selfnum() {
		return als_d7_id_bank_selfnum;
	}

	public void setAls_d7_id_bank_selfnum(Integer als_d7_id_bank_selfnum) {
		this.als_d7_id_bank_selfnum = als_d7_id_bank_selfnum;
	}

	public Integer getAls_d7_id_bank_allnum() {
		return als_d7_id_bank_allnum;
	}

	public void setAls_d7_id_bank_allnum(Integer als_d7_id_bank_allnum) {
		this.als_d7_id_bank_allnum = als_d7_id_bank_allnum;
	}

	public Integer getAls_d7_id_bank_orgnum() {
		return als_d7_id_bank_orgnum;
	}

	public void setAls_d7_id_bank_orgnum(Integer als_d7_id_bank_orgnum) {
		this.als_d7_id_bank_orgnum = als_d7_id_bank_orgnum;
	}

	public Integer getAls_d7_id_nbank_selfnum() {
		return als_d7_id_nbank_selfnum;
	}

	public void setAls_d7_id_nbank_selfnum(Integer als_d7_id_nbank_selfnum) {
		this.als_d7_id_nbank_selfnum = als_d7_id_nbank_selfnum;
	}

	public Integer getAls_d7_id_nbank_allnum() {
		return als_d7_id_nbank_allnum;
	}

	public void setAls_d7_id_nbank_allnum(Integer als_d7_id_nbank_allnum) {
		this.als_d7_id_nbank_allnum = als_d7_id_nbank_allnum;
	}

	public Integer getAls_d7_id_nbank_p2p_allnum() {
		return als_d7_id_nbank_p2p_allnum;
	}

	public void setAls_d7_id_nbank_p2p_allnum(Integer als_d7_id_nbank_p2p_allnum) {
		this.als_d7_id_nbank_p2p_allnum = als_d7_id_nbank_p2p_allnum;
	}

	public Integer getAls_d7_id_nbank_mc_allnum() {
		return als_d7_id_nbank_mc_allnum;
	}

	public void setAls_d7_id_nbank_mc_allnum(Integer als_d7_id_nbank_mc_allnum) {
		this.als_d7_id_nbank_mc_allnum = als_d7_id_nbank_mc_allnum;
	}

	public Integer getAls_d7_id_nbank_ca_allnum() {
		return als_d7_id_nbank_ca_allnum;
	}

	public void setAls_d7_id_nbank_ca_allnum(Integer als_d7_id_nbank_ca_allnum) {
		this.als_d7_id_nbank_ca_allnum = als_d7_id_nbank_ca_allnum;
	}

	public Integer getAls_d7_id_nbank_cf_allnum() {
		return als_d7_id_nbank_cf_allnum;
	}

	public void setAls_d7_id_nbank_cf_allnum(Integer als_d7_id_nbank_cf_allnum) {
		this.als_d7_id_nbank_cf_allnum = als_d7_id_nbank_cf_allnum;
	}

	public Integer getAls_d7_id_nbank_com_allnum() {
		return als_d7_id_nbank_com_allnum;
	}

	public void setAls_d7_id_nbank_com_allnum(Integer als_d7_id_nbank_com_allnum) {
		this.als_d7_id_nbank_com_allnum = als_d7_id_nbank_com_allnum;
	}

	public Integer getAls_d7_id_nbank_oth_allnum() {
		return als_d7_id_nbank_oth_allnum;
	}

	public void setAls_d7_id_nbank_oth_allnum(Integer als_d7_id_nbank_oth_allnum) {
		this.als_d7_id_nbank_oth_allnum = als_d7_id_nbank_oth_allnum;
	}

	public Integer getAls_d7_id_nbank_orgnum() {
		return als_d7_id_nbank_orgnum;
	}

	public void setAls_d7_id_nbank_orgnum(Integer als_d7_id_nbank_orgnum) {
		this.als_d7_id_nbank_orgnum = als_d7_id_nbank_orgnum;
	}

	public Integer getAls_d7_id_nbank_p2p_orgnum() {
		return als_d7_id_nbank_p2p_orgnum;
	}

	public void setAls_d7_id_nbank_p2p_orgnum(Integer als_d7_id_nbank_p2p_orgnum) {
		this.als_d7_id_nbank_p2p_orgnum = als_d7_id_nbank_p2p_orgnum;
	}

	public Integer getAls_d7_id_nbank_mc_orgnum() {
		return als_d7_id_nbank_mc_orgnum;
	}

	public void setAls_d7_id_nbank_mc_orgnum(Integer als_d7_id_nbank_mc_orgnum) {
		this.als_d7_id_nbank_mc_orgnum = als_d7_id_nbank_mc_orgnum;
	}

	public Integer getAls_d7_id_nbank_ca_orgnum() {
		return als_d7_id_nbank_ca_orgnum;
	}

	public void setAls_d7_id_nbank_ca_orgnum(Integer als_d7_id_nbank_ca_orgnum) {
		this.als_d7_id_nbank_ca_orgnum = als_d7_id_nbank_ca_orgnum;
	}

	public Integer getAls_d7_id_nbank_cf_orgnum() {
		return als_d7_id_nbank_cf_orgnum;
	}

	public void setAls_d7_id_nbank_cf_orgnum(Integer als_d7_id_nbank_cf_orgnum) {
		this.als_d7_id_nbank_cf_orgnum = als_d7_id_nbank_cf_orgnum;
	}

	public Integer getAls_d7_id_nbank_com_orgnum() {
		return als_d7_id_nbank_com_orgnum;
	}

	public void setAls_d7_id_nbank_com_orgnum(Integer als_d7_id_nbank_com_orgnum) {
		this.als_d7_id_nbank_com_orgnum = als_d7_id_nbank_com_orgnum;
	}

	public Integer getAls_d7_id_nbank_oth_orgnum() {
		return als_d7_id_nbank_oth_orgnum;
	}

	public void setAls_d7_id_nbank_oth_orgnum(Integer als_d7_id_nbank_oth_orgnum) {
		this.als_d7_id_nbank_oth_orgnum = als_d7_id_nbank_oth_orgnum;
	}

	public Integer getAls_d7_cell_bank_selfnum() {
		return als_d7_cell_bank_selfnum;
	}

	public void setAls_d7_cell_bank_selfnum(Integer als_d7_cell_bank_selfnum) {
		this.als_d7_cell_bank_selfnum = als_d7_cell_bank_selfnum;
	}

	public Integer getAls_d7_cell_bank_allnum() {
		return als_d7_cell_bank_allnum;
	}

	public void setAls_d7_cell_bank_allnum(Integer als_d7_cell_bank_allnum) {
		this.als_d7_cell_bank_allnum = als_d7_cell_bank_allnum;
	}

	public Integer getAls_d7_cell_bank_orgnum() {
		return als_d7_cell_bank_orgnum;
	}

	public void setAls_d7_cell_bank_orgnum(Integer als_d7_cell_bank_orgnum) {
		this.als_d7_cell_bank_orgnum = als_d7_cell_bank_orgnum;
	}

	public Integer getAls_d7_cell_nbank_selfnum() {
		return als_d7_cell_nbank_selfnum;
	}

	public void setAls_d7_cell_nbank_selfnum(Integer als_d7_cell_nbank_selfnum) {
		this.als_d7_cell_nbank_selfnum = als_d7_cell_nbank_selfnum;
	}

	public Integer getAls_d7_cell_nbank_allnum() {
		return als_d7_cell_nbank_allnum;
	}

	public void setAls_d7_cell_nbank_allnum(Integer als_d7_cell_nbank_allnum) {
		this.als_d7_cell_nbank_allnum = als_d7_cell_nbank_allnum;
	}

	public Integer getAls_d7_cell_nbank_p2p_allnum() {
		return als_d7_cell_nbank_p2p_allnum;
	}

	public void setAls_d7_cell_nbank_p2p_allnum(Integer als_d7_cell_nbank_p2p_allnum) {
		this.als_d7_cell_nbank_p2p_allnum = als_d7_cell_nbank_p2p_allnum;
	}

	public Integer getAls_d7_cell_nbank_mc_allnum() {
		return als_d7_cell_nbank_mc_allnum;
	}

	public void setAls_d7_cell_nbank_mc_allnum(Integer als_d7_cell_nbank_mc_allnum) {
		this.als_d7_cell_nbank_mc_allnum = als_d7_cell_nbank_mc_allnum;
	}

	public Integer getAls_d7_cell_nbank_ca_allnum() {
		return als_d7_cell_nbank_ca_allnum;
	}

	public void setAls_d7_cell_nbank_ca_allnum(Integer als_d7_cell_nbank_ca_allnum) {
		this.als_d7_cell_nbank_ca_allnum = als_d7_cell_nbank_ca_allnum;
	}

	public Integer getAls_d7_cell_nbank_cf_allnum() {
		return als_d7_cell_nbank_cf_allnum;
	}

	public void setAls_d7_cell_nbank_cf_allnum(Integer als_d7_cell_nbank_cf_allnum) {
		this.als_d7_cell_nbank_cf_allnum = als_d7_cell_nbank_cf_allnum;
	}

	public Integer getAls_d7_cell_nbank_com_allnum() {
		return als_d7_cell_nbank_com_allnum;
	}

	public void setAls_d7_cell_nbank_com_allnum(Integer als_d7_cell_nbank_com_allnum) {
		this.als_d7_cell_nbank_com_allnum = als_d7_cell_nbank_com_allnum;
	}

	public Integer getAls_d7_cell_nbank_oth_allnum() {
		return als_d7_cell_nbank_oth_allnum;
	}

	public void setAls_d7_cell_nbank_oth_allnum(Integer als_d7_cell_nbank_oth_allnum) {
		this.als_d7_cell_nbank_oth_allnum = als_d7_cell_nbank_oth_allnum;
	}

	public Integer getAls_d7_cell_nbank_orgnum() {
		return als_d7_cell_nbank_orgnum;
	}

	public void setAls_d7_cell_nbank_orgnum(Integer als_d7_cell_nbank_orgnum) {
		this.als_d7_cell_nbank_orgnum = als_d7_cell_nbank_orgnum;
	}

	public Integer getAls_d7_cell_nbank_p2p_orgnum() {
		return als_d7_cell_nbank_p2p_orgnum;
	}

	public void setAls_d7_cell_nbank_p2p_orgnum(Integer als_d7_cell_nbank_p2p_orgnum) {
		this.als_d7_cell_nbank_p2p_orgnum = als_d7_cell_nbank_p2p_orgnum;
	}

	public Integer getAls_d7_cell_nbank_mc_orgnum() {
		return als_d7_cell_nbank_mc_orgnum;
	}

	public void setAls_d7_cell_nbank_mc_orgnum(Integer als_d7_cell_nbank_mc_orgnum) {
		this.als_d7_cell_nbank_mc_orgnum = als_d7_cell_nbank_mc_orgnum;
	}

	public Integer getAls_d7_cell_nbank_ca_orgnum() {
		return als_d7_cell_nbank_ca_orgnum;
	}

	public void setAls_d7_cell_nbank_ca_orgnum(Integer als_d7_cell_nbank_ca_orgnum) {
		this.als_d7_cell_nbank_ca_orgnum = als_d7_cell_nbank_ca_orgnum;
	}

	public Integer getAls_d7_cell_nbank_cf_orgnum() {
		return als_d7_cell_nbank_cf_orgnum;
	}

	public void setAls_d7_cell_nbank_cf_orgnum(Integer als_d7_cell_nbank_cf_orgnum) {
		this.als_d7_cell_nbank_cf_orgnum = als_d7_cell_nbank_cf_orgnum;
	}

	public Integer getAls_d7_cell_nbank_com_orgnum() {
		return als_d7_cell_nbank_com_orgnum;
	}

	public void setAls_d7_cell_nbank_com_orgnum(Integer als_d7_cell_nbank_com_orgnum) {
		this.als_d7_cell_nbank_com_orgnum = als_d7_cell_nbank_com_orgnum;
	}

	public Integer getAls_d7_cell_nbank_oth_orgnum() {
		return als_d7_cell_nbank_oth_orgnum;
	}

	public void setAls_d7_cell_nbank_oth_orgnum(Integer als_d7_cell_nbank_oth_orgnum) {
		this.als_d7_cell_nbank_oth_orgnum = als_d7_cell_nbank_oth_orgnum;
	}

	public Integer getAls_d15_id_bank_selfnum() {
		return als_d15_id_bank_selfnum;
	}

	public void setAls_d15_id_bank_selfnum(Integer als_d15_id_bank_selfnum) {
		this.als_d15_id_bank_selfnum = als_d15_id_bank_selfnum;
	}

	public Integer getAls_d15_id_bank_allnum() {
		return als_d15_id_bank_allnum;
	}

	public void setAls_d15_id_bank_allnum(Integer als_d15_id_bank_allnum) {
		this.als_d15_id_bank_allnum = als_d15_id_bank_allnum;
	}

	public Integer getAls_d15_id_bank_orgnum() {
		return als_d15_id_bank_orgnum;
	}

	public void setAls_d15_id_bank_orgnum(Integer als_d15_id_bank_orgnum) {
		this.als_d15_id_bank_orgnum = als_d15_id_bank_orgnum;
	}

	public Integer getAls_d15_id_nbank_selfnum() {
		return als_d15_id_nbank_selfnum;
	}

	public void setAls_d15_id_nbank_selfnum(Integer als_d15_id_nbank_selfnum) {
		this.als_d15_id_nbank_selfnum = als_d15_id_nbank_selfnum;
	}

	public Integer getAls_d15_id_nbank_allnum() {
		return als_d15_id_nbank_allnum;
	}

	public void setAls_d15_id_nbank_allnum(Integer als_d15_id_nbank_allnum) {
		this.als_d15_id_nbank_allnum = als_d15_id_nbank_allnum;
	}

	public Integer getAls_d15_id_nbank_p2p_allnum() {
		return als_d15_id_nbank_p2p_allnum;
	}

	public void setAls_d15_id_nbank_p2p_allnum(Integer als_d15_id_nbank_p2p_allnum) {
		this.als_d15_id_nbank_p2p_allnum = als_d15_id_nbank_p2p_allnum;
	}

	public Integer getAls_d15_id_nbank_mc_allnum() {
		return als_d15_id_nbank_mc_allnum;
	}

	public void setAls_d15_id_nbank_mc_allnum(Integer als_d15_id_nbank_mc_allnum) {
		this.als_d15_id_nbank_mc_allnum = als_d15_id_nbank_mc_allnum;
	}

	public Integer getAls_d15_id_nbank_ca_allnum() {
		return als_d15_id_nbank_ca_allnum;
	}

	public void setAls_d15_id_nbank_ca_allnum(Integer als_d15_id_nbank_ca_allnum) {
		this.als_d15_id_nbank_ca_allnum = als_d15_id_nbank_ca_allnum;
	}

	public Integer getAls_d15_id_nbank_cf_allnum() {
		return als_d15_id_nbank_cf_allnum;
	}

	public void setAls_d15_id_nbank_cf_allnum(Integer als_d15_id_nbank_cf_allnum) {
		this.als_d15_id_nbank_cf_allnum = als_d15_id_nbank_cf_allnum;
	}

	public Integer getAls_d15_id_nbank_com_allnum() {
		return als_d15_id_nbank_com_allnum;
	}

	public void setAls_d15_id_nbank_com_allnum(Integer als_d15_id_nbank_com_allnum) {
		this.als_d15_id_nbank_com_allnum = als_d15_id_nbank_com_allnum;
	}

	public Integer getAls_d15_id_nbank_oth_allnum() {
		return als_d15_id_nbank_oth_allnum;
	}

	public void setAls_d15_id_nbank_oth_allnum(Integer als_d15_id_nbank_oth_allnum) {
		this.als_d15_id_nbank_oth_allnum = als_d15_id_nbank_oth_allnum;
	}

	public Integer getAls_d15_id_nbank_orgnum() {
		return als_d15_id_nbank_orgnum;
	}

	public void setAls_d15_id_nbank_orgnum(Integer als_d15_id_nbank_orgnum) {
		this.als_d15_id_nbank_orgnum = als_d15_id_nbank_orgnum;
	}

	public Integer getAls_d15_id_nbank_p2p_orgnum() {
		return als_d15_id_nbank_p2p_orgnum;
	}

	public void setAls_d15_id_nbank_p2p_orgnum(Integer als_d15_id_nbank_p2p_orgnum) {
		this.als_d15_id_nbank_p2p_orgnum = als_d15_id_nbank_p2p_orgnum;
	}

	public Integer getAls_d15_id_nbank_mc_orgnum() {
		return als_d15_id_nbank_mc_orgnum;
	}

	public void setAls_d15_id_nbank_mc_orgnum(Integer als_d15_id_nbank_mc_orgnum) {
		this.als_d15_id_nbank_mc_orgnum = als_d15_id_nbank_mc_orgnum;
	}

	public Integer getAls_d15_id_nbank_ca_orgnum() {
		return als_d15_id_nbank_ca_orgnum;
	}

	public void setAls_d15_id_nbank_ca_orgnum(Integer als_d15_id_nbank_ca_orgnum) {
		this.als_d15_id_nbank_ca_orgnum = als_d15_id_nbank_ca_orgnum;
	}

	public Integer getAls_d15_id_nbank_cf_orgnum() {
		return als_d15_id_nbank_cf_orgnum;
	}

	public void setAls_d15_id_nbank_cf_orgnum(Integer als_d15_id_nbank_cf_orgnum) {
		this.als_d15_id_nbank_cf_orgnum = als_d15_id_nbank_cf_orgnum;
	}

	public Integer getAls_d15_id_nbank_com_orgnum() {
		return als_d15_id_nbank_com_orgnum;
	}

	public void setAls_d15_id_nbank_com_orgnum(Integer als_d15_id_nbank_com_orgnum) {
		this.als_d15_id_nbank_com_orgnum = als_d15_id_nbank_com_orgnum;
	}

	public Integer getAls_d15_id_nbank_oth_orgnum() {
		return als_d15_id_nbank_oth_orgnum;
	}

	public void setAls_d15_id_nbank_oth_orgnum(Integer als_d15_id_nbank_oth_orgnum) {
		this.als_d15_id_nbank_oth_orgnum = als_d15_id_nbank_oth_orgnum;
	}

	public Integer getAls_d15_cell_bank_selfnum() {
		return als_d15_cell_bank_selfnum;
	}

	public void setAls_d15_cell_bank_selfnum(Integer als_d15_cell_bank_selfnum) {
		this.als_d15_cell_bank_selfnum = als_d15_cell_bank_selfnum;
	}

	public Integer getAls_d15_cell_bank_allnum() {
		return als_d15_cell_bank_allnum;
	}

	public void setAls_d15_cell_bank_allnum(Integer als_d15_cell_bank_allnum) {
		this.als_d15_cell_bank_allnum = als_d15_cell_bank_allnum;
	}

	public Integer getAls_d15_cell_bank_orgnum() {
		return als_d15_cell_bank_orgnum;
	}

	public void setAls_d15_cell_bank_orgnum(Integer als_d15_cell_bank_orgnum) {
		this.als_d15_cell_bank_orgnum = als_d15_cell_bank_orgnum;
	}

	public Integer getAls_d15_cell_nbank_selfnum() {
		return als_d15_cell_nbank_selfnum;
	}

	public void setAls_d15_cell_nbank_selfnum(Integer als_d15_cell_nbank_selfnum) {
		this.als_d15_cell_nbank_selfnum = als_d15_cell_nbank_selfnum;
	}

	public Integer getAls_d15_cell_nbank_allnum() {
		return als_d15_cell_nbank_allnum;
	}

	public void setAls_d15_cell_nbank_allnum(Integer als_d15_cell_nbank_allnum) {
		this.als_d15_cell_nbank_allnum = als_d15_cell_nbank_allnum;
	}

	public Integer getAls_d15_cell_nbank_p2p_allnum() {
		return als_d15_cell_nbank_p2p_allnum;
	}

	public void setAls_d15_cell_nbank_p2p_allnum(Integer als_d15_cell_nbank_p2p_allnum) {
		this.als_d15_cell_nbank_p2p_allnum = als_d15_cell_nbank_p2p_allnum;
	}

	public Integer getAls_d15_cell_nbank_mc_allnum() {
		return als_d15_cell_nbank_mc_allnum;
	}

	public void setAls_d15_cell_nbank_mc_allnum(Integer als_d15_cell_nbank_mc_allnum) {
		this.als_d15_cell_nbank_mc_allnum = als_d15_cell_nbank_mc_allnum;
	}

	public Integer getAls_d15_cell_nbank_ca_allnum() {
		return als_d15_cell_nbank_ca_allnum;
	}

	public void setAls_d15_cell_nbank_ca_allnum(Integer als_d15_cell_nbank_ca_allnum) {
		this.als_d15_cell_nbank_ca_allnum = als_d15_cell_nbank_ca_allnum;
	}

	public Integer getAls_d15_cell_nbank_cf_allnum() {
		return als_d15_cell_nbank_cf_allnum;
	}

	public void setAls_d15_cell_nbank_cf_allnum(Integer als_d15_cell_nbank_cf_allnum) {
		this.als_d15_cell_nbank_cf_allnum = als_d15_cell_nbank_cf_allnum;
	}

	public Integer getAls_d15_cell_nbank_com_allnum() {
		return als_d15_cell_nbank_com_allnum;
	}

	public void setAls_d15_cell_nbank_com_allnum(Integer als_d15_cell_nbank_com_allnum) {
		this.als_d15_cell_nbank_com_allnum = als_d15_cell_nbank_com_allnum;
	}

	public Integer getAls_d15_cell_nbank_oth_allnum() {
		return als_d15_cell_nbank_oth_allnum;
	}

	public void setAls_d15_cell_nbank_oth_allnum(Integer als_d15_cell_nbank_oth_allnum) {
		this.als_d15_cell_nbank_oth_allnum = als_d15_cell_nbank_oth_allnum;
	}

	public Integer getAls_d15_cell_nbank_orgnum() {
		return als_d15_cell_nbank_orgnum;
	}

	public void setAls_d15_cell_nbank_orgnum(Integer als_d15_cell_nbank_orgnum) {
		this.als_d15_cell_nbank_orgnum = als_d15_cell_nbank_orgnum;
	}

	public Integer getAls_d15_cell_nbank_p2p_orgnum() {
		return als_d15_cell_nbank_p2p_orgnum;
	}

	public void setAls_d15_cell_nbank_p2p_orgnum(Integer als_d15_cell_nbank_p2p_orgnum) {
		this.als_d15_cell_nbank_p2p_orgnum = als_d15_cell_nbank_p2p_orgnum;
	}

	public Integer getAls_d15_cell_nbank_mc_orgnum() {
		return als_d15_cell_nbank_mc_orgnum;
	}

	public void setAls_d15_cell_nbank_mc_orgnum(Integer als_d15_cell_nbank_mc_orgnum) {
		this.als_d15_cell_nbank_mc_orgnum = als_d15_cell_nbank_mc_orgnum;
	}

	public Integer getAls_d15_cell_nbank_ca_orgnum() {
		return als_d15_cell_nbank_ca_orgnum;
	}

	public void setAls_d15_cell_nbank_ca_orgnum(Integer als_d15_cell_nbank_ca_orgnum) {
		this.als_d15_cell_nbank_ca_orgnum = als_d15_cell_nbank_ca_orgnum;
	}

	public Integer getAls_d15_cell_nbank_cf_orgnum() {
		return als_d15_cell_nbank_cf_orgnum;
	}

	public void setAls_d15_cell_nbank_cf_orgnum(Integer als_d15_cell_nbank_cf_orgnum) {
		this.als_d15_cell_nbank_cf_orgnum = als_d15_cell_nbank_cf_orgnum;
	}

	public Integer getAls_d15_cell_nbank_com_orgnum() {
		return als_d15_cell_nbank_com_orgnum;
	}

	public void setAls_d15_cell_nbank_com_orgnum(Integer als_d15_cell_nbank_com_orgnum) {
		this.als_d15_cell_nbank_com_orgnum = als_d15_cell_nbank_com_orgnum;
	}

	public Integer getAls_d15_cell_nbank_oth_orgnum() {
		return als_d15_cell_nbank_oth_orgnum;
	}

	public void setAls_d15_cell_nbank_oth_orgnum(Integer als_d15_cell_nbank_oth_orgnum) {
		this.als_d15_cell_nbank_oth_orgnum = als_d15_cell_nbank_oth_orgnum;
	}

	public Integer getAls_m1_id_bank_selfnum() {
		return als_m1_id_bank_selfnum;
	}

	public void setAls_m1_id_bank_selfnum(Integer als_m1_id_bank_selfnum) {
		this.als_m1_id_bank_selfnum = als_m1_id_bank_selfnum;
	}

	public Integer getAls_m1_id_bank_allnum() {
		return als_m1_id_bank_allnum;
	}

	public void setAls_m1_id_bank_allnum(Integer als_m1_id_bank_allnum) {
		this.als_m1_id_bank_allnum = als_m1_id_bank_allnum;
	}

	public Integer getAls_m1_id_bank_orgnum() {
		return als_m1_id_bank_orgnum;
	}

	public void setAls_m1_id_bank_orgnum(Integer als_m1_id_bank_orgnum) {
		this.als_m1_id_bank_orgnum = als_m1_id_bank_orgnum;
	}

	public Integer getAls_m1_id_nbank_selfnum() {
		return als_m1_id_nbank_selfnum;
	}

	public void setAls_m1_id_nbank_selfnum(Integer als_m1_id_nbank_selfnum) {
		this.als_m1_id_nbank_selfnum = als_m1_id_nbank_selfnum;
	}

	public Integer getAls_m1_id_nbank_allnum() {
		return als_m1_id_nbank_allnum;
	}

	public void setAls_m1_id_nbank_allnum(Integer als_m1_id_nbank_allnum) {
		this.als_m1_id_nbank_allnum = als_m1_id_nbank_allnum;
	}

	public Integer getAls_m1_id_nbank_p2p_allnum() {
		return als_m1_id_nbank_p2p_allnum;
	}

	public void setAls_m1_id_nbank_p2p_allnum(Integer als_m1_id_nbank_p2p_allnum) {
		this.als_m1_id_nbank_p2p_allnum = als_m1_id_nbank_p2p_allnum;
	}

	public Integer getAls_m1_id_nbank_mc_allnum() {
		return als_m1_id_nbank_mc_allnum;
	}

	public void setAls_m1_id_nbank_mc_allnum(Integer als_m1_id_nbank_mc_allnum) {
		this.als_m1_id_nbank_mc_allnum = als_m1_id_nbank_mc_allnum;
	}

	public Integer getAls_m1_id_nbank_ca_allnum() {
		return als_m1_id_nbank_ca_allnum;
	}

	public void setAls_m1_id_nbank_ca_allnum(Integer als_m1_id_nbank_ca_allnum) {
		this.als_m1_id_nbank_ca_allnum = als_m1_id_nbank_ca_allnum;
	}

	public Integer getAls_m1_id_nbank_cf_allnum() {
		return als_m1_id_nbank_cf_allnum;
	}

	public void setAls_m1_id_nbank_cf_allnum(Integer als_m1_id_nbank_cf_allnum) {
		this.als_m1_id_nbank_cf_allnum = als_m1_id_nbank_cf_allnum;
	}

	public Integer getAls_m1_id_nbank_com_allnum() {
		return als_m1_id_nbank_com_allnum;
	}

	public void setAls_m1_id_nbank_com_allnum(Integer als_m1_id_nbank_com_allnum) {
		this.als_m1_id_nbank_com_allnum = als_m1_id_nbank_com_allnum;
	}

	public Integer getAls_m1_id_nbank_oth_allnum() {
		return als_m1_id_nbank_oth_allnum;
	}

	public void setAls_m1_id_nbank_oth_allnum(Integer als_m1_id_nbank_oth_allnum) {
		this.als_m1_id_nbank_oth_allnum = als_m1_id_nbank_oth_allnum;
	}

	public Integer getAls_m1_id_nbank_orgnum() {
		return als_m1_id_nbank_orgnum;
	}

	public void setAls_m1_id_nbank_orgnum(Integer als_m1_id_nbank_orgnum) {
		this.als_m1_id_nbank_orgnum = als_m1_id_nbank_orgnum;
	}

	public Integer getAls_m1_id_nbank_p2p_orgnum() {
		return als_m1_id_nbank_p2p_orgnum;
	}

	public void setAls_m1_id_nbank_p2p_orgnum(Integer als_m1_id_nbank_p2p_orgnum) {
		this.als_m1_id_nbank_p2p_orgnum = als_m1_id_nbank_p2p_orgnum;
	}

	public Integer getAls_m1_id_nbank_mc_orgnum() {
		return als_m1_id_nbank_mc_orgnum;
	}

	public void setAls_m1_id_nbank_mc_orgnum(Integer als_m1_id_nbank_mc_orgnum) {
		this.als_m1_id_nbank_mc_orgnum = als_m1_id_nbank_mc_orgnum;
	}

	public Integer getAls_m1_id_nbank_ca_orgnum() {
		return als_m1_id_nbank_ca_orgnum;
	}

	public void setAls_m1_id_nbank_ca_orgnum(Integer als_m1_id_nbank_ca_orgnum) {
		this.als_m1_id_nbank_ca_orgnum = als_m1_id_nbank_ca_orgnum;
	}

	public Integer getAls_m1_id_nbank_cf_orgnum() {
		return als_m1_id_nbank_cf_orgnum;
	}

	public void setAls_m1_id_nbank_cf_orgnum(Integer als_m1_id_nbank_cf_orgnum) {
		this.als_m1_id_nbank_cf_orgnum = als_m1_id_nbank_cf_orgnum;
	}

	public Integer getAls_m1_id_nbank_com_orgnum() {
		return als_m1_id_nbank_com_orgnum;
	}

	public void setAls_m1_id_nbank_com_orgnum(Integer als_m1_id_nbank_com_orgnum) {
		this.als_m1_id_nbank_com_orgnum = als_m1_id_nbank_com_orgnum;
	}

	public Integer getAls_m1_id_nbank_oth_orgnum() {
		return als_m1_id_nbank_oth_orgnum;
	}

	public void setAls_m1_id_nbank_oth_orgnum(Integer als_m1_id_nbank_oth_orgnum) {
		this.als_m1_id_nbank_oth_orgnum = als_m1_id_nbank_oth_orgnum;
	}

	public Integer getAls_m1_cell_bank_selfnum() {
		return als_m1_cell_bank_selfnum;
	}

	public void setAls_m1_cell_bank_selfnum(Integer als_m1_cell_bank_selfnum) {
		this.als_m1_cell_bank_selfnum = als_m1_cell_bank_selfnum;
	}

	public Integer getAls_m1_cell_bank_allnum() {
		return als_m1_cell_bank_allnum;
	}

	public void setAls_m1_cell_bank_allnum(Integer als_m1_cell_bank_allnum) {
		this.als_m1_cell_bank_allnum = als_m1_cell_bank_allnum;
	}

	public Integer getAls_m1_cell_bank_orgnum() {
		return als_m1_cell_bank_orgnum;
	}

	public void setAls_m1_cell_bank_orgnum(Integer als_m1_cell_bank_orgnum) {
		this.als_m1_cell_bank_orgnum = als_m1_cell_bank_orgnum;
	}

	public Integer getAls_m1_cell_nbank_selfnum() {
		return als_m1_cell_nbank_selfnum;
	}

	public void setAls_m1_cell_nbank_selfnum(Integer als_m1_cell_nbank_selfnum) {
		this.als_m1_cell_nbank_selfnum = als_m1_cell_nbank_selfnum;
	}

	public Integer getAls_m1_cell_nbank_allnum() {
		return als_m1_cell_nbank_allnum;
	}

	public void setAls_m1_cell_nbank_allnum(Integer als_m1_cell_nbank_allnum) {
		this.als_m1_cell_nbank_allnum = als_m1_cell_nbank_allnum;
	}

	public Integer getAls_m1_cell_nbank_p2p_allnum() {
		return als_m1_cell_nbank_p2p_allnum;
	}

	public void setAls_m1_cell_nbank_p2p_allnum(Integer als_m1_cell_nbank_p2p_allnum) {
		this.als_m1_cell_nbank_p2p_allnum = als_m1_cell_nbank_p2p_allnum;
	}

	public Integer getAls_m1_cell_nbank_mc_allnum() {
		return als_m1_cell_nbank_mc_allnum;
	}

	public void setAls_m1_cell_nbank_mc_allnum(Integer als_m1_cell_nbank_mc_allnum) {
		this.als_m1_cell_nbank_mc_allnum = als_m1_cell_nbank_mc_allnum;
	}

	public Integer getAls_m1_cell_nbank_ca_allnum() {
		return als_m1_cell_nbank_ca_allnum;
	}

	public void setAls_m1_cell_nbank_ca_allnum(Integer als_m1_cell_nbank_ca_allnum) {
		this.als_m1_cell_nbank_ca_allnum = als_m1_cell_nbank_ca_allnum;
	}

	public Integer getAls_m1_cell_nbank_cf_allnum() {
		return als_m1_cell_nbank_cf_allnum;
	}

	public void setAls_m1_cell_nbank_cf_allnum(Integer als_m1_cell_nbank_cf_allnum) {
		this.als_m1_cell_nbank_cf_allnum = als_m1_cell_nbank_cf_allnum;
	}

	public Integer getAls_m1_cell_nbank_com_allnum() {
		return als_m1_cell_nbank_com_allnum;
	}

	public void setAls_m1_cell_nbank_com_allnum(Integer als_m1_cell_nbank_com_allnum) {
		this.als_m1_cell_nbank_com_allnum = als_m1_cell_nbank_com_allnum;
	}

	public Integer getAls_m1_cell_nbank_oth_allnum() {
		return als_m1_cell_nbank_oth_allnum;
	}

	public void setAls_m1_cell_nbank_oth_allnum(Integer als_m1_cell_nbank_oth_allnum) {
		this.als_m1_cell_nbank_oth_allnum = als_m1_cell_nbank_oth_allnum;
	}

	public Integer getAls_m1_cell_nbank_orgnum() {
		return als_m1_cell_nbank_orgnum;
	}

	public void setAls_m1_cell_nbank_orgnum(Integer als_m1_cell_nbank_orgnum) {
		this.als_m1_cell_nbank_orgnum = als_m1_cell_nbank_orgnum;
	}

	public Integer getAls_m1_cell_nbank_p2p_orgnum() {
		return als_m1_cell_nbank_p2p_orgnum;
	}

	public void setAls_m1_cell_nbank_p2p_orgnum(Integer als_m1_cell_nbank_p2p_orgnum) {
		this.als_m1_cell_nbank_p2p_orgnum = als_m1_cell_nbank_p2p_orgnum;
	}

	public Integer getAls_m1_cell_nbank_mc_orgnum() {
		return als_m1_cell_nbank_mc_orgnum;
	}

	public void setAls_m1_cell_nbank_mc_orgnum(Integer als_m1_cell_nbank_mc_orgnum) {
		this.als_m1_cell_nbank_mc_orgnum = als_m1_cell_nbank_mc_orgnum;
	}

	public Integer getAls_m1_cell_nbank_ca_orgnum() {
		return als_m1_cell_nbank_ca_orgnum;
	}

	public void setAls_m1_cell_nbank_ca_orgnum(Integer als_m1_cell_nbank_ca_orgnum) {
		this.als_m1_cell_nbank_ca_orgnum = als_m1_cell_nbank_ca_orgnum;
	}

	public Integer getAls_m1_cell_nbank_cf_orgnum() {
		return als_m1_cell_nbank_cf_orgnum;
	}

	public void setAls_m1_cell_nbank_cf_orgnum(Integer als_m1_cell_nbank_cf_orgnum) {
		this.als_m1_cell_nbank_cf_orgnum = als_m1_cell_nbank_cf_orgnum;
	}

	public Integer getAls_m1_cell_nbank_com_orgnum() {
		return als_m1_cell_nbank_com_orgnum;
	}

	public void setAls_m1_cell_nbank_com_orgnum(Integer als_m1_cell_nbank_com_orgnum) {
		this.als_m1_cell_nbank_com_orgnum = als_m1_cell_nbank_com_orgnum;
	}

	public Integer getAls_m1_cell_nbank_oth_orgnum() {
		return als_m1_cell_nbank_oth_orgnum;
	}

	public void setAls_m1_cell_nbank_oth_orgnum(Integer als_m1_cell_nbank_oth_orgnum) {
		this.als_m1_cell_nbank_oth_orgnum = als_m1_cell_nbank_oth_orgnum;
	}

	public Integer getAls_m3_id_bank_selfnum() {
		return als_m3_id_bank_selfnum;
	}

	public void setAls_m3_id_bank_selfnum(Integer als_m3_id_bank_selfnum) {
		this.als_m3_id_bank_selfnum = als_m3_id_bank_selfnum;
	}

	public Integer getAls_m3_id_bank_allnum() {
		return als_m3_id_bank_allnum;
	}

	public void setAls_m3_id_bank_allnum(Integer als_m3_id_bank_allnum) {
		this.als_m3_id_bank_allnum = als_m3_id_bank_allnum;
	}

	public Integer getAls_m3_id_bank_orgnum() {
		return als_m3_id_bank_orgnum;
	}

	public void setAls_m3_id_bank_orgnum(Integer als_m3_id_bank_orgnum) {
		this.als_m3_id_bank_orgnum = als_m3_id_bank_orgnum;
	}

	public Integer getAls_m3_id_nbank_selfnum() {
		return als_m3_id_nbank_selfnum;
	}

	public void setAls_m3_id_nbank_selfnum(Integer als_m3_id_nbank_selfnum) {
		this.als_m3_id_nbank_selfnum = als_m3_id_nbank_selfnum;
	}

	public Integer getAls_m3_id_nbank_allnum() {
		return als_m3_id_nbank_allnum;
	}

	public void setAls_m3_id_nbank_allnum(Integer als_m3_id_nbank_allnum) {
		this.als_m3_id_nbank_allnum = als_m3_id_nbank_allnum;
	}

	public Integer getAls_m3_id_nbank_p2p_allnum() {
		return als_m3_id_nbank_p2p_allnum;
	}

	public void setAls_m3_id_nbank_p2p_allnum(Integer als_m3_id_nbank_p2p_allnum) {
		this.als_m3_id_nbank_p2p_allnum = als_m3_id_nbank_p2p_allnum;
	}

	public Integer getAls_m3_id_nbank_mc_allnum() {
		return als_m3_id_nbank_mc_allnum;
	}

	public void setAls_m3_id_nbank_mc_allnum(Integer als_m3_id_nbank_mc_allnum) {
		this.als_m3_id_nbank_mc_allnum = als_m3_id_nbank_mc_allnum;
	}

	public Integer getAls_m3_id_nbank_ca_allnum() {
		return als_m3_id_nbank_ca_allnum;
	}

	public void setAls_m3_id_nbank_ca_allnum(Integer als_m3_id_nbank_ca_allnum) {
		this.als_m3_id_nbank_ca_allnum = als_m3_id_nbank_ca_allnum;
	}

	public Integer getAls_m3_id_nbank_cf_allnum() {
		return als_m3_id_nbank_cf_allnum;
	}

	public void setAls_m3_id_nbank_cf_allnum(Integer als_m3_id_nbank_cf_allnum) {
		this.als_m3_id_nbank_cf_allnum = als_m3_id_nbank_cf_allnum;
	}

	public Integer getAls_m3_id_nbank_com_allnum() {
		return als_m3_id_nbank_com_allnum;
	}

	public void setAls_m3_id_nbank_com_allnum(Integer als_m3_id_nbank_com_allnum) {
		this.als_m3_id_nbank_com_allnum = als_m3_id_nbank_com_allnum;
	}

	public Integer getAls_m3_id_nbank_oth_allnum() {
		return als_m3_id_nbank_oth_allnum;
	}

	public void setAls_m3_id_nbank_oth_allnum(Integer als_m3_id_nbank_oth_allnum) {
		this.als_m3_id_nbank_oth_allnum = als_m3_id_nbank_oth_allnum;
	}

	public Integer getAls_m3_id_nbank_orgnum() {
		return als_m3_id_nbank_orgnum;
	}

	public void setAls_m3_id_nbank_orgnum(Integer als_m3_id_nbank_orgnum) {
		this.als_m3_id_nbank_orgnum = als_m3_id_nbank_orgnum;
	}

	public Integer getAls_m3_id_nbank_p2p_orgnum() {
		return als_m3_id_nbank_p2p_orgnum;
	}

	public void setAls_m3_id_nbank_p2p_orgnum(Integer als_m3_id_nbank_p2p_orgnum) {
		this.als_m3_id_nbank_p2p_orgnum = als_m3_id_nbank_p2p_orgnum;
	}

	public Integer getAls_m3_id_nbank_mc_orgnum() {
		return als_m3_id_nbank_mc_orgnum;
	}

	public void setAls_m3_id_nbank_mc_orgnum(Integer als_m3_id_nbank_mc_orgnum) {
		this.als_m3_id_nbank_mc_orgnum = als_m3_id_nbank_mc_orgnum;
	}

	public Integer getAls_m3_id_nbank_ca_orgnum() {
		return als_m3_id_nbank_ca_orgnum;
	}

	public void setAls_m3_id_nbank_ca_orgnum(Integer als_m3_id_nbank_ca_orgnum) {
		this.als_m3_id_nbank_ca_orgnum = als_m3_id_nbank_ca_orgnum;
	}

	public Integer getAls_m3_id_nbank_cf_orgnum() {
		return als_m3_id_nbank_cf_orgnum;
	}

	public void setAls_m3_id_nbank_cf_orgnum(Integer als_m3_id_nbank_cf_orgnum) {
		this.als_m3_id_nbank_cf_orgnum = als_m3_id_nbank_cf_orgnum;
	}

	public Integer getAls_m3_id_nbank_com_orgnum() {
		return als_m3_id_nbank_com_orgnum;
	}

	public void setAls_m3_id_nbank_com_orgnum(Integer als_m3_id_nbank_com_orgnum) {
		this.als_m3_id_nbank_com_orgnum = als_m3_id_nbank_com_orgnum;
	}

	public Integer getAls_m3_id_nbank_oth_orgnum() {
		return als_m3_id_nbank_oth_orgnum;
	}

	public void setAls_m3_id_nbank_oth_orgnum(Integer als_m3_id_nbank_oth_orgnum) {
		this.als_m3_id_nbank_oth_orgnum = als_m3_id_nbank_oth_orgnum;
	}

	public Integer getAls_m3_cell_bank_selfnum() {
		return als_m3_cell_bank_selfnum;
	}

	public void setAls_m3_cell_bank_selfnum(Integer als_m3_cell_bank_selfnum) {
		this.als_m3_cell_bank_selfnum = als_m3_cell_bank_selfnum;
	}

	public Integer getAls_m3_cell_bank_allnum() {
		return als_m3_cell_bank_allnum;
	}

	public void setAls_m3_cell_bank_allnum(Integer als_m3_cell_bank_allnum) {
		this.als_m3_cell_bank_allnum = als_m3_cell_bank_allnum;
	}

	public Integer getAls_m3_cell_bank_orgnum() {
		return als_m3_cell_bank_orgnum;
	}

	public void setAls_m3_cell_bank_orgnum(Integer als_m3_cell_bank_orgnum) {
		this.als_m3_cell_bank_orgnum = als_m3_cell_bank_orgnum;
	}

	public Integer getAls_m3_cell_nbank_selfnum() {
		return als_m3_cell_nbank_selfnum;
	}

	public void setAls_m3_cell_nbank_selfnum(Integer als_m3_cell_nbank_selfnum) {
		this.als_m3_cell_nbank_selfnum = als_m3_cell_nbank_selfnum;
	}

	public Integer getAls_m3_cell_nbank_allnum() {
		return als_m3_cell_nbank_allnum;
	}

	public void setAls_m3_cell_nbank_allnum(Integer als_m3_cell_nbank_allnum) {
		this.als_m3_cell_nbank_allnum = als_m3_cell_nbank_allnum;
	}

	public Integer getAls_m3_cell_nbank_p2p_allnum() {
		return als_m3_cell_nbank_p2p_allnum;
	}

	public void setAls_m3_cell_nbank_p2p_allnum(Integer als_m3_cell_nbank_p2p_allnum) {
		this.als_m3_cell_nbank_p2p_allnum = als_m3_cell_nbank_p2p_allnum;
	}

	public Integer getAls_m3_cell_nbank_mc_allnum() {
		return als_m3_cell_nbank_mc_allnum;
	}

	public void setAls_m3_cell_nbank_mc_allnum(Integer als_m3_cell_nbank_mc_allnum) {
		this.als_m3_cell_nbank_mc_allnum = als_m3_cell_nbank_mc_allnum;
	}

	public Integer getAls_m3_cell_nbank_ca_allnum() {
		return als_m3_cell_nbank_ca_allnum;
	}

	public void setAls_m3_cell_nbank_ca_allnum(Integer als_m3_cell_nbank_ca_allnum) {
		this.als_m3_cell_nbank_ca_allnum = als_m3_cell_nbank_ca_allnum;
	}

	public Integer getAls_m3_cell_nbank_cf_allnum() {
		return als_m3_cell_nbank_cf_allnum;
	}

	public void setAls_m3_cell_nbank_cf_allnum(Integer als_m3_cell_nbank_cf_allnum) {
		this.als_m3_cell_nbank_cf_allnum = als_m3_cell_nbank_cf_allnum;
	}

	public Integer getAls_m3_cell_nbank_com_allnum() {
		return als_m3_cell_nbank_com_allnum;
	}

	public void setAls_m3_cell_nbank_com_allnum(Integer als_m3_cell_nbank_com_allnum) {
		this.als_m3_cell_nbank_com_allnum = als_m3_cell_nbank_com_allnum;
	}

	public Integer getAls_m3_cell_nbank_oth_allnum() {
		return als_m3_cell_nbank_oth_allnum;
	}

	public void setAls_m3_cell_nbank_oth_allnum(Integer als_m3_cell_nbank_oth_allnum) {
		this.als_m3_cell_nbank_oth_allnum = als_m3_cell_nbank_oth_allnum;
	}

	public Integer getAls_m3_cell_nbank_orgnum() {
		return als_m3_cell_nbank_orgnum;
	}

	public void setAls_m3_cell_nbank_orgnum(Integer als_m3_cell_nbank_orgnum) {
		this.als_m3_cell_nbank_orgnum = als_m3_cell_nbank_orgnum;
	}

	public Integer getAls_m3_cell_nbank_p2p_orgnum() {
		return als_m3_cell_nbank_p2p_orgnum;
	}

	public void setAls_m3_cell_nbank_p2p_orgnum(Integer als_m3_cell_nbank_p2p_orgnum) {
		this.als_m3_cell_nbank_p2p_orgnum = als_m3_cell_nbank_p2p_orgnum;
	}

	public Integer getAls_m3_cell_nbank_mc_orgnum() {
		return als_m3_cell_nbank_mc_orgnum;
	}

	public void setAls_m3_cell_nbank_mc_orgnum(Integer als_m3_cell_nbank_mc_orgnum) {
		this.als_m3_cell_nbank_mc_orgnum = als_m3_cell_nbank_mc_orgnum;
	}

	public Integer getAls_m3_cell_nbank_ca_orgnum() {
		return als_m3_cell_nbank_ca_orgnum;
	}

	public void setAls_m3_cell_nbank_ca_orgnum(Integer als_m3_cell_nbank_ca_orgnum) {
		this.als_m3_cell_nbank_ca_orgnum = als_m3_cell_nbank_ca_orgnum;
	}

	public Integer getAls_m3_cell_nbank_cf_orgnum() {
		return als_m3_cell_nbank_cf_orgnum;
	}

	public void setAls_m3_cell_nbank_cf_orgnum(Integer als_m3_cell_nbank_cf_orgnum) {
		this.als_m3_cell_nbank_cf_orgnum = als_m3_cell_nbank_cf_orgnum;
	}

	public Integer getAls_m3_cell_nbank_com_orgnum() {
		return als_m3_cell_nbank_com_orgnum;
	}

	public void setAls_m3_cell_nbank_com_orgnum(Integer als_m3_cell_nbank_com_orgnum) {
		this.als_m3_cell_nbank_com_orgnum = als_m3_cell_nbank_com_orgnum;
	}

	public Integer getAls_m3_cell_nbank_oth_orgnum() {
		return als_m3_cell_nbank_oth_orgnum;
	}

	public void setAls_m3_cell_nbank_oth_orgnum(Integer als_m3_cell_nbank_oth_orgnum) {
		this.als_m3_cell_nbank_oth_orgnum = als_m3_cell_nbank_oth_orgnum;
	}

	public Integer getAls_m6_id_tot_mons() {
		return als_m6_id_tot_mons;
	}

	public void setAls_m6_id_tot_mons(Integer als_m6_id_tot_mons) {
		this.als_m6_id_tot_mons = als_m6_id_tot_mons;
	}

	public Double getAls_m6_id_avg_monnum() {
		return als_m6_id_avg_monnum;
	}

	public void setAls_m6_id_avg_monnum(Double als_m6_id_avg_monnum) {
		this.als_m6_id_avg_monnum = als_m6_id_avg_monnum;
	}

	public Integer getAls_m6_id_max_monnum() {
		return als_m6_id_max_monnum;
	}

	public void setAls_m6_id_max_monnum(Integer als_m6_id_max_monnum) {
		this.als_m6_id_max_monnum = als_m6_id_max_monnum;
	}

	public Integer getAls_m6_id_min_monnum() {
		return als_m6_id_min_monnum;
	}

	public void setAls_m6_id_min_monnum(Integer als_m6_id_min_monnum) {
		this.als_m6_id_min_monnum = als_m6_id_min_monnum;
	}

	public Integer getAls_m6_id_max_inteday() {
		return als_m6_id_max_inteday;
	}

	public void setAls_m6_id_max_inteday(Integer als_m6_id_max_inteday) {
		this.als_m6_id_max_inteday = als_m6_id_max_inteday;
	}

	public Integer getAls_m6_id_min_inteday() {
		return als_m6_id_min_inteday;
	}

	public void setAls_m6_id_min_inteday(Integer als_m6_id_min_inteday) {
		this.als_m6_id_min_inteday = als_m6_id_min_inteday;
	}

	public Integer getAls_m6_cell_tot_mons() {
		return als_m6_cell_tot_mons;
	}

	public void setAls_m6_cell_tot_mons(Integer als_m6_cell_tot_mons) {
		this.als_m6_cell_tot_mons = als_m6_cell_tot_mons;
	}

	public Double getAls_m6_cell_avg_monnum() {
		return als_m6_cell_avg_monnum;
	}

	public void setAls_m6_cell_avg_monnum(Double als_m6_cell_avg_monnum) {
		this.als_m6_cell_avg_monnum = als_m6_cell_avg_monnum;
	}

	public Integer getAls_m6_cell_max_monnum() {
		return als_m6_cell_max_monnum;
	}

	public void setAls_m6_cell_max_monnum(Integer als_m6_cell_max_monnum) {
		this.als_m6_cell_max_monnum = als_m6_cell_max_monnum;
	}

	public Integer getAls_m6_cell_min_monnum() {
		return als_m6_cell_min_monnum;
	}

	public void setAls_m6_cell_min_monnum(Integer als_m6_cell_min_monnum) {
		this.als_m6_cell_min_monnum = als_m6_cell_min_monnum;
	}

	public Integer getAls_m6_cell_max_inteday() {
		return als_m6_cell_max_inteday;
	}

	public void setAls_m6_cell_max_inteday(Integer als_m6_cell_max_inteday) {
		this.als_m6_cell_max_inteday = als_m6_cell_max_inteday;
	}

	public Integer getAls_m6_cell_min_inteday() {
		return als_m6_cell_min_inteday;
	}

	public void setAls_m6_cell_min_inteday(Integer als_m6_cell_min_inteday) {
		this.als_m6_cell_min_inteday = als_m6_cell_min_inteday;
	}

	public Integer getAls_fst_id_bank_inteday() {
		return als_fst_id_bank_inteday;
	}

	public void setAls_fst_id_bank_inteday(Integer als_fst_id_bank_inteday) {
		this.als_fst_id_bank_inteday = als_fst_id_bank_inteday;
	}

	public Integer getAls_fst_id_nbank_inteday() {
		return als_fst_id_nbank_inteday;
	}

	public void setAls_fst_id_nbank_inteday(Integer als_fst_id_nbank_inteday) {
		this.als_fst_id_nbank_inteday = als_fst_id_nbank_inteday;
	}

	public Integer getAls_fst_cell_bank_inteday() {
		return als_fst_cell_bank_inteday;
	}

	public void setAls_fst_cell_bank_inteday(Integer als_fst_cell_bank_inteday) {
		this.als_fst_cell_bank_inteday = als_fst_cell_bank_inteday;
	}

	public Integer getAls_fst_cell_nbank_inteday() {
		return als_fst_cell_nbank_inteday;
	}

	public void setAls_fst_cell_nbank_inteday(Integer als_fst_cell_nbank_inteday) {
		this.als_fst_cell_nbank_inteday = als_fst_cell_nbank_inteday;
	}

	public Integer getAls_lst_id_bank_inteday() {
		return als_lst_id_bank_inteday;
	}

	public void setAls_lst_id_bank_inteday(Integer als_lst_id_bank_inteday) {
		this.als_lst_id_bank_inteday = als_lst_id_bank_inteday;
	}

	public Integer getAls_lst_id_bank_consnum() {
		return als_lst_id_bank_consnum;
	}

	public void setAls_lst_id_bank_consnum(Integer als_lst_id_bank_consnum) {
		this.als_lst_id_bank_consnum = als_lst_id_bank_consnum;
	}

	public Integer getAls_lst_id_bank_csinteday() {
		return als_lst_id_bank_csinteday;
	}

	public void setAls_lst_id_bank_csinteday(Integer als_lst_id_bank_csinteday) {
		this.als_lst_id_bank_csinteday = als_lst_id_bank_csinteday;
	}

	public Integer getAls_lst_id_nbank_inteday() {
		return als_lst_id_nbank_inteday;
	}

	public void setAls_lst_id_nbank_inteday(Integer als_lst_id_nbank_inteday) {
		this.als_lst_id_nbank_inteday = als_lst_id_nbank_inteday;
	}

	public Integer getAls_lst_id_nbank_consnum() {
		return als_lst_id_nbank_consnum;
	}

	public void setAls_lst_id_nbank_consnum(Integer als_lst_id_nbank_consnum) {
		this.als_lst_id_nbank_consnum = als_lst_id_nbank_consnum;
	}

	public Integer getAls_lst_id_nbank_csinteday() {
		return als_lst_id_nbank_csinteday;
	}

	public void setAls_lst_id_nbank_csinteday(Integer als_lst_id_nbank_csinteday) {
		this.als_lst_id_nbank_csinteday = als_lst_id_nbank_csinteday;
	}

	public Integer getAls_lst_cell_bank_inteday() {
		return als_lst_cell_bank_inteday;
	}

	public void setAls_lst_cell_bank_inteday(Integer als_lst_cell_bank_inteday) {
		this.als_lst_cell_bank_inteday = als_lst_cell_bank_inteday;
	}

	public Integer getAls_lst_cell_bank_consnum() {
		return als_lst_cell_bank_consnum;
	}

	public void setAls_lst_cell_bank_consnum(Integer als_lst_cell_bank_consnum) {
		this.als_lst_cell_bank_consnum = als_lst_cell_bank_consnum;
	}

	public Integer getAls_lst_cell_bank_csinteday() {
		return als_lst_cell_bank_csinteday;
	}

	public void setAls_lst_cell_bank_csinteday(Integer als_lst_cell_bank_csinteday) {
		this.als_lst_cell_bank_csinteday = als_lst_cell_bank_csinteday;
	}

	public Integer getAls_lst_cell_nbank_inteday() {
		return als_lst_cell_nbank_inteday;
	}

	public void setAls_lst_cell_nbank_inteday(Integer als_lst_cell_nbank_inteday) {
		this.als_lst_cell_nbank_inteday = als_lst_cell_nbank_inteday;
	}

	public Integer getAls_lst_cell_nbank_consnum() {
		return als_lst_cell_nbank_consnum;
	}

	public void setAls_lst_cell_nbank_consnum(Integer als_lst_cell_nbank_consnum) {
		this.als_lst_cell_nbank_consnum = als_lst_cell_nbank_consnum;
	}

	public Integer getAls_lst_cell_nbank_csinteday() {
		return als_lst_cell_nbank_csinteday;
	}

	public void setAls_lst_cell_nbank_csinteday(Integer als_lst_cell_nbank_csinteday) {
		this.als_lst_cell_nbank_csinteday = als_lst_cell_nbank_csinteday;
	}
}
