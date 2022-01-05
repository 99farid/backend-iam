package com.lawencon.assetsmanagement.dto.items;

import java.math.BigDecimal;

public class GetItemResTotalPriceDto {

	private BigDecimal data;

	private String msg;

	public BigDecimal getData() {
		return data;
	}

	public void setData(BigDecimal data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}