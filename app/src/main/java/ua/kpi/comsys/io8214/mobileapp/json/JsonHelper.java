package ua.kpi.comsys.io8214.mobileapp.json;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.loader.ResourcesProvider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ua.kpi.comsys.io8214.mobileapp.dao.Film;
import ua.kpi.comsys.io8214.mobileapp.R;
import ua.kpi.comsys.io8214.mobileapp.dao.FilmDetailed;

public class JsonHelper {

    private Resources resources;
    private final Gson gson = new Gson();

    public JsonHelper(Resources resources) {
        this.resources = resources;
    }

    public ArrayList<Film> parseJsonToArrFilm(int resourceId) throws Exception{

        ArrayList<Film> films;

        InputStream inputStream = resources.openRawResource(resourceId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Type listType = new TypeToken<List<Film>>() {}.getType();
        films = gson.fromJson(reader, listType);

        return films;
    }


    public ArrayList<Film> parseJsonToArrFilm (InputStream is){
        ArrayList<Film> films;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        Type listType = new TypeToken<List<Film>>() {}.getType();
        films = gson.fromJson(bufferedReader, listType);
        return films;
    }

    public FilmDetailed parseJsonToFilm(int resourceId) throws Exception{
        FilmDetailed filmDetailed;
        String readLine;
        InputStream inputStream = resources.openRawResource(resourceId);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        filmDetailed = gson.fromJson(bufferedReader, FilmDetailed.class);
        return filmDetailed;
    }

    public String saveFilmArrToJson(ArrayList<Film> films){
        return gson.toJson(films);
    }

}
