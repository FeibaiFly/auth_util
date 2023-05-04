package GeJie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class NetUtil {

    private static Logger logger = LogManager.getLogger(NetUtil.class);

    /**
     * 获取IP
     *
     * @param request
     * @return
     */
//    public static String getIpAddr(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }

    /**
     * post请求
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String httpPost(String ytdUrl) throws IOException {
        URL url = new URL(ytdUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        InputStream inputStream = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        StringBuffer sb = new StringBuffer();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append("\r\n");
            line = br.readLine();
        }
        br.close();
        inputStream.close();
        connection.disconnect();
        return sb.toString();
    }

    /**
     * 发送HttpPost请求
     *
     * @param strURL   url地址
     * @param paramMap 传参，Map对象
     * @param token    token,访问hdkt项目时,传token;访问极光推送时,token为null
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> postHttpMethod(String strURL, Map<String, Object> paramMap, String token)
            throws IOException {
        // paramMap解析为字符串
        String params = null;
        try {
            params = JSON.toJSONString(paramMap);
        } catch (Exception e1) {
            e1.printStackTrace();
            logger.error("map转换json字符串失败");
            return null;
        }
        HttpURLConnection connection = null;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            URL url = new URL(strURL);// 创建连接
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            if (null != token) {
                connection.setRequestProperty("token", token); // 设置请求头，token
            }
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuffer sb = new StringBuffer();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\r\n");
                line = br.readLine();
            }
            br.close();
            inputStream.close();
            resultMap = (Map<String, Object>) JSONObject.parse(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return resultMap;
    }


    /**
     * 发送HttpPost请求,带超时时间
     *
     * @param strURL   url地址
     * @param paramMap 传参，Map对象
     * @param token    token,访问hdkt项目时,传token;访问极光推送时,token为null
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> postHttpMethod(String strURL, Map<String, Object> paramMap, String token, int timeOut)
            throws Exception {
        // paramMap解析为字符串
        String params = JSON.toJSONString(paramMap);
        HttpURLConnection connection = null;
        Map<String, Object> resultMap = new HashMap<>();
        URL url = new URL(strURL);// 创建连接
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestMethod("POST"); // 设置请求方式
        connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
        connection.setConnectTimeout(timeOut);
        connection.setReadTimeout(timeOut);
        if (null != token) {
            connection.setRequestProperty("token", token); // 设置请求头，token
        }
        connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
        connection.connect();
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
        out.append(params);
        out.flush();
        out.close();
        // 读取响应
        InputStream inputStream = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        StringBuffer sb = new StringBuffer();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append("\r\n");
            line = br.readLine();
        }
        br.close();
        inputStream.close();
        resultMap = (Map<String, Object>) JSONObject.parse(sb.toString());
        return resultMap;
    }


    /**
     * 发送HttpPost请求
     *
     * @param strURL   url地址
     * @param paramMap 传参，Map对象
     * @param token    token
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static String sendHttpMethod(String strURL, Map<String, Object> map) throws IOException {
        // paramMap解析为字符串
        String params = null;
        StringBuffer sb = new StringBuffer();
        try {
            params = JSON.toJSONString(map);
        } catch (Exception e1) {
            e1.printStackTrace();
            logger.error("map转换json字符串失败");
            return null;
        }
        HttpURLConnection connection = null;
        try {
            URL url = new URL(strURL);// 创建连接
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.write("action=" + params);
            out.flush();
            out.close();
            // 读取响应
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\r\n");
                line = br.readLine();
            }
            br.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public static String getHttpMethod(String strURL, Map<String, Object> map, String token) throws IOException {
        // paramMap解析为字符串
        String params = null;
        StringBuffer sb = new StringBuffer();
        try {
            params = JSON.toJSONString(map);
        } catch (Exception e1) {
            e1.printStackTrace();
            logger.error("map转换json字符串失败");
            return null;
        }
        HttpURLConnection connection = null;
        try {
            URL url = new URL(strURL);// 创建连接
            connection = (HttpURLConnection) url.openConnection();
            if (!StringUtils.isEmpty(token)) {
                connection.setRequestProperty("token", token);
            }
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.write("?" + params);
            out.flush();
            out.close();
            // 读取响应
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\r\n");
                line = br.readLine();
            }
            br.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }

    public static String getHttpMethodV2(String strURL, Map<String, Object> map, String token) throws IOException {
        // paramMap解析为字符串
        String params = null;
        StringBuffer sb = new StringBuffer();
        try {
            params = JSON.toJSONString(map);
        } catch (Exception e1) {
            e1.printStackTrace();
            logger.error("map转换json字符串失败");
            return null;
        }
        HttpURLConnection connection = null;
        try {
            URL url = new URL(strURL);// 创建连接
            connection = (HttpURLConnection) url.openConnection();
            if (!StringUtils.isEmpty(token)) {
                connection.setRequestProperty("token", token);
            }
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("GET"); // 设置请求方式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.write("?" + params);
            out.flush();
            out.close();
            // 读取响应
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\r\n");
                line = br.readLine();
            }
            br.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }

    /**
     * @param token chkOauthUrl
     * @throws Exception
     * @author yuanjinxin
     * @Description http请求发送get请求
     * @time 2018年4月16日 上午10:29:24
     */
    @SuppressWarnings("unchecked")
    public static String httpGetUserInfo(String token, String chkOauthUrl) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("accesstoken", token);
        OutputStreamWriter out = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(chkOauthUrl);
            // http协议传输
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            if (StringUtils.isBlank(chkOauthUrl)) {
                return null;
            }
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod("GET");
            // 2.传入参数部分
            // 得到请求的输出流对象
            out = new OutputStreamWriter(httpUrlConn.getOutputStream(), "UTF-8");
            // 把数据写入请求的Body
            out.write("data=" + map);
            out.flush();
            out.close();
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            System.out.println(buffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

    /**
     * @Title:InputStream
     * @Description:传输byte数据
     * @author: xuezhilei
     * @date: 2018年12月6日下午5:05:03
     * @parameter:url 请求的url PostData byte类型的数据
     * @return:
     * @throws:
     */
    public static String httpPostByte(String url, byte[] PostData) {
        URL u = null;
        HttpURLConnection con = null;
        InputStream inputStream = null;
        StringBuffer sb = new StringBuffer();
        // 尝试发送请求
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "binary/octet-stream");
            OutputStream outStream = con.getOutputStream();
            outStream.write(PostData);
            outStream.flush();
            outStream.close();
            // 读取返回内容
            inputStream = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            br.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
                ;
            }
        }
        return sb.toString();
    }

    /**
     * 鑫源翼智云 GET请求
     */
    public static Map<String, Object> getHttpMethod(String url) {
        StringBuffer result = new StringBuffer();
        Map<String, Object> resultMap = new HashMap<>();
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            resultMap = (Map<String, Object>) JSONObject.parse(result.toString());
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        return resultMap;
    }


    public static Map<String, Object> getHJYHttpMethod(String url) throws Exception {
        StringBuffer result = new StringBuffer();
        Map<String, Object> resultMap = new HashMap<>();
        BufferedReader in = null;
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection connection = realUrl.openConnection();
        // 设置通用的请求属性
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        // 建立实际的连接
        connection.connect();
        // 定义 BufferedReader输入流来读取URL的响应
        in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String line;
        while ((line = in.readLine()) != null) {
            result.append(line);
        }
        resultMap = (Map<String, Object>) JSONObject.parse(result.toString());
        return resultMap;
    }

    /**
     * GET 请求带token
     *
     * @param strURL
     * @param token
     * @return
     * @throws IOException 参数
     *                     Map<String,Object>    返回类型
     * @throws
     * @Author huangguangxi
     * @Date 2021年3月16日下午5:24:46
     * @Description: TODO
     */
    public static Map<String, Object> getHttpMethod(String strURL, String token)
            throws IOException {

        HttpURLConnection connection = null;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            URL url = new URL(strURL);// 创建连接
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("GET"); // 设置请求方式
            if (null != token) {
                connection.setRequestProperty("token", token); // 设置请求头，token
            }
            connection.connect();
            // 读取响应
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuffer sb = new StringBuffer();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\r\n");
                line = br.readLine();
            }
            br.close();
            inputStream.close();
            resultMap = (Map<String, Object>) JSONObject.parse(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return resultMap;
    }

    /**
     * POST请求，参数为Map<String, String>
     *
     * @param strURL
     * @param paramMap
     * @param token
     * @return
     * @throws IOException 参数
     *                     Map<String,Object>    返回类型
     * @throws
     * @Author huangguangxi
     * @Date 2021年3月23日上午11:57:31
     * @Description: TODO
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> postHttpMethodStr(String strURL, String params, String token)
            throws IOException {

        HttpURLConnection connection = null;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            URL url = new URL(strURL);// 创建连接
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            if (null != token) {
                connection.setRequestProperty("token", token); // 设置请求头，token
            }
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuffer sb = new StringBuffer();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\r\n");
                line = br.readLine();
            }
            br.close();
            inputStream.close();
            resultMap = (Map<String, Object>) JSONObject.parse(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return resultMap;
    }

    /**
     * 河南专用
     */
    public static Map<String, Object> getHttpMethodHenan(String url) {
        StringBuffer result = new StringBuffer();
        Map<String, Object> resultMap = new HashMap<>();
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
            connection.setRequestProperty("Cache-Control", "max-age=0");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36 Edg/89.0.774.63");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            resultMap = (Map<String, Object>) JSONObject.parse(result.toString());
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        return resultMap;
    }
    /**
     * create by: 和教育教师端登录
     * description: TODO
     * create time: 17:08 2020/9/3
     *
     * @return
     * @Param: null
     */
//    public Response httpsPost(String url) throws IOException {
//        OkHttpClient client = new OkHttpClient().newBuilder().build();
//        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//        RequestBody body = RequestBody.create(mediaType);
//        Request request = new Request.Builder().url("https://edu.10086.cn/test-sso/rest/newtickets?username=13719939201&password=1q2w3e4r").method("POST",body).addHeader("Content-Type","application/x-www-form-urlencoded").build();
//        Response response = client.newCall(request).execute();
//        String location = response.header("Location");
//        System.out.println(url);
//        return response;
//    }
}
