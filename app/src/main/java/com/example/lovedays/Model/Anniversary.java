package com.example.lovedays.Model;

/**
 * Created by KING JINHO on 2019-08-07
 */
public class Anniversary {

    private boolean isPassed;
    private boolean isUpcoming;
    private String dateAnniversary;     //기념일 날짜
    private String nameAnniversary;         //기념일
    private String dateFromToday = "D"; //오늘 날짜 - 각 기념일
    private int dateFrom;

    public Anniversary(boolean isPassed, boolean isUpcoming, String dateAnniversary, String nameAnniversary, int dateFrom) {
        this.isPassed = isPassed;
        this.isUpcoming = isUpcoming;
        this.nameAnniversary = nameAnniversary;
        this.dateAnniversary = dateAnniversary;
        dateFromToday += (this.isPassed ? "+" + dateFrom : "-" + dateFrom);
    }

    public boolean isPassed() {
        return isPassed;
    }

    public String getDateAnniversary() {
        return dateAnniversary;
    }

    public String getNameAnniversary() {
        return nameAnniversary;
    }

    public String getDateFromToday() {
        return dateFromToday;
    }

    public boolean isUpcoming() {
        return isUpcoming;
    }
}
