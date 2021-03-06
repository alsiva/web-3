package edu.ifmo.web.lab3;

import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class HitHistory implements Serializable {
    @Inject DbManager dbManager;
    private List<HitResult> hitResultList;

    @PostConstruct
    public void initializeHits() {
        hitResultList = dbManager.getHits();
    }

    public List<HitResult> getHitResultList() {
        return hitResultList;
    }

    @Inject FormHit formhit;
    public void addFromForm() {
        int x = formhit.getX();
        double y = formhit.getY();

        List<HitResult> submittedHits = formhit.getSelectedRadiusList()
            .stream()
            .map(radius -> calculateHit(x, y, radius))
            .collect(Collectors.toList());

        addHits(submittedHits);
    }

    private HitResult calculateHit(double x, double y, double radius) {
        return new HitResult(x, y, radius, doesItHit(x, y, radius));
    }

    private boolean doesItHit(double x, double y, double radius) {
        if (-radius <= x && x <= 0 && 0 <= y && y <= (x + radius) / 2) {
            return true;
        }

        if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(radius, 2) && x >= 0 && y >= 0) {
            return true;
        }

        return 0 <= x && x <= radius && -radius / 2 <= y && y <= 0;
    }

    @Inject ChartHit chartHit;
    public void addFromChart() {
        HitResult hitResult = calculateHit(chartHit.getX(), chartHit.getY(), chartHit.getR());
        addHits(Collections.singletonList(hitResult));
    }

    private void addHits(List<HitResult> hits) {
        if (dbManager.addHits(hits)) {
            hitResultList.addAll(hits);
            addHitsToCanvas(hits);
        }
    }

    public void addStoredHitsToCanvas() {
        addHitsToCanvas(hitResultList);
    }

    private void addHitsToCanvas(List<HitResult> hits) {
        String json = hits.stream()
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
