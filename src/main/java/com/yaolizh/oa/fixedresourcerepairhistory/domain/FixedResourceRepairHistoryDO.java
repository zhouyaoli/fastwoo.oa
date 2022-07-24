package com.yaolizh.oa.fixedresourcerepairhistory.domain;

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
 * 固定资产维修记录
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:03
 */
@Table(name="t_fixed_resource_repair_history")
@Entity
public class FixedResourceRepairHistoryDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**维修序号*/
	  	  		@Column(name ="serial_num")
		private String serialNum;
			   	
	   	/**编码*/
	  	  		@Column(name ="code")
		private String code;
			   	
	   	/**名称*/
	  	  		@Column(name ="name")
		private String name;
			   	
	   	/**状态(1:待维修,2:维修中,4:维修完成,8:报废)*/
	  	  		@Column(name ="state")
		private Integer state;
			   	
	   	/**维修原因*/
	  	  		@Column(name ="reason")
		private String reason;
			   	
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
