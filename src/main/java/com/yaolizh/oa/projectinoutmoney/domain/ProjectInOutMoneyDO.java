package com.yaolizh.oa.projectinoutmoney.domain;

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
 * 收支明细
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 11:53:19
 */
@Table(name="t_project_in_out_money")
@Entity
public class ProjectInOutMoneyDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**  在父类中公用 */
			   	
	   	/**
	 * @Fields serialVersionUID long
	 */
	private static final long serialVersionUID = -2247361954490061868L;

		/**交易类型(1：现金记账,2:银行记账,4:工会工行记账,8:工会贵州银行记账)*/
	  	  		@Column(name ="type")
		private Integer type;
			   	
	   	/**项目id*/
	  	  		@Column(name ="project_id")
		private String projectId;
			   	
	   	/**项目名称*/
	  	  		@Column(name ="project_name")
		private String projectName;
			   	
	   	/**合同id*/
	  	  		@Column(name ="contract_id")
		private String contractId;
			   	
	   	/**合同名称*/
	  	  		@Column(name ="contract_name")
		private String contractName;
			   	
	   	/**对方名称*/
	  	  		@Column(name ="client_name")
		private String clientName;
			   	
	   	/**明细科目*/
	  	  		@Column(name ="subject_item")
		private String subjectItem;
			   	
	   	/**描述*/
	  	  		@Column(name ="descript")
		private String descript;
			   	
	   	/**初期金额*/
	  	  		@Column(name ="begin_amount")
		private BigDecimal beginAmount;
			   	
	   	/**收支类型(1:收入,2:支出)*/
	  	  		@Column(name ="in_out")
		private Integer inOut;
			   	
	   	/**交易时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="happen_time")
		private Date happenTime;
			   	
	   	/**收入金额*/
	  	  		@Column(name ="in_amount")
		private BigDecimal inAmount;
			   	
	   	/**支出金额*/
	  	  		@Column(name ="out_amount")
		private String outAmount;
			   	
	   	/**结存金额*/
	  	  		@Column(name ="end_amount")
		private BigDecimal endAmount;
				/**  在父类中公用 */
				/**  在父类中公用 */
				/**  在父类中公用 */
				/**  在父类中公用 */
				/**  在父类中公用 */
				/**  在父类中公用 */
				/**  在父类中公用 */
				/**  在父类中公用 */
				/**  在父类中公用 */
				/**  在父类中公用 */
				/**  在父类中公用 */
				/**  在父类中公用 */
				/**  在父类中公用 */
			   	
	   	/***/
	  	  		@Column(name ="remark")
		private String remark;
				/**  在父类中公用 */
				/**  在父类中公用 */
			   	
	   	/***/
	  	  		@Column(name ="pay_amount")
		private BigDecimal payAmount;
	
	
  		/**  在父类中公用 */
  	
  	/**
	 * 设置：交易类型(1：现金记账,2:银行记账,4:工会工行记账,8:工会贵州银行记账)
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：交易类型(1：现金记账,2:银行记账,4:工会工行记账,8:工会贵州银行记账)
	 */
	public Integer getType() {
		return type;
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
	 * 设置：合同id
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * 获取：合同id
	 */
	public String getContractId() {
		return contractId;
	}
  	
  	/**
	 * 设置：合同名称
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	/**
	 * 获取：合同名称
	 */
	public String getContractName() {
		return contractName;
	}
  	
  	/**
	 * 设置：对方名称
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	/**
	 * 获取：对方名称
	 */
	public String getClientName() {
		return clientName;
	}
  	
  	/**
	 * 设置：明细科目
	 */
	public void setSubjectItem(String subjectItem) {
		this.subjectItem = subjectItem;
	}
	/**
	 * 获取：明细科目
	 */
	public String getSubjectItem() {
		return subjectItem;
	}
  	
  	/**
	 * 设置：描述
	 */
	public void setDescript(String descript) {
		this.descript = descript;
	}
	/**
	 * 获取：描述
	 */
	public String getDescript() {
		return descript;
	}
  	
  	/**
	 * 设置：初期金额
	 */
	public void setBeginAmount(BigDecimal beginAmount) {
		this.beginAmount = beginAmount;
	}
	/**
	 * 获取：初期金额
	 */
	public BigDecimal getBeginAmount() {
		return beginAmount;
	}
  	
  	/**
	 * 设置：收支类型(1:收入,2:支出)
	 */
	public void setInOut(Integer inOut) {
		this.inOut = inOut;
	}
	/**
	 * 获取：收支类型(1:收入,2:支出)
	 */
	public Integer getInOut() {
		return inOut;
	}
  	
  	/**
	 * 设置：交易时间
	 */
	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}
	/**
	 * 获取：交易时间
	 */
	public Date getHappenTime() {
		return happenTime;
	}
  	
  	/**
	 * 设置：收入金额
	 */
	public void setInAmount(BigDecimal inAmount) {
		this.inAmount = inAmount;
	}
	/**
	 * 获取：收入金额
	 */
	public BigDecimal getInAmount() {
		return inAmount;
	}
  	
  	/**
	 * 设置：支出金额
	 */
	public void setOutAmount(String outAmount) {
		this.outAmount = outAmount;
	}
	/**
	 * 获取：支出金额
	 */
	public String getOutAmount() {
		return outAmount;
	}
  	
  	/**
	 * 设置：结存金额
	 */
	public void setEndAmount(BigDecimal endAmount) {
		this.endAmount = endAmount;
	}
	/**
	 * 获取：结存金额
	 */
	public BigDecimal getEndAmount() {
		return endAmount;
	}
  	
  		/**  在父类中公用 */
  	
  		/**  在父类中公用 */
  	
  		/**  在父类中公用 */
  	
  		/**  在父类中公用 */
  	
  		/**  在父类中公用 */
  	
  		/**  在父类中公用 */
  	
  		/**  在父类中公用 */
  	
  		/**  在父类中公用 */
  	
  		/**  在父类中公用 */
  	
  		/**  在父类中公用 */
  	
  		/**  在父类中公用 */
  	
  		/**  在父类中公用 */
  	
  		/**  在父类中公用 */
  	
  	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}
  	
  		/**  在父类中公用 */
  	
  		/**  在父类中公用 */
  	
  	/**
	 * 设置：
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
	}
  	
}
