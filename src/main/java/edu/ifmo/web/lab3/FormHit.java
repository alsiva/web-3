package edu.ifmo.web.lab3;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FormHit implements Serializable {
    private int x = 0;
    private double y = 0;

    private final List<RadiusCheckBox> checkBoxes = Arrays.asList(
        new RadiusCheckBox(1.0, false),
        new RadiusCheckBox(1.5, false),
        new RadiusCheckBox(2.0, true),
        new RadiusCheckBox(2.5, false),
        new RadiusCheckBox(3.0, false)
    );

    public List<RadiusCheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public List<Double> getSelectedRadiusList() {
        return checkBoxes.stream()
            .filter(RadiusCheckBox::isSelected)
            .map(RadiusCheckBox::getRadius)
            .collect(Collectors.toList());
    }

    public static class RadiusCheckBox implements Serializable {
        private boolean isSelected;
        private final double radius;

        public RadiusCheckBox(double radius, boolean isSelected) {
            this.radius = radius;
            this.isSelected = isSelected;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public double getRadius() {
            return radius;
        }
    }
}
