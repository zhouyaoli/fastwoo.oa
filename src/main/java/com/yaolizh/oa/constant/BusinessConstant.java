package com.yaolizh.oa.constant;

import com.yaolizh.fastwoo.common.utils.StringUtils;

public class BusinessConstant {
	//项目状态(1:未立项，2立项成立，4：结束)
	public static enum ProjectState {
		noStand(
				1, "1", "未立项"),
		stand(
				2, "2", "已立项"),
		close(
				4, "4", "已关闭");

		private int key;
		private String code;
		private String name;

		// 构造方法
		private ProjectState(final int key, final String code, final String name) {
			this.key = key;
			this.code = code;
			this.name = name;
		}

		// 根据key获取枚举
		public static ProjectState getByKey(Integer key) {
			if (StringUtils.isNull(key)) {
				return stand;
			}
			for (ProjectState temp : ProjectState.values()) {
				if (temp.key == key) {
					return temp;
				}
			}
			return stand;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	//	付款方式(1：阶段付款；2：比例付款；3：全额付款)
	public static enum ContractPayWay {
		stage(
				1, "1", "阶段付款"),
		ratio(
				2, "2", "比例付款"),
		full(
				3, "3", "全额付款");

		private int key;
		private String code;
		private String name;

		// 构造方法
		private ContractPayWay(final int key, final String code, final String name) {
			this.key = key;
			this.code = code;
			this.name = name;
		}

		// 根据key获取枚举
		public static ContractPayWay getByKey(Integer key) {
			if (StringUtils.isNull(key)) {
				return full;
			}
			for (ContractPayWay temp : ContractPayWay.values()) {
				if (temp.key == key) {
					return temp;
				}
			}
			return full;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	//	开票类型（1：增值税发票，2：普通发票）
	public static enum BillType {
		addValue(
				1, "1", "增值税发票"),
		normal(
				2, "2", "普通发票");

		private int key;
		private String code;
		private String name;

		// 构造方法
		private BillType(final int key, final String code, final String name) {
			this.key = key;
			this.code = code;
			this.name = name;
		}

		// 根据key获取枚举
		public static BillType getByKey(Integer key) {
			if (StringUtils.isNull(key)) {
				return normal;
			}
			for (BillType temp : BillType.values()) {
				if (temp.key == key) {
					return temp;
				}
			}
			return normal;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	//	合同类型(1:收入合同，2:支出合同)
	public static enum ContractType {
		inPay(
				1, "1", "收入合同"),
		outPay(
				2, "2", "支出合同");

		private int key;
		private String code;
		private String name;

		// 构造方法
		private ContractType(final int key, final String code, final String name) {
			this.key = key;
			this.code = code;
			this.name = name;
		}

		// 根据key获取枚举
		public static ContractType getByKey(Integer key) {
			if (StringUtils.isNull(key)) {
				return inPay;
			}
			for (ContractType temp : ContractType.values()) {
				if (temp.key == key) {
					return temp;
				}
			}
			return inPay;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

}
