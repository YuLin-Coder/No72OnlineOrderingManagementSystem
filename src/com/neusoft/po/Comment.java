package com.neusoft.po;
import java.util.*;
import java.io.Serializable;


public class Comment implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields UserId : userId
	 * 
	 * */
	private Integer userId;
	
	private User user;
	
	/** 
	 *  @Fields ItemId : itemId
	 * 
	 * */
	private Integer itemId;
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
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getItemId() {
		return this.itemId;
	}
	
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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
	
	
    public Comment() {
		
	}

	public Comment(Integer id ,Integer userId ,Integer itemId ,String content ,java.util.Date addTime ){
	super();
	this.id=id;
	this.userId=userId;
	this.itemId=itemId;
	this.content=content;
	this.addTime=addTime;
	}
	
	@Override
	public String toString() {
		return "Comment [id="+ id + ",userId="+ userId + ",itemId="+ itemId + ",content="+ content + ",addTime="+ addTime +  "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}

