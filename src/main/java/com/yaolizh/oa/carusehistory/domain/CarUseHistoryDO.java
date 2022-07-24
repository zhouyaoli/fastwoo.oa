package com.yaolizh.oa.carusehistory.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yaolizh.fastwoo.base.SuperBaseData;
import com.yaolizh.fastwoo.common.utils.DateUtils;
 

 


/**
 * 车辆使用记录信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:59
 */
@Table(name="t_car_use_history")
@Entity
public class CarUseHistoryDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**
	 * @Fields serialVersionUID long
	 */
	private static final long serialVersionUID = -4437258482100280727L;

		/**借出序号*/
	  	  		@Column(name ="serial_num")
		private String serialNum;
			   	
	   	/**车牌号码*/
	  	  		@Column(name ="car_num")
		private String carNum;
			   	
	   	/**是否归还*/
	  	  		@Column(name ="has_return")
		private Integer hasReturn;
			   	
	   	/**使用人*/
	  	  		@Column(name ="use_person")
		private String usePerson;
			   	
	   	/**用车原因*/
	  	  		@Column(name ="use_reason")
		private String useReason;
			   	
	   	/**目的地*/
	  	  		@Column(name ="destination")
		private String destination;
			   	
	   	/**开始里程数*/
	  	  		@Column(name ="begin_mileage")
		private Integer beginMileage;
			   	
	   	/**结束里程数*/
	  	  		@Column(name ="end_mileage")
		private Integer endMileage;
			   	
	   	/**开始油量*/
	  	  		@Column(name ="begin_oil")
		private String beginOil;
			   	
	   	/**结束油量*/
	  	  		@Column(name ="end_oil")
		private String endOil;
			   	
	   	/**开始使用时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="begin_use_time")
		private Date beginUseTime;
			   	
	   	/**归还时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="end_use_time")
		private Date endUseTime;
	
	
  		/**主键  在父类中公用 */
  	
  	/**
	 * 设置：借出序号
	 */
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	/**
	 * 获取：借出序号
	 */
	public String getSerialNum() {
		return serialNum;
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
	 * 设置：是否归还
	 */
	public void setHasReturn(Integer hasReturn) {
		this.hasReturn = hasReturn;
	}
	/**
	 * 获取：是否归还
	 */
	public Integer getHasReturn() {
		return hasReturn;
	}
  	
  	/**
	 * 设置：使用人
	 */
	public void setUsePerson(String usePerson) {
		this.usePerson = usePerson;
	}
	/**
	 * 获取：使用人
	 */
	public String getUsePerson() {
		return usePerson;
	}
  	
  	/**
	 * 设置：用车原因
	 */
	public void setUseReason(String useReason) {
		this.useReason = useReason;
	}
	/**
	 * 获取：用车原因
	 */
	public String getUseReason() {
		return useReason;
	}
  	
  	/**
	 * 设置：目的地
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * 获取：目的地
	 */
	public String getDestination() {
		return destination;
	}
  	
  	/**
	 * 设置：开始里程数
	 */
	public void setBeginMileage(Integer beginMileage) {
		this.beginMileage = beginMileage;
	}
	/**
	 * 获取：开始里程数
	 */
	public Integer getBeginMileage() {
		return beginMileage;
	}
  	
  	/**
	 * 设置：结束里程数
	 */
	public void setEndMileage(Integer endMileage) {
		this.endMileage = endMileage;
	}
	/**
	 * 获取：结束里程数
	 */
	public Integer getEndMileage() {
		return endMileage;
	}
  	
  	/**
	 * 设置：开始油量
	 */
	public void setBeginOil(String beginOil) {
		this.beginOil = beginOil;
	}
	/**
	 * 获取：开始油量
	 */
	public String getBeginOil() {
		return beginOil;
	}
  	
  	/**
	 * 设置：结束油量
	 */
	public void setEndOil(String endOil) {
		this.endOil = endOil;
	}
	/**
	 * 获取：结束油量
	 */
	public String getEndOil() {
		return endOil;
	}
  	
  	/**
	 * 设置：开始使用时间
	 */
	public void setBeginUseTime(Date beginUseTime) {
		this.beginUseTime = beginUseTime;
	}
	/**
	 * 获取：开始使用时间
	 */
	public Date getBeginUseTime() {
		return beginUseTime;
	}
  	
  	/**
	 * 设置：归还时间
	 */
	public void setEndUseTime(Date endUseTime) {
		this.endUseTime = endUseTime;
	}
	/**
	 * 获取：归还时间
	 */
	public Date getEndUseTime() {
		return endUseTime;
	}
  	
}
