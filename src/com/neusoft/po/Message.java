package com.neusoft.po;

import java.io.Serializable;


public class Message implements Serializable {
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
	 *  @Fields Phone : phone
	 * 
	 * */
	private String phone;
	/** 
	 *  @Fields Content : content
	 * 
	 * */
	private String content;

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
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	
    public Message() {
		
	}

	public Message(Integer id ,String name ,String phone ,String content ){
	super();
	this.id=id;
	this.name=name;
	this.phone=phone;
	this.content=content;
	}
	
	@Override
	public String toString() {
		return "Message [id="+ id + ",name="+ name + ",phone="+ phone + ",content="+ content +  "]";
	}


}

