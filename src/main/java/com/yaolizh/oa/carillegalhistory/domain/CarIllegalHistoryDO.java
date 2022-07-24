package com.yaolizh.oa.carillegalhistory.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yaolizh.fastwoo.base.SuperBaseData;
import com.yaolizh.fastwoo.common.utils.DateUtils;
 

 


/**
 * 车辆违章记录信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:59
 */
@Table(name="t_car_illegal_history")
@Entity
public class CarIllegalHistoryDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**
	 * @Fields serialVersionUID long
	 */
	private static final long serialVersionUID = 7202069059979156808L;

		/**违章序号*/
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
			   	
	   	/**违章描述*/
	  	  		@Column(name ="descript")
		private String descript;
			   	
	   	/**申请违章人*/
	  	  		@Column(name ="fault_person")
		private String faultPerson;
			   	
	   	/**违章时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="fault_time")
		private Date faultTime;
			   	
	   	/**违章地址*/
	  	  		@Column(name ="fault_addr")
		private String faultAddr;
			   	
	   	/**扣分*/
	  	  		@Column(name ="penalty_score")
		private String penaltyScore;
			   	
	   	/**罚款金额*/
	  	  		@Column(name ="penalty_amount")
		private String penaltyAmount;
	
	
  		/**主键  在父类中公用 */
  	
  	/**
	 * 设置：违章序号
	 */
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	/**
	 * 获取：违章序号
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
	 * 设置：违章描述
	 */
	public void setDescript(String descript) {
		this.descript = descript;
	}
	/**
	 * 获取：违章描述
	 */
	public String getDescript() {
		return descript;
	}
  	
  	/**
	 * 设置：申请违章人
	 */
	public void setFaultPerson(String faultPerson) {
		this.faultPerson = faultPerson;
	}
	/**
	 * 获取：申请违章人
	 */
	public String getFaultPerson() {
		return faultPerson;
	}
  	
  	/**
	 * 设置：违章时间
	 */
	public void setFaultTime(Date faultTime) {
		this.faultTime = faultTime;
	}
	/**
	 * 获取：违章时间
	 */
	public Date getFaultTime() {
		return faultTime;
	}
  	
  	/**
	 * 设置：违章地址
	 */
	public void setFaultAddr(String faultAddr) {
		this.faultAddr = faultAddr;
	}
	/**
	 * 获取：违章地址
	 */
	public String getFaultAddr() {
		return faultAddr;
	}
  	
  	/**
	 * 设置：扣分
	 */
	public void setPenaltyScore(String penaltyScore) {
		this.penaltyScore = penaltyScore;
	}
	/**
	 * 获取：扣分
	 */
	public String getPenaltyScore() {
		return penaltyScore;
	}
  	
  	/**
	 * 设置：罚款金额
	 */
	public void setPenaltyAmount(String penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}
	/**
	 * 获取：罚款金额
	 */
	public String getPenaltyAmount() {
		return penaltyAmount;
	}
  	
}
