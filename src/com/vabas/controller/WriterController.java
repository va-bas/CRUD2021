package com.vabas.controller;

import com.vabas.model.Post;
import com.vabas.model.PostStatus;
import com.vabas.model.Writer;
import com.vabas.repository.impl.PostRepositoryImpl;
import com.vabas.repository.impl.WriterRepositoryImpl;
import com.vabas.service.PostService;
import com.vabas.service.WriterService;
import com.vabas.view.ForConsole;
import com.vabas.view.PostView;
import com.vabas.view.WriterView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WriterController {
    public static void startM() throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        do {
            WriterView.show();
            String resp = sc.next();
            switch (resp) {
                case "1":
                    create();
                    break;
                case "2":
                    //edit();
                    break;
                case "3":
                    //delete();
                    break;
                case "4":
                    //show();
                    break;
                case "5":
                    //addListElementsForWriter();
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
        WriterRepositoryImpl wR = new WriterRepositoryImpl();
        PostRepositoryImpl pR = new PostRepositoryImpl();
        boolean isExit = false;
        WriterView.showFirsName();
        String fName = sc.next();
        int demonId = WriterService.getMaxId(wR.getAll());
        Writer newWriter = new Writer(demonId + 1, fName, "");
        WriterView.showLastName();
        String lName = sc.next();
        newWriter.setLastName(lName);
        newWriter.setPostsList(new ArrayList<>());
        try {
            do {
                WriterView.showCreateList();
                int ca = sc.nextInt();
                switch (ca){
                    case 1:
                        try {
                            List<Post> posts = pR.getAll();
                            PostView.showPostsList(posts);
                            PostView.editId();
                            int pId = sc.nextInt();
                            int maxId = PostService.getMaxId(posts);
                            if (pId > 0 && pId <= maxId) {
                                Post post = pR.getById(pId);
                                if (!post.getPostStatus().equals(PostStatus.DELETED) &&
                                        !PostService.containPost(newWriter.getPostsList(), post)) {
                                    newWriter.getPostsList().add(post);
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
        wR.save(newWriter);
        System.out.println(wR.getAll().toString());
    }

}
