package com.yaolizh.oa.fixedresourceusehistory.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yaolizh.fastwoo.base.SuperBaseData;
import com.yaolizh.fastwoo.common.utils.DateUtils;
 

 


/**
 * 固定资产借用归还记录
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:58
 */
@Table(name="t_fixed_resource_use_history")
@Entity
public class FixedResourceUseHistoryDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**
	 * @Fields serialVersionUID long
	 */
	private static final long serialVersionUID = 2399217992642163133L;

		/**借出序号*/
	  	  		@Column(name ="serial_num")
		private String serialNum;
			   	
	   	/**编码*/
	  	  		@Column(name ="code")
		private String code;
			   	
	   	/**名称*/
	  	  		@Column(name ="name")
		private String name;
			   	
	   	/**是否归还*/
	  	  		@Column(name ="has_return")
		private Integer hasReturn;
			   	
	   	/**使用人*/
	  	  		@Column(name ="use_person")
		private String usePerson;
			   	
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
