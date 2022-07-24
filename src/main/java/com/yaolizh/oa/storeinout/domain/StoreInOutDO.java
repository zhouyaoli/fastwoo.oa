package com.yaolizh.oa.storeinout.domain;

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
 * 仓库出入库记录
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:00
 */
@Table(name="t_store_in_out")
@Entity
public class StoreInOutDO extends SuperBaseData {
	 
 
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
			   	
	   	/**初期金额*/
	  	  		@Column(name ="begin_num")
		private BigDecimal beginNum;
			   	
	   	/**出入类型(1:入,2:出)*/
	  	  		@Column(name ="in_out")
		private Integer inOut;
			   	
	   	/**出入时间*/
	  	  		@Column(name ="inout_time")
		private BigDecimal inoutTime;
			   	
	   	/**出入数量*/
	  	  		@Column(name ="inout_num")
		private BigDecimal inoutNum;
			   	
	   	/**结存数量*/
	  	  		@Column(name ="end_num")
		private BigDecimal endNum;
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
	 * 设置：初期金额
	 */
	public void setBeginNum(BigDecimal beginNum) {
		this.beginNum = beginNum;
	}
	/**
	 * 获取：初期金额
	 */
	public BigDecimal getBeginNum() {
		return beginNum;
	}
  	
  	/**
	 * 设置：出入类型(1:入,2:出)
	 */
	public void setInOut(Integer inOut) {
		this.inOut = inOut;
	}
	/**
	 * 获取：出入类型(1:入,2:出)
	 */
	public Integer getInOut() {
		return inOut;
	}
  	
  	/**
	 * 设置：出入时间
	 */
	public void setInoutTime(BigDecimal inoutTime) {
		this.inoutTime = inoutTime;
	}
	/**
	 * 获取：出入时间
	 */
	public BigDecimal getInoutTime() {
		return inoutTime;
	}
  	
  	/**
	 * 设置：出入数量
	 */
	public void setInoutNum(BigDecimal inoutNum) {
		this.inoutNum = inoutNum;
	}
	/**
	 * 获取：出入数量
	 */
	public BigDecimal getInoutNum() {
		return inoutNum;
	}
  	
  	/**
	 * 设置：结存数量
	 */
	public void setEndNum(BigDecimal endNum) {
		this.endNum = endNum;
	}
	/**
	 * 获取：结存数量
	 */
	public BigDecimal getEndNum() {
		return endNum;
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
