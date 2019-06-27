package com.woniuxy.util;

import java.math.BigDecimal;

import com.woniuxy.datacenter.DataCenter;
import com.woniuxy.pojo.Member;

public class MemberDiscount {

	/**
	 * 线上支付会员优惠价格
	 * @param member
	 * @param price
	 * @return
	 */
	public BigDecimal getOnlieDiscount(Member member, BigDecimal price) {
		DataCenter data = new DataCenter();
		switch (member.getRank()) {
		case 1:
			return data.on1().multiply(price);

		case 2:
			return data.on2().multiply(price);

		case 3:
			return data.on3().multiply(price);

		default:
			return price;
		}

	}
	
	/**
	 * 线下支付会员优惠价格
	 * @param member
	 * @param price
	 * @return
	 */
	public BigDecimal getDownDiscount(Member member, BigDecimal price) {
		DataCenter data = new DataCenter();
		switch (member.getRank()) {
		case 1:
			return data.down1().multiply(price);

		case 2:
			return data.down2().multiply(price);

		case 3:
			return data.down3().multiply(price);

		default:
			return price;
		}

	}

}
