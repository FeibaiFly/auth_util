package GeJie;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.core.config.json.JsonConfiguration;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * create by: zhangpk
 * description: 学生卡号割接
 * create time: 13:45 2021/9/28
 *
 * @Param: null
 * @return
 */
public class GeJieCard {


    static Logger logger = Logger.getLogger(GeJieCard.class);

    public static void main(String[] args) {
//        File file = new File("C:\\Users\\Administrator.USER-20200720VX\\Desktop\\割接学校\\岷县二中(学生证)和岷县第二中学匹配.xlsx");
////        try {
////            //newStudentId,newSchoolId,学校名称,班级,oldStudentId,studentName,parentName,家长号,rfid,imei,imeiPhone,亲情号名称,亲情号
////            List<String[]> fileString = PoiUtil.readExcel(file, 11);
////            fileString.remove(0);
////            List<HeJiaoYuSyncCardInfo> students = new ArrayList<>();
////            StringBuffer sb = new StringBuffer();
////            for (String[] sArr : fileString) {
////                HeJiaoYuSyncCardInfo studnet = new HeJiaoYuSyncCardInfo();
////                studnet.setStudentId(Integer.valueOf(sArr[0]));
////                studnet.setSchoolId(Integer.valueOf(sArr[1]));
////                studnet.setOldStudentId(Integer.valueOf(sArr[2]));
////                studnet.setStudentName(sArr[3]);
////                studnet.setRfid(sArr[6]);
////                studnet.setImei(sArr[7]);
////                studnet.setEquipPhone(sArr[8]);
////                studnet.setFamiNumberName1(sArr[9]);
////                studnet.setFamiNumber1(sArr[10]);
////                students.add(studnet);
////            }
////            analysisFamiPhone(students);
////            System.out.println("数据处理完成，开始切换卡数据");
////            int i = 1;
//////            for (HeJiaoYuSyncCardInfo student : students) {
//////                System.out.println("开始割接第"+i+"个");
//////                //解绑就学生
//////                unBindStudentCard(student.getOldStudentId());
////////                绑定新学生
//////                bindStudentCard(student.getSchoolId(), student.getStudentId(), student.getStudentName(), student.getRfid(),
//////                        student.getImei(), student.getEquipPhone(), 1,sb);
//////                //绑定亲情号
//////                bindStudentFamiNumber(student.getStudentId(), 2, student.getFamiNumberName1(), student.getFamiNumberName2(),
//////                        student.getFamiNumberName3(), student.getFamiNumberNameSos(), student.getFamiNumber1(), student.getFamiNumber2(),
//////                        student.getFamiNumber3(), student.getFamiNumberSos(), student.getImei());
//////                i++;
//////            }
//////
//////            System.out.println(sb.toString());
////            Integer[] failStudentIds = {2500851,2540216,2473853};
////            List<Integer> array = Arrays.asList(failStudentIds);
////            for (HeJiaoYuSyncCardInfo student : students) {
////                if (array.contains(student.getStudentId())) {
////                    sb.append("UPDATE student s set s.cardNum = '").append(student.getRfid()).append("',s.send_time ='2022-03-10 16:13:38',card_type=2 WHERE s.id =").append(student.getStudentId()).append("AND s.is_deleted = 0;\r\n");
////                    sb.append("insert into student_extend (id,card_status,imei,imei_phone) VALUES ( ").append(student.getStudentId()).append(",1,'").append(student.getImei()).append("','").append(student.getEquipPhone()).append("');\r\n");
//////                    bindStudentFamiNumber(student.getStudentId(), 2, student.getFamiNumberName1(), student.getFamiNumberName2(),
//////                            student.getFamiNumberName3(), student.getFamiNumberNameSos(), student.getFamiNumber1(), student.getFamiNumber2(),
//////                            student.getFamiNumber3(), student.getFamiNumberSos(), student.getImei());
////                }
////            }
////            System.out.println(sb.toString());
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
        String studentIds = "2314274,2314279,2314286,2314287,2314289,2314290,2314291,2314471,2314475,2314517,2314927,2315562,2315637,2315640,2316045,2316266,2316268,2316428,2316429,2316430,2316431,2316432,2316437,2316438,2316440,2316442,2316443,2316445,2316446,2316448,2316449,2316450,2316452,2316453,2316454,2316456,2316457,2316459,2316460,2316462,2316466,2316469,2316470,2316472,2316474,2316475,2316476,2316477,2316478,2316479,2316480,2316481,2316482,2316522,2316523,2316524,2316525,2316526,2316527,2316529,2316530,2316531,2316532,2316533,2316536,2317765,2317793,2317794,2317795,2317796,2317797,2317798,2317799,2317800,2317801,2317802,2317803,2317804,2317805,2317806,2317807,2317809,2317810,2317811,2317812,2317813,2317815,2317817,2317818,2317819,2317821,2317822,2317823,2317824,2317826,2317827,2317828,2317830,2317831,2317833,2317834,2317835,2317836,2317837,2317840,2317841,2317842,2317843,2317844,2317845,2317889,2317979,2318094,2318135,2318325,2318532,2318533,2318555,2318560,2318586,2318595,2318598,2318703,2318708,2318714,2318740,2318775,2318796,2318805,2318809,2318818,2318830,2320233,2320234,2320236,2320464,2320466,2320467,2320468,2320469,2320470,2320471,2320474,2320478,2320480,2320481,2320482,2320483,2320484,2320485,2320486,2320488,2320491,2320531,2320532,2320533,2320534,2320539,2320540,2320542,2320546,2320547,2320579,2320584,2320586,2320590,2320592,2320593,2320594,2320596,2320597,2320598,2320601,2320635,2320645,2320646,2320649,2320657,2320661,2320662,2320663,2320664,2320665,2320718,2320719,2320721,2320725,2320729,2320730,2320731,2320732,2320733,2320735,2320743,2320784,2320786,2320788,2320791,2320795,2321135,2321136,2321150,2321804,2323432,2323434,2326659,2326661,2326662,2326663,2326664,2326668,2326670,2326671,2326672,2326675,2326676,2326678,2326680,2326682,2326683,2326686,2326688,2326689,2328472,2328573,2328894,2328895,2328896,2328898,2328899,2328900,2328901,2328902,2328903,2328904,2328905,2328906,2328907,2328908,2328909,2328910,2328912,2328913,2328915,2328916,2328917,2328918,2328922,2328923,2328925,2328927,2328928,2328929,2328930,2328932,2328933,2329235,2329289,2329290,2329292,2329296,2329297,2329323,2329374,2329375,2329402,2329468,2330372,2330399,2330404,2330405,2330407,2330408,2330460,2330725,2330726,2330855,2330856,2330857,2330858,2330929,2330947,2330951,2330954,2330956,2330961,2330965,2330969,2330971,2330972,2330974,2330975,2330976,2330979,2330980,2330982,2330989,2330990,2330993,2330998,2331001,2331004,2331007,2331011,2331012,2331013,2331015,2331022,2331024,2331026,2331027,2331028,2331029,2331030,2331033,2331039,2331040,2331044,2331045,2331048,2331050,2331053,2331056,2331058,2331059,2331060,2331061,2331068,2331069,2331074,2331075,2331139,2331930,2331931,2332187,2332233,2332519,2332853,2332960,2332963,2333115,2333122,2334431,2334435,2334439,2334441,2334483,2334486,2336339,2336380,2336723,2336732,2336733,2336734,2336735,2336741,2336742,2336743,2336744,2336751,2336767,2336880,2336881,2336882,2336886,2337141,2337142,2337143,2337337,2338112,2339096,2340301,2340416,2340445,2340446,2341330,2341377,2341379,2341395,2341397,2341398,2341503,2342252,2342253,2342303,2342304,2342944,2343046,2343049,2343053,2344242,2345080,2345084,2346344,2346425,2348421,2348423,2348548,2348550,2348552,2348586,2348596,2348787,2348788,2348797,2348869,2348876,2348877,2348900,2348905,2350378,2350387,2350396,2350845,2353273,2353313,2353417,2353664,2353701,2356093,2357934,2359244,2359939,2359940,2359991,2360947,2361702,2361846,2365752,2365753,2365807,2366162,2367820,2371187,2377821,2383654,2387915,2399022,2400224,2400225,2400226,2400227,2400228,2400229,2400230,2400231,2400232,2400233,2400234,2400235,2400236,2400237,2400238,2400239,2400240,2400241,2400242,2400243,2400244,2400245,2400246,2400247,2400248,2400249,2400250,2400251,2400252,2400253,2400254,2400255,2400256,2400257,2400258,2400259,2400260,2400261,2400262,2400263,2400264,2400266,2400267,2400268,2400269,2400270,2400272,2400273,2400274,2400276,2400277,2400278,2400279,2400280,2400281,2400282,2400283,2400284,2400285,2400286,2400287,2400288,2400289,2400291,2400292,2400293,2400294,2400295,2400296,2400297,2400299,2400300,2400301,2400302,2400303,2400304,2400305,2400306,2400307,2400308,2400309,2400310,2400311,2400312,2400313,2400314,2400315,2400316,2400317,2400318,2400319,2400320,2400321,2400322,2400323,2400324,2400325,2400326,2400327,2400328,2400329,2400330,2400331,2400332,2400333,2400334,2400335,2400336,2400337,2400339,2400341,2400342,2400343,2400346,2400347,2400352,2400354,2400356,2400368,2400372,2400377,2400430,2400431,2400441,2400448,2400450,2400454,2400487,2549705,2612645,2618263,2653227,2653238,2662238,2664680,2665955,2706210,2731255,2751805,2752885,2765502,2831136,2884246,3202435,3307895,3308275";
        String ids[] = studentIds.split(",");
        for(String id:ids) {
            unBindStudentCard(StringUtils.objToInt(id));
        }
    }

