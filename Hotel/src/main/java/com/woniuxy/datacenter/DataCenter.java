package com.woniuxy.datacenter;

public class DataCenter {
	//每日折扣
	private static  Double discount=1.0;

	public static Double getDiscount() {
		return discount;
	}

	public static void setDiscount(Double discount) {
		DataCenter.discount = discount;
	}
	
	/**
	 * 线上
	 * @return
	 */
	//普通会员 
	public Double on1( ){
		Double discount2 = getDiscount()*0.95;
		return discount2;
	}
	
	//铂金会员 
	public Double on2( ){
		Double discount2 = getDiscount()*0.9;
		return discount2;
	}
	
	//普通会员 
	public Double on3( ){
		Double discount2 = getDiscount()*0.85;
		return discount2;
	}
	
	/**
	 * 线下
	 */
	//普通会员 
	public Double down1( ){
		Double discount2 = getDiscount()*0.97;
		return discount2;
	}
	
	//铂金会员 
	public Double down2( ){
		Double discount2 = getDiscount()*0.92;
		return discount2;
	}
	
	//普通会员 
	public Double down3( ){
		Double discount2 = getDiscount()*0.88;
		return discount2;
	}
	
	
	

}
