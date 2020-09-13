package com.myblog.blog.util;

import java.util.ArrayList;
import java.util.List;

public class typeConvertUtils {
    public List<Long> stringToList(String ids) {
        List<Long> list = new ArrayList<>();
        if("".equals(ids) && ids != null){
            String[] idArray = ids.split(",");
            for (int i=0; i < idArray.length; i++){
                list.add(Long.valueOf(idArray[i]));
            }
        }
        return list;
    }
}
