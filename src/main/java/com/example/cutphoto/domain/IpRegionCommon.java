package com.example.cutphoto.domain;

import lombok.Data;

/**
 * 属地实体类
 */
@Data
public class IpRegionCommon {
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 地区
	 */
	private String region;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 运营商
	 */
	private String isp;
}
