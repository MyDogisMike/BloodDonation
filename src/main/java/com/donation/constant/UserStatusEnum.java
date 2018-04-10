package com.donation.constant;


/**
 * 约定 状态 枚举
 */
public enum UserStatusEnum {
	disabled(0, "停用"), 
	usable(1, "可用"),
	inactive(2, "未激活"),
	activated(3, "已激活");
	

	private Integer value; // 枚举值
	private String label; // 显示值

	private UserStatusEnum(Integer value, String label) {
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
