package com.vabas.repository.impl;

import com.vabas.ioutils.IoUtils;
import com.vabas.model.Label;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LabelSgavno {
    /*tpublic void save(Label label) throws FileNotFoundException {
        List<Label> labels = getAllInternal();
        AtomicBoolean flag = new AtomicBoolean(false);
        try {
            labels.forEach((a) -> {
                if (a.getId() == label.getId()) {
                    a.setId(label.getId());
                    a.setName(label.getName());
                    flag.set(true);
                }
            });
            if (!flag.get()) {
                labels.add(label);
            }
            IoUtils.writeToFile(labels, fileName);
        } catch (Exception er) {
            Sysem.out.println("Id not exist");
        }
    }*/
}
