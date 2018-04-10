package com.donation.constant;

public enum BloodTypeEnum {
	O(0, "O型血"),
	A(1, "A型血"),
	B(2, "B型血"),
	AB(3, "AB型血"),
	RARE(4, "稀有血型"),
	SECRET(5, "保密");
	
	private Integer value; // 枚举值
	private String label; // 显示值
	
	private BloodTypeEnum(Integer value, String label){
		this.value = value;
		this.label = label;
	}
	
	public static String getLabelByValue(Integer val) {
		String lab = "";
		for (BloodTypeEnum e : BloodTypeEnum.values()) {
			if (e.getValue() == val) {
				lab = e.getLabel();
				break;
			}
		}
		return lab;
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
