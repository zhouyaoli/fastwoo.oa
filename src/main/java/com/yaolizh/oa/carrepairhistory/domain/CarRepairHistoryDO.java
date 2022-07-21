package com.yaolizh.oa.carrepairhistory.domain;

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
 * 车辆维修维保记录信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:59
 */
@Table(name="t_car_repair_history")
@Entity
public class CarRepairHistoryDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**维修/保养(1:维修,2:保养)*/
	  	  		@Column(name ="type")
		private Integer type;
			   	
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
			   	
	   	/**状态(1:待维修,2:维修中,4:维修完成,8:报废)*/
	  	  		@Column(name ="state")
		private Integer state;
			   	
	   	/**维修原因*/
	  	  		@Column(name ="reason")
		private String reason;
			   	
	   	/**维修厂家*/
	  	  		@Column(name ="repair_shop")
		private String repairShop;
			   	
	   	/**维修地址*/
	  	  		@Column(name ="repair_addr")
		private String repairAddr;
			   	
	   	/**维修金额*/
	  	  		@Column(name ="repair_amount")
		private BigDecimal repairAmount;
			   	
	   	/**保险报销金额*/
	  	  		@Column(name ="insurance_amount")
		private BigDecimal insuranceAmount;
			   	
	   	/**申请维修人*/
	  	  		@Column(name ="apply_person")
		private String applyPerson;
			   	
	   	/**申请维修时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="apply_time")
		private Date applyTime;
			   	
	   	/**维修时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="repair_time")
		private Date repairTime;
	
	
  		/**主键  在父类中公用 */
  	
  	/**
	 * 设置：维修/保养(1:维修,2:保养)
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：维修/保养(1:维修,2:保养)
	 */
	public Integer getType() {
		return type;
	}
  	
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
	 * 设置：状态(1:待维修,2:维修中,4:维修完成,8:报废)
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：状态(1:待维修,2:维修中,4:维修完成,8:报废)
	 */
	public Integer getState() {
		return state;
	}
  	
  	/**
	 * 设置：维修原因
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 获取：维修原因
	 */
	public String getReason() {
		return reason;
	}
  	
  	/**
	 * 设置：维修厂家
	 */
	public void setRepairShop(String repairShop) {
		this.repairShop = repairShop;
	}
	/**
	 * 获取：维修厂家
	 */
	public String getRepairShop() {
		return repairShop;
	}
  	
  	/**
	 * 设置：维修地址
	 */
	public void setRepairAddr(String repairAddr) {
		this.repairAddr = repairAddr;
	}
	/**
	 * 获取：维修地址
	 */
	public String getRepairAddr() {
		return repairAddr;
	}
  	
  	/**
	 * 设置：维修金额
	 */
	public void setRepairAmount(BigDecimal repairAmount) {
		this.repairAmount = repairAmount;
	}
	/**
	 * 获取：维修金额
	 */
	public BigDecimal getRepairAmount() {
		return repairAmount;
	}
  	
  	/**
	 * 设置：保险报销金额
	 */
	public void setInsuranceAmount(BigDecimal insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}
	/**
	 * 获取：保险报销金额
	 */
	public BigDecimal getInsuranceAmount() {
		return insuranceAmount;
	}
  	
  	/**
	 * 设置：申请维修人
	 */
	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}
	/**
	 * 获取：申请维修人
	 */
	public String getApplyPerson() {
		return applyPerson;
	}
  	
  	/**
	 * 设置：申请维修时间
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	/**
	 * 获取：申请维修时间
	 */
	public Date getApplyTime() {
		return applyTime;
	}
  	
  	/**
	 * 设置：维修时间
	 */
	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}
	/**
	 * 获取：维修时间
	 */
	public Date getRepairTime() {
		return repairTime;
	}
  	
}
