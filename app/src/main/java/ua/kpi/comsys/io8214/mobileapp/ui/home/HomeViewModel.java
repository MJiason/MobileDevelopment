package ua.kpi.comsys.io8214.mobileapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("Невмержицький Михайло\nГрупа ІО-82\nЗК-8214");
    }

    public LiveData<String> getText() {
        return mText;
    }
}