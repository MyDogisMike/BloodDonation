package com.donation.constant;

public enum ConsultStatusEnum {
	notReply(0, "未回复"), 
	reply(1, "已回复"),
	check(3, "已查看");
	

	private Integer value; // 枚举值
	private String label; // 显示值

	private ConsultStatusEnum(Integer value, String label) {
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
