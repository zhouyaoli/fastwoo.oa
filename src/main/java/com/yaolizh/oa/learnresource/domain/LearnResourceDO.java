package com.yaolizh.oa.learnresource.domain;

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
 * 学习平台资源信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:56
 */
@Table(name="t_learn_resource")
@Entity
public class LearnResourceDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**标题*/
	  	  		@Column(name ="title")
		private String title;
			   	
	   	/**状态(1:新增,2:发布,4:撤销)*/
	  	  		@Column(name ="state")
		private String state;
			   	
	   	/**使用人*/
	  	  		@Column(name ="use_person")
		private String usePerson;
			   	
	   	/**开始使用时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="begin_time")
		private Date beginTime;
			   	
	   	/**结束使用时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="end_time")
		private Date endTime;
			   	
	   	/**描述*/
	  	  		@Column(name ="descript")
		private String descript;
			   	
	   	/**外部连接*/
	  	  		@Column(name ="uri")
		private String uri;
	
	
  		/**主键  在父类中公用 */
  	
  	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
  	
  	/**
	 * 设置：状态(1:新增,2:发布,4:撤销)
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态(1:新增,2:发布,4:撤销)
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
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * 获取：开始使用时间
	 */
	public Date getBeginTime() {
		return beginTime;
	}
  	
  	/**
	 * 设置：结束使用时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束使用时间
	 */
	public Date getEndTime() {
		return endTime;
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
	 * 设置：外部连接
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * 获取：外部连接
	 */
	public String getUri() {
		return uri;
	}
  	
}
