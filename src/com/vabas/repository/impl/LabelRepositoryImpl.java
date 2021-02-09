package com.vabas.repository.impl;

import com.vabas.ioutils.IoUtils;
import com.vabas.model.Label;
import com.vabas.repository.LabelRepository;
import com.vabas.ioutils.LabelIO;
import com.vabas.view.LabelView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class LabelRepositoryImpl implements LabelRepository {
    File fileName = new File("./src/com/vabas/resource/Labels.txt");

    @Override
    public Label getById(Integer id) throws FileNotFoundException {
        List<Label> labels = getAllInternal();
        return labels.stream().filter(a -> a.getId() == id).findFirst().orElse(new Label());
    }

    @Override
    public List<Label> getAll() throws FileNotFoundException {
        if (Objects.isNull(IoUtils.readFromFile(fileName))){
            return new ArrayList<>();
        }
        else{
            return IoUtils.readFromFile(fileName);
        }
    }

    @Override
    public void update(Label label) throws FileNotFoundException {
        List<Label> labels = getAllInternal();
        try {
            labels.forEach((a) -> {
                if (a.getId() == label.getId()) {
                    a.setId(label.getId());
                    a.setName(label.getName());
                }
            });
            IoUtils.writeToFile(labels, fileName);
        } catch (Exception er) {
            System.out.println("Id not exist");
        }
    }

    @Override
    public void save(Label label) throws FileNotFoundException {
        List<Label> labels = getAllInternal();
        try {
            labels.add(label);
            IoUtils.writeToFile(labels, fileName);
        } catch (Exception er) {
            System.out.println("Id not exist");
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        List<Label> labels = getAllInternal();
        if (LabelIO.getMaxId(labels) == id) {
            labels.forEach((a) ->
            {
                if (a.getId() == id) {
                    a.setName(LabelView.dell);
                }
            });
        } else {
            labels.removeIf((a) -> a.getId() == id);
        }
        IoUtils.writeToFile(labels, fileName);
    }

    private List<Label> getAllInternal() throws FileNotFoundException {
        if (Objects.isNull(IoUtils.readFromFile(fileName))){
            return new ArrayList<>();
        }
        else{
            return IoUtils.readFromFile(fileName);
        }
    }



    //------------------------------------------------/*@Override
    //    public Label save(Label label) throws FileNotFoundException {
        //        return null;
        //    }
        //
        //    @Override
        //    public Label update(Label label) throws FileNotFoundException {
        //        return null;
        //    }*/
}
