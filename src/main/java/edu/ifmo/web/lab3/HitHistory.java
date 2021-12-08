package edu.ifmo.web.lab3;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class HitHistory implements Serializable {
    @Inject HitAttempt hitAttempt;

    private List<HitResult> hitResultList = new ArrayList<>();

    public List<HitResult> getHitResultList() {
        return hitResultList;
    }

    public void submit() {
    }
}
