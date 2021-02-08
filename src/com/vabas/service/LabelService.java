package com.vabas.service;

import com.vabas.model.Label;
import com.vabas.view.LabelView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public static boolean containLabel(List<Label> labelList, Label label){
        AtomicBoolean flag = new AtomicBoolean(false);
        labelList.forEach((a) -> {
            if (a.getId() == label.getId()){
                flag.set(true);
            }
        });
        return flag.get();
    }

    //Проверка на то есть ли у поста в списке лейблов удаленные
    //Здесь выдает те лейблы которые можно добавить к посту
    public static List<Label> delLabel(List<Label> l1, List<Label> l2){
        List<Label> res = new ArrayList<>();
        l1.stream().filter((a) -> !containLabel(l2, a)).filter((a) -> !a.getName().equals(LabelView.dell))
        .forEach((a) -> res.add(a));
        return res;
    }

    //Здесь выдает те лейблы которые есть у поста без удаленных
    public static List<Label> notDelLabel(List<Label> l1, List<Label> l2){
        List<Label> res = new ArrayList<>();
        l1.stream().filter((a) -> containLabel(l2, a)).filter((a) -> !a.getName().equals(LabelView.dell))
                .forEach((a) -> res.add(a));
        return res;
    }
}
