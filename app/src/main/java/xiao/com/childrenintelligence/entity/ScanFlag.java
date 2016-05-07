package xiao.com.childrenintelligence.entity;

import java.io.Serializable;

public class ScanFlag implements Serializable{

	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	private int id;
	private int date;
	private String num;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
}
