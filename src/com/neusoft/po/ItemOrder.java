package com.neusoft.po;
import java.util.*;
import java.io.Serializable;

public class ItemOrder implements Serializable {
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
	
	private User user;
	
	/** 
	 *  @Fields Code : code
	 * 
	 * */
	private String code;
	/** 
	 *  @Fields AddTime : addTime
	 * 
	 * */
	private java.util.Date addTime;
	/** 
	 *  @Fields Total : total
	 * 
	 * */
	private String total;
	/** 
	 *  @Fields IsDelete : isDelete
	 * 
	 * */
	private Integer isDelete;
	
	private List<OrderDetail> details;
	/** 
	 *  @Fields Status : 0.新建代发货1.已取消 2已已发货3.到收货
	 * 
	 * */
	private Integer status;

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
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public java.util.Date getAddTime() {
		return this.addTime;
	}
	
	public void setAddTime(java.util.Date addTime) {
		this.addTime = addTime;
	}	
	
	public String getTotal() {
		return this.total;
	}
	
	public void setTotal(String total) {
		this.total = total;
	}
	
	public Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
    public ItemOrder() {
		
	}

	public ItemOrder(Integer id ,Integer itemId ,Integer userId ,String code ,java.util.Date addTime ,String total ,Integer isDelete ,Integer status ){
	super();
	this.id=id;
	this.itemId=itemId;
	this.userId=userId;
	this.code=code;
	this.addTime=addTime;
	this.total=total;
	this.isDelete=isDelete;
	this.status=status;
	}
	
	@Override
	public String toString() {
		return "ItemOrder [id="+ id + ",itemId="+ itemId + ",userId="+ userId + ",code="+ code + ",addTime="+ addTime + ",total="+ total + ",isDelete="+ isDelete + ",status="+ status +  "]";
	}


	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}

