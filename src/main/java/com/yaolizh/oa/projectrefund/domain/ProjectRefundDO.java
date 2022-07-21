package com.yaolizh.oa.projectrefund.domain;

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
 * 报销信息表
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:55
 */
@Table(name="t_project_refund")
@Entity
public class ProjectRefundDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**项目ID*/
	  	  		@Column(name ="project_id")
		private String projectId;
			   	
	   	/**用户*/
	  	  		@Column(name ="user_id")
		private String userId;
			   	
	   	/**用户名字*/
	  	  		@Column(name ="user_name")
		private String userName;
			   	
	   	/**`项目名称`*/
	  	  		@Column(name ="project_name")
		private String projectName;
			   	
	   	/**报销日期*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="refund_time")
		private Date refundTime;
			   	
	   	/**报销金额*/
	  	  		@Column(name ="amount")
		private BigDecimal amount;
			   	
	   	/**报销说明*/
	  	  		@Column(name ="reason")
		private String reason;
			   	
	   	/**流程id*/
	  	  		@Column(name ="process_instance_id")
		private String processInstanceId;
			   	
	   	/**状态(1：新建，2：审核通过，4：审核不通过,8：撤销，16：完成)*/
	  	  		@Column(name ="state")
		private Integer state;
			   	
	   	/**当前环节*/
	  	  		@Column(name ="cur_node_name")
		private String curNodeName;
			   	
	   	/**当前操作人*/
	  	  		@Column(name ="cur_check_name")
		private String curCheckName;
			   	
	   	/**当前操作意见*/
	  	  		@Column(name ="cur_check_comment")
		private String curCheckComment;
			   	
	   	/**选择的值*/
	  	  		@Column(name ="choose_code")
		private String chooseCode;
			   	
	   	/**选择的字符串*/
	  	  		@Column(name ="choose_text")
		private String chooseText;
	
	
  		/**主键  在父类中公用 */
  	
  	/**
	 * 设置：项目ID
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：项目ID
	 */
	public String getProjectId() {
		return projectId;
	}
  	
  	/**
	 * 设置：用户
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户
	 */
	public String getUserId() {
		return userId;
	}
  	
  	/**
	 * 设置：用户名字
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名字
	 */
	public String getUserName() {
		return userName;
	}
  	
  	/**
	 * 设置：`项目名称`
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 获取：`项目名称`
	 */
	public String getProjectName() {
		return projectName;
	}
  	
  	/**
	 * 设置：报销日期
	 */
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	/**
	 * 获取：报销日期
	 */
	public Date getRefundTime() {
		return refundTime;
	}
  	
  	/**
	 * 设置：报销金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：报销金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}
  	
  	/**
	 * 设置：报销说明
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 获取：报销说明
	 */
	public String getReason() {
		return reason;
	}
  	
  	/**
	 * 设置：流程id
	 */
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	/**
	 * 获取：流程id
	 */
	public String getProcessInstanceId() {
		return processInstanceId;
	}
  	
  	/**
	 * 设置：状态(1：新建，2：审核通过，4：审核不通过,8：撤销，16：完成)
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：状态(1：新建，2：审核通过，4：审核不通过,8：撤销，16：完成)
	 */
	public Integer getState() {
		return state;
	}
  	
  	/**
	 * 设置：当前环节
	 */
	public void setCurNodeName(String curNodeName) {
		this.curNodeName = curNodeName;
	}
	/**
	 * 获取：当前环节
	 */
	public String getCurNodeName() {
		return curNodeName;
	}
  	
  	/**
	 * 设置：当前操作人
	 */
	public void setCurCheckName(String curCheckName) {
		this.curCheckName = curCheckName;
	}
	/**
	 * 获取：当前操作人
	 */
	public String getCurCheckName() {
		return curCheckName;
	}
  	
  	/**
	 * 设置：当前操作意见
	 */
	public void setCurCheckComment(String curCheckComment) {
		this.curCheckComment = curCheckComment;
	}
	/**
	 * 获取：当前操作意见
	 */
	public String getCurCheckComment() {
		return curCheckComment;
	}
  	
  	/**
	 * 设置：选择的值
	 */
	public void setChooseCode(String chooseCode) {
		this.chooseCode = chooseCode;
	}
	/**
	 * 获取：选择的值
	 */
	public String getChooseCode() {
		return chooseCode;
	}
  	
  	/**
	 * 设置：选择的字符串
	 */
	public void setChooseText(String chooseText) {
		this.chooseText = chooseText;
	}
	/**
	 * 获取：选择的字符串
	 */
	public String getChooseText() {
		return chooseText;
	}
  	
}
