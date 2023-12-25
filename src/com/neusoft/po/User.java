package com.neusoft.po;
import java.util.*;
import java.io.Serializable;


public class User implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields UserName : userName
	 * 
	 * */
	private String userName;
	/** 
	 *  @Fields PassWord : passWord
	 * 
	 * */
	private String passWord;
	/** 
	 *  @Fields Phone : phone
	 * 
	 * */
	private String phone;
	/** 
	 *  @Fields RealName : realName
	 * 
	 * */
	private String realName;
	/** 
	 *  @Fields Sex : sex
	 * 
	 * */
	private String sex;
	/** 
	 *  @Fields Address : address
	 * 
	 * */
	private String address;
	/** 
	 *  @Fields Email : email
	 * 
	 * */
	private String email;

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassWord() {
		return this.passWord;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getRealName() {
		return this.realName;
	}
	
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getSex() {
		return this.sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
    public User() {
		
	}

	public User(Integer id ,String userName ,String passWord ,String phone ,String realName ,String sex ,String address ,String email ){
	super();
	this.id=id;
	this.userName=userName;
	this.passWord=passWord;
	this.phone=phone;
	this.realName=realName;
	this.sex=sex;
	this.address=address;
	this.email=email;
	}
	
	@Override
	public String toString() {
		return "User [id="+ id + ",userName="+ userName + ",passWord="+ passWord + ",phone="+ phone + ",realName="+ realName + ",sex="+ sex + ",address="+ address + ",email="+ email +  "]";
	}


}

