package com.donation.vo;

public class Email {
	private String addressee;

	private String subject;

	private String content;
	/**
	 * 解析邮件地址
	 * @return
	 */
	public String[] getAddress() {
		if (this.addressee == null || "".equals(this.addressee)) {
			return null;
		}
		addressee = addressee.trim();
		addressee.replaceAll("；", ";");
		addressee.replaceAll(" ", ";");
		addressee.replaceAll(",", ";");
		addressee.replaceAll("，", ";");
		addressee.replaceAll("|", ";");
		return addressee.split(";");
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
