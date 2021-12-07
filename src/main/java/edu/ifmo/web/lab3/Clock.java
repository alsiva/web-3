package edu.ifmo.web.lab3;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ManagedBean(name = "clock")
@ViewScoped
public class Clock {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMMM yyyy, HH:mm:ss");

    public String getTime() {
        return dtf.format(LocalDateTime.now());
    }
}
