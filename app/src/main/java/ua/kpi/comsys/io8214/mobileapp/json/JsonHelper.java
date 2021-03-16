package ua.kpi.comsys.io8214.mobileapp.json;

import android.content.Context;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ua.kpi.comsys.io8214.mobileapp.Film;
import ua.kpi.comsys.io8214.mobileapp.R;

public class JsonHelper {



    public static ArrayList<Film> parseJsonToArr(Context context) throws Exception{

        Resources resources = context.getResources();

        ArrayList<Film> films;

        InputStream inputStream = resources.openRawResource(R.raw.movieslist);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Film>>() {}.getType();
        films = gson.fromJson(reader, listType);


        return films;
    }

}
