
import java.util.*;

/**
 * @ClassName BingCardUtil
 * @Description TODO
 * @Author liuzhichao
 * @Date 2021/4/15 14:56
 * @Version 1.0
 */
public class AuthUtil {


    /**
     * create by: zhangpk
     * description: 生成签名
     * create time: 11:46 2021/4/25
     *
     * @return
     * @Param: null
     */
    public static String generateSignature(final Map<String, String> data, String key, ZxSignUtil.SignType signType) throws Exception {
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
        if (ZxSignUtil.SignType.MD5.equals(signType)) {
            return ZxSignUtil.MD5(sb.toString()).toUpperCase();
        } else {
            throw new Exception(String.format("Invalid sign_type: %s", signType));
        }
    }


}
