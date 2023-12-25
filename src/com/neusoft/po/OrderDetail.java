package com.neusoft.po;
import java.util.*;
import java.io.Serializable;


public class OrderDetail implements Serializable {
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
	 *  @Fields OrderId : orderId
	 * 
	 * */
	private Integer orderId;
	/** 
	 *  @Fields Status : 0.未退货 1已退货
	 * 
	 * */
	private Integer status;
	
	private Integer num;
	
	private String total;
	
	

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

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
	
	public Integer getOrderId() {
		return this.orderId;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
    public OrderDetail() {
		
	}

	public OrderDetail(Integer id ,Integer itemId ,Integer orderId ,Integer status ){
	super();
	this.id=id;
	this.itemId=itemId;
	this.orderId=orderId;
	this.status=status;
	}
	
	@Override
	public String toString() {
		return "OrderDetail [id="+ id + ",itemId="+ itemId + ",orderId="+ orderId + ",status="+ status +  "]";
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}


}

