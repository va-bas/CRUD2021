package com.vabas.service;

import com.vabas.model.Post;
import com.vabas.model.Writer;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public static boolean containWriter(List<Writer> writerList, Writer writer){
        AtomicBoolean flag = new AtomicBoolean(false);
        writerList.forEach((a) -> {
            if (a.getId() == writer.getId()){
                flag.set(true);
            }
        });
        return flag.get();
    }
}
