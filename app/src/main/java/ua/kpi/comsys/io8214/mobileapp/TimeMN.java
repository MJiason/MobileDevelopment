package ua.kpi.comsys.io8214.mobileapp;


public class TimeMN {
    private int hours;
    private int minutes;
    private int seconds;

    public TimeMN() {
    }

    public TimeMN(Data data) {
        timeInit(data);
    }


    public void timeInit() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }


    public void timeInit(int hours, int minutes, int seconds) throws IllegalArgumentException {

        if (hours > 23 || hours < 0) {
            throw new IllegalArgumentException("Invalid hours param");
        }

        this.hours = hours;

        if (minutes > 59 || minutes < 0) {
            throw new IllegalArgumentException("Invalid minutes param");
        }
        this.minutes = minutes;

        if (seconds > 59 || seconds < 0) {
            throw new IllegalArgumentException("Invalid seconds param");
        }

        this.seconds = seconds;
    }

    public void timeInit(Data data) {
        timeInit(data.getHours(), data.getMinutes(), data.getSeconds());
    }

    public String getTime() {
        String time;
        String end;

        if (hours > 12){
            end = "PM";
            hours -= 12;
        }else {
            end = "AM";
        }

        if (hours < 10) {
            time = "0" + hours + ":";
        } else {
            time = hours + ":";
        }

        if (minutes < 10) {
            time = time + "0" + minutes + ":";
        } else {
            time = time + minutes + ":";
        }

        if (seconds < 10) {
            time = time + "0" + seconds;
        } else {
            time = time + seconds;
        }

        return time + end;

    }

    private static Data sumTimeCorr(int seconds, int minutes, int hours) {
        if (seconds > 59) {
            seconds = seconds - 60;
            minutes++;
        }
        if (minutes > 59) {
            minutes = minutes - 60;
            hours++;
        }
        if (hours > 23) {
            hours -= 24;
        }
        return new Data(hours, minutes, seconds);
    }

    private static Data subTimeCorr(int seconds, int minutes, int hours) {
        if (seconds < 0) {
            seconds = seconds + 60;
            minutes--;
        }
        if (minutes < 0) {
            minutes = minutes + 60;
            hours--;
        }
        if (hours < 0) {
            hours += 24;
        }
        return new Data(hours, minutes, seconds);
    }

    public TimeMN sumTime(TimeMN timeMN) {
        int seconds = this.seconds + timeMN.getSeconds();
        int minutes = this.minutes + timeMN.getMinutes();
        int hours = this.hours + timeMN.getHours();
        return new TimeMN(sumTimeCorr(seconds, minutes, hours));
    }

    public TimeMN subTime(TimeMN timeMN) {
        int seconds = this.seconds - timeMN.getSeconds();
        int minutes = this.minutes - timeMN.getMinutes();
        int hours = this.hours - timeMN.getHours();

        return new TimeMN(subTimeCorr(seconds, minutes, hours));
    }

    public static TimeMN sumTime(TimeMN timeMN1, TimeMN timeMN2) {
        int seconds = timeMN1.getSeconds() + timeMN2.getSeconds();
        int minutes = timeMN1.getMinutes() + timeMN2.getMinutes();
        int hours = timeMN1.getHours() + timeMN2.getHours();
        return new TimeMN(sumTimeCorr(seconds, minutes, hours));
    }

    public static TimeMN subTime(TimeMN timeMN1, TimeMN timeMN2) {
        int seconds = timeMN1.getSeconds() - timeMN2.getSeconds();
        int minutes = timeMN1.getMinutes() - timeMN2.getMinutes();
        int hours = timeMN1.getHours() - timeMN2.getHours();
        return new TimeMN(subTimeCorr(seconds, minutes, hours));
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
