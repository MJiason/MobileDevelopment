package ua.kpi.comsys.io8214.mobileapp;

public class Data {
    private int hours = 18;
    private int minutes = 34;
    private int seconds = 18;

    public Data(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}

