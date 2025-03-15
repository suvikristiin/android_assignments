package com.example.week9;

import java.util.Calendar;

public class Note {

    public static int Idstatic = 0;;
    private String title;
    private String content;
    private String timeAndDate;
    private int Id;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        Idstatic++;
        this.Id = Idstatic;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        this.timeAndDate = hour + ":" + minute + " " + day + "." + month + "." + year;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return Id;
    }

    public String getTimeAndDate() {
        return timeAndDate;
    }
}
