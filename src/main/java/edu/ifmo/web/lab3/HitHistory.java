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
    private final List<HitResult> hitResultList = new ArrayList<>();

    public List<HitResult> getHitResultList() {
        return hitResultList;
    }

    @Inject FormHit formhit;
    public void addFromForm() {
        int x = formhit.getX();
        double y = formhit.getY();

        List<HitResult> submittedHits = formhit.getSelectedRadiusList()
            .stream()
            .map(radius -> new HitResult(x, y, radius, true))
            .collect(Collectors.toList());

        hitResultList.addAll(submittedHits);
    }

    @Inject ChartHit chartHit;
    public void addFromChart() {
        HitResult hitResult = new HitResult(chartHit.getX(), chartHit.getY(), chartHit.getR(), true);
        hitResultList.add(hitResult);
    }
}
