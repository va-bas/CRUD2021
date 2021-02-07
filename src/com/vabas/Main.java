package com.vabas;

import com.vabas.controller.MainController;
import com.vabas.repository.impl.LabelRepositoryImpl;
import com.vabas.view.MainMenu;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws Exception {
        LabelRepositoryImpl labelRepository = new LabelRepositoryImpl();
        MainController.startM();



        /*Label label1 = new Label(1,"label1");
        Label label4 = new Label(4,"label4");
        Label label2 = new Label(2,"label55552");
        Label label3 = new Label(3,"label3");

        labelRepository.save(label1);
        labelRepository.save(label4);
        labelRepository.save(label3);
        labelRepository.save(label2);
        labelRepository.deleteById(3);

        System.out.println(labelRepository.getById(1) + "\n------\n" +
                labelRepository.getAll());*/

    }
}
