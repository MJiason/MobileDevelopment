package ua.kpi.comsys.io8214.mobileapp.db;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import ua.kpi.comsys.io8214.mobileapp.dao.Film;
import ua.kpi.comsys.io8214.mobileapp.dao.FilmDetailed;
import ua.kpi.comsys.io8214.mobileapp.json.JsonHelper;

public class DataMethodsInternalStorage implements DataMethods {

    private Context context;
    private JsonHelper jsonHelper;
    private final String filmRes = "movieslist";
    private final String filmsData = "filmsData.json";


    public DataMethodsInternalStorage(Context context) {
        this.context = context;
        this.jsonHelper = new JsonHelper(context.getResources());
        internalStorageInit();
    }

    @Override
    public FilmDetailed getFilmById(String id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Film> getAll() throws Exception {
        return jsonHelper.parseJsonToArrFilm(readData());
    }

    @Override
    public void SaveFilm(ArrayList<Film> filmArrayList) {
        writeData(jsonHelper.saveFilmArrToJson(filmArrayList));
    }


    private InputStream readData() throws IOException {

        return context.openFileInput(filmsData);
    }

    private void writeData(String data) {
        try {
            FileOutputStream fos = context.openFileOutput(filmsData, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void internalStorageInit() {
        boolean isStorageEmpty = true;
        for (String file : context.fileList()) {
            if (file.equals(filmsData)) {
                isStorageEmpty = false;
                break;
            }
        }

        if (isStorageEmpty) {
            File newFile = new File(context.getFilesDir(), filmsData);

            try {
                FileWriter writer = new FileWriter(newFile);
                writer.write(getFilmsDataFromRes(filmRes, "raw"));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private int getResourceIdentifierByName(String name, String defType) {
        int identifier = context.getResources().getIdentifier(name,
                defType,
                context.getPackageName());
        return identifier;
    }

    private String getFilmsDataFromRes(String name, String defType) throws IOException {
        InputStream is = context.getResources().openRawResource(getResourceIdentifierByName(name, defType));
        int a;
        StringBuilder temp = new StringBuilder();
        while ((a = is.read()) != -1)
        {
            temp.append((char)a);
        }
        return temp.toString();
    }
}