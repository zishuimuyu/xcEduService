package com.xuecheng.framework.utils;

import com.alibaba.fastjson.JSONObject;
import com.xuecheng.framework.pojo.AccessToken;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 微信工具类
 */
public class WeiXinUtil {

    public static final String APPID = "";
    public static final String APPSECRET = "";
    public static final String TOKEN = "mlnsoft";

    /**
     * 验证微信签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{TOKEN, timestamp, nonce};
        //排序
        Arrays.sort(arr);
        //生成字符串
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        //sha1加密
        String tem = getSha1(sb.toString());
        return tem.equals(signature);
    }

    /**
     * 加密签名
     *
     * @param str 被加密的签名
     * @return
     */
    public static String getSha1(String str) {
        if (null == str || 0 == str.length()) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 以get形式获取accesstoken
     *
     * @return String
     */
    public static String getAccessToken() {
        String URL = "https://api.weixin.qq.com/cgi-bin/token";
        String param = "grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;
        String rs = HttpRequest.sendGet(URL, param);
        JSONObject json = JSONObject.parseObject(rs);
        //字符串转json
        if (json.containsKey("access_token")) {
            AccessToken accessToken = JSONObject.toJavaObject(json, AccessToken.class);
            //json转对象
            return accessToken.getAccess_token();
        } else {
            return "error";
        }
    }

    /**
     * 获取公众号关注者openid列表
     *
     * @param accessToken
     * @return
     */
    public static List<String> getOpenidList(String accessToken) {
   /*     String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken;
        JSONObject resStr = HttpHelperUtil.httpGet(url).getJSONObject("data");
        ArrayList<String> openIdList = new ArrayList<>();
        if (resStr != null && resStr.containsKey("openid")) {
            JSONArray openid = resStr.getJSONArray("openid");
            for (int i = 0; i < openid.size(); i++) {
                String openidStr = openid.getString(i);
                openIdList.add(openidStr);
            }
        }
        return openIdList;*/
        return null;
    }

    /**
     * 把微信传过来的xml转成map
     *
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request)
            throws IOException, DocumentException {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);
        Element root = doc.getRootElement();
        List<Element> elementList = root.elements();
        for (Element element : elementList) {
            map.put(element.getName(), element.getText());
        }
        ins.close();
        return map;
    }


    /**微信文本对象转成xml字符串返回
     * @param message
     * @return
     */
 /*   public static String textMessageToXml(TextMessage message){
        XStream xStream = new XStream();
        xStream.alias("xml", message.getClass());
        return xStream.toXML(message);

    }*/

    /**微信图片对象转成xml字符串返回
     * @param image
     * @return
     */
  /*  public static String imageMessageToXml(ImagetMessage image){
        XStream xStream = new XStream();
        xStream.alias("xml", image.getClass());
        xStream.alias("image", image.getImage().getClass());
        return xStream.toXML(image);

    }*/

    /**
     * 将地理位置转化为xml
     * @param location
     * @return
     */

   /* public static String locationToXml(Location location){
        XStream xStream = new XStream();
        xStream.alias("xml", location.getClass());
        return xStream.toXML(location);
    }*/

    /**
     * 将微信图文消息转化为xml
     *
     * @param
     * @return
     */

    /*public static String newsMessageToXml(NewsMessage newsMessage ){
        XStream xStream = new XStream();
        xStream.alias("xml", newsMessage.getClass());
        xStream.alias("item", new ItemOfNewsMessage().getClass());
        return xStream.toXML(newsMessage);
    }*/
}
