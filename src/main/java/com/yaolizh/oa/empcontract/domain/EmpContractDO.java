package com.yaolizh.oa.empcontract.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yaolizh.fastwoo.base.SuperBaseData;
import com.yaolizh.fastwoo.common.utils.DateUtils;
 

 


/**
 * 职工合同信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:57
 */
@Table(name="t_emp_contract")
@Entity
public class EmpContractDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**
	 * @Fields serialVersionUID long
	 */
	private static final long serialVersionUID = 8398056967692571254L;

		/**用户id*/
	  	  		@Column(name ="user_id")
		private String userId;
			   	
	   	/**员工编号*/
	  	  		@Column(name ="no")
		private String no;
			   	
	   	/**名字*/
	  	  		@Column(name ="name")
		private String name;
			   	
	   	/**身份证号码*/
	  	  		@Column(name ="id_card")
		private String idCard;
			   	
	   	/**电话*/
	  	  		@Column(name ="phone")
		private String phone;
			   	
	   	/**签订日期*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="sign_date")
		private Date signDate;
			   	
	   	/**开始时间*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="begin_date")
		private Date beginDate;
			   	
	   	/**结束时间*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="end_time")
		private Date endTime;
	
	
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
	 * 设置：身份证号码
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * 获取：身份证号码
	 */
	public String getIdCard() {
		return idCard;
	}
  	
  	/**
	 * 设置：电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话
	 */
	public String getPhone() {
		return phone;
	}
  	
  	/**
	 * 设置：签订日期
	 */
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	/**
	 * 获取：签订日期
	 */
	public Date getSignDate() {
		return signDate;
	}
  	
  	/**
	 * 设置：开始时间
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * 获取：开始时间
	 */
	public Date getBeginDate() {
		return beginDate;
	}
  	
  	/**
	 * 设置：结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
  	
}
