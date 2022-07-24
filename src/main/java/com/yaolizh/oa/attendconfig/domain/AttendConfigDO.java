package com.yaolizh.oa.attendconfig.domain;

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
 * 考勤配置信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:07
 */
@Table(name="t_attend_config")
@Entity
public class AttendConfigDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**上班经度*/
	  	  		@Column(name ="work_lng")
		private BigDecimal workLng;
			   	
	   	/**上班纬度*/
	  	  		@Column(name ="work_lat")
		private BigDecimal workLat;
			   	
	   	/**上班时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="go_work_time")
		private Date goWorkTime;
			   	
	   	/**最迟上班时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="go_work_limit_time")
		private Date goWorkLimitTime;
			   	
	   	/**下班时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="off_work_time")
		private Date offWorkTime;
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
	 * 设置：上班经度
	 */
	public void setWorkLng(BigDecimal workLng) {
		this.workLng = workLng;
	}
	/**
	 * 获取：上班经度
	 */
	public BigDecimal getWorkLng() {
		return workLng;
	}
  	
  	/**
	 * 设置：上班纬度
	 */
	public void setWorkLat(BigDecimal workLat) {
		this.workLat = workLat;
	}
	/**
	 * 获取：上班纬度
	 */
	public BigDecimal getWorkLat() {
		return workLat;
	}
  	
  	/**
	 * 设置：上班时间
	 */
	public void setGoWorkTime(Date goWorkTime) {
		this.goWorkTime = goWorkTime;
	}
	/**
	 * 获取：上班时间
	 */
	public Date getGoWorkTime() {
		return goWorkTime;
	}
  	
  	/**
	 * 设置：最迟上班时间
	 */
	public void setGoWorkLimitTime(Date goWorkLimitTime) {
		this.goWorkLimitTime = goWorkLimitTime;
	}
	/**
	 * 获取：最迟上班时间
	 */
	public Date getGoWorkLimitTime() {
		return goWorkLimitTime;
	}
  	
  	/**
	 * 设置：下班时间
	 */
	public void setOffWorkTime(Date offWorkTime) {
		this.offWorkTime = offWorkTime;
	}
	/**
	 * 获取：下班时间
	 */
	public Date getOffWorkTime() {
		return offWorkTime;
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
