package com.yaolizh.oa.projectrefunditem.domain;

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
 * 报销子项表
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:54
 */
@Table(name="t_project_refund_item")
@Entity
public class ProjectRefundItemDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**报销详情ID  在父类中公用 */
			   	
	   	/**
	 * @Fields serialVersionUID long
	 */
	private static final long serialVersionUID = -5609140068858772098L;

		/**报销ID*/
	  	  		@Column(name ="refund_id")
		private String refundId;
			   	
	   	/**报销类型(字典类型)*/
	  	  		@Column(name ="type")
		private String type;
			   	
	   	/**开票单位*/
	  	  		@Column(name ="bill_name")
		private String billName;
			   	
	   	/**开票日期*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="bill_date")
		private Date billDate;
			   	
	   	/**开票内容*/
	  	  		@Column(name ="bill_content")
		private String billContent;
			   	
	   	/**客户识别号*/
	  	  		@Column(name ="identifier_no")
		private String identifierNo;
			   	
	   	/**发票代码*/
	  	  		@Column(name ="bill_code")
		private String billCode;
			   	
	   	/**发票号码*/
	  	  		@Column(name ="bill_no")
		private String billNo;
			   	
	   	/**开票金额*/
	  	  		@Column(name ="bill_amount")
		private BigDecimal billAmount;
			   	
	   	/**物品名称*/
	  	  		@Column(name ="goods_name")
		private String goodsName;
			   	
	   	/**报销金额*/
	  	  		@Column(name ="amount")
		private BigDecimal amount;
	
	
  		/**报销详情ID  在父类中公用 */
  	
  	/**
	 * 设置：报销ID
	 */
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	/**
	 * 获取：报销ID
	 */
	public String getRefundId() {
		return refundId;
	}
  	
  	/**
	 * 设置：报销类型(字典类型)
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：报销类型(字典类型)
	 */
	public String getType() {
		return type;
	}
  	
  	/**
	 * 设置：开票单位
	 */
	public void setBillName(String billName) {
		this.billName = billName;
	}
	/**
	 * 获取：开票单位
	 */
	public String getBillName() {
		return billName;
	}
  	
  	/**
	 * 设置：开票日期
	 */
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	/**
	 * 获取：开票日期
	 */
	public Date getBillDate() {
		return billDate;
	}
  	
  	/**
	 * 设置：开票内容
	 */
	public void setBillContent(String billContent) {
		this.billContent = billContent;
	}
	/**
	 * 获取：开票内容
	 */
	public String getBillContent() {
		return billContent;
	}
  	
  	/**
	 * 设置：客户识别号
	 */
	public void setIdentifierNo(String identifierNo) {
		this.identifierNo = identifierNo;
	}
	/**
	 * 获取：客户识别号
	 */
	public String getIdentifierNo() {
		return identifierNo;
	}
  	
  	/**
	 * 设置：发票代码
	 */
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	/**
	 * 获取：发票代码
	 */
	public String getBillCode() {
		return billCode;
	}
  	
  	/**
	 * 设置：发票号码
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	/**
	 * 获取：发票号码
	 */
	public String getBillNo() {
		return billNo;
	}
  	
  	/**
	 * 设置：开票金额
	 */
	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}
	/**
	 * 获取：开票金额
	 */
	public BigDecimal getBillAmount() {
		return billAmount;
	}
  	
  	/**
	 * 设置：物品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * 获取：物品名称
	 */
	public String getGoodsName() {
		return goodsName;
	}
  	
  	/**
	 * 设置：报销金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：报销金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}
  	
}
