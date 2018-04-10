package com.donation.constant;

public enum GenderEnum {
	
	secret(0, "保密"),
	man(1, "男"),
	women(2, "女");
	
	private Integer value; // 枚举值
	private String label; // 显示值
	
	private GenderEnum(Integer value, String label){
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
