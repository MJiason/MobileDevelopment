package ua.kpi.comsys.io8214.mobileapp.db;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import ua.kpi.comsys.io8214.mobileapp.dao.Film;
import ua.kpi.comsys.io8214.mobileapp.dao.FilmDetailed;
import ua.kpi.comsys.io8214.mobileapp.json.JsonHelper;

public class DataMethodsImpl implements DataMethods {
    private final String filmDataFile = "movieslist";
    private final String filename = "demoFile.txt";
    private final Context context;
    private JsonHelper jsonHelper;

    public DataMethodsImpl(Context context) {
        this.context = context;
        jsonHelper = new JsonHelper(context.getResources());
    }


    @Override
    public FilmDetailed getFilmById(String id) throws Exception {
        int filmRes = getResourceIdentifierByName(id, "raw");
        return jsonHelper.parseJsonToFilm(filmRes);
    }

    @Override
    public ArrayList<Film> getAll() throws Exception {
        int filmRes = getResourceIdentifierByName(filmDataFile, "raw");
        return jsonHelper.parseJsonToArrFilm(filmRes);
    }

    @Override
    public void SaveFilm(ArrayList<Film> filmArrayList) {
    }


    private int getResourceIdentifierByName(String name, String defType) {
        int identifier = context.getResources().getIdentifier(name,
                defType,
                context.getPackageName());
        return identifier;
    }


    public String readData() throws IOException {

            FileInputStream fin = context.openFileInput(filename);
            int a;
            StringBuilder temp = new StringBuilder();
            while ((a = fin.read()) != -1) {
                temp.append((char) a);
            }

            fin.close();
            return temp.toString();
    }

    public void writeData(String data)
    {
        try
        {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.flush();
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }


}
