package com.qiang.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommonUtils {

    public static String parseJson(String key, String result) {//aaa.bbb[1].cc[2];
        if (StringUtils.isBlank(key)) return null;
        if (result == null) return null;
        JSONObject js = JSONObject.parseObject(result);
        key = key.trim();
        String[] keyArr = key.split("\\.");
        for (int i = 0; i < keyArr.length; i++) {
            if (js == null)
                return null;
            String k = keyArr[i];
            k = k.trim();
            if (StringUtils.isBlank(k))
                continue;
            else if (k.contains("[") && k.contains("]")) {
                String[] kSplit = k.split("\\[");
                JSONArray jsonArray = js.getJSONArray(kSplit[0]);
                if (jsonArray == null)
                    return null;

                if (i == keyArr.length - 1) {
                    return jsonArray.getString(Integer.valueOf(kSplit[1].split("\\]")[0]));
                } else {
                    js = jsonArray.getJSONObject(Integer.valueOf(kSplit[1].split("\\]")[0]));
                }
            } else {
                if (i == keyArr.length - 1) {
                    return js.getString(k);
                } else {
                    js = js.getJSONObject(k);
                }
            }
        }

        return js == null ? null : js.toString();
        //System.out.println("IWJW202247".equals(js.getJSONObject("1212").getString("encryptHouseId")));
    }


    public static String parseJson(String result) {
        if (result == null) {
            return null;
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        Map<String, Object> map = new LinkedHashMap<>();
        map = parseJsonObject(null, jsonObject, map);
        return parseMap(map);
    }

    private static Map<String, Object> parseJsonObject(String key, JSONObject result, Map<String, Object> map) {
        if (result == null) {
            return Collections.emptyMap();
        }
        for (String kk : result.keySet()) {
            String kkk = key == null ? kk : key + "." + kk;
            if (map.get(kkk) != null) {
                map.remove(kkk);
            }
            Object value = result.get(kk);
            if (value instanceof JSONObject) {
                for (String k : ((JSONObject) value).keySet()) {
                    map.put(kkk + "." + k, ((JSONObject) value).get(k));
                }
                parseJsonObject(kkk, (JSONObject) value, map);
            } else if (value instanceof JSONArray) {
                JSONArray array = (JSONArray) value;
                for (int i = 0; i < array.size(); i++) {
                    if (array.get(i) instanceof JSONObject) {
                        parseJsonObject(kkk + "[" + i + "]", (JSONObject) array.get(i), map);
                    } else if (array.get(i) instanceof JSONArray) {
                        JSONObject jo = new JSONObject();
                        jo.put(kkk + "[" + i + "]", array.get(i));
                        parseJsonObject(kkk + "[" + i + "]", jo, map);
                    } else {
                        map.put(kkk + "[" + i + "]", array.get(i));
                    }
                }
            } else {
                map.put(kkk, value);
            }
        }
        return map;

    }

    private static String parseMap(Map<String, Object> map) {
        if (map.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            sb.append("|check\t|");
            sb.append("parseJson\t|");
            sb.append(key).append("\t|");
            sb.append(map.get(key)).append("\t|");
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String args[]) {
//        System.out.print(parseJson("{\"1798089\":{\"houseId\":1798089,\"hasVideo\":true,\"houseVideoObject\":{\"taskId\":126002,\"videoType\":0,\"source\":1},\"picNum\":10,\"firstIsCover\":true,\"houseImageList\":[{\"type\":\"bedRoom\",\"description\":\"主卧\",\"imgKey\":\"housecheckpc/2016/11/29/7eb796ae560a4b71808d0215c2b177be\",\"orderId\":14,\"takePhotoTime\":1480355136000,\"addTime\":1480355136000,\"scaleType\":2},{\"type\":\"livingRoom\",\"description\":\"厅1\",\"imgKey\":\"housecheckpc/2016/11/29/ac4f6017d6394c189e890a9985f9aca6\",\"orderId\":4,\"takePhotoTime\":1480355136000,\"addTime\":1480355136000,\"scaleType\":2},{\"type\":\"livingRoom\",\"description\":\"厅2\",\"imgKey\":\"housecheckpc/2016/11/29/711d4e2c15dc449b899177ee736d4e74\",\"orderId\":5,\"takePhotoTime\":1480355136000,\"addTime\":1480355136000,\"scaleType\":2},{\"type\":\"bedRoom\",\"description\":\"次卧1\",\"imgKey\":\"housecheckpc/2016/11/29/ba9a366026cc406a973db4943f967c37\",\"orderId\":15,\"takePhotoTime\":1480355136000,\"addTime\":1480355136000,\"scaleType\":2},{\"type\":\"bedRoom\",\"description\":\"次卧2\",\"imgKey\":\"housecheckpc/2016/11/29/e48809abaa59495a90b766a4e554aaa9\",\"orderId\":16,\"takePhotoTime\":1480355136000,\"addTime\":1480355136000,\"scaleType\":2},{\"type\":\"bedRoom\",\"description\":\"次卧3\",\"imgKey\":\"housecheckpc/2016/11/29/0ddbe7af51da457f8071fa1adaa4fdd0\",\"orderId\":17,\"takePhotoTime\":1480355136000,\"addTime\":1480355136000,\"scaleType\":2},{\"type\":\"bedRoom\",\"description\":\"次卧4\",\"imgKey\":\"housecheckpc/2016/11/29/fad104d0756a47b1a060da217295a8d2\",\"orderId\":18,\"takePhotoTime\":1480355136000,\"addTime\":1480355136000,\"scaleType\":2},{\"type\":\"wc\",\"description\":\"卫生间1\",\"imgKey\":\"housecheckpc/2016/11/29/a826174c67d840dd8a9635104ab0b412\",\"orderId\":25,\"takePhotoTime\":1480355136000,\"addTime\":1480355136000,\"scaleType\":2},{\"type\":\"kitchen\",\"description\":\"厨房\",\"imgKey\":\"housecheckpc/2016/11/29/4bf0132a46774ab09f33cfa507b113b1\",\"orderId\":35,\"takePhotoTime\":1480355136000,\"addTime\":1480355136000,\"scaleType\":2},{\"type\":\"exterior\",\"description\":\"外景\",\"imgKey\":\"housecheckpc/2016/11/29/3cccd9d5c578416ab19e0da2bd3f22cc\",\"orderId\":37,\"takePhotoTime\":1480355136000,\"addTime\":1480355136000,\"scaleType\":2}],\"houseLayoutImage\":null},\"1798076\":{\"houseId\":1798076,\"hasVideo\":true,\"houseVideoObject\":{\"taskId\":125999,\"videoType\":0,\"source\":0},\"picNum\":2,\"firstIsCover\":true,\"houseImageList\":[{\"type\":\"bedRoom\",\"description\":\"主卧\",\"imgKey\":\"housecheck/2016/11/28/2b6e14823dc740e1aa55fd38528e9fdc\",\"orderId\":14,\"takePhotoTime\":1480317471000,\"addTime\":1480317520000,\"scaleType\":0},{\"type\":\"wc\",\"description\":\"卫生间1\",\"imgKey\":\"housecheck/2016/11/28/eeef22ad55424767a74319bc4af7e8c0\",\"orderId\":25,\"takePhotoTime\":1480317475000,\"addTime\":1480317521000,\"scaleType\":0}],\"houseLayoutImage\":null}}"));
//        System.out.print(parseJson("{\"k\":[1,2,3],\"key1\":2}\n" + "\t\t"));
    	System.out.println(parseJson("mobile","{'mobile':'15921135537'}"));
    }
}

