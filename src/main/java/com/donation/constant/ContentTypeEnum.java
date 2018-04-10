package com.donation.constant;


/**
 * 约定 状态 枚举
 */
public enum ContentTypeEnum {
	character(1, "文字"),
	picture(2, "图片");
	

	private Integer value; // 枚举值
	private String label; // 显示值

	private ContentTypeEnum(Integer value, String label) {
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
