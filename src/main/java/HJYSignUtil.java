import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HJYSignUtil {
    private static String apikey = "COHeJfoWQgaYBuna";
    private static String secretKey = "2pdC7CdzMF8Y6FM23hLcCclHtB4rm8W9";
    private static final String IV = "5075428636499153";


    private static SecretKeySpec getKey(String strKey) throws Exception {
        byte[] arrBTmp = strKey.getBytes("UTF-8");
        byte[] arrB = new byte[16];
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");
        return skeySpec;
    }

    /**
     * 加密算法
     *
     * @param strKey 密钥
     * @param strIn  明文
     * @return 密文
     * @throws Exception
     */
    public static String encrypt(String strKey, String strIn) throws Exception {
        SecretKeySpec skeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(strIn.getBytes("UTF-8"));
        return Base64.encodeBase64String(encrypted);
//        return org.apache.commons.codes.binary.Base64.encodeBase64String(encrypted);
    }


    public static void main(String[] args) throws Exception {
        String singnKey = "COHeJfoWQgaYBuna";
        String appKey ="21fdb19dae57ecbe8ef6d47497b76ffc";
//        String appKey = "15335cc5991fc2b254295719fccc421d";
        //同步学校
//                String content = "{\n" +
//                "    \"timeStamp\":\"1616751245023\",\n" +
//                "    \"operation\":\"DELETE\",\n" +
//                "    \"schoolId\":\"20211017\",\n" +
//                "    \"platformSchoolId\":\"220211017\",\n" +
//                "    \"name\":\"测试融合平台同步\",\n" +
//                "    \"province\":\"620000\",\n" +
//                "    \"city\":\"620100\",\n" +
//                "    \"district\":\"620102\",\n" +
//                "    \"type\":\"2\"\n" +
//                "}";
        //同步班级
//        String content = "{\n" +
//                "    \"timeStamp\":\"1616751245023\",\n" +
//                "    \"operation\":\"SAVE\",\n" +
//                "    \"classId\":\"20211017\",\n" +
//                "    \"schoolId\":\"20211017\",\n" +
//                "    \"platformClassId\":\"220211017\",\n" +
//                "    \"platformSchoolId\":\"220211017\",\n" +
//                "    \"name\":\"测试甘肃班级日同步\",\n" +
//                "    \"type\":\"2\"\n" +
//                "}";

        //同步学生
//        String content = "{\n" +
//                "    \"timeStamp\":\"1616751245023\",\n" +
//                "    \"operation\":\"DELETE\",\n" +
//                "    \"userId\":\"20211017\",\n" +
//                "    \"platformUserId\":\"220211017\",\n" +
//                "    \"name\":\"测试甘肃学生同步二\",\n" +
//                "    \"phone\":\"\",\n" +
//                "    \"nickName\":\"测试学生同步\",\n" +
//                "    \"type\":\"2\"\n" +
//                "}";
//        System.out.println(UCApiUtil.getRequestToken());

        //同步家长
//        String content = "{\n" +
//                "    \"timeStamp\":\"1616751245023\",\n" +
//                "    \"operation\":\"SAVE\",\n" +
//                "    \"userId\":\"20211017\",\n" +
//                "    \"platformUserId\":\"220211017\",\n" +
//                "    \"name\":\"测试家长同步二\",\n" +
//                "    \"phone\":\"15155923321\",\n" +
//                "    \"nickName\":\"测试家长同步\",\n" +
//                "    \"type\":\"3\"\n" +
//                "}";

        //同步学生家长
//        String content = "{\n" +
//                "    \"timeStamp\":\"1636438343000\",\n" +
//                "    \"operation\":\"DELETE\",\n" +
//                "    \"studentId\":\"5003074217322\",\n" +
//                "    \"parentId\":\"5003074217323\",\n" +
//                "    \"roletype\":\"3\"\n" +
//                "}";

        //同步学生班级
//        String content = "{\n" +
//                "    \"timeStamp\":\"1636438343000\",\n" +
//                "    \"operation\":\"DELETE\",\n" +
//                "    \"userId\":\"5003074217322\",\n" +
//                "    \"classId\":\"3391926985\",\n" +
//                "    \"role\":\"2\"\n" +
//                "}";

        //同步教师
//        String content = "{\n" +
//                "    \"timeStamp\":\"1616751245023\",\n" +
//                "    \"operation\":\"DELETE\",\n" +
//                "    \"userId\":\"20211017\",\n" +
//                "    \"platformUserId\":\"220211017\",\n" +
//                "    \"name\":\"教师同步\",\n" +
//                "    \"phone\":\"15155923321\",\n" +
//                "    \"nickName\":\"教师同步\",\n" +
//                "    \"type\":\"1\"\n" +
//                "}";
        //同步教师班级
//                String content = "{\n" +
//                "    \"timeStamp\":\"1608172600374\",\n" +
//                "    \"operation\":\"SAVE\",\n" +
//                "    \"userId\":\"20211017\",\n" +
//                "    \"classId\":\"20211017\",\n" +
//                "    \"platformUserId\":\"220211017\",\n" +
//                "    \"platformClassId\":\"220211017\",\n" +
//                "    \"role\":\"1\"\n" +
//                "}";
        //加密后 学校考勤设备状态查询接口
//        String content ="{\n" +
//                "    \"schoolId\":\"30008384\"\n" +
//                "}";
        //加密后 通过学生ID查询学生证信息接口
//        String content = "{\n" +
//                "    \"studentId\":\"7106728761\"\n" +
//                "}";
        //加密后 2.5.	学生绑定解绑卡号接口
//        String content = "{\n" +
//                "    \"studentId\":\"7106713190\",\n" +
//                "    \"opId\":\"12345\",\n" +
//                "    \"opName\":\"测试客服\",\n" +
//                "    \"opType\":2,\n" +
//                "    \"schoolId\":\"30078267\",\n" +
//                "    \"rfid\":\"0067513190\",\n" +
//                "    \"imei\":\"\",\n" +
//                "    \"equipPhone\":\"15155923327\"\n" +
//                "}";

        //加密后 2.6.	学生修改指定亲情号接口
//        String content = "{\n" +
//                "    \"studentId\":\"7106713190\",\n" +
//                "    \"opId\":\"12345\",\n" +
//                "    \"opName\":\"测试客服\",\n" +
//                "    \"schoolId\":\"30078267\",\n" +
//                "    \"famiNumber\":2,\n" +
//                "    \"famiTelName\":\"\",\n" +
//                "    \"famiTel\":\"\"\n" +
//                "}";

//                String content = "{\n" +
//                "    \"phone\":\"15155923321\",\n" +
//                        "    \"startTime\":\"2021-11-16 00:56:36\",\n" +
//                        "    \"endTime\":\"2021-11-17 18:56:36\",\n" +
//                "    \"schoolId\":400043221\n" +
//                "}";

        /**
         * create by: zhangpk
         * description: 江苏电子工卡
         * create time: 14:43 2021/12/1
         * @Param: args
         * @return void
         */
//        String content = "{\"date\":\"2021-11-17\", \"phone\":\"15155923321\", \"schoolId\":400043221}";

                String content = "{" +"\"imei\":\"866930050023341\",\"date\":\"2022-05-13\"}";
        content = URLEncoder.encode(encrypt(singnKey, content), "utf-8");
        System.out.println(content);
        Map<String, String> dataMap = new HashMap();
        dataMap.put("appKey", appKey);
        dataMap.put("data", content);
        String authorization = AuthUtil.generateSignature(dataMap, appKey, ZxSignUtil.SignType.MD5);
        System.out.println();
        System.out.println(authorization);

//        System.out.println(URLEncoder.encode("kMEOuKakuGrdKAmlYhV2E4w9QvlZMKUuQW82KNVbJt%2Bjs1O8mf29KiQ6qm5pCLr5NqIhMhpUjEFoQnshHur%2BdIbc4C06zokmaBTKzeyjlemDqN01teUlAu5pBUT7OczTDnZHC5uhrk9oEPMGZdpECH1MAqqntqgzsa3%2F2OinqeTcNmOpm%2F58ikvWXy%2FJkMNizIejnfm%2FvWUUnuuzz5H2hT4xU8zsgirofQpvsg5lFfA%3D","utf-8"));
    }
}
