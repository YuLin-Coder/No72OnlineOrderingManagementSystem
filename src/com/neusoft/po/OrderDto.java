package com.neusoft.po;

import java.util.List;

public class OrderDto {

	private List<Integer> ids;
	
	private Integer addressId;
	
	private String total;
	
	

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	
	
}
