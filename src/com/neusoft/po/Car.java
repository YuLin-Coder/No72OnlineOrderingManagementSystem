package com.neusoft.po;
import java.util.*;
import java.io.Serializable;


public class Car implements Serializable {
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
	/** 
	 *  @Fields UserId : userId
	 * 
	 * */
	private Integer userId;
	/** 
	 *  @Fields Num : num
	 * 
	 * */
	private Integer num;
	/** 
	 *  @Fields Total : total
	 * 
	 * */
	private String total;
	
	private double price;
	
	
	
	private Item item;
	

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
	
	public Integer getNum() {
		return this.num;
	}
	
	public void setNum(Integer num) {
		this.num = num;
	}
	
	public String getTotal() {
		return this.total;
	}
	
	public void setTotal(String total) {
		this.total = total;
	}
	
	
    public Car() {
		
	}

	public Car(Integer id ,Integer itemId ,Integer userId ,Integer num ,String total ){
	super();
	this.id=id;
	this.itemId=itemId;
	this.userId=userId;
	this.num=num;
	this.total=total;
	}
	
	@Override
	public String toString() {
		return "Car [id="+ id + ",itemId="+ itemId + ",userId="+ userId + ",num="+ num + ",total="+ total +  "]";
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


}

