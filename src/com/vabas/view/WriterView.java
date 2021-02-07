package com.vabas.view;

import com.vabas.model.Label;
import com.vabas.model.Post;
import com.vabas.model.PostStatus;
import com.vabas.model.Writer;

import java.util.ArrayList;

public class WriterView {
    public static final String dell = "This writer was deleted";

    public static void show(){
        System.out.println(ForConsole.BORDER.getMessage());
        String mainMessage = "Choose an action with writer:\n" +
                " 1. Create\n" +
                " 2. Edit name\n" +
                " 3. Delete\n" +
                " 4. Show writer by id\n" +
                " 5. Create or edit posts list for writer by ID\n" +
                " 6. Main menu\n" +
                " 7. Exit";
        System.out.println(mainMessage);
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void showFirsName(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Enter first name: ");
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void showLastName(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Enter last name: ");
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void showCreateList(){
        System.out.println(ForConsole.BORDER.getMessage());
        String mainMessage = "Add post in posts list for writer ?\n" +
                " 1. Yes\n" +
                " 2. No" ;
        System.out.println(mainMessage);
        System.out.println(ForConsole.BORDER.getMessage());
    }



    public static void editId(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Enter writer id: ");
        System.out.println(ForConsole.BORDER.getMessage());
    }





    public static String ifLabelsListIsEmpty(ArrayList<Label> arr){
        String str = "";
        if (!arr.isEmpty()){
            for (Label label : arr) {
                if (!label.getName().equals(LabelView.dell))
                str += "Id: " + label.getId() + " Name: "
                        + label.getName() + "\n";
            }
            return str;
        }
        else{
            return "Labels is empty\n";
        }
    }

}
