package ua.kpi.comsys.io8214.mobileapp.db;

import java.io.IOException;
import java.util.ArrayList;

import ua.kpi.comsys.io8214.mobileapp.dao.Film;
import ua.kpi.comsys.io8214.mobileapp.dao.FilmDetailed;

public interface DataMethods {
    public FilmDetailed getFilmById(String id) throws Exception;
    public ArrayList<Film> getAll() throws Exception;
    public void SaveFilm(ArrayList<Film> filmArrayList);
}
