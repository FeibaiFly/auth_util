
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.*;

/**
 * @ClassName ZxSignUtil
 * @Description TODO
 * @Author liuzhichao
 * @Date 2020/10/22 11:53
 * @Version 1.0
 */
public class ZxSignUtil {

    private final static String KEY = "zxhdforyzy2019";

    //加密类型
    public enum SignType {
        MD5, HMACSHA256
    }

    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 生成 HMACSHA256
     *
     * @param data 待处理数据
     * @param key  密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 生成签名 (默认KEY)
     *
     * @param data 待签名数据
     * @return 签名
     */
    public static String generateSignature(final Map<String, Object> data) throws Exception {
        Map<String, String> map = new HashMap();
        for (String key : data.keySet()) {
            map.put(key, StringUtils.objToStr(data.get(key)));
        }
        return generateSignature(map, KEY, SignType.MD5);
    }

    /**
     * 生成签名
     *
     * @param data 待签名数据
     * @param key  API密钥
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key) throws Exception {
        return generateSignature(data, key, SignType.MD5);
    }

    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data     待签名数据
     * @param key      API密钥
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key, SignType signType) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals("sign")) {
                continue;
            }
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(key);
        if (SignType.MD5.equals(signType)) {
            return MD5(sb.toString()).toUpperCase();
        } else if (SignType.HMACSHA256.equals(signType)) {
            return HMACSHA256(sb.toString(), key);
        } else {
            throw new Exception(String.format("Invalid sign_type: %s", signType));
        }
    }

    /**
     * 验签 (默认KEY)
     *
     * @param data 签名数据
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, Object> data) throws Exception {
        if (!data.containsKey("sign")) {
            return false;
        }
        String sign = StringUtils.objToStr(data.get("sign"));
        Map<String, String> map = new HashMap();
        for (String key : data.keySet()) {
            String value = StringUtils.objToStr(data.get(key));
            map.put(key, value);
        }
        return generateSignature(map, KEY).equals(sign);
    }

    /**
     * 验签
     *
     * @param data 签名数据
     * @param key  API密钥
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, String> data, String key) throws Exception {
        if (!data.containsKey("sign")) {
            return false;
        }
        String sign = data.get("sign");
        return generateSignature(data, key).equals(sign);
    }

//    public static void main(String[] args) throws Exception {
//
////        Map<String, Object> map = new HashMap();
////        map.put("schoolId", "1");
////        map.put("studentId", "2");
////        map.put("studentName", "小明");
////        map.put("outTradeNo", "111222333");
////        map.put("totalFee", "1");
////        map.put("nonceStr", "a123");
////        map.put("openId", "or4ef0Ytlq3jcwO5f_j3Xex8Cq-k");
////        //map.put("contractDisplayAccount", "666");089A62DC3B507CACD76C0E8373E00B16
////        //map.put("sign", "7F9B85D4F6C5C33C0F5616BCAA1E6DDD");
////
////
////        //System.out.println();
////        //System.out.println(isSignatureValid(map));
////        System.out.println(generateSignature(map));
////        System.out.println(UUID.randomUUID().toString().substring(20));
//
//         long t1 = 1649865600000l;
//        long t2 = 1649952000000l;
//        String t1STr = Long.toHexString(t1/1000 )+ "0000000000000000";
//        String t2STr = Long.toHexString(t2/1000 )+ "0000000000000000";
//        System.out.println(t1+"    "+t2);
//
//    }

    public static String encrypt(String str){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] md5Bytes = md5.digest(str.getBytes());
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        String sign = hexValue.toString().toUpperCase();
        return sign;
    }

    public static String addSymbol(List<String> list){
        StringBuilder content = new StringBuilder();
        Collections.sort(list);
        for (String element : list) {
            content.append(element).append("&");
        }
        content.deleteCharAt(content.length() - 1);
        String str = content.toString();
        return str;
    }


    public static String createAnfang() {
        String appKey = "357lXCxyMMGeRSYewrapQMeHoakRPCE8yaBJ";
        String secret = "XyFCJnerhntaDxwD7AjsXHuA764ZZjE83DAq";
        UUID uuid = UUID.randomUUID();
        String nonce = uuid.toString().replace("-", "");
        String timestamp = String.valueOf(System.currentTimeMillis());
        List<String> list = new ArrayList<>();
        list.add(appKey);
        list.add(timestamp);
        list.add(secret);
        list.add(nonce);
        String sign = encrypt(addSymbol(list));
        String suffix = "timestamp=" + timestamp + "&nonce=" + nonce + "&sign=" + sign + "&appKey=" + appKey;
        String url = "http://iotvideoserver.zjxxt.com/communicate/createVirtualUse?";
        System.out.println("url:"+url+suffix);
        return suffix;
    }


    public static void main(String[] args) {
//        String appKey ="d8dfd506ff08d6fa";
//        String secret="29c6f9a129d54084a0a045e23e41942f";
//        String timestamp = System.currentTimeMillis()+"";
//        UUID uuid = UUID.randomUUID();
//        String nonce = uuid.toString().replace("-", "");
//        List<String> list = new ArrayList<>();
//        list.add(appKey);
//        list.add(timestamp);
//        list.add(secret);
//        list.add(nonce);
//
//        String str = addSymbol(list);
//        String sign = encrypt(str);
//
//        System.out.println("url:"+"https://iotvideoserver.zjxxt.com/vsafe/getSchoolFace?sign="+sign+"&appKey=f258f8282668428e&nonce="+nonce+"&timestamp="+timestamp);
//
//        System.out.println("appkey:"+appKey);
//        System.out.println("timestamp:"+timestamp);
//        System.out.println("nonce:"+nonce);
//        System.out.println("sign:"+sign);
        createAnfang();
    }

}
