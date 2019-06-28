package com.woniuxy.datacenter;

import java.math.BigDecimal;

public  class  DataCenter {
	//每日折扣
	private static  BigDecimal discount= new BigDecimal("1.0");


	
	public static BigDecimal getDiscount() {
		return discount;
	}

	public static void setDiscount(BigDecimal discount) {
		DataCenter.discount = discount;
	}

	/**
	 * 线上
	 * @return
	 */
	//普通会员 
	public static BigDecimal on1( ){
		BigDecimal discount2 = getDiscount().multiply(new BigDecimal("0.9"));
		return discount2;
	}
	
	//铂金会员 
	public static BigDecimal on2( ){
		BigDecimal discount2 = getDiscount().multiply(new BigDecimal("0.85"));
		return discount2;
	}
	
	//普通会员 
	public static BigDecimal on3( ){
		BigDecimal discount2 = getDiscount().multiply(new BigDecimal("0.8"));
		return discount2;
	}
	
	/**
	 * 线下
	 */
	//普通会员 
	public static BigDecimal down1( ){
		BigDecimal discount2 = getDiscount().multiply(new BigDecimal("0.92"));
		return discount2;
	}
	
	//铂金会员 
	public static BigDecimal down2( ){
		BigDecimal discount2 = getDiscount().multiply(new BigDecimal("0.87"));
		return discount2;
	}
	
	//普通会员 
	public static BigDecimal down3( ){
		BigDecimal discount2 = getDiscount().multiply(new BigDecimal("0.83"));
		return discount2;
	}
	
	
	

}
