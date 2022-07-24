package com.yaolizh.oa.emp.domain;

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
 * 职工档案信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:05
 */
@Table(name="t_emp")
@Entity
public class EmpDO extends SuperBaseData {
	 
 
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
			   	
	   	/**部门id*/
	  	  		@Column(name ="dept_id")
		private String deptId;
			   	
	   	/**部门*/
	  	  		@Column(name ="dept_name")
		private String deptName;
			   	
	   	/**年假天数*/
	  	  		@Column(name ="year_rest_num")
		private Integer yearRestNum;
			   	
	   	/**已休息年假天数*/
	  	  		@Column(name ="has_rest_year_rest_num")
		private Integer hasRestYearRestNum;
			   	
	   	/**工资卡*/
	  	  		@Column(name ="wage_card")
		private String wageCard;
			   	
	   	/**福利卡*/
	  	  		@Column(name ="freeca_card")
		private String freecaCard;
			   	
	   	/**职务*/
	  	  		@Column(name ="post")
		private String post;
			   	
	   	/**员工状态(1在职，2:离职)*/
	  	  		@Column(name ="state")
		private Integer state;
			   	
	   	/**电话*/
	  	  		@Column(name ="phone")
		private String phone;
			   	
	   	/**入职日期*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="in_date")
		private Date inDate;
			   	
	   	/**转正日期*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="formal_date")
		private Date formalDate;
			   	
	   	/**工作时间*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="work_date")
		private Date workDate;
			   	
	   	/**qq*/
	  	  		@Column(name ="qq")
		private String qq;
			   	
	   	/**性别*/
	  	  		@Column(name ="sex")
		private Integer sex;
			   	
	   	/**生日*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="birthday_day")
		private Date birthdayDay;
			   	
	   	/**是否结婚*/
	  	  		@Column(name ="has_marry")
		private Integer hasMarry;
			   	
	   	/**政治面貌*/
	  	  		@Column(name ="police_face")
		private String policeFace;
			   	
	   	/**户口性质*/
	  	  		@Column(name ="house_type")
		private String houseType;
			   	
	   	/**学校*/
	  	  		@Column(name ="school")
		private String school;
			   	
	   	/**学历*/
	  	  		@Column(name ="educate")
		private String educate;
			   	
	   	/**专业*/
	  	  		@Column(name ="major")
		private String major;
			   	
	   	/**名族*/
	  	  		@Column(name ="nation")
		private String nation;
			   	
	   	/**身份证地址*/
	  	  		@Column(name ="id_card_addr")
		private String idCardAddr;
			   	
	   	/**联系地址*/
	  	  		@Column(name ="contact_addr")
		private String contactAddr;
			   	
	   	/**紧急联系人*/
	  	  		@Column(name ="urgent_name")
		private String urgentName;
			   	
	   	/**紧急联系电话*/
	  	  		@Column(name ="urgent_phone")
		private String urgentPhone;
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
	 * 设置：部门id
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：部门id
	 */
	public String getDeptId() {
		return deptId;
	}
  	
  	/**
	 * 设置：部门
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取：部门
	 */
	public String getDeptName() {
		return deptName;
	}
  	
  	/**
	 * 设置：年假天数
	 */
	public void setYearRestNum(Integer yearRestNum) {
		this.yearRestNum = yearRestNum;
	}
	/**
	 * 获取：年假天数
	 */
	public Integer getYearRestNum() {
		return yearRestNum;
	}
  	
  	/**
	 * 设置：已休息年假天数
	 */
	public void setHasRestYearRestNum(Integer hasRestYearRestNum) {
		this.hasRestYearRestNum = hasRestYearRestNum;
	}
	/**
	 * 获取：已休息年假天数
	 */
	public Integer getHasRestYearRestNum() {
		return hasRestYearRestNum;
	}
  	
  	/**
	 * 设置：工资卡
	 */
	public void setWageCard(String wageCard) {
		this.wageCard = wageCard;
	}
	/**
	 * 获取：工资卡
	 */
	public String getWageCard() {
		return wageCard;
	}
  	
