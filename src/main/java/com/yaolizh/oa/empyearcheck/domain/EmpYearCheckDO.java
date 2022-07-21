package com.yaolizh.oa.empyearcheck.domain;

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
 * 职工年度考核报告
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:56
 */
@Table(name="t_emp_year_check")
@Entity
public class EmpYearCheckDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**用户id*/
	  	  		@Column(name ="user_id")
		private String userId;
			   	
	   	/**员工编号*/
	  	  		@Column(name ="no")
		private String no;
			   	
	   	/**名字*/
	  	  		@Column(name ="name")
		private String name;
			   	
	   	/**身份证*/
	  	  		@Column(name ="id_card")
		private String idCard;
			   	
	   	/**年度*/
	  	  		@Column(name ="examine_year")
		private Integer examineYear;
			   	
	   	/**描述*/
	  	  		@Column(name ="descript")
		private String descript;
			   	
	   	/**附件路劲*/
	  	  		@Column(name ="file_path")
		private Integer filePath;
	
	
  		/**主键  在父类中公用 */
  	
  	/**
	 * 设置：用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public String getUserId() {
		return userId;
	}
  	
  	/**
	 * 设置：员工编号
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * 获取：员工编号
	 */
	public String getNo() {
		return no;
	}
  	
  	/**
	 * 设置：名字
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名字
	 */
	public String getName() {
		return name;
	}
  	
  	/**
	 * 设置：身份证
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * 获取：身份证
	 */
	public String getIdCard() {
		return idCard;
	}
  	
  	/**
	 * 设置：年度
	 */
	public void setExamineYear(Integer examineYear) {
		this.examineYear = examineYear;
	}
	/**
	 * 获取：年度
	 */
	public Integer getExamineYear() {
		return examineYear;
	}
  	
  	/**
	 * 设置：描述
	 */
	public void setDescript(String descript) {
		this.descript = descript;
	}
	/**
	 * 获取：描述
	 */
	public String getDescript() {
		return descript;
	}
  	
  	/**
	 * 设置：附件路劲
	 */
	public void setFilePath(Integer filePath) {
		this.filePath = filePath;
	}
	/**
	 * 获取：附件路劲
	 */
	public Integer getFilePath() {
		return filePath;
	}
  	
}
