package syncCardInfo;

import GeJie.PoiUtil;
import GeJie.StringUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * create by: zhangpk
 * description: 和教育需要异步请求的相关接口
 * create time: 11:18 2021/6/16
 *
 * @Param: null
 * @return
 */
@Component
public class HeJiaoYuSyncCardInfoApiUtil {


    private static String gsApIKey = "bG1aCm6nDABGdO6g";
    private static String gsSecretKey = "w7RcHSJgybSLwBruugOUp2sA2gCiM9YJ";
    private static String syncCardInfoUrl = "https://gs.andedu.com.cn/gsgateway/mark-sx-user-server/binding/cardNumber";
    private static final String IV = "5075428636499153";
//    private static String gsApIKey = "bG1aCm6nDABGdO6g";
//    private static String gsSecretKey ="w7RcHSJgybSLwBruugOUp2sA2gCiM9YJ";
////    private static String syncCardInfoUrl = "http://117.156.17.183:9000/gsgateway/mark-sx-user-server/binding/cardNumber";
//    private static String syncCardInfoUrl ="https://gs.andedu.com.cn/gsgateway/mark-sx-user-server/binding/cardNumber";

    private static RestTemplate restTemplate = new RestTemplate();

    public String syncCardInfo(HeJiaoYuSyncCardInfo heJiaoYuSyncCardInfo) {
        String flag;
        String res = null;
        MultiValueMap<String, String> paramMap = null;
        try {
            String jsonString = JSONObject.toJSONString(heJiaoYuSyncCardInfo);
            Map<String, String> map = (Map<String, String>) JSONObject.parse(jsonString);
            paramMap = new LinkedMultiValueMap<>();
            Set<String> set = map.keySet();
            for (String key : set) {
                paramMap.add(key, map.get(key));
            }
            paramMap.add("apikey", gsApIKey);
            paramMap.add("timestamp", System.currentTimeMillis() + "");
            String sign = sign(syncCardInfoUrl, paramMap);
            paramMap.add("sign", sign);
            String result = restTemplate.postForObject(syncCardInfoUrl, paramMap, String.class);
            res = decrypt(gsSecretKey, result);
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        Map<String, Object> returnMap = (Map) JSONObject.parse(res);
        if (StringUtils.objToInt(returnMap.get("ret")) == 0) {
            flag = "success";
        } else {
            flag = "fail";
        }
        return flag;
    }


    private static String sign(String url, MultiValueMap<String, String> paraMap) {
        url = "POST" + url;
        Set<String> keySet = paraMap.keySet();
        List<String> list = new ArrayList<>(keySet);
        list.sort(String::compareTo);
        StringBuilder urlBuilder = new StringBuilder(url);
        for (String str : list) {
            urlBuilder.append(str).append("=").append(StringUtils.objToStr(paraMap.getFirst(str)));
        }
        url = urlBuilder.toString();
//        System.out.println("======beforeSign:" + url);
        String sign = null;
        try {
            sign = md5Hex(URLEncoder.encode(url + gsSecretKey, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }

    public static String md5Hex(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] datas = new byte[0];
        try {
            datas = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] bs = md5.digest(datas);
        String result = new String(new Hex().encode(bs));
        return result;
    }


    private static SecretKeySpec getKey(String strKey) {
        byte[] arrBTmp;
        try {
            arrBTmp = strKey.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        byte[] arrB = new byte[16];
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");
        return skeySpec;
    }


    public static String decrypt(String strKey, String strIn) {
        try {
            SecretKeySpec skeySpec = getKey(strKey);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = org.apache.commons.codec.binary.Base64.decodeBase64(strIn);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            return "";
        }

    }

//    public static void main(String[] args) {
//        File file = new File("C:\\Users\\Administrator.USER-20200720VX\\Desktop\\数据割接字段需求\\手动同步学生卡号1108.xlsx");
//        try {
//            //统一用户id,rfid,imei,imei_phone,呼出号,昵称
//            List<String[]> fileString = PoiUtil.readExcel(file, 6);
//            fileString.remove(0);
//            List<HeJiaoYuSyncCardInfo> students = new ArrayList<>();
//            StringBuffer sb = new StringBuffer();
//            for (String[] sArr : fileString) {
//                HeJiaoYuSyncCardInfo studnet = new HeJiaoYuSyncCardInfo();
//                studnet.setUserId(sArr[0]);
//                studnet.setRfid(sArr[1]);
//                studnet.setImei(sArr[2]);
//                studnet.setEquipPhone(sArr[3]);
//                studnet.setFamiNumberName1(sArr[4]);
//                studnet.setFamiNumber1(sArr[5]);
//                students.add(studnet);
//            }
//            analysisFamiPhone(students);
//            System.out.println("数据处理完成，开始切换卡数据");
//            HeJiaoYuSyncCardInfoApiUtil heJiaoYuSyncCardInfoApiUtil = new HeJiaoYuSyncCardInfoApiUtil();
//            for (HeJiaoYuSyncCardInfo student : students) {
//                heJiaoYuSyncCardInfoApiUtil.syncCardInfo(student);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void analysisFamiPhone(List<HeJiaoYuSyncCardInfo> allUpdateInfos) {
        for (HeJiaoYuSyncCardInfo cardInfo : allUpdateInfos) {
            String targetTelStr = cardInfo.getFamiNumber1();
            String targeTelNameStr = cardInfo.getFamiNumberName1();
            cardInfo.setFamiNumber1(null);
            cardInfo.setFamiNumberName1(null);
            if (StringUtils.isEmpty(targetTelStr)) {
                continue;
            }

            //0=17610661251!1=17610661252!2=!3=!
            //0=号码1!1=号码2!2=!3=!
            targetTelStr = targetTelStr.replace("0=", "").replace("1=", "")
                    .replace("2=", "").replace("3=", "").replaceAll(" ", "");
            targeTelNameStr = targeTelNameStr.replace("0=", "").replace("1=", "")
                    .replace("2=", "").replace("3=", "").replaceAll(" ", "");
            String[] telStr = targetTelStr.split("!");
            String[] nameStr = targeTelNameStr.split("!");
            try {
                String targeTelName = "";
                for (int i = 0; i < telStr.length; i++) {
                    if (StringUtils.isEmpty(telStr[i])) {
                        continue;
                    }
                    if (i == 0) {
                        cardInfo.setFamiNumberSos(telStr[i]);
                        if (nameStr.length > i) {
                            targeTelName = StringUtils.isEmpty(nameStr[i]) ? "号码1" : nameStr[i];
                        } else {
                            targeTelName = "号码1";
                        }
                        cardInfo.setFamiNumberNameSos(targeTelName);
                        continue;
                    }
                    if (i == 1) {
                        cardInfo.setFamiNumber1(telStr[i]);
                        if (nameStr.length > i) {
                            targeTelName = StringUtils.isEmpty(nameStr[i]) ? "号码2" : nameStr[i];
                        } else {
                            targeTelName = "号码2";
                        }
                        cardInfo.setFamiNumberName1(targeTelName);
                        continue;
                    }
                    if (i == 2) {
                        cardInfo.setFamiNumber2(telStr[i]);
                        if (nameStr.length > i) {
                            targeTelName = StringUtils.isEmpty(nameStr[i]) ? "号码e" : nameStr[i];
                        } else {
                            targeTelName = "号码3";
                        }
                        cardInfo.setFamiNumberName2(targeTelName);
                        continue;
                    }
                    if (i == 3) {
                        cardInfo.setFamiNumber3(telStr[i]);
                        if (nameStr.length > i) {
                            targeTelName = StringUtils.isEmpty(nameStr[i]) ? "号码e" : nameStr[i];
                        } else {
                            targeTelName = "号码4";
                        }
                        cardInfo.setFamiNumberName3(targeTelName);
                        continue;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String start = "\033[";
        String end = "m";
//        for (int i = 0; i < 16; i++) {
//            for (int j = 0; j < 16; j++) {
//                int x = i * 16 + j;
//                System.out.print( x);a
////                System.out.print(reset + " ");
//            }
//            System.out.println("|");
//        }

        //创建一个扫描器对象，用于接收键盘数据
        Scanner scanner = new Scanner(System.in);
        //next方式接收字符串(不可以接收空格)
        //判断用户还有没有输入字符
        File file = new File("D:\\用户目录\\下载\\我真不想当灭世主.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        while (true) {
            String str = scanner.next();
            if(str.equals("a")) {
                System.out.println(new String(new char[50]).replace("\0", "\r\n"));
                System.out.println(start + "38;5;" + 238 + end +txt2String(br));
            }else if(str.startsWith("find")) {
                str = str.replace("find","");
                System.out.println(find(br,str));
            }else if(str.equals("s")) {
                System.out.println(new String(new char[50]).replace("\0", "\r\n"));
            }
        }
//        scanner.close();
    }

    public static String txt2String(BufferedReader br){
        try{//构造一个BufferedReader类来读取文件
            String txt="";
            int i=0;
            while (true) {
                String s = br.readLine();
//                if(s.length()>30) {
//                   s = splitStr(s,90);
//                }
                if(!StringUtils.isEmpty(s)) {
                    txt+=s+"\r\n";
                    if(i>0) {
                        return txt;
                    }
                    i++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String find(BufferedReader br,String findStr) {
        try{//构造一个BufferedReader类来读取文件
            while (true) {
                String s = br.readLine();
                if(StringUtils.isNotEmpty(s) && s.contains(findStr)) {
                    return s;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static  String splitStr(String str,int len) throws Exception {
        String charSet = "utf-8";
       String strSection = "";
        byte[] bt = str.getBytes(charSet);
        int strLen = bt.length;
        int startPos = 0;
        int startStrPos = 0;
        while (startPos < strLen) {
            Integer subSectionLen = len;
            if (strLen - startPos < len) {
                subSectionLen = strLen - startPos;
            }
            byte[] br = new byte[subSectionLen];
            System.arraycopy(bt, startPos, br, 0, subSectionLen);
            String res = new String(br, charSet);
            int resLen = res.length();
            if (str.substring(startStrPos, startStrPos + resLen).getBytes(charSet).length > len) {
                res = res.substring(0, resLen - 1);
            }
            startStrPos += res.length();
            strSection+=res+"\r\n";
            startPos += res.getBytes(charSet).length;
        }
        return strSection;
    }

}
