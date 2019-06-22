package com.woniuxy.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016100100637055";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHnlS2CoW5q955oN/8t+nNbY22NVNjJBdwdCghSsK/wLgj/C4NF5GH4rftnZoahNp5NS4xzQT8ueyXvjsBrL/jMP7fYDclVPvYTTwxai8HLfwzDN3sW3HgosEoZpBa+kNz7A3aAbKEP/iTKBKEBvwenR/K3pOIu9W/BdS/pAKxbybyBdV0jzfloPBf6NbadgRu8dpw/rRf0yrr1KNn8cid4uIDlu9MFuv4wDhU4LUQkLGnEHd8dJdS/0A99INhiMeaJGvjlERwjcjaqhqkStDihwPRaRMacM9EDfR51YDJqZGNpVpitUD85jqNbVp33OOUwartceg/1jl3tKKgiq9fAgMBAAECggEAb0S+Z+VWNjIHBG+zllsmnn9GB7rUwcC44nDLBBWcl0r9Sbj7543wvzaKhZALAbsCrPOvBPkbIGdC8HUJYKRlg3eNhaQhWqXRQ3SK8FgYYaGfDBuAcQ3N7upZ5FXSFnLCqPeBnd5KoysBPWthVaWNcWikzwOytDq4clPMa7ea5gwD648V9pSRmF3aRF47j1Zfte9t7L4DOjOss8arpXhtKXLI0gPGlW2kjGfNJsP8mO7D3OXuBL5c+9AJRyTts9PllJ73/x4uNcur8hm9PVTxdNNDSJ/2+mZyM+s5Hb6Dw7YJNT/q7SSSYyLlf8YeVsgPkRQAvTlfUTqnlM4LJYu+QQKBgQDGDyYhFmgHt58M1kjOTlmB1Tu8BAhvucOfL0f6/8Rzysx1/mLKnIXFSoMxrQ3btdbz9PFKKpzMvHS2aEnVUY5c9h8eKUgH4piG24oJ6wJ3v0AOydf7QmFM5Li9ZSjvTIG2Hy+/BzbeATi3IBmA3MJYkraPSIgoP7NwHmrZPnRbjwKBgQCvSu93P1ZUuvlpQ5kBp8hgwfSkJv6NbyIg6VDGyBUrwbDqbly7n5jzIzl3hIA/lpCovUhYkMqTeUGL+yKMgM6F1UtuNP0JEahs+usC/OXR5buaAkkeMYXbFmWUICGAVD/9u7dCruFtd+CMaaTy3RIJxPsJIKRU7o7vGMJYbNfHMQKBgANCxpbu3oGaBVVZxdMXuSOJFjWvP1BPl6fsSFwaj+p0IU+kA45ga1dnNafAuKSwZryXgM1+zBwW0jkamHSZpiVjq/l8JoJyuZ4mySwGoYDFNlHIAvVRD3cLoPsFSlo+QTh1ZPzZKo8XKA7jyWe0usJoj/bl88/t1I95ALtY6uODAoGAN+o2UppwhlyR/GLkpqOHhl24FLifkCv3/fmOm6ntHuUM8uq7R9oT5tLAnQA2DJKRmFGwhxfAlqQiJW25Soo31ncANapPYJoyWHj9iBl1kauvWOQLwMbZRNZzFnndVdsKAnzjazDvlYScqw1z4UhlXZpo3TN9V940183VCHHHrrECgYEAq9yJDfcs8Sv77boqgw7JqgYPsoSaphht/32jYY0s/Jqfhsydn6XE6Mjyf9+VNJVag5nZ3EB3H2zqSVG+CNsZYIvHCoOEdNi1YjWI6U7f9hSV27BwL5CKvAMG6HY6B5DX4r32JkYAAgfphlbaSIrzqtgX2E2rwR5salB6uTL/3Bk=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5NL/Def4j60pt3ssS/9lu9bbr4jZdAedhpqpVJh44HKnpdJ4DkT6b8F1qGI+ncePwEQVosijxdkDjVnO4XtJ7nX/lawZ1JLpnXbmIgLLYc4+TsHwnkhXICAEalOsLPMXDVQw8h9ql+CsNHThm5xsKQR4J2/Viz8eD0mCrRZi3NgDyfTI/WUKJf6O/BYcWuzxle3G6gAFJhyPMGODAiHlXtzTa8xv88935Skp4dGUhndxRv6dYPvjbHatpPvYmuG91NT6ZFiAnTqRJzc/lttSfGmFtI2hmKS3dDHemwgemEB7m1qjj6h6d5Fp9c22f7dZ1lgdfvMLmMU3ncove6Q2DwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://24808f5i38.zicp.vip/pay/savemsg";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://24808f5i38.zicp.vip/pay/ui";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

