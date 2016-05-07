package xiao.com.childrenintelligence.entity;

import java.io.Serializable;

public class HealthScan implements Serializable{

	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	private int id;
	private String HSsteps;
	private String HSheart;
	private String HSkaloria;
	private String HSsleepDeep;
	private String HSstepTime;
	private String HShealthScore;
	private String HShealthDate;
	private String phoneNum;
	private String HSstepsPer;
	private String HSratePer;
	private String HSdeepPer;
	private String newT;
	
	private String higPre;
	private String lowPre;
	private String befSugar;
	private String afterSugar;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTest() {
		return newT;
	}
	public void setTest(String test) {
		this.newT = test;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}
	public String getHSsteps() {
		return HSsteps;
	}
	public void setHSsteps(String hSsteps) {
		HSsteps = hSsteps;
	}
	public String getHSheart() {
		return HSheart;
	}
	public void setHSheart(String hSheart) {
		HSheart = hSheart;
	}
	public String getHSkaloria() {
		return HSkaloria;
	}
	public void setHSkaloria(String hSkaloria) {
		HSkaloria = hSkaloria;
	}
	public String getHSsleepDeep() {
		return HSsleepDeep;
	}
	public void setHSsleepDeep(String hSsleepDeep) {
		HSsleepDeep = hSsleepDeep;
	}
	public String getHSstepTime() {
		return HSstepTime;
	}
	public void setHSstepTime(String hSstepTime) {
		HSstepTime = hSstepTime;
	}
	public String getHShealthScore() {
		return HShealthScore;
	}
	public void setHShealthScore(String hShealthScore) {
		HShealthScore = hShealthScore;
	}
	public String getHShealthDate() {
		return HShealthDate;
	}
	public void setHShealthDate(String hShealthDate) {
		HShealthDate = hShealthDate;
	}
	public String getHSstepsPer() {
		return HSstepsPer;
	}
	public void setHSstepsPer(String hSstepsPer) {
		HSstepsPer = hSstepsPer;
	}
	public String getHSratePer() {
		return HSratePer;
	}
	public void setHSratePer(String hSratePer) {
		HSratePer = hSratePer;
	}
	public String getHSdeepPer() {
		return HSdeepPer;
	}
	public void setHSdeepPer(String hSdeepPer) {
		HSdeepPer = hSdeepPer;
	}
	public String getNewT() {
		return newT;
	}
	public void setNewT(String newT) {
		this.newT = newT;
	}
	public String getHigPre() {
		return higPre;
	}
	public void setHigPre(String higPre) {
		this.higPre = higPre;
	}
	public String getLowPre() {
		return lowPre;
	}
	public void setLowPre(String lowPre) {
		this.lowPre = lowPre;
	}
	public String getBefSugar() {
		return befSugar;
	}
	public void setBefSugar(String befSugar) {
		this.befSugar = befSugar;
	}
	public String getAfterSugar() {
		return afterSugar;
	}
	public void setAfterSugar(String afterSugar) {
		this.afterSugar = afterSugar;
	}
	
	
}
