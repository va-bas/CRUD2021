package com.vabas.service;

import com.vabas.model.Writer;

import java.util.Comparator;
import java.util.List;

public class WriterService {
    public static int getMaxId(List<Writer> t){
        int maxId;
        if(t.isEmpty()){
            maxId = 0;
        }
        else {
            t.sort(Comparator.comparing(Writer::getId));
            maxId = t.get(t.size() - 1).getId();;
        }
        return maxId;
    }
}
