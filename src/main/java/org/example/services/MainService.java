package org.example.services;

import jakarta.annotation.PostConstruct;
import org.example.configurations.Params;
import org.example.ui.IntervalOptimization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MainService {
    @Autowired
    private List<IntervalOptimization> methods;

    @PostConstruct
    public void post() {
        this.methods.forEach(method -> {
            Params.delta.forEach(del -> {
                System.out.println(method.getName() + "(del= " + del + "):" + method.getResult(Params.a0, Params.b0, del));
            });
        });
    }
}
