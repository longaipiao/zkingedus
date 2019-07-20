package com.zking.zkingedu.common.config;

/**
 * @ClassName AlipayConfig
 * @Author likai
 **/
public class AlipayConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092500596275";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCG3x8Lk0pp0Pz4eQv4priM2XaQaLwBiKf0Pbs1EGF47tpeAhER6smBorFINimEn2XoEBZW16LXnrpH1JKrWUqZ4GwETsz65NJV8quPtHBORPqv4na7y3vNS2uCpLNIF8o1Dw37saW2OdOOjvjbSQg2C06IReLzr4QK6TLEcOaB9SXE7Vw4vIwOYqsXY7zJwaY3tSOROTYaTSsJ8vZFinJyD9B4Gz+KbRO79wE7xtn9I752OOUOTTs4EzPszDZWsI1dMarid4Hgntso8f5cy8Fw20ZOm0WBH5iKJ//uUav/mGbxjoouyEFTFkUFSDhYB56l7eckkOG6a+iKOBTjYEUPAgMBAAECggEAVwfPjrajBBlZxDTIJtHesPT670DGYO8AAjl7fkhtvY3T12nZ/gfVtBN59rEMpYn+RntrDurjJ+RDLGrdojhiTV1mFnmLCDN0DwYi8v5qofcdFTY7i/9Iijcqzwjcr566Wva1/T6rQiWagJl9uHMuG0G8kW8Xfkw6FphYrC52cFD+M74dlA/sz6AtYMUuXUgGc7rHjQu6JvImVtEMFevh+TLm749tAYk53yq//52meolYVgaeW8NEeb8AdaVVypdOxCrAX+gzjhQ230jk0HITOZpONtSoLdIEiT/LOVreVhh5cN4dJv/TRr3WDSZYIrwdSOmAJh2sfNZViTHSelx7gQKBgQDjV6SKA4JYRglPhS/+z0m3uwkPkvoEY5tTIEuehMC/HWJzT+kle2G4htPwIj48ZCik0IPd14FIh7uFGFaRzMxdFvCE9+ER92mT0ertG2mfm8rr2EJwLLZkflrgNlIaeDvO9171qTRm6J5EesQedIkER+hDNW77KhISdI+dNrToLwKBgQCX33A1e7d0bIleFWaoIoDqoFsWYReAJmpIsc7wXdaq6PHjVbQKoPeZ954/TCFkS2nDjx89UUCh91QMs+fHF5VpuYoEIlk0LIYWGrNPuANl1q1LxQ+IOu/XflF5NLLZWmE1jWVoQJYtEO5/3Pk9UadOY/AC1IXHXIEYvF+ruIpZIQKBgQCe4wL4/ijb3t7k97WqhV8FDK/xdYxNAvxsZVbUS/2V1EiZRNEyQ//xAjIfUx9h/7I0NhLjOZl+gnZ5N6BRN+vgZ4bjt4ZSkzLzf7OL0SvJxA1XNYaBdY5nv7NtnilVnJBYPqTcVkxsW8k5HP/yNQoJtuPKDQ8qdKW6xkIU40YeOwKBgBAiwjY8fcJV4cZoOa71c4L6JJNdH3UTH8m3coghQDSKaWDA808JySTHpT2OZiHUzVjktOn73wUWVQzAdIi3nb1M5DgKOjYL4PcqQRDgOSQxydm6BytvL5pSpm9dRP9I1fktm6+W5R3GpcBaBvUX3tG6Vn7Ge6wxzNmG8hc5lSFhAoGBAKqA4uPiCYqRpHvFtUEcHu9bCZtoXl0GqefFu5XsF6Iw/PO8kAbP/CiAmqulJo2lpPEYSAIvteoVqk50R4CSzH79u4cQbkjnbtgzisRbS+7OduHfHo9nYqUXvXJt1cHT/DYalegEJ9ynKpa0IyygCn2U0VdNV3kuQnVMrhD09kaY";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAht8fC5NKadD8+HkL+Ka4jNl2kGi8AYin9D27NRBheO7aXgIREerJgaKxSDYphJ9l6BAWVtei1566R9SSq1lKmeBsBE7M+uTSVfKrj7RwTkT6r+J2u8t7zUtrgqSzSBfKNQ8N+7GltjnTjo7420kINgtOiEXi86+ECukyxHDmgfUlxO1cOLyMDmKrF2O8ycGmN7UjkTk2Gk0rCfL2RYpycg/QeBs/im0Tu/cBO8bZ/SO+djjlDk07OBMz7Mw2VrCNXTGq4neB4J7bKPH+XMvBcNtGTptFgR+Yiif/7lGr/5hm8Y6KLshBUxZFBUg4WAeepe3nJJDhumvoijgU42BFDwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://47.107.120.48:8800/pay;";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://47.107.120.48:8800/user/userinfo";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "D:\\";
}
