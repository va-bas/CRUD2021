package com.vabas.controller;

import com.vabas.model.Label;
import com.vabas.model.Post;
import com.vabas.model.PostStatus;
import com.vabas.repository.impl.LabelRepositoryImpl;
import com.vabas.repository.impl.PostRepositoryImpl;
import com.vabas.service.LabelService;
import com.vabas.service.PostService;
import com.vabas.view.ForConsole;
import com.vabas.view.LabelView;
import com.vabas.view.PostView;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.*;

public class PostController {
    public static void startM() throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        do {
            PostView.show();
            String resp = sc.next();
            switch (resp)
            {
                case "1":
                    create();
                    break;
                case "2":
                    edit();
                    break;
                case "3":
                    delete();
                    break;
                case "4":
                    show();
                    break;
                case "5":
                    addListElementsForPost();
                    break;
                case "6":
                    isExit = true;
                    MainController.startM();
                    break;
                case "7":
                    isExit = true;
                    break;
                default:
                    System.out.println(ForConsole.ERROR_INPUT.getMessage());
                    break;
            }
        } while (!isExit);
        sc.close();
    }

    public static void create() throws Exception {
        Scanner sc = new Scanner(System.in);
        LabelRepositoryImpl lR = new LabelRepositoryImpl();
        PostRepositoryImpl pR = new PostRepositoryImpl();
        boolean isExit = false;
        PostView.create();
        String name = sc.next();
        int demonId = PostService.getMaxId(pR.getAll());
        Post newPost = new Post(demonId + 1, name);
        newPost.setPostLabelList(new ArrayList<>());
        try {
            do {
                PostView.showAddLabel();
                int ca = sc.nextInt();
                switch (ca){
                    case 1:
                        try {
                            List<Label> labels = lR.getAll();
                            LabelView.showLabelsList(labels);
                            LabelView.editId();
                            int labelId = sc.nextInt();
                            int maxId = LabelService.getMaxId(labels);
                            if (labelId > 0 && labelId <= maxId) {
                                Label label = lR.getById(labelId);
                                if (!label.getName().equals(LabelView.dell) &&
                                        !PostService.containLabel(newPost.getPostLabelList(),label)) {
                                    newPost.getPostLabelList().add(label);
                                }
                                else {
                                    System.out.println("Id not exist !!!!");
                                }
                            }
                        } catch (NullPointerException e) {
                            System.out.println("Id not exist !!!!");
                        }
                        break;
                    case 2:
                        isExit = true;
                        break;
                }
            }while (!isExit);
        } catch (InputMismatchException nfe) {
            System.out.println("It's not a number !!!");
        }
        newPost.setCreated(new Date().toString());
        newPost.setUpdated(newPost.getCreated());
        newPost.setPostStatus(PostStatus.ACTIVE);
        pR.save(newPost);
    }

    public static void edit() throws Exception {
        Scanner sc = new Scanner(System.in);
        PostRepositoryImpl pR = new PostRepositoryImpl();
        boolean isExit = false;
        List<Post> posts = pR.getAll();
        PostView.showPostsList(posts);
        int demonId = PostService.getMaxId(posts);
        try {
            do {
                PostView.editId();
                int id = sc.nextInt();
                Post post = pR.getById(id);
                if (id > 0 && demonId >= id && !post.getPostStatus().equals(PostStatus.DELETED)){
                    PostView.editName();
                    String content = sc.next();
                    post.setContent(content);
                    pR.save(post);
                    isExit = true;
                }
                else {
                    System.out.println("Id not exist !!!!");
                }
            }
            while (!isExit);
        }
        catch (InputMismatchException nfe) {
            System.out.println("It's not a number !!!");
        }
    }

    public static void delete() throws Exception {
        Scanner sc = new Scanner(System.in);
        PostRepositoryImpl pR = new PostRepositoryImpl();
        boolean isExit = false;
        List<Post> posts = pR.getAll();
        PostView.showPostsList(posts);
        int demonId = PostService.getMaxId(posts);
        try {
            do {
                PostView.editId();
                int id = sc.nextInt();
                Post post = pR.getById(id);
                if (id > 0 && demonId >= id && !post.getPostStatus().equals(PostStatus.DELETED)){
                    pR.deleteById(id);
                    isExit = true;
                }
                else {
                    System.out.println("Id not exist !!!!");
                }
            }
            while (!isExit);
        }
        catch (InputMismatchException nfe) {
            System.out.println("It's not a number !!!");
        }
    }

    public static void show() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        PostRepositoryImpl pR = new PostRepositoryImpl();
        boolean isExit = false;
        List<Post> posts = pR.getAll();
        PostView.showPostsList(posts);
        int demonId = PostService.getMaxId(posts);
        try{
            do {
                PostView.editId();
                int id = sc.nextInt();
                Post post = pR.getById(id);
                if (id > 0 && demonId >= id && !post.getPostStatus().equals(PostStatus.DELETED)){
                    if (!post.getPostLabelList().isEmpty()) {
                        PostView.showPost(post);
                        LabelView.showLabelsList(post.getPostLabelList());
                        isExit = true;
                    }
                    else{
                        PostView.showPost(post);
                        PostView.listEmpty();
                    }
                }
                else {
                    System.out.println("Id not exist !!!!");
                }
            }
        while (!isExit);
        }
        catch (InputMismatchException nfe) {
            System.out.println("It's not a number !!!");
        }
    }

    public static void addListElementsForPost() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        PostRepositoryImpl pR = new PostRepositoryImpl();
        LabelRepositoryImpl lR = new LabelRepositoryImpl();
        boolean isExit = false;
        List<Post> posts = pR.getAll();
        List<Label> labels = lR.getAll();
        PostView.showPostsList(posts);
        int demonId = PostService.getMaxId(posts);
        try{
            do {
                PostView.editId();
                int id = sc.nextInt();
                Post post = pR.getById(id);
                if (id > 0 && demonId >= id && !post.getPostStatus().equals(PostStatus.DELETED)){
                    PostView.showPost(post);
                    System.out.println(ForConsole.BORDER.getMessage());
                    System.out.println("This post contain labels:");
                    if (!post.getPostLabelList().isEmpty()) {
                        LabelView.showLabelsList(post.getPostLabelList());
                    }
                    else{
                        PostView.listEmpty();
                    }
                    System.out.println(ForConsole.BORDER.getMessage());
                    System.out.println("You can add this labels:");
                    List<Label> tmpList = new ArrayList<>();
                    labels.forEach((a) ->{
                        if (!PostService.containLabel(post.getPostLabelList(), a))
                        {
                            tmpList.add(a);
                        } });
                    LabelView.showLabelsList(tmpList);

                }
                else if (id == 0){
                    isExit = true;
                }
                else {
                    System.out.println("Id not exist !!!!");
                }
            }
            while (!isExit);
        }
        catch (InputMismatchException nfe) {
            System.out.println("It's not a number !!!");
        }
    }
}
