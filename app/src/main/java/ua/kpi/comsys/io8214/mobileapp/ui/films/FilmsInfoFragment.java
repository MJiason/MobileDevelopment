package ua.kpi.comsys.io8214.mobileapp.ui.films;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ua.kpi.comsys.io8214.mobileapp.dao.Film;
import ua.kpi.comsys.io8214.mobileapp.R;
import ua.kpi.comsys.io8214.mobileapp.dao.FilmDetailed;

public class FilmsInfoFragment extends Fragment {
    private FilmDetailed film;
    private ImageView imageView;
    private TextView title;
    private TextView year;
    private TextView genre;
    private TextView director;
    private TextView actors;
    private TextView country;
    private TextView language;
    private TextView production;
    private TextView released;
    private TextView runtime;
    private TextView awards;
    private TextView rating;
    private TextView plot;


    public FilmsInfoFragment(FilmDetailed film) {
        this.film = film;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_films_info, container, false);
        // Inflate the layout for this fragment
        imageView = root.findViewById(R.id.filmImage);
        imageView.setImageResource(getDrawableIdByName(film.getPoster()));

        title = root.findViewById(R.id.filmTitle);
        year = root.findViewById(R.id.filmYear);
        genre = root.findViewById(R.id.filmGenre);
        director = root.findViewById(R.id.filmDirector);
        actors = root.findViewById(R.id.filmActors);
        country = root.findViewById(R.id.filmCountry);
        language = root.findViewById(R.id.filmLanguage);
        production = root.findViewById(R.id.filmProduction);
        released = root.findViewById(R.id.filmReleased);
        runtime = root.findViewById(R.id.filmRuntime);
        awards = root.findViewById(R.id.filmAwards);
        rating = root.findViewById(R.id.filmRating);
        plot = root.findViewById(R.id.filmPlot);

        title.setText(film.getTitle());
        year.setText(film.getYear());
        genre.setText(film.getGenre());
        director.setText(film.getDirector());
        actors.setText(film.getActors());
        country.setText(film.getCounty());
        language.setText(film.getLanguage());
        production.setText(film.getProduction());
        released.setText(film.getReleased());
        runtime.setText(film.getRuntime());
        awards.setText(film.getAwards());
        rating.setText(film.getImdbRating());
        plot.setText(film.getPlot());

        return root;
    }

    private int getDrawableIdByName(String name) {
        if (name.isEmpty()){
            return R.drawable.kpi_logo;
        }
        return getContext().getResources().getIdentifier(name.toLowerCase().replace(".jpg", ""),
                "drawable",
                getContext().getPackageName());
    }
}