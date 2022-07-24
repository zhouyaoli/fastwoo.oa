package com.yaolizh.oa.empresthistory.domain;

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
 * 职工请假记录
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:04
 */
@Table(name="t_emp_rest_history")
@Entity
public class EmpRestHistoryDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**用户id*/
	  	  		@Column(name ="user_id")
		private String userId;
			   	
	   	/**员工编号*/
	  	  		@Column(name ="no")
		private String no;
			   	
	   	/**名字*/
	  	  		@Column(name ="name")
		private String name;
			   	
	   	/**身份证*/
	  	  		@Column(name ="id_card")
		private String idCard;
			   	
	   	/**年假、育儿假、产假、婚嫁、陪产假、丧假、病假、事假*/
	  	  		@Column(name ="type")
		private Integer type;
			   	
	   	/**休息天数*/
	  	  		@Column(name ="rest_days")
		private String restDays;
			   	
	   	/**开始休息时间*/
	  	  		@Column(name ="begin_time")
		private Integer beginTime;
			   	
	   	/**结束休息时间*/
	  	  		@Column(name ="end_time")
		private Integer endTime;
			   	
	   	/**请假原因*/
	  	  		@Column(name ="reason")
		private String reason;
			   	
	   	/**状态(1：新建，2：审核通过，4：审核不通过,8：撤销，16：完成)*/
	  	  		@Column(name ="state")
		private Integer state;
			   	
	   	/**流程id*/
	  	  		@Column(name ="process_instance_id")
		private String processInstanceId;
			   	
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
	
	
  		/**主键  在父类中公用 */
  	
  	/**
	 * 设置：用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public String getUserId() {
		return userId;
	}
  	
  	/**
	 * 设置：员工编号
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * 获取：员工编号
	 */
	public String getNo() {
		return no;
	}
  	
  	/**
	 * 设置：名字
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名字
	 */
	public String getName() {
		return name;
	}
  	
  	/**
	 * 设置：身份证
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * 获取：身份证
	 */
	public String getIdCard() {
		return idCard;
	}
  	
  	/**
	 * 设置：年假、育儿假、产假、婚嫁、陪产假、丧假、病假、事假
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：年假、育儿假、产假、婚嫁、陪产假、丧假、病假、事假
	 */
	public Integer getType() {
		return type;
	}
  	
  	/**
	 * 设置：休息天数
	 */
	public void setRestDays(String restDays) {
		this.restDays = restDays;
	}
	/**
	 * 获取：休息天数
	 */
	public String getRestDays() {
		return restDays;
	}
  	
  	/**
	 * 设置：开始休息时间
	 */
	public void setBeginTime(Integer beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * 获取：开始休息时间
	 */
	public Integer getBeginTime() {
		return beginTime;
	}
  	
  	/**
	 * 设置：结束休息时间
	 */
	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束休息时间
	 */
	public Integer getEndTime() {
		return endTime;
	}
  	
  	/**
	 * 设置：请假原因
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 获取：请假原因
	 */
	public String getReason() {
		return reason;
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
  	
}
