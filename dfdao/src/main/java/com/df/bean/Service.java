package com.df.bean;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Service {
	
	@Id
	private String serviceId;
	
	private String serviceName;
	
	private List<Item> item;
	
	private List<Para> para;
	
	private String ctId;

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public List<Para> getPara() {
		return para;
	}

	public void setPara(List<Para> para) {
		this.para = para;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getCtId() {
		return ctId;
	}

	public void setCtId(String ctId) {
		this.ctId = ctId;
	}
}
