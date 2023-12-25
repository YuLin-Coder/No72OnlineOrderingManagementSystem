package com.neusoft.po;
import java.util.*;
import java.io.Serializable;


public class Manage implements Serializable {
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
	 *  @Fields RealName : realName
	 * 
	 * */
	private String realName;

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
	
	public String getRealName() {
		return this.realName;
	}
	
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	
    public Manage() {
		
	}

	public Manage(Integer id ,String userName ,String passWord ,String realName ){
	super();
	this.id=id;
	this.userName=userName;
	this.passWord=passWord;
	this.realName=realName;
	}
	
	@Override
	public String toString() {
		return "Manage [id="+ id + ",userName="+ userName + ",passWord="+ passWord + ",realName="+ realName +  "]";
	}


}

