package ua.kpi.comsys.io8214.mobileapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ua.kpi.comsys.io8214.mobileapp.Data;
import ua.kpi.comsys.io8214.mobileapp.TimeMN;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private TimeMN timeMN = new TimeMN();
    private TimeMN timeMN1 = new TimeMN();
    private TimeMN timeMN2 = new TimeMN();
    private TimeMN timeMN3 = new TimeMN();
    private TimeMN timeMN4 = new TimeMN();
    private TimeMN timeMN5 = new TimeMN();
    private TimeMN timeMN6 = new TimeMN();
    private TimeMN timeMN7 = new TimeMN();
    private TimeMN timeMN8 = new TimeMN();
    private TimeMN timeMN9 = new TimeMN();

    public HomeViewModel() {
        String ex = "";
        mText = new MutableLiveData<>();
        timeMN1.timeInit();// initialize 0h, 0m, 0s
        timeMN2.timeInit(new Data(0, 0, 1)); // initialize with data obj;
        timeMN3.timeInit(23, 59, 59);
        timeMN4.timeInit(12, 0, 1);
        timeMN5.timeInit(14,2,45);
        timeMN6.timeInit(20,23,10);
        timeMN7.timeInit(17,13,59);
        timeMN8.timeInit(4,11,19);
        TimeMN timeMN12 = timeMN1.subTime(timeMN2);
        TimeMN timeMN34 = timeMN3.sumTime(timeMN4);
        TimeMN timeMN56 = TimeMN.sumTime(timeMN5, timeMN6);
        TimeMN timeMN78 = TimeMN.subTime(timeMN7, timeMN8);
        try {
            timeMN9.timeInit(50, 10, 11);
        }catch (Exception exception){
            ex = exception.getMessage();
        }

//        mText.setValue(timeMN1.getTime() + " - " + timeMN2.getTime() + " = " + timeMN12.getTime() + "\n"
//                + timeMN3.getTime() + " + " + timeMN4.getTime() + " = " + timeMN34.getTime() + "\n"
//                + timeMN5.getTime() + " + " + timeMN6.getTime() + " = " + timeMN56.getTime() + "\n"
//                + timeMN7.getTime() + " - " + timeMN8.getTime() + " = " + timeMN78.getTime() + "\n"
//                + "Exception message: " + ex);
    }

    public LiveData<String> getText() {
        return mText;
    }
}