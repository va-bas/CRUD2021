package com.vabas.repository.impl;

import com.vabas.ioutils.IoUtils;
import com.vabas.model.Label;
import com.vabas.model.Post;
import com.vabas.model.PostStatus;
import com.vabas.repository.PostRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class PostRepositoryImpl implements PostRepository {
    File fileName = new File("./src/com/vabas/resource/Posts.txt");

    @Override
    public Post getById(Integer id) throws FileNotFoundException {
        List<Post> posts = getAllInternal();
        return posts.stream().filter(a -> a.getId() == id).findFirst().orElse(new Post());
    }

    @Override
    public List<Post> getAll() throws FileNotFoundException {
        if (Objects.isNull(IoUtils.readFromFile(fileName))){
            return new ArrayList<>();
        }
        else{
            return IoUtils.readFromFile(fileName);
        }
    }

    @Override
    public void update(Post post) throws FileNotFoundException {
        List<Post> posts = getAllInternal();
        try{
            posts.forEach((a) -> {
                if (a.getId() == post.getId()) {
                    a.setId(post.getId());
                    a.setContent(post.getContent());
                    a.setUpdated(new Date().toString());
                    a.setPostLabelList(post.getPostLabelList());
                    a.setPostStatus(PostStatus.ACTIVE);
                }
            });
            IoUtils.writeToFile(posts, fileName);
        }
        catch (Exception er){
            System.out.println("Id not exist");
        }
    }

    @Override
    public void save(Post post) throws FileNotFoundException {
        List<Post> posts = getAllInternal();
        try {
        posts.add(post);
        IoUtils.writeToFile(posts, fileName);
        }
        catch (Exception er){
            System.out.println("Id not exist");
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        List<Post> posts = getAllInternal();
        posts.forEach((a) ->
        {
            if (a.getId() == id) {
                a.setUpdated(new Date().toString());
                a.setPostStatus(PostStatus.DELETED);
            }
        });
        IoUtils.writeToFile(posts, fileName);
    }

    private List<Post> getAllInternal() throws FileNotFoundException {
        if (Objects.isNull(IoUtils.readFromFile(fileName))){
            return new ArrayList<>();
        }
        else{
            return IoUtils.readFromFile(fileName);
        }
    }
}

