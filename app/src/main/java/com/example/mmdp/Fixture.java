package com.example.mmdp;

public class Fixture {

    private String Hteam;
    private String Ateam;
    private String Date;
    private Integer Hscore;
    private Integer Ascore;


    Fixture(String home, String away, String date) {
        Hteam = home;
        Ateam = away;
        Date = date;
        Hscore = 0;
        Ascore = 0;


    }

    String GetHome() {
        return Hteam;
    }

    String GetAway() {
        return Ateam;
    }


    String GetDate() {
        return Date;
    }


}
