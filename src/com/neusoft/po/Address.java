package com.neusoft.po;
import java.util.*;
import java.io.Serializable;


public class Address implements Serializable {
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
	 *  @Fields Area : area
	 * 
	 * */
	private String area;
	/** 
	 *  @Fields Bm : bm
	 * 
	 * */
	private String bm;
	/** 
	 *  @Fields IsUse : isUse
	 * 
	 * */
	private Integer isUse;
	
	private Integer userId;

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
	
	public String getArea() {
		return this.area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getBm() {
		return this.bm;
	}
	
	public void setBm(String bm) {
		this.bm = bm;
	}
	
	public Integer getIsUse() {
		return this.isUse;
	}
	
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	
	
    public Address() {
		
	}

	public Address(Integer id ,String name ,String phone ,String area ,String bm ,Integer isUse ){
	super();
	this.id=id;
	this.name=name;
	this.phone=phone;
	this.area=area;
	this.bm=bm;
	this.isUse=isUse;
	}
	
	@Override
	public String toString() {
		return "Address [id="+ id + ",name="+ name + ",phone="+ phone + ",area="+ area + ",bm="+ bm + ",isUse="+ isUse +  "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}

