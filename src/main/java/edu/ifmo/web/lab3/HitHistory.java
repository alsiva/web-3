package edu.ifmo.web.lab3;

import org.primefaces.PrimeFaces;
import org.primefaces.shaded.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

        addHits(submittedHits);
    }

    @Inject ChartHit chartHit;
    public void addFromChart() {
        HitResult hitResult = new HitResult(chartHit.getX(), chartHit.getY(), chartHit.getR(), true);
        addHits(Collections.singletonList(hitResult));
    }


    private void addHits(List<HitResult> submittedHits) {
        hitResultList.addAll(submittedHits);

        String json = submittedHits.stream()
            .map(hit -> "{" +
                " x: " + hit.getX() + "," +
                " y: " + hit.getY() + "," +
                " r: " + hit.getR() + "," +
                " doesHit: " + hit.isDoesHit() + " }"
            )
            .collect(Collectors.joining(", ", "[", "]"));


        PrimeFaces.current().executeScript("addHits(" + json + ")");
    }
}
