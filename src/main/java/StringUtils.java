/**
 * @author <a href="mailto:wangxuhui@ssreader.cn">wangxuhui</a>
 * @version @date
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    /**
     * 对象为空，返回空字符串
     *
     * @param obj 对象
     * @return
     * @author :huangguangxi
     * @date :2017-9-25 下午3:08:54
     */
    public static String objToStr(Object obj) {
        if (null == obj) {
            return "";
        }
        if ("null".equals(obj.toString().trim())) {
            return "";
        }
        return obj.toString().trim();
    }
}