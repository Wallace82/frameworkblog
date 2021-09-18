package com.frameworkdigital.blog.page;


import java.util.List;

import org.springframework.data.domain.Page;

public class Paginacao {


	private int draw;
	private int  recordsTotal;
	private int recordsFiltered;
	private List<?> data ;
	private String search;
	private int start;
	

	
	public Paginacao() {
		super();
	}
	
	public Paginacao(Page<?> pagina, List<?> data) {
		super();
		this.draw = pagina.getNumber();
		this.recordsTotal = pagina.getNumberOfElements();
		this.recordsFiltered = (int)pagina.getTotalElements();
		this.data = data;
	}
	public Paginacao(Paginacao paginacao, List<?> data) {
		this.draw = paginacao.getDraw();
		this.recordsTotal = paginacao.getRecordsTotal();
		this.recordsFiltered = paginacao.getRecordsFiltered();
		this.data = data;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<?> getData() {
		return data;
	}

	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}


	
	
}
