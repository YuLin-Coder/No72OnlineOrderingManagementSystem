package com.neusoft.po;

import java.io.Serializable;


public class News implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields Name : name
	 * 
	 * */
	private String name;
	/** 
	 *  @Fields Content : content
	 * 
	 * */
	private String content;
	/** 
	 *  @Fields AddTime : addTime
	 * 
	 * */
	private java.util.Date addTime;

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public java.util.Date getAddTime() {
		return this.addTime;
	}
	
	public void setAddTime(java.util.Date addTime) {
		this.addTime = addTime;
	}	
	
	
    public News() {
		
	}

	public News(Integer id ,String name ,String content ,java.util.Date addTime ){
	super();
	this.id=id;
	this.name=name;
	this.content=content;
	this.addTime=addTime;
	}
	
	@Override
	public String toString() {
		return "News [id="+ id + ",name="+ name + ",content="+ content + ",addTime="+ addTime +  "]";
	}


}

