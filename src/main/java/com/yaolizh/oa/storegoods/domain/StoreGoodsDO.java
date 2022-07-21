package com.yaolizh.oa.storegoods.domain;

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
 * 仓库物品
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:56
 */
@Table(name="t_store_goods")
@Entity
public class StoreGoodsDO extends SuperBaseData {
	 
 
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
  	
}
