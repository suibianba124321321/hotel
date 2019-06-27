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
	public static String app_id = "2016100100637042";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCuuEmVT4jorB5lgrC/FkWXQXVEbDm+xrDGxagX5ciXDjmC0wvKpbHRYwYRhE3NzDvCKDqXR+Ib1FoUIcW90AelqSSdIH5hGYgFrxt1Eb2x2q06WRzndDlL9q/MTWQlTRYpef+Wl1NRc9GdIrp3Pys8YJJM9t4XAmZDcDPfa47ahuTMYYG3v4dHEBEnJV0aoYPwRZebpjpqhVCKBPVJhEVjTaDW6h4Rin4qT2ZWhwccSuUxUFs6gyRaj7ttSnifxNII1E1ev4QlwCKeOLYuJX7AGdOTyIAaBoo5NKTttdHj52146U9cHZFuY0TT21YSR4iIJulxMhV2T/Cxa8pjEpMXAgMBAAECggEAA6DeHlStgi943iDNrNo4jcCVP9uDg3u61hXqvLxP4xsvhJD/cLDqNW/6A0kdGIYCQ7D0LooWnv9U85AT/DIixoTxtrWjCj8ufPe6+T5qExR4fnq1R/U472PrGLIOv1uPd0MKVMVFvK5MDxhWiP1t124l+6UwzYeGOiZNXXltWQplTtfTS14gCbizJyUoMFV6dcNuU5Ojs1DS0Cg21AhU/LT8VYxKuPlt0kQIM2mayI6HfzxAycZPGBjZSfWgxVIYytxcJNQsTrw5KH0QRN0cZTsfDJ60fPpwB/G7zf6nF//KT5eRTgtqRo4kRUDCYDi5MAseFr4Rfyg+2M4ri/a6wQKBgQDhJo6pvjzU1zZmIAmVUx3zefTblKQVqpEhHEMDM/2aLBG7OAWYvoLfBjTdSIAIA8bjg5cwck5o7UzOV4jEu5djLINL0Xivhb2RhDMaU28T19ZXNogoWH6Fo6BYE1xCM02KIuwLgqwznwDhAez+41lUDbwyhFQ9XHkDOU4oaAPwNwKBgQDGqM7eJUIae7/sI3PvYB+JFLaqiGzuNcrHmlTaJjyODatuREzdHZTrwQEN9Ku7ieEavWHivFZtGiIf6EC4CgKGkZz/EjOeeGjpX5N6w1EsNOj1W/saTs5dS1NEED9mL5ahzmTtBYKcc7PeYpJuUqZ2i0nFz66WOX0EOCjaMk5EIQKBgAHxyU2h/PiBm1Cqeiq1+w49QWYcFIazYGl+yv1eF5SbSNPrGfFFw++VKCO5GpH3JmKUoluiwRaa5gzqKJ46WIZcvg2Lk5VAfHCjSaWyjsUHGizKpcZjbUROM3ZPRH613reQsaUvzbjsaJ+I2rkg0YauT6/IAMo+4DSs7AXhUBdlAoGBALHxnTLwrrBoLnZHH17IfBbevewsvV32Qwfdnqm03ELlzau8dBkPmUg/HkGw6m7WxxKblmVxC/04Dl8EO4xeMvr0zWrQk/obouV/neu1rizpy82TMW3ULxb0FMZ/wzo14DhZVp/UcrTPEstdAgrd74t5ooAUUi7vmaLA5x7jNqXhAoGBAMvMDocKHv59HsqgZ4GaJe4qbY9Q1ut0gZeG+V7dp7gFkJ0u1ZPjWkMmwvEvjUdtuhUndDv/hltVzPs34sGchZYuPzGefnLdzJwr1nlYh5CZkSzBWZ4YhBZGvJi0DW/IeXucD999H9ohb5NPrhGBTQTFgMMeFeDM9lmBf9ApxYQ7";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl1h43xitAAK133F0nWRigEdqUlLjQdss37fCQ/W7aZ19EmFgNsZ7WD41AdKFwOwPvDoBdI3oep+NuxehfRrRziiIDFF30XrGPAQImpNObUrJTrpk0YEEgwKxMR7lROezXpkQ4KfnixW6D9Fs/Qf2Dd4PSVtXnrIYHEo4f2C2xw3mp0IZWgJfiTzbefGyNNZTjsOsz7aItakFdsZT26NvjQ4KjFYP0fK33nTFda8ENNQ0Ecj93UKX7AGdPT75PT1bMujfXM6/WQxAG+uNc8DDtHcFKTnUibC8aUU4EhQk2QPljrRZxDHBTAvHCRT7o67JuhX0P71K+A1bmUmEg/u0CQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://2t48084s26.wicp.vip/pay/savemsg";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://2t48084s26.wicp.vip/pay/ui";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "F:\\";


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

