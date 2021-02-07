package com.vabas.service;

import com.vabas.model.Label;

import java.util.Comparator;
import java.util.List;

public class LabelService {
    public static int getMaxId(List<Label> t){
        int maxId;
        if(t.isEmpty()){
            maxId = 0;
        }
        else {
            t.sort(Comparator.comparing(Label::getId));
            maxId = t.get(t.size() - 1).getId();;
        }
        return maxId;
    }
}
