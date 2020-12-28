package com.gzgz.cloud.authority.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * @ClassName: CommUtils
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/28 18:00
 * @Version: 1.0
 */
public class CommUtils {
    /**
     * 替换多个字符串
     * @param content
     * @param resultParam
     * @param templateParam
     * @return
     */
    private static String replaceTemplateContent(String content, String resultParam, Map<String,String> templateParam){
        content=content.replace("${","").replace("}","");
        AtomicReference<String> str= new AtomicReference<>(content);
        Stream.of(resultParam.split(",")).forEach((s -> {
            str.set(StringUtils.replace(str.get(), s, templateParam.get(s)));
        }));
        return str.get();
    }

    public static void main(String[] args) {
        //        String str="a,b,v,c";
//        Map<String,String> map=new HashMap<>();
//        map.put("a","1");
//        map.put("b","2");
//        map.put("c","3");
//        map.put("d","3");
//        System.out.println(str.split(",").length+" "+map.size());
//        BusinessException.throwIt(str.split(",").length!=map.size(),"传入参数个数不对");
//
//        Stream.of(str.split(",")).forEach(s -> BusinessException.throwIt(StringUtils.isEmpty(map.get(s)),"传入参数不对："+s+""));
        String sy="金额：${amount}，消息:${msg}";
        sy=sy.replace("${","").replace("}","");
        AtomicReference<String> str= new AtomicReference<>(sy);
        String sparam="amount,msg";
        Map<String,String> map=new HashMap<>();
        map.put("amount","100");
        map.put("msg","就开始看见了士大夫看见路上打开链接");


        String re=replaceTemplateContent(sy,sparam,map);
        //str.replace()
        //str.replace();
        System.out.println(re);
    }
}
