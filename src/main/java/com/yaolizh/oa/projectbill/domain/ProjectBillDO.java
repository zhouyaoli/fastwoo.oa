package com.yaolizh.oa.projectbill.domain;

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
 * 开票情况信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:56
 */
@Table(name="t_project_bill")
@Entity
public class ProjectBillDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**开票时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="bill_time")
		private Date billTime;
			   	
	   	/**项目id*/
	  	  		@Column(name ="project_id")
		private String projectId;
			   	
	   	/**项目名称*/
	  	  		@Column(name ="project_name")
		private String projectName;
			   	
	   	/**客户名称*/
	  	  		@Column(name ="client_name")
		private String clientName;
			   	
	   	/**客户识别号*/
	  	  		@Column(name ="Identifier_no")
		private String identifierNo;
			   	
	   	/**发票代码*/
	  	  		@Column(name ="bill_code")
		private String billCode;
			   	
	   	/**发票号码*/
	  	  		@Column(name ="bill_no")
		private String billNo;
			   	
	   	/**开票内容*/
	  	  		@Column(name ="content")
		private String content;
			   	
	   	/**不含税金额*/
	  	  		@Column(name ="no_tax_amount")
		private BigDecimal noTaxAmount;
			   	
	   	/**税额*/
	  	  		@Column(name ="tax_amount")
		private BigDecimal taxAmount;
			   	
	   	/**价税合计*/
	  	  		@Column(name ="total_amount")
		private BigDecimal totalAmount;
			   	
	   	/**税率*/
	  	  		@Column(name ="tax_rate")
		private BigDecimal taxRate;
			   	
	   	/**发票类型*/
	  	  		@Column(name ="type")
		private String type;
			   	
	   	/**开票人*/
	  	  		@Column(name ="bill_person")
		private String billPerson;
			   	
	   	/**状态(1新开，2作废)*/
	  	  		@Column(name ="state")
		private Integer state;
			   	
	   	/**作废人*/
	  	  		@Column(name ="crap_person")
		private String crapPerson;
			   	
	   	/**作废时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="crap_time")
		private Date crapTime;
			   	
	   	/**作废原因*/
	  	  		@Column(name ="crap_reason")
		private String crapReason;
	
	
  		/**主键  在父类中公用 */
  	
  	/**
	 * 设置：开票时间
	 */
	public void setBillTime(Date billTime) {
		this.billTime = billTime;
	}
	/**
	 * 获取：开票时间
	 */
	public Date getBillTime() {
		return billTime;
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
	 * 设置：客户名称
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	/**
	 * 获取：客户名称
	 */
	public String getClientName() {
		return clientName;
	}
  	
  	/**
	 * 设置：客户识别号
	 */
	public void setIdentifierNo(String identifierNo) {
		this.identifierNo = identifierNo;
	}
	/**
	 * 获取：客户识别号
	 */
	public String getIdentifierNo() {
		return identifierNo;
	}
  	
  	/**
	 * 设置：发票代码
	 */
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	/**
	 * 获取：发票代码
	 */
	public String getBillCode() {
		return billCode;
	}
  	
  	/**
	 * 设置：发票号码
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	/**
	 * 获取：发票号码
	 */
	public String getBillNo() {
		return billNo;
	}
  	
  	/**
	 * 设置：开票内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：开票内容
	 */
	public String getContent() {
		return content;
	}
  	
  	/**
	 * 设置：不含税金额
	 */
	public void setNoTaxAmount(BigDecimal noTaxAmount) {
		this.noTaxAmount = noTaxAmount;
	}
	/**
	 * 获取：不含税金额
	 */
	public BigDecimal getNoTaxAmount() {
		return noTaxAmount;
	}
  	
  	/**
	 * 设置：税额
	 */
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	/**
	 * 获取：税额
	 */
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
  	
  	/**
	 * 设置：价税合计
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 获取：价税合计
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
  	
  	/**
	 * 设置：税率
	 */
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	/**
	 * 获取：税率
	 */
	public BigDecimal getTaxRate() {
		return taxRate;
	}
  	
  	/**
	 * 设置：发票类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：发票类型
	 */
	public String getType() {
		return type;
	}
  	
  	/**
	 * 设置：开票人
	 */
	public void setBillPerson(String billPerson) {
		this.billPerson = billPerson;
	}
	/**
	 * 获取：开票人
	 */
	public String getBillPerson() {
		return billPerson;
	}
  	
  	/**
	 * 设置：状态(1新开，2作废)
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：状态(1新开，2作废)
	 */
	public Integer getState() {
		return state;
	}
  	
  	/**
	 * 设置：作废人
	 */
	public void setCrapPerson(String crapPerson) {
		this.crapPerson = crapPerson;
	}
	/**
	 * 获取：作废人
	 */
	public String getCrapPerson() {
		return crapPerson;
	}
  	
  	/**
	 * 设置：作废时间
	 */
	public void setCrapTime(Date crapTime) {
		this.crapTime = crapTime;
	}
	/**
	 * 获取：作废时间
	 */
	public Date getCrapTime() {
		return crapTime;
	}
  	
  	/**
	 * 设置：作废原因
	 */
	public void setCrapReason(String crapReason) {
		this.crapReason = crapReason;
	}
	/**
	 * 获取：作废原因
	 */
	public String getCrapReason() {
		return crapReason;
	}
  	
}
