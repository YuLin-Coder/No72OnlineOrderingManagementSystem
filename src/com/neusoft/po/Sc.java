package com.neusoft.po;
import java.util.*;
import java.io.Serializable;


public class Sc implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields ItemId : itemId
	 * 
	 * */
	private Integer itemId;
	
	private Item item;
	
	/** 
	 *  @Fields UserId : userId
	 * 
	 * */
	private Integer userId;

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getItemId() {
		return this.itemId;
	}
	
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
    public Sc() {
		
	}

	public Sc(Integer id ,Integer itemId ,Integer userId ){
	super();
	this.id=id;
	this.itemId=itemId;
	this.userId=userId;
	}
	
	@Override
	public String toString() {
		return "Sc [id="+ id + ",itemId="+ itemId + ",userId="+ userId +  "]";
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}


}

