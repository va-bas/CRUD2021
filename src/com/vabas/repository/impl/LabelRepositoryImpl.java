package com.vabas.repository.impl;

import com.vabas.ioutils.IoUtils;
import com.vabas.model.Label;
import com.vabas.repository.LabelRepository;
import com.vabas.service.LabelService;
import com.vabas.view.LabelView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LabelRepositoryImpl implements LabelRepository {
    File fileName = new File("./src/com/vabas/resource/Labels.txt");

    @Override
    public Label getById(Integer id) throws FileNotFoundException {
        List<Label> labels = getAll();
        /*AtomicReference<Label> tmp = new AtomicReference<>(new Label());
        try{
            labels.forEach((a) -> {
                if (a.getId() == id) {
                    tmp.set(a);
                }
            });
        }
        catch (Exception er){
            System.out.println("Id not exist");
        }
        return tmp.get();*/
        return labels.stream().filter(a -> a.getId() == id).findFirst().orElse(new Label());
    }

    @Override
    public List<Label> getAll() throws FileNotFoundException {
        if (IoUtils.ReadFromFile(fileName) == null){
            return new ArrayList<>();
        }
        else{
            return IoUtils.ReadFromFile(fileName);
        }
    }

    @Override
    public void save(Label label) throws FileNotFoundException {
        List<Label> labels = getAll();
        AtomicBoolean flag = new AtomicBoolean(false);
        try{
            labels.forEach((a) -> {
                if (a.getId() == label.getId()) {
                    a.setId(label.getId());
                    a.setName(label.getName());
                    flag.set(true);
                }
            });
            if (!flag.get()){
                labels.add(label);
            }
            IoUtils.WriteToFile(labels, fileName);
        }
        catch (Exception er){
            System.out.println("Id not exist");
        }

    }

    @Override
    public void deleteById(Integer id) throws Exception {
        List<Label> labels = getAll();
        if (LabelService.getMaxId(labels) == id) {
            labels.forEach((a) ->
            {
                if (a.getId() == id) {
                    a.setName(LabelView.dell);
                }
            });
        } else {
            labels.removeIf((a) -> a.getId() == id);
        }
        IoUtils.WriteToFile(labels, fileName);
    }
}
