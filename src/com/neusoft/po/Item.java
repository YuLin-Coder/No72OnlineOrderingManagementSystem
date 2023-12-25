package com.neusoft.po;
import java.util.*;
import java.io.Serializable;


public class Item implements Serializable {
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
	 *  @Fields Price : price
	 * 
	 * */
	private String price;
	/** 
	 *  @Fields ScNum : 收藏数
	 * 
	 * */
	private Integer scNum;
	/** 
	 *  @Fields GmNum : 购买数
	 * 
	 * */
	private Integer gmNum;
	/** 
	 *  @Fields Url1 : url1
	 * 
	 * */
	private String url1;
	/** 
	 *  @Fields Url2 : url2
	 * 
	 * */
	private String url2;
	/** 
	 *  @Fields Url3 : url3
	 * 
	 * */
	private String url3;
	/** 
	 *  @Fields Url4 : url4
	 * 
	 * */
	private String url4;
	/** 
	 *  @Fields Url5 : url5
	 * 
	 * */
	private String url5;
	/** 
	 *  @Fields Ms : ms
	 * 
	 * */
	private String ms;
	/** 
	 *  @Fields Pam1 : 作者
	 * 
	 * */
	private String pam1;
	/** 
	 *  @Fields Pam2 : 出版社
	 * 
	 * */
	private String pam2;
	/** 
	 *  @Fields Pam3 : 出版时间
	 * 
	 * */
	private String pam3;
	/** 
	 *  @Fields Type : 
	 * 
	 * */
	private Integer type;
	
	
	private String val3;
	/** 
	 *  @Fields Val2 : 值2
	 * 
	 * */
	private String val2;
	/** 
	 *  @Fields Val1 : 值1
	 * 
	 * */
	private String val1;
	/** 
	 *  @Fields Zk : 折扣
	 * 
	 * */
	private Integer zk;
	/** 
	 *  @Fields CategoryIdOne : 类别id
	 * 
	 * */
	private ItemCategory yiji;
	
	
	private Integer categoryIdOne;
	/** 
	 *  @Fields CategoryIdTwo : 类别2级
	 * 
	 * */
	private Integer categoryIdTwo;
	
	private ItemCategory erji;
	
	
	
	/** 
	 *  @Fields IsDelete : 0否 1是
	 * 
	 * */
	private Integer isDelete;
	
	
	private List<Comment> pls;
	

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
	
	public String getPrice() {
		return this.price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public Integer getScNum() {
		return this.scNum;
	}
	
	public void setScNum(Integer scNum) {
		this.scNum = scNum;
	}
	
	public Integer getGmNum() {
		return this.gmNum;
	}
	
	public void setGmNum(Integer gmNum) {
		this.gmNum = gmNum;
	}
	
	public String getUrl1() {
		return this.url1;
	}
	
	public void setUrl1(String url1) {
		this.url1 = url1;
	}
	
	public String getUrl2() {
		return this.url2;
	}
	
	public void setUrl2(String url2) {
		this.url2 = url2;
	}
	
	public String getUrl3() {
		return this.url3;
	}
	
	public void setUrl3(String url3) {
		this.url3 = url3;
	}
	
	public String getUrl4() {
		return this.url4;
	}
	
	public void setUrl4(String url4) {
		this.url4 = url4;
	}
	
	public String getUrl5() {
		return this.url5;
	}
	
	public void setUrl5(String url5) {
		this.url5 = url5;
	}
	
	public String getMs() {
		return this.ms;
	}
	
	public void setMs(String ms) {
		this.ms = ms;
	}
	
	public String getPam1() {
		return this.pam1;
	}
	
	public void setPam1(String pam1) {
		this.pam1 = pam1;
	}
	
	public String getPam2() {
		return this.pam2;
	}
	
	public void setPam2(String pam2) {
		this.pam2 = pam2;
	}
	
	public String getPam3() {
		return this.pam3;
	}
	
	public void setPam3(String pam3) {
		this.pam3 = pam3;
	}
	
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
    public Item() {
		
	}

	public String getVal3() {
		return val3;
	}

	public void setVal3(String val3) {
		this.val3 = val3;
	}

	public String getVal2() {
		return val2;
	}

	public void setVal2(String val2) {
		this.val2 = val2;
	}

	public String getVal1() {
		return val1;
	}

	public void setVal1(String val1) {
		this.val1 = val1;
	}

	public Integer getZk() {
		return zk;
	}

	public void setZk(Integer zk) {
		this.zk = zk;
	}

	public Integer getCategoryIdOne() {
		return categoryIdOne;
	}

	public void setCategoryIdOne(Integer categoryIdOne) {
		this.categoryIdOne = categoryIdOne;
	}

	public Integer getCategoryIdTwo() {
		return categoryIdTwo;
	}

	public void setCategoryIdTwo(Integer categoryIdTwo) {
		this.categoryIdTwo = categoryIdTwo;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", scNum=" + scNum + ", gmNum=" + gmNum
				+ ", url1=" + url1 + ", url2=" + url2 + ", url3=" + url3 + ", url4=" + url4 + ", url5=" + url5 + ", ms="
				+ ms + ", pam1=" + pam1 + ", pam2=" + pam2 + ", pam3=" + pam3 + ", type=" + type + ", val3=" + val3
				+ ", val2=" + val2 + ", val1=" + val1 + ", zk=" + zk + ", categoryIdOne=" + categoryIdOne
				+ ", categoryIdTwo=" + categoryIdTwo + ", isDelete=" + isDelete + "]";
	}

	public ItemCategory getErji() {
		return erji;
	}

	public void setErji(ItemCategory erji) {
		this.erji = erji;
	}

	public ItemCategory getYiji() {
		return yiji;
	}

	public void setYiji(ItemCategory yiji) {
		this.yiji = yiji;
	}

	public List<Comment> getPls() {
		return pls;
	}

	public void setPls(List<Comment> pls) {
		this.pls = pls;
	}



}

