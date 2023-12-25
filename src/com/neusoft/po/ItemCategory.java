package com.neusoft.po;
import java.util.*;
import java.io.Serializable;


public class ItemCategory implements Serializable {
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
	 *  @Fields Pid : pid
	 * 
	 * */
	private Integer pid;
	/** 
	 *  @Fields IsDelete : isDelete
	 * 
	 * */
	private Integer isDelete;

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
	
	public Integer getPid() {
		return this.pid;
	}
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	public Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
    public ItemCategory() {
		
	}

	public ItemCategory(Integer id ,String name ,Integer pid ,Integer isDelete ){
	super();
	this.id=id;
	this.name=name;
	this.pid=pid;
	this.isDelete=isDelete;
	}
	
	@Override
	public String toString() {
		return "ItemCategory [id="+ id + ",name="+ name + ",pid="+ pid + ",isDelete="+ isDelete +  "]";
	}


}

