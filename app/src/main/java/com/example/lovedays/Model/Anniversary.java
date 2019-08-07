package com.example.lovedays.Model;

/**
 * Created by KING JINHO on 2019-08-07
 */
public class Anniversary {
    /*
     * 100일, 200일 300일 1주년 -> 시작날짜에서 100씩
     * 밑에 날짜: 시작날짜에서 차근차근
     * 지나간 날짜, 아직 안지난 날짜 색깔 구분: 오늘 날짜랑 비교해서 색깔 구분
     * 곧 다가올 날짜: 로직 추가 생각(아마 recyclerview에서 바로 전 아이템이랑 다음 아이템 비교해서 isPassed가 true,false각각 나오면 upcoming true
     *  D+100, D-100 : 오늘 날짜 - 기념일
     * */

    private boolean isPassed;
    private boolean isUpcoming = false;
    private String dateAnniversary;     //기념일 날짜
    private String nameAnniversary;         //기념일
    private String dateFromToday = "D"; //오늘 날짜 - 각 기념일
    private int dateFrom;

    public Anniversary(boolean isPassed, String dateAnniversary, String nameAnniversary, int dateFrom) {
        this.isPassed = isPassed;
        this.nameAnniversary = nameAnniversary;
        this.dateAnniversary = dateAnniversary;
        dateFromToday += (this.isPassed? "+" +dateFrom : "-"+dateFrom);
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
}
