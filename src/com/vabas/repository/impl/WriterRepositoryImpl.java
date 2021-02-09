package com.vabas.repository.impl;

import com.vabas.ioutils.IoUtils;
import com.vabas.model.Writer;
import com.vabas.repository.WriterRepository;
import com.vabas.view.WriterView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class WriterRepositoryImpl implements WriterRepository {
    File fileName = new File("./src/com/vabas/resource/Writers.txt");

    @Override
    public Writer getById(Integer id) throws FileNotFoundException {
        List<Writer> writers = getAllInternal();
        return writers.stream().filter(a -> a.getId() == id).findFirst().orElse(new Writer());
    }

    @Override
    public List<Writer> getAll() throws FileNotFoundException {
        if (Objects.isNull(IoUtils.readFromFile(fileName))){
            return new ArrayList<>();
        }
        else{
            return IoUtils.readFromFile(fileName);
        }
    }

    @Override
    public void save(Writer writer) throws FileNotFoundException {
        List<Writer> writers = getAllInternal();
        AtomicBoolean flag = new AtomicBoolean(false);
        try{
            writers.forEach((a) -> {
                if (a.getId() == writer.getId()) {
                    a.setId(writer.getId());
                    a.setFirstName(writer.getFirstName());
                    a.setLastName(writer.getLastName());
                    a.setPostsList(writer.getPostsList());
                    flag.set(true);
                }
            });
            if (!flag.get()){
                writers.add(writer);
            }
            IoUtils.writeToFile(writers, fileName);
        }
        catch (Exception er){
            System.out.println("Id not exist");
        }

    }

    @Override
    public void deleteById(Integer id) throws Exception {
        List<Writer> writers = getAllInternal();
        writers.forEach((a) ->
        {
            if (a.getId() == id) {
                a.setLastName(WriterView.dell);
            }
        });
        IoUtils.writeToFile(writers, fileName);
    }

    public List<Writer> getAllInternal() throws FileNotFoundException {
        if (Objects.isNull(IoUtils.readFromFile(fileName))){
            return new ArrayList<>();
        }
        else{
            return IoUtils.readFromFile(fileName);
        }
    }
}
