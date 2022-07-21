package com.yaolizh.oa.project.domain;

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
 * 项目信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:54
 */
@Table(name="t_project")
@Entity
public class ProjectDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**项目编号*/
	  	  		@Column(name ="no")
		private String no;
			   	
	   	/**项目名称*/
	  	  		@Column(name ="name")
		private String name;
			   	
	   	/**状态(1:未立项，2立项成立，4：结束)*/
	  	  		@Column(name ="state")
		private Integer state;
			   	
	   	/**项目金额*/
	  	  		@Column(name ="amount")
		private BigDecimal amount;
			   	
	   	/**已收款金额*/
	  	  		@Column(name ="in_amount")
		private BigDecimal inAmount;
			   	
	   	/**实际已支出成本*/
	  	  		@Column(name ="out_amount")
		private BigDecimal outAmount;
			   	
	   	/**预算成本*/
	  	  		@Column(name ="plan_out_amount")
		private BigDecimal planOutAmount;
			   	
	   	/**进度情况*/
	  	  		@Column(name ="progress")
		private String progress;
			   	
	   	/**项目计划开始日期*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="plan_begin_date")
		private Date planBeginDate;
			   	
	   	/**项目计划完成日期*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="plan_end_date")
		private Date planEndDate;
			   	
	   	/**项目实际完成日期*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="end_date")
		private Date endDate;
	
	
  		/**主键  在父类中公用 */
  	
  	/**
	 * 设置：项目编号
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * 获取：项目编号
	 */
	public String getNo() {
		return no;
	}
  	
  	/**
	 * 设置：项目名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：项目名称
	 */
	public String getName() {
		return name;
	}
  	
  	/**
	 * 设置：状态(1:未立项，2立项成立，4：结束)
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：状态(1:未立项，2立项成立，4：结束)
	 */
	public Integer getState() {
		return state;
	}
  	
  	/**
	 * 设置：项目金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：项目金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}
  	
  	/**
	 * 设置：已收款金额
	 */
	public void setInAmount(BigDecimal inAmount) {
		this.inAmount = inAmount;
	}
	/**
	 * 获取：已收款金额
	 */
	public BigDecimal getInAmount() {
		return inAmount;
	}
  	
  	/**
	 * 设置：实际已支出成本
	 */
	public void setOutAmount(BigDecimal outAmount) {
		this.outAmount = outAmount;
	}
	/**
	 * 获取：实际已支出成本
	 */
	public BigDecimal getOutAmount() {
		return outAmount;
	}
  	
  	/**
	 * 设置：预算成本
	 */
	public void setPlanOutAmount(BigDecimal planOutAmount) {
		this.planOutAmount = planOutAmount;
	}
	/**
	 * 获取：预算成本
	 */
	public BigDecimal getPlanOutAmount() {
		return planOutAmount;
	}
  	
  	/**
	 * 设置：进度情况
	 */
	public void setProgress(String progress) {
		this.progress = progress;
	}
	/**
	 * 获取：进度情况
	 */
	public String getProgress() {
		return progress;
	}
  	
  	/**
	 * 设置：项目计划开始日期
	 */
	public void setPlanBeginDate(Date planBeginDate) {
		this.planBeginDate = planBeginDate;
	}
	/**
	 * 获取：项目计划开始日期
	 */
	public Date getPlanBeginDate() {
		return planBeginDate;
	}
  	
  	/**
	 * 设置：项目计划完成日期
	 */
	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}
	/**
	 * 获取：项目计划完成日期
	 */
	public Date getPlanEndDate() {
		return planEndDate;
	}
  	
  	/**
	 * 设置：项目实际完成日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：项目实际完成日期
	 */
	public Date getEndDate() {
		return endDate;
	}
  	
}
