package com.yaolizh.oa.car.domain;

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
 * 车辆信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:16:00
 */
@Table(name="t_car")
@Entity
public class CarDO extends SuperBaseData {
	 
 
	/** 公共字段在父类中继承  */
			/**主键  在父类中公用 */
			   	
	   	/**状态(1:可以使用,2:使用中,4:保修,8:报废)*/
	  	  		@Column(name ="status")
		private Integer status;
			   	
	   	/**车牌号码*/
	  	  		@Column(name ="car_num")
		private String carNum;
			   	
	   	/**使用人*/
	  	  		@Column(name ="use_person")
		private String usePerson;
			   	
	   	/**开始使用时间*/
	  	  		@JsonFormat(pattern = DateUtils._DATETIMES_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATETIMES_DEFAULT)  
	  		@Column(name ="begin_use_time")
		private Date beginUseTime;
			   	
	   	/**号牌种类(1:小型汽车号牌，2:大型汽车号牌，4:普通摩托车号牌，8:轻便摩托车号牌，16:低速车号牌，32:挂车号牌)*/
	  	  		@Column(name ="car_num_type")
		private Integer carNumType;
			   	
	   	/**变速箱类别（1:手动挡、2：自动档，4：手自一体）*/
	  	  		@Column(name ="car_type")
		private Integer carType;
			   	
	   	/**车身颜色(1:黑,2:白,4:红,8:蓝)*/
	  	  		@Column(name ="car_num_color")
		private String carNumColor;
			   	
	   	/**车身颜色(1:黑,2:白,4:红,8:蓝)*/
	  	  		@Column(name ="body_color")
		private String bodyColor;
			   	
	   	/**车架号*/
	  	  		@Column(name ="vin")
		private String vin;
			   	
	   	/**厂牌型号*/
	  	  		@Column(name ="factory_model")
		private String factoryModel;
			   	
	   	/**购买时间*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="buy_date")
		private Date buyDate;
			   	
	   	/**购买金额*/
	  	  		@Column(name ="buy_amount")
		private BigDecimal buyAmount;
			   	
	   	/**是否新车*/
	  	  		@Column(name ="has_new")
		private Integer hasNew;
			   	
	   	/**发动机号*/
	  	  		@Column(name ="engine_no")
		private String engineNo;
			   	
	   	/**发动机型号*/
	  	  		@Column(name ="engine_model")
		private String engineModel;
			   	
	   	/**制动方式（1液压制动2气压制动）*/
	  	  		@Column(name ="brake_mode")
		private Integer brakeMode;
			   	
	   	/**载人数*/
	  	  		@Column(name ="manned")
		private Integer manned;
			   	
	   	/**是否乘用（1乘用2非乘用）*/
	  	  		@Column(name ="has_manned")
		private Integer hasManned;
			   	
	   	/**里程表读数*/
	  	  		@Column(name ="mileage")
		private Integer mileage;
			   	
	   	/**燃油种类(1:92,2:95,4:柴油:,8:纯电,16:油电混动)*/
	  	  		@Column(name ="oil_type")
		private Integer oilType;
			   	
	   	/**整车长*/
	  	  		@Column(name ="car_len")
		private BigDecimal carLen;
			   	
	   	/**整车宽*/
	  	  		@Column(name ="car_width")
		private BigDecimal carWidth;
			   	
	   	/**整车高*/
	  	  		@Column(name ="car_height")
		private BigDecimal carHeight;
			   	
	   	/**出厂日期*/
	  		@JsonFormat(pattern = DateUtils._DATE_DEFAULT, timezone = "GMT+8")
		@DateTimeFormat(pattern=DateUtils._DATE_DEFAULT)  
	  	  		@Column(name ="product_date")
		private Date productDate;
	
	
  		/**主键  在父类中公用 */
  	
  	/**
	 * 设置：状态(1:可以使用,2:使用中,4:保修,8:报废)
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态(1:可以使用,2:使用中,4:保修,8:报废)
	 */
	public Integer getStatus() {
		return status;
	}
  	
