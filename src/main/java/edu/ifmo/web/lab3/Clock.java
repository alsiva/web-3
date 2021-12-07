package edu.ifmo.web.lab3;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ManagedBean(name = "clock")
@ApplicationScoped
public class Clock {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMMM yyyy, HH:mm:ss");

    public final String getCurrentTime() {
        return dtf.format(LocalDateTime.now());
    }
}
