package com.arun.model;

import java.util.List;

public class Order {

	public Integer oid;
	public Integer totalItems;
	public Double totalBill;
	public String address;
	public List<Item> items;
	

	public Order(Integer oid, Integer totalItems, Double totalBill, String address, List<Item> items) {
		super();
		this.oid = oid;
		this.totalItems = totalItems;
		this.totalBill = totalBill;
		this.address = address;
		this.items = items;
		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

	public Double getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(Double totalBill) {
		this.totalBill = totalBill;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", totalItems=" + totalItems + ", totalBill=" + totalBill + ", address=" + address
				+ ", items=" + items + "]";
	}
	
	

}
