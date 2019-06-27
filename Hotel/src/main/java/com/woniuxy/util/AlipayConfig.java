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
	public static String app_id = "2016100100637056";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCea3YrKx7RknujiFTXfEq7DIMEYjfCsYa2Xpn0DZ3PDE3wTW6UFAGM3ubN9O6QBcq6XtcOkFxQWfql3ocznARB/qgtKJCiChn0IdEp5Gr+Q0YKqU0zaSVnkTeCsh6qMIK3Kd+cSchgv216V7gNm4jfWSJ+BkVBzJsBf7/k+ftNjdo/CUIqI4+g6CCz5U7uBb3o8ffmSruD3/H595gnKOxFRNTGKkH0NIfOdiw0EMa25V9KyoXE+grptiPhjIik4V4jLD8cUk8RJogrCSNSIsz6rxMNgC2qZ0rCwgEZwXC9TxLTMJDJbcX2scqF/LQcEtuPzoEqsJJFn4jPrMMndeGlAgMBAAECggEAMsAUXCH1PoghybLMEzhDLIcGlentGRcMI/JMea2/B2xWr4vux8tc78ccw5AauNLSLkBhvs2qdSdOL8MJJxRtHMM7FzRumSqJQ/dtbXE6gXgFwTaZf8XIcNRJpznurvDZf1oPw56zbaMFrHLbiKhyAbMpZJwXpcupQKKvU+hthFtSNY8+ymuioH/WOjloKKQjuUruFPwckF6kYYwAqHl7snMLcuTrIXZlpjVuHMZpZiZjaj28WWu8aFYo/hB2ZUKFCHDdp4Rfj+9NZBeuj/KCAGlgR0EiRycdLGQIcknxC/O8gvkppZR9e7nFDn+nfA4w5RFNOLlrCneaE+Qq2JtdwQKBgQDjr8AMoHeReUPeGNO5TGQRhoWcTAuHQK8g6674v+Fk+TvA0oQIIQXfnyZ5cIo1O2AxYBUe3nM3oGDlNg9VUTIUj4Nwv/pX09w2SUlt7B4HJoIF3zZQTvgc9lF2pqR+T18y+NjOhiaTwFT6Zw7A6mNhtbHK218iDtHHMtVpy/wGkQKBgQCyHqZymBdnWHIyjvoxY5aGMIUeNbwIhNl6fiD93zY2OVu88+ahiYP/QBakPSwwV9twzVIapCUV1kgSatiui1xYL1h/1KfFPihemD8MWqERcOF00R0oQfICh8OUaGDPrfqyTU5TZ1tNYGb5A56uV3PKPWNkxbt/zHH7dtf5X7E71QKBgQC3reLazOFoxFFR7OlxBQV9de53xolOVYpynm+LURB+KupfhfTpsepRGs0pDvcQNpRFgOPJAWAlsgY5I0F/bH9gQt6gqN9G1y1BxiGswdz/m7stDYN9ihdzFetmMs1k8Jc2QbZ19ZdNfs2PLHCkhPIjszZ5FjxK93WYo5OzQ9JPYQKBgGo9oeYkFYGM3cCOS2j+D6BGfIACaP9jzhwj8yW556n8GF3h1KRxU4wB+/9GhX7oSHlF3j0Jvx0BTMe/dzLU6OWoh87nVpJtesLBaInD3OJ8Q93bhNqoSuN7ewqgH26ARVHojlltAqZqY8EPiaxPeT8d0pUe47FcjngA8N1LWHlpAoGAPdxjTfje8C5v4vDTRy548PEqBSDvB/lS0LO9NnSNU76sGWivtLnPM7Jo3bJEl/XX4KAWfCse+GSUPW75oMcaHW+8nkwQwFpt7jcuDazHkBoVSz3diFdGwpiqggbI1NYRUoTqKBTlgXsQA72mXU7TaMIttI0FoHBtwq7VuYnhMII=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArI/CwDmbmfQd4TxehvZEchqRtAJLoo20B3PnJfm30e21VjA8v9r4Rn2ooOHnbvshGHBHE+QJRmTiNtE16bmM8pJlsMwZ/6R+W6pWIgRlbmaM8X5VCKoqwvT0TMIkPMn4O3XSs/fJ0aO1vKdktF1nbgG1icuBJREdCtDPd4w0yCxDqlA/CgxJ+MkvAK70J+ZyvyKnamkaR6T3bSuiqvfETPj08X/8Z2lPe3fvwS3KUPTOvaHeNwik41ETR9yzr8bMkMgd9HYs4V/jZOnGuQ4aO/acTh7AvO6CjBcmQJ16nWkE8ak4eRFoA5ZHytl3En6oHcP5LyO+kcFW6CaYpTr+hwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://248o08h661.qicp.vip/pay/savemsg";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://248o08h661.qicp.vip/pay/ui";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "d:\\";


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

