package edu.ifmo.web.lab3;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class HitHistory implements Serializable {
    @Inject HitAttempt hitAttempt;

    private final List<HitResult> hitResultList = new ArrayList<>();

    public List<HitResult> getHitResultList() {
        return hitResultList;
    }

    public void submit() {
        int x = hitAttempt.getX();
        double y = hitAttempt.getY();

        List<HitResult> submittedHits = hitAttempt.getSelectedRadiusList()
            .stream()
            .map(radius -> new HitResult(x, y, radius, true))
            .collect(Collectors.toList());

        hitResultList.addAll(submittedHits);
    }
}
