package com.donation.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpValidator {
	
	public static boolean isEmail( String str ) {
        //String regex = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}" ;
		String regex = "/^([0-9a-zA-Z._]|-)+@{1}([0-9a-zA-Z._]|-)+\\.{1}([0-9a-zA-Z._]|-)+$/";
        return match( regex ,str );
    }
	
	private static boolean match( String regex ,String str ){
        Pattern pattern = Pattern.compile(regex);
        Matcher  matcher = pattern.matcher( str );
        return matcher.matches();
    }
}
