package com.yaolizh.oa.fixedresource.domain;

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
 * 固定资产信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:03
 */
@Table(name="t_fixed_resource")
@Entity
public class FixedResourceDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**编码*/
	  	  		@Column(name ="code")
		private String code;
			   	
	   	/**名称*/
	  	  		@Column(name ="name")
		private String name;
			   	
	   	/**规格型号*/
	  	  		@Column(name ="model")
		private String model;
			   	
	   	/**购买日期*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="buy_date")
		private Date buyDate;
			   	
	   	/**购买金额*/
	  	  		@Column(name ="buy_amount")
		private BigDecimal buyAmount;
			   	
	   	/**状态(1:正常,2:出借,4:保修,8:报废)*/
	  	  		@Column(name ="state")
		private String state;
			   	
	   	/**使用人*/
	  	  		@Column(name ="use_person")
		private String usePerson;
			   	
	   	/**开始使用时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="begin_use_time")
		private Date beginUseTime;
			   	
	   	/**报废批次*/
	  	  		@Column(name ="crap_serial_num")
		private String crapSerialNum;
			   	
	   	/**报废人*/
	  	  		@Column(name ="crap_person")
		private String crapPerson;
			   	
	   	/**报废时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="crap_time")
		private Date crapTime;
			   	
	   	/**报废时间*/
	  	  		@Column(name ="crap_reason")
		private String crapReason;
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
	 * 设置：编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：编码
	 */
	public String getCode() {
		return code;
	}
  	
  	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
  	
  	/**
	 * 设置：规格型号
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * 获取：规格型号
	 */
	public String getModel() {
		return model;
	}
  	
  	/**
	 * 设置：购买日期
	 */
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	/**
	 * 获取：购买日期
	 */
	public Date getBuyDate() {
		return buyDate;
	}
  	
  	/**
	 * 设置：购买金额
	 */
	public void setBuyAmount(BigDecimal buyAmount) {
		this.buyAmount = buyAmount;
	}
	/**
	 * 获取：购买金额
	 */
	public BigDecimal getBuyAmount() {
		return buyAmount;
	}
  	
  	/**
	 * 设置：状态(1:正常,2:出借,4:保修,8:报废)
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态(1:正常,2:出借,4:保修,8:报废)
	 */
	public String getState() {
		return state;
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
	 * 设置：报废批次
	 */
	public void setCrapSerialNum(String crapSerialNum) {
		this.crapSerialNum = crapSerialNum;
	}
	/**
	 * 获取：报废批次
	 */
	public String getCrapSerialNum() {
		return crapSerialNum;
	}
  	
  	/**
	 * 设置：报废人
	 */
	public void setCrapPerson(String crapPerson) {
		this.crapPerson = crapPerson;
	}
	/**
	 * 获取：报废人
	 */
	public String getCrapPerson() {
		return crapPerson;
	}
  	
  	/**
	 * 设置：报废时间
	 */
	public void setCrapTime(Date crapTime) {
		this.crapTime = crapTime;
	}
	/**
	 * 获取：报废时间
	 */
	public Date getCrapTime() {
		return crapTime;
	}
  	
  	/**
	 * 设置：报废时间
	 */
	public void setCrapReason(String crapReason) {
		this.crapReason = crapReason;
	}
	/**
	 * 获取：报废时间
	 */
	public String getCrapReason() {
		return crapReason;
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
