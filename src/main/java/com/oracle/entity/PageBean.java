package com.oracle.entity;

import java.util.List;

//ʹ�÷���ģ��,��߳����ͨ����
public class PageBean<T> {

	// ��ǰ��ҳ��
	private int page;
	// ������
	private int rowcount;
	// ÿҳ��ʾ��
	private int pagesize;
	// ��ǰҳ������
	private List<T> list;
	// �ܵ�ҳ��
	private int pages;//�ܵ�ҳ��=������/pagesize   ����10/5 = 2  ������11/5 + 1 = 3

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRowcount() {
		return rowcount;
	}

	public void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPages() {
		return pages;
	}

	// ��ȡ�ܵ�ҳ��
	public void setPages(int pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "PageBean [page=" + page + ", rowcount=" + rowcount + ", pagesize=" + pagesize + ", list=" + list
				+ ", pages=" + pages + "]";
	}

}
