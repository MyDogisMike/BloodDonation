package com.donation.vo;

import java.util.HashMap;
import java.util.List;

public class LayUIVO extends HashMap<String, Object>{
	private static final long serialVersionUID = 1L;

	public static LayUIVO data(Integer count,List<?> data){
		LayUIVO r = new LayUIVO();
        r.put("code", 0);
        r.put("msg", "");
        r.put("count", count);
        r.put("data", data);
        return r;
    }

}
