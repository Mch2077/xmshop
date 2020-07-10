package com.oracle.entity;
//封装公告数据的实体类
public class Note {
  private Integer id; 
  private String title; //标题
  private String content; //内容
  private String publisher; //作者
  private java.util.Date publishdate; //发布日期
    
    public Note() {
		super();
	}
    public Note(Integer id,String title,String content,String publisher,java.util.Date publishdate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.publisher = publisher;
		this.publishdate = publishdate;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/**
	 * @return the publishdate
	 */
	public java.util.Date getPublishdate() {
		return publishdate;
	}
	/**
	 * @param publishdate the publishdate to set
	 */
	public void setPublishdate(java.util.Date publishdate) {
		this.publishdate = publishdate;
	}
    
    

}
