package com.vabas.service;

import com.vabas.model.Label;
import com.vabas.model.Post;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PostService {
    public static int getMaxId(List<Post> t){
        int maxId;
        if(t.isEmpty()){
            maxId = 0;
        }
        else {
            t.sort(Comparator.comparing(Post::getId));
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

    public static boolean containPost(List<Post> postList, Post post){
        AtomicBoolean flag = new AtomicBoolean(false);
        postList.forEach((a) -> {
            if (a.getId() == post.getId()){
                flag.set(true);
            }
        });
        return flag.get();
    }
}
