package com.donation.constant;

public enum UserTypeEnumeration {
	superAdmin(0, "超级管理员"), 
	areaAdmin(1, "区域管理员"),
	user(2, "普通用户");
	

	private Integer value; // 枚举值
	private String label; // 显示值

	private UserTypeEnumeration(Integer value, String label) {
		this.value = value;
		this.label = label;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
