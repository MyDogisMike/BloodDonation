package com.donation.constant;

public enum ActivityStatusEnum {
	PREPARE(0, "准备阶段"),
	PROGRESS(1, "进行中"),
	END(2, "结束");
	
	private Integer value; // 枚举值
	private String label; // 显示值
	
	private ActivityStatusEnum(Integer value, String label){
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
