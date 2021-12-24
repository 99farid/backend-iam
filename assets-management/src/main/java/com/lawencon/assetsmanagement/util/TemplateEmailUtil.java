package com.lawencon.assetsmanagement.util;

import java.util.HashMap;
import java.util.Map;

import com.lawencon.assetsmanagement.exception.ValidationIamException;

public class TemplateEmailUtil {
	private String[] keyArr; 
	private Object[] valueArr;
	
	public String replacteTextTemplate(String text, Map<String, Object> replace) {
		for(String i : replace.keySet()) {
			text = text.replaceAll(i, replace.get(i).toString());
		}
		return text;
	}
	
	public Map<String, Object> build() throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		if(keyArr.length != valueArr.length) {
			throw new ValidationIamException("Key and Value not same");
		}
		for(int i = 0; i<keyArr.length;i++) {
			result.put(keyArr[i], valueArr[i]);
		}
		return result;
	}
	public TemplateEmailUtil setKey(String... keys) {
		keyArr = keys;
		return this;
	}
	public TemplateEmailUtil setValue(Object... values) {
		valueArr = values;
		return this;
	}
}
