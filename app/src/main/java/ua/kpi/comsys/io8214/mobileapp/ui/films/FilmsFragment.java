package ua.kpi.comsys.io8214.mobileapp.ui.films;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ua.kpi.comsys.io8214.mobileapp.Film;
import ua.kpi.comsys.io8214.mobileapp.R;
import ua.kpi.comsys.io8214.mobileapp.json.JsonHelper;

public class FilmsFragment extends Fragment {

    private final String PATH_TO_FILE = "src/main/res/raw/movieslist.json";
    private FilmAdapter filmAdapter;
    private FilmsViewModel mViewModel;
    ArrayList<Film> films = new ArrayList<>();

    public static FilmsFragment newInstance() {
        return new FilmsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.films_fragment, container, false);
        ListView filmList =  root.findViewById(R.id.filmListView);
        try {
            films = JsonHelper.parseJsonToArr(getActivity().getBaseContext());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try {
            filmAdapter = new FilmAdapter(getActivity(), R.layout.list_view_items, films);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if (!(filmAdapter == null)){
            filmList.setAdapter(filmAdapter);
        }



        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FilmsViewModel.class);
        // TODO: Use the ViewModel
    }

}