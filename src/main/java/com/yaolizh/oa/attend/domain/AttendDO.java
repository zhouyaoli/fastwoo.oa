package com.yaolizh.oa.attend.domain;

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
 * 考勤打卡信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:07
 */
@Table(name="t_attend")
@Entity
public class AttendDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**用户id*/
	  	  		@Column(name ="user_id")
		private String userId;
			   	
	   	/**用户名字*/
	  	  		@Column(name ="user_name")
		private String userName;
			   	
	   	/**日期*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="work_day")
		private Date workDay;
			   	
	   	/**状态(1:缺勤,2:正常，4:迟到，8:早退；16:加班,32:上午请假,64:下午请假,128:补卡)*/
	  	  		@Column(name ="state")
		private Integer state;
			   	
	   	/**工作时长*/
	  	  		@Column(name ="work_hours")
		private BigDecimal workHours;
			   	
	   	/**上班时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="go_work_time")
		private Date goWorkTime;
			   	
	   	/**上班经度*/
	  	  		@Column(name ="go_work_lng")
		private BigDecimal goWorkLng;
			   	
	   	/**上班纬度*/
	  	  		@Column(name ="go_work_lat")
		private BigDecimal goWorkLat;
			   	
	   	/**上班备注*/
	  	  		@Column(name ="go_work_descript")
		private String goWorkDescript;
			   	
	   	/**下班时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="off_work_time")
		private Date offWorkTime;
			   	
	   	/**下班经度*/
	  	  		@Column(name ="off_work_lng")
		private BigDecimal offWorkLng;
			   	
	   	/**下班纬度*/
	  	  		@Column(name ="off_work_lat")
		private BigDecimal offWorkLat;
			   	
	   	/**下班备注*/
	  	  		@Column(name ="off_work_descript")
		private String offWorkDescript;
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
	 * 设置：用户名字
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名字
	 */
	public String getUserName() {
		return userName;
	}
  	
  	/**
	 * 设置：日期
	 */
	public void setWorkDay(Date workDay) {
		this.workDay = workDay;
	}
	/**
	 * 获取：日期
	 */
	public Date getWorkDay() {
		return workDay;
	}
  	
  	/**
	 * 设置：状态(1:缺勤,2:正常，4:迟到，8:早退；16:加班,32:上午请假,64:下午请假,128:补卡)
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：状态(1:缺勤,2:正常，4:迟到，8:早退；16:加班,32:上午请假,64:下午请假,128:补卡)
	 */
	public Integer getState() {
		return state;
	}
  	
  	/**
	 * 设置：工作时长
	 */
	public void setWorkHours(BigDecimal workHours) {
		this.workHours = workHours;
	}
	/**
	 * 获取：工作时长
	 */
	public BigDecimal getWorkHours() {
		return workHours;
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
	 * 设置：上班经度
	 */
	public void setGoWorkLng(BigDecimal goWorkLng) {
		this.goWorkLng = goWorkLng;
	}
	/**
	 * 获取：上班经度
	 */
	public BigDecimal getGoWorkLng() {
		return goWorkLng;
	}
  	
  	/**
	 * 设置：上班纬度
	 */
	public void setGoWorkLat(BigDecimal goWorkLat) {
		this.goWorkLat = goWorkLat;
	}
	/**
	 * 获取：上班纬度
	 */
	public BigDecimal getGoWorkLat() {
		return goWorkLat;
	}
  	
  	/**
	 * 设置：上班备注
	 */
	public void setGoWorkDescript(String goWorkDescript) {
		this.goWorkDescript = goWorkDescript;
	}
	/**
	 * 获取：上班备注
	 */
	public String getGoWorkDescript() {
		return goWorkDescript;
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
  	
  	/**
	 * 设置：下班经度
	 */
	public void setOffWorkLng(BigDecimal offWorkLng) {
		this.offWorkLng = offWorkLng;
	}
	/**
	 * 获取：下班经度
	 */
	public BigDecimal getOffWorkLng() {
		return offWorkLng;
	}
  	
  	/**
	 * 设置：下班纬度
	 */
	public void setOffWorkLat(BigDecimal offWorkLat) {
		this.offWorkLat = offWorkLat;
	}
	/**
	 * 获取：下班纬度
	 */
	public BigDecimal getOffWorkLat() {
		return offWorkLat;
	}
  	
  	/**
	 * 设置：下班备注
	 */
	public void setOffWorkDescript(String offWorkDescript) {
		this.offWorkDescript = offWorkDescript;
	}
	/**
	 * 获取：下班备注
	 */
	public String getOffWorkDescript() {
		return offWorkDescript;
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
