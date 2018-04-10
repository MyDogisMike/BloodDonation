package com.donation.utils;

public class Test {
	public static void main(String[] args) {
		String a = "a\"bc\":jcde";
		System.out.println(a.replaceAll("\"", ""));
		System.out.println(a);
		StringBuffer text = new StringBuffer();
		System.out.println(text.length());
		double d = (9+0.0) / 21;
		System.out.println(d);
		System.out.println(Math.ceil(d));
	}
}
