package com.sound.batteries.utils;

/**
 * 
 * @author liulin
 * @date 2018年7月26日 下午2:23:01
 * @Description: 调用吉利的溯源接口电芯返回码信息
 */
public enum BatteryProduceEnum {
	
	RESULTCODE0("1", "请求正确"),
	RESULTCODE1001("1001","token不合法或无权限"),
	RESULTCODE1002("1002","参数缺失"),
	RESULTCODE1003("1003","令牌为空"),
	RESULTCODE1004("1004","该账号处于无效状态"),
	RESULTCODE1005("1005","该账号已过期"),
	RESULTCODE1006("1006","您无当前接口的访问权限"),
	RESULTCODE1011("1011","该账号当天访问次数已达到限制"),
	RESULTCODE1012("1012","该账号每分钟访问次数已达到限制"),
	RESULTCODE1013("1013","您当前接口当天的访问次数已达到限制"),
	RESULTCODE1014("1014","您当前接口每分钟访问次数已达到限制"),
	RESULTCODE1021("1021","请求已过期,请确认时间戳正常"),
	RESULTCODE1022("1022","签名验证失败");
	
	public String code;
	
	public String value;
	
	private BatteryProduceEnum(String code, String value)
	{
		this.code = code;
		this.value = value;
	}
	
	/**
	 * 获取错误描述
	 * @author liulin
	 * @date 2018年7月26日 下午2:15:16
	 * @Description: TODO
	 * @param outCode
	 * @return
	 */
	public static String getDes(String outCode)
	{
		String des = "";
		BatteryProduceEnum[] values = BatteryProduceEnum.values();
		for(BatteryProduceEnum oneValue:values)
		{
			if(oneValue.code.equals(outCode))
			{
				des = oneValue.code + ":"+oneValue.value;
			}
		}
		return des;		
	}
	
}