  	/**
	 * 设置：车牌号码
	 */
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	/**
	 * 获取：车牌号码
	 */
	public String getCarNum() {
		return carNum;
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
	 * 设置：号牌种类(1:小型汽车号牌，2:大型汽车号牌，4:普通摩托车号牌，8:轻便摩托车号牌，16:低速车号牌，32:挂车号牌)
	 */
	public void setCarNumType(Integer carNumType) {
		this.carNumType = carNumType;
	}
	/**
	 * 获取：号牌种类(1:小型汽车号牌，2:大型汽车号牌，4:普通摩托车号牌，8:轻便摩托车号牌，16:低速车号牌，32:挂车号牌)
	 */
	public Integer getCarNumType() {
		return carNumType;
	}
  	
  	/**
	 * 设置：变速箱类别（1:手动挡、2：自动档，4：手自一体）
	 */
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	/**
	 * 获取：变速箱类别（1:手动挡、2：自动档，4：手自一体）
	 */
	public Integer getCarType() {
		return carType;
	}
  	
  	/**
	 * 设置：车身颜色(1:黑,2:白,4:红,8:蓝)
	 */
	public void setCarNumColor(String carNumColor) {
		this.carNumColor = carNumColor;
	}
	/**
	 * 获取：车身颜色(1:黑,2:白,4:红,8:蓝)
	 */
	public String getCarNumColor() {
		return carNumColor;
	}
  	
  	/**
	 * 设置：车身颜色(1:黑,2:白,4:红,8:蓝)
	 */
	public void setBodyColor(String bodyColor) {
		this.bodyColor = bodyColor;
	}
	/**
	 * 获取：车身颜色(1:黑,2:白,4:红,8:蓝)
	 */
	public String getBodyColor() {
		return bodyColor;
	}
  	
  	/**
	 * 设置：车架号
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
	/**
	 * 获取：车架号
	 */
	public String getVin() {
		return vin;
	}
  	
  	/**
	 * 设置：厂牌型号
	 */
	public void setFactoryModel(String factoryModel) {
		this.factoryModel = factoryModel;
	}
	/**
	 * 获取：厂牌型号
	 */
	public String getFactoryModel() {
		return factoryModel;
	}
  	
  	/**
	 * 设置：购买时间
	 */
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	/**
	 * 获取：购买时间
	 */
	public Date getBuyDate() {
		return buyDate;
	}
  	
  	/**
	 * 设置：购买金额
	 */
	public void setBuyAmount(BigDecimal buyAmount) {
		this.buyAmount = buyAmount;
	}
	/**
	 * 获取：购买金额
	 */
	public BigDecimal getBuyAmount() {
		return buyAmount;
	}
  	
  	/**
	 * 设置：是否新车
	 */
	public void setHasNew(Integer hasNew) {
		this.hasNew = hasNew;
	}
	/**
	 * 获取：是否新车
	 */
	public Integer getHasNew() {
		return hasNew;
	}
  	
  	/**
	 * 设置：发动机号
	 */
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	/**
	 * 获取：发动机号
	 */
	public String getEngineNo() {
		return engineNo;
	}
  	
  	/**
	 * 设置：发动机型号
	 */
	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}
	/**
	 * 获取：发动机型号
	 */
	public String getEngineModel() {
		return engineModel;
	}
  	
  	/**
	 * 设置：制动方式（1液压制动2气压制动）
	 */
	public void setBrakeMode(Integer brakeMode) {
		this.brakeMode = brakeMode;
	}
	/**
	 * 获取：制动方式（1液压制动2气压制动）
	 */
	public Integer getBrakeMode() {
		return brakeMode;
	}
  	
  	/**
	 * 设置：载人数
	 */
	public void setManned(Integer manned) {
		this.manned = manned;
	}
	/**
	 * 获取：载人数
	 */
	public Integer getManned() {
		return manned;
	}
  	
  	/**
	 * 设置：是否乘用（1乘用2非乘用）
	 */
	public void setHasManned(Integer hasManned) {
		this.hasManned = hasManned;
	}
	/**
	 * 获取：是否乘用（1乘用2非乘用）
	 */
	public Integer getHasManned() {
		return hasManned;
	}
  	
  	/**
	 * 设置：里程表读数
	 */
	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}
	/**
	 * 获取：里程表读数
	 */
	public Integer getMileage() {
		return mileage;
	}
  	
  	/**
	 * 设置：燃油种类(1:92,2:95,4:柴油:,8:纯电,16:油电混动)
	 */
	public void setOilType(Integer oilType) {
		this.oilType = oilType;
	}
	/**
	 * 获取：燃油种类(1:92,2:95,4:柴油:,8:纯电,16:油电混动)
	 */
	public Integer getOilType() {
		return oilType;
	}
  	
  	/**
	 * 设置：整车长
	 */
	public void setCarLen(BigDecimal carLen) {
		this.carLen = carLen;
	}
	/**
	 * 获取：整车长
	 */
	public BigDecimal getCarLen() {
		return carLen;
	}
  	
  	/**
	 * 设置：整车宽
	 */
	public void setCarWidth(BigDecimal carWidth) {
		this.carWidth = carWidth;
	}
	/**
	 * 获取：整车宽
	 */
	public BigDecimal getCarWidth() {
		return carWidth;
	}
  	
  	/**
	 * 设置：整车高
	 */
	public void setCarHeight(BigDecimal carHeight) {
		this.carHeight = carHeight;
	}
	/**
	 * 获取：整车高
	 */
	public BigDecimal getCarHeight() {
		return carHeight;
	}
  	
  	/**
	 * 设置：出厂日期
	 */
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	/**
	 * 获取：出厂日期
	 */
	public Date getProductDate() {
		return productDate;
	}
  	
}
