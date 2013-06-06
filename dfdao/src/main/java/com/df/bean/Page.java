package com.df.bean;


import java.util.List;

public class Page {

	private Integer pageNo;
	private Integer pageSize;
	private Integer start;
	private Integer totalCount;
	private Integer totalPage;
	private List<?> list;

	public Page(Integer pageNo, Integer pageSize) {
		if(pageNo==null){
			pageNo=1;
		}
		if(pageSize==null){
			pageSize=20;
		}
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		start=(pageNo-1)*pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalPage=totalCount/pageSize+1;
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
	
}