    //解析亲情号
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
                System.out.println("亲情号解析失败：" + targetTelStr + "    " + targeTelNameStr);
                e.printStackTrace();
            }
        }
    }


    //绑卡接口
    public static void bindStudentCard(int schoolId, int studentId, String studentName, String cardNum, String imei,
                                       String imeiPhone, int saveDirectly, StringBuffer sb) {
        if (schoolId <= 0 || studentId <= 0 || StringUtils.isEmpty(studentName)) {
            logger.info("请求uc绑定卡号接口参数错误");
        }
        Map<String, Object> returnMap = null;
        try {
            String token = getRequestToken();
            String url = "http://cardapi.hejiaoyu.com.cn/uc/api/student/send/card/v2";
            Map<String, Object> paramMap = new HashMap();
            paramMap.put("schoolId", schoolId);
            paramMap.put("userId", studentId);
            paramMap.put("userName", studentName);
            paramMap.put("userType", 2);
            paramMap.put("cardNum", cardNum);
            paramMap.put("imei", imei);
            paramMap.put("imeiPhone", imeiPhone);
            paramMap.put("saveDirectly", saveDirectly);
            logger.info("请求uc绑定学生卡号接口:" + url);
            logger.info("请求参数：" + JSONObject.toJSONString(paramMap));
            returnMap = NetUtil.postHttpMethod(url, paramMap, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!returnMap.get("msg").equals("成功")) {
            sb.append(studentId + ",");
        }
        String returnStr = JSONObject.toJSONString(returnMap);
        logger.info("请求uc绑定卡号接口返回:" + returnMap);
    }

    //绑定亲情号
    public static void bindStudentFamiNumber(Integer userId, Integer userType, String famiNumberName1, String famiNumberName2, String famiNumberName3,
                                             String famiNumberNameSos, String famiNumber1, String famiNumber2, String famiNumber3,
                                             String famiNumberSos, String imei) {
        if (userId == null || userType == null || (StringUtils.isEmpty(famiNumber1) && StringUtils.isEmpty(famiNumber2)
                && StringUtils.isEmpty(famiNumber3) && StringUtils.isEmpty(famiNumberSos))) {
            logger.info("请求uc绑定亲情号接口参数错误");
        }
        String token = getRequestToken();
        String url = "http://cardapi.hejiaoyu.com.cn/position/api/terminalOutboundCall/saveOutbound/v2";
        Map<String, Object> paramMap = new HashMap();
        //中间件操作人用户固定
        paramMap.put("userId", 0);
        paramMap.put("realName", "融合平台");
        paramMap.put("studentId", userId);
        paramMap.put("userType", userType);
        paramMap.put("targetNameStr1", famiNumberName1);
        paramMap.put("targetNameStr2", famiNumberName2);
        paramMap.put("targetNameStr3", famiNumberName3);
        paramMap.put("targetNameStrSos", famiNumberNameSos);
        paramMap.put("targetTelStr1", famiNumber1);
        paramMap.put("targetTelStr2", famiNumber2);
        paramMap.put("targetTelStr3", famiNumber3);
        paramMap.put("targetTelStrSos", famiNumberSos);
        paramMap.put("teno", imei);
        logger.info("请求uc绑定亲情号接口:" + url + "，请求参数:" + JSONObject.toJSONString(paramMap));
        Map<String, Object> returnMap = null;
        try {
            returnMap = NetUtil.postHttpMethod(url, paramMap, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String returnStr = JSONObject.toJSONString(returnMap);
        logger.info("请求uc绑定亲情号接口返回:" + returnStr);
    }

    //解绑卡接口
    public static Map<String, Object> unBindStudentCard(Integer studentId) {
        try {
            String token = getRequestToken();
            String url = "http://cardapi.hejiaoyu.com.cn/uc/api/student/unbindImei/v1";
            Map<String, Object> paramMap = new HashMap();
            paramMap.put("studentId", studentId);
            logger.info("请求uc解绑学生卡号操作接口:" + url);
            Map<String, Object> returnMap = NetUtil.postHttpMethod(url, paramMap, token);
            logger.info("请求uc解绑学生卡号操作接口返回结果:" + returnMap + ",卡平台学生id为：" + studentId);
            return returnMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getRequestToken() {
        String token = EncryptUtil.encryptUser(11000, 99, 11000,
                0, "移动云中间件", "11000", 1);
        return token;
    }

}
