package com.gzgz.cloud.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BeanCopyUtil
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/2 17:43
 * @Version: 1.0
 */
@Slf4j
public class BeanCopyUtil {
    public static Object getBean(Object souce, Class to) {
        if (souce == null){
            return null;
        }
        Object t = null;
        try {
            t =  to.newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            log.info(e.getMessage());
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            log.info(e.getMessage());
        }
        return copyProperties(t, souce) ? t : null;
    }

    public static Object getBeanList(Object souce, Class to) {
        List<Object> souce1=null;
        if(souce instanceof  List){
            souce1 = (List<Object>) souce;
        }else{
            return null;
        }
        List<Object> list1 = new ArrayList<>();
        if (souce1 != null && !souce1.isEmpty()) {
            for (Object org : souce1) {
                Object org1 = getBean(org, to);
                if (org1 != null) {
                    list1.add(org1);
                }
            }
        }
        return list1;
    }


    private static boolean copyProperties(Object dest, Object src) {
        try {
            BeanUtils.copyProperties(dest, src);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
