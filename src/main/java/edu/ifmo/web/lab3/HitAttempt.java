package edu.ifmo.web.lab3;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static java.util.stream.Collectors.toList;

@Named
@RequestScoped
public class HitAttempt implements Serializable {
    private double x = 0;
    private double y = 0;

    private final List<RadiusCheckBox> checkBoxes = DoubleStream.of(1.0, 1.5, 2.0, 2.5, 3.0)
        .mapToObj(RadiusCheckBox::new)
        .collect(toList());

    public List<RadiusCheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
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
            .map(RadiusCheckBox::getValue)
            .collect(Collectors.toList());
    }

    public static class RadiusCheckBox implements Serializable {
        private boolean isSelected = false;
        private final double value;

        public RadiusCheckBox(double value) {
            this.value = value;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public double getValue() {
            return value;
        }
    }
}