  	/**
	 * 设置：福利卡
	 */
	public void setFreecaCard(String freecaCard) {
		this.freecaCard = freecaCard;
	}
	/**
	 * 获取：福利卡
	 */
	public String getFreecaCard() {
		return freecaCard;
	}
  	
  	/**
	 * 设置：职务
	 */
	public void setPost(String post) {
		this.post = post;
	}
	/**
	 * 获取：职务
	 */
	public String getPost() {
		return post;
	}
  	
  	/**
	 * 设置：员工状态(1在职，2:离职)
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：员工状态(1在职，2:离职)
	 */
	public Integer getState() {
		return state;
	}
  	
  	/**
	 * 设置：电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话
	 */
	public String getPhone() {
		return phone;
	}
  	
  	/**
	 * 设置：入职日期
	 */
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	/**
	 * 获取：入职日期
	 */
	public Date getInDate() {
		return inDate;
	}
  	
  	/**
	 * 设置：转正日期
	 */
	public void setFormalDate(Date formalDate) {
		this.formalDate = formalDate;
	}
	/**
	 * 获取：转正日期
	 */
	public Date getFormalDate() {
		return formalDate;
	}
  	
  	/**
	 * 设置：工作时间
	 */
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	/**
	 * 获取：工作时间
	 */
	public Date getWorkDate() {
		return workDate;
	}
  	
  	/**
	 * 设置：qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * 获取：qq
	 */
	public String getQq() {
		return qq;
	}
  	
  	/**
	 * 设置：性别
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public Integer getSex() {
		return sex;
	}
  	
  	/**
	 * 设置：生日
	 */
	public void setBirthdayDay(Date birthdayDay) {
		this.birthdayDay = birthdayDay;
	}
	/**
	 * 获取：生日
	 */
	public Date getBirthdayDay() {
		return birthdayDay;
	}
  	
  	/**
	 * 设置：是否结婚
	 */
	public void setHasMarry(Integer hasMarry) {
		this.hasMarry = hasMarry;
	}
	/**
	 * 获取：是否结婚
	 */
	public Integer getHasMarry() {
		return hasMarry;
	}
  	
  	/**
	 * 设置：政治面貌
	 */
	public void setPoliceFace(String policeFace) {
		this.policeFace = policeFace;
	}
	/**
	 * 获取：政治面貌
	 */
	public String getPoliceFace() {
		return policeFace;
	}
  	
  	/**
	 * 设置：户口性质
	 */
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	/**
	 * 获取：户口性质
	 */
	public String getHouseType() {
		return houseType;
	}
  	
  	/**
	 * 设置：学校
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	/**
	 * 获取：学校
	 */
	public String getSchool() {
		return school;
	}
  	
  	/**
	 * 设置：学历
	 */
	public void setEducate(String educate) {
		this.educate = educate;
	}
	/**
	 * 获取：学历
	 */
	public String getEducate() {
		return educate;
	}
  	
  	/**
	 * 设置：专业
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * 获取：专业
	 */
	public String getMajor() {
		return major;
	}
  	
  	/**
	 * 设置：名族
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}
	/**
	 * 获取：名族
	 */
	public String getNation() {
		return nation;
	}
  	
  	/**
	 * 设置：身份证地址
	 */
	public void setIdCardAddr(String idCardAddr) {
		this.idCardAddr = idCardAddr;
	}
	/**
	 * 获取：身份证地址
	 */
	public String getIdCardAddr() {
		return idCardAddr;
	}
  	
  	/**
	 * 设置：联系地址
	 */
	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}
	/**
	 * 获取：联系地址
	 */
	public String getContactAddr() {
		return contactAddr;
	}
  	
  	/**
	 * 设置：紧急联系人
	 */
	public void setUrgentName(String urgentName) {
		this.urgentName = urgentName;
	}
	/**
	 * 获取：紧急联系人
	 */
	public String getUrgentName() {
		return urgentName;
	}
  	
  	/**
	 * 设置：紧急联系电话
	 */
	public void setUrgentPhone(String urgentPhone) {
		this.urgentPhone = urgentPhone;
	}
	/**
	 * 获取：紧急联系电话
	 */
	public String getUrgentPhone() {
		return urgentPhone;
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
