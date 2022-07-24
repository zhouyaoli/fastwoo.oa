package com.yaolizh.oa.projectcontract.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yaolizh.fastwoo.base.SuperBaseData;
import com.yaolizh.fastwoo.common.utils.DateUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 合同信息（附件：中标通知书）
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 17:13:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_project_contract")
@Entity
public class ProjectContractDO extends SuperBaseData {

	/** 公共字段在父类中继承  */
	/**主键  在父类中公用 */

	/**
	 * @Fields serialVersionUID long
	 */
	private static final long serialVersionUID = -6127588034879274052L;

	/**合同编号*/
	@Column(name = "no")
	private String no;

	/**合同名称*/
	@Column(name = "name")
	private String name;

	/**项目id*/
	@Column(name = "project_id")
	private String projectId;

	/**项目名称*/
	@Column(name = "project_name")
	private String projectName;

	/**销售人员*/
	@Column(name = "person")
	private String person;

	/**甲方公司*/
	@Column(name = "one_company")
	private String oneCompany;

	/**甲方负责人*/
	@Column(name = "one_leader")
	private String oneLeader;

	/**甲方联系人*/
	@Column(name = "one_phone")
	private String onePhone;

	/**乙方名称（）*/
	@Column(name = "two_company")
	private String twoCompany;

	/**乙方负责人*/
	@Column(name = "two_leader")
	private String twoLeader;

	/**乙方联系电话*/
	@Column(name = "two_phone")
	private String twoPhone;

	/**合同状态*/
	@Column(name = "state")
	private Integer state;

	/**付款方式(1：阶段付款；2：比例付款；3：全额付款)*/
	@Column(name = "pay_way")
	private Integer payWay;

	/**合同金额*/
	@Column(name = "amount")
	private BigDecimal amount;

	/**已(收/付)款金额*/
	@Column(name = "pay_amount")
	private BigDecimal payAmount;

	/**已开票金额*/
	@Column(name = "bill_amount")
	private BigDecimal billAmount;

	/**开票类型（1：增值税发票，2：普通发票）*/
	@Column(name = "bill_type")
	private Integer billType;

	/**合同签署日期*/
	@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
	@DateTimeFormat(pattern = DateUtils._DATE_DEFAULT)
	@Column(name = "sign_date")
	private Date signDate;

	/**合同生效日期*/
	@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
	@DateTimeFormat(pattern = DateUtils._DATE_DEFAULT)
	@Column(name = "valid_date")
	private Date validDate;

	/**合同截止日期*/
	@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
	@DateTimeFormat(pattern = DateUtils._DATE_DEFAULT)
	@Column(name = "limit_date")
	private Date limitDate;

	/**合同类型(1:收入合同，2:支出合同)*/
	@Column(name = "type")
	private Integer type;

	/**合同数*/
	@Column(name = "contract_num")
	private Integer contractNum;

}
