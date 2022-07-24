package com.yaolizh.oa.carinsurehistory.domain;

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
 * 车辆保险记录信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:59
 */
@Table(name="t_car_insure_history")
@Entity
public class CarInsureHistoryDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**
	 * @Fields serialVersionUID long
	 */
	private static final long serialVersionUID = 7379017211057571569L;

		/**维修序号*/
	  	  		@Column(name ="serial_num")
		private String serialNum;
			   	
	   	/**用车序号*/
	  	  		@Column(name ="use_serial_num")
		private String useSerialNum;
			   	
	   	/**车牌号码*/
	  	  		@Column(name ="car_num")
		private String carNum;
			   	
	   	/**变速箱类别（1:手动挡、2：自动档，4：手自一体）*/
	  	  		@Column(name ="car_type")
		private Integer carType;
			   	
	   	/**车身颜色*/
	  	  		@Column(name ="car_num_color")
		private String carNumColor;
			   	
	   	/**保险公司*/
	  	  		@Column(name ="company")
		private String company;
			   	
	   	/**投保时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="insure_date")
		private Date insureDate;
			   	
	   	/**投保金额*/
	  	  		@Column(name ="amount")
		private BigDecimal amount;
			   	
	   	/**保险开始时间*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="begin_date")
		private Date beginDate;
			   	
	   	/**保险结束时间*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="end_date")
		private Date endDate;
			   	
	   	/**联系电话*/
	  	  		@Column(name ="phone")
		private String phone;
	
	
  		/**主键  在父类中公用 */
  	
  	/**
	 * 设置：维修序号
	 */
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	/**
	 * 获取：维修序号
	 */
	public String getSerialNum() {
		return serialNum;
	}
  	
  	/**
	 * 设置：用车序号
	 */
	public void setUseSerialNum(String useSerialNum) {
		this.useSerialNum = useSerialNum;
	}
	/**
	 * 获取：用车序号
	 */
	public String getUseSerialNum() {
		return useSerialNum;
	}
  	
  	/**
	 * 设置：车牌号码
	 */
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	/**
	 * 获取：车牌号码
	 */
	public String getCarNum() {
		return carNum;
	}
  	
  	/**
	 * 设置：变速箱类别（1:手动挡、2：自动档，4：手自一体）
	 */
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	/**
	 * 获取：变速箱类别（1:手动挡、2：自动档，4：手自一体）
	 */
	public Integer getCarType() {
		return carType;
	}
  	
  	/**
	 * 设置：车身颜色
	 */
	public void setCarNumColor(String carNumColor) {
		this.carNumColor = carNumColor;
	}
	/**
	 * 获取：车身颜色
	 */
	public String getCarNumColor() {
		return carNumColor;
	}
  	
  	/**
	 * 设置：保险公司
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * 获取：保险公司
	 */
	public String getCompany() {
		return company;
	}
  	
  	/**
	 * 设置：投保时间
	 */
	public void setInsureDate(Date insureDate) {
		this.insureDate = insureDate;
	}
	/**
	 * 获取：投保时间
	 */
	public Date getInsureDate() {
		return insureDate;
	}
  	
  	/**
	 * 设置：投保金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：投保金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}
  	
  	/**
	 * 设置：保险开始时间
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * 获取：保险开始时间
	 */
	public Date getBeginDate() {
		return beginDate;
	}
  	
  	/**
	 * 设置：保险结束时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：保险结束时间
	 */
	public Date getEndDate() {
		return endDate;
	}
  	
  	/**
	 * 设置：联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getPhone() {
		return phone;
	}
  	
}
