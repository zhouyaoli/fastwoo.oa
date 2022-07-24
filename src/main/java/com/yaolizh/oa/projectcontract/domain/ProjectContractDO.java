package com.yaolizh.oa.projectcontract.domain;

import java.util.Date;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yaolizh.fastwoo.base.SuperBaseData;
import com.yaolizh.fastwoo.common.utils.DateUtils;
 

 


/**
 * 合同信息（附件：中标通知书）
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:53
 */
@Table(name="t_project_contract")
@Entity
public class ProjectContractDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**
	 * @Fields serialVersionUID long
	 */
	private static final long serialVersionUID = -2058760583795586808L;

		/**合同编号*/
	  	  		@Column(name ="no")
		private String no;
			   	
	   	/**合同名称*/
	  	  		@Column(name ="name")
		private String name;
			   	
	   	/**项目id*/
	  	  		@Column(name ="project_id")
		private String projectId;
			   	
	   	/**项目名称*/
	  	  		@Column(name ="project_name")
		private String projectName;
			   	
	   	/**销售人员*/
	  	  		@Column(name ="person")
		private String person;
			   	
	   	/**甲方公司*/
	  	  		@Column(name ="one_company")
		private String oneCompany;
			   	
	   	/**甲方负责人*/
	  	  		@Column(name ="one_leader")
		private String oneLeader;
			   	
	   	/**甲方联系人*/
	  	  		@Column(name ="one_phone")
		private String onePhone;
			   	
	   	/**乙方名称（）*/
	  	  		@Column(name ="two_company")
		private String twoCompany;
			   	
	   	/**乙方负责人*/
	  	  		@Column(name ="two_leader")
		private String twoLeader;
			   	
	   	/**乙方联系电话*/
	  	  		@Column(name ="two_phone")
		private String twoPhone;
			   	
	   	/**合同状态*/
	  	  		@Column(name ="status")
		private String status;
			   	
	   	/**付款方式(1：阶段付款；2：比例付款；3：全额付款)*/
	  	  		@Column(name ="pay_way")
		private Integer payWay;
			   	
	   	/**合同金额*/
	  	  		@Column(name ="amount")
		private BigDecimal amount;
			   	
	   	/**已(收/付)款金额*/
	  	  		@Column(name ="pay_amount")
		private BigDecimal payAmount;
			   	
	   	/**已开票金额*/
	  	  		@Column(name ="bill_amount")
		private BigDecimal billAmount;
			   	
	   	/**开票类型（1：增值税发票，2：普通发票）*/
	  	  		@Column(name ="bill_type")
		private Integer billType;
			   	
	   	/**合同签署日期*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="sign_date")
		private Date signDate;
			   	
	   	/**合同生效日期*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="valid_date")
		private Date validDate;
			   	
	   	/**合同截止日期*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="limit_date")
		private Date limitDate;
			   	
	   	/**合同类型(1:收入合同，2:支出合同)*/
	  	  		@Column(name ="type")
		private Integer type;
			   	
	   	/**合同数*/
	  	  		@Column(name ="contract_num")
		private Integer contractNum;
	
	
  		/**主键  在父类中公用 */
  	
  	/**
	 * 设置：合同编号
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * 获取：合同编号
	 */
	public String getNo() {
		return no;
	}
  	
  	/**
	 * 设置：合同名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：合同名称
	 */
	public String getName() {
		return name;
	}
  	
  	/**
	 * 设置：项目id
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：项目id
	 */
	public String getProjectId() {
		return projectId;
	}
  	
  	/**
	 * 设置：项目名称
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 获取：项目名称
	 */
	public String getProjectName() {
		return projectName;
	}
  	
  	/**
	 * 设置：销售人员
	 */
	public void setPerson(String person) {
		this.person = person;
	}
	/**
	 * 获取：销售人员
	 */
	public String getPerson() {
		return person;
	}
  	
  	/**
	 * 设置：甲方公司
	 */
	public void setOneCompany(String oneCompany) {
		this.oneCompany = oneCompany;
	}
	/**
	 * 获取：甲方公司
	 */
	public String getOneCompany() {
		return oneCompany;
	}
  	
  	/**
	 * 设置：甲方负责人
	 */
	public void setOneLeader(String oneLeader) {
		this.oneLeader = oneLeader;
	}
	/**
	 * 获取：甲方负责人
	 */
	public String getOneLeader() {
		return oneLeader;
	}
  	
  	/**
	 * 设置：甲方联系人
	 */
	public void setOnePhone(String onePhone) {
		this.onePhone = onePhone;
	}
	/**
	 * 获取：甲方联系人
	 */
	public String getOnePhone() {
		return onePhone;
	}
  	
  	/**
	 * 设置：乙方名称（）
	 */
	public void setTwoCompany(String twoCompany) {
		this.twoCompany = twoCompany;
	}
	/**
	 * 获取：乙方名称（）
	 */
	public String getTwoCompany() {
		return twoCompany;
	}
  	
  	/**
	 * 设置：乙方负责人
	 */
	public void setTwoLeader(String twoLeader) {
		this.twoLeader = twoLeader;
	}
	/**
	 * 获取：乙方负责人
	 */
	public String getTwoLeader() {
		return twoLeader;
	}
  	
  	/**
	 * 设置：乙方联系电话
	 */
	public void setTwoPhone(String twoPhone) {
		this.twoPhone = twoPhone;
	}
	/**
	 * 获取：乙方联系电话
	 */
	public String getTwoPhone() {
		return twoPhone;
	}
  	
  	/**
	 * 设置：合同状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：合同状态
	 */
	public String getStatus() {
		return status;
	}
  	
  	/**
	 * 设置：付款方式(1：阶段付款；2：比例付款；3：全额付款)
	 */
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	/**
	 * 获取：付款方式(1：阶段付款；2：比例付款；3：全额付款)
	 */
	public Integer getPayWay() {
		return payWay;
	}
  	
  	/**
	 * 设置：合同金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：合同金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}
  	
  	/**
	 * 设置：已(收/付)款金额
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	/**
	 * 获取：已(收/付)款金额
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
	}
  	
  	/**
	 * 设置：已开票金额
	 */
	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}
	/**
	 * 获取：已开票金额
	 */
	public BigDecimal getBillAmount() {
		return billAmount;
	}
  	
  	/**
	 * 设置：开票类型（1：增值税发票，2：普通发票）
	 */
	public void setBillType(Integer billType) {
		this.billType = billType;
	}
	/**
	 * 获取：开票类型（1：增值税发票，2：普通发票）
	 */
	public Integer getBillType() {
		return billType;
	}
  	
  	/**
	 * 设置：合同签署日期
	 */
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	/**
	 * 获取：合同签署日期
	 */
	public Date getSignDate() {
		return signDate;
	}
  	
  	/**
	 * 设置：合同生效日期
	 */
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	/**
	 * 获取：合同生效日期
	 */
	public Date getValidDate() {
		return validDate;
	}
  	
  	/**
	 * 设置：合同截止日期
	 */
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	/**
	 * 获取：合同截止日期
	 */
	public Date getLimitDate() {
		return limitDate;
	}
  	
  	/**
	 * 设置：合同类型(1:收入合同，2:支出合同)
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：合同类型(1:收入合同，2:支出合同)
	 */
	public Integer getType() {
		return type;
	}
  	
  	/**
	 * 设置：合同数
	 */
	public void setContractNum(Integer contractNum) {
		this.contractNum = contractNum;
	}
	/**
	 * 获取：合同数
	 */
	public Integer getContractNum() {
		return contractNum;
	}
  	
}
