package ua.kpi.comsys.io8214.mobileapp.ui.films;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;

import ua.kpi.comsys.io8214.mobileapp.FilmAdd;
import ua.kpi.comsys.io8214.mobileapp.dao.Film;
import ua.kpi.comsys.io8214.mobileapp.R;
import ua.kpi.comsys.io8214.mobileapp.db.DataMethods;
import ua.kpi.comsys.io8214.mobileapp.db.DataMethodsImpl;
import ua.kpi.comsys.io8214.mobileapp.db.DataMethodsInternalStorage;

public class FilmsFragment extends Fragment {

    private final String PATH_TO_FILE = "src/main/res/raw/movieslist.json";
    private SearchableFilmAdapter filmAdapter;
    private FilmsViewModel mViewModel;
    private ArrayList<Film> films = new ArrayList<>();
    private DataMethods dataMethods;
    private SearchView textFilter;
    private ImageButton addFilmButton;
    private DataMethods methods;



    public static FilmsFragment newInstance() {
        return new FilmsFragment();
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.films_fragment, container, false);
        ListView filmList = root.findViewById(R.id.filmListView);
        textFilter = root.findViewById(R.id.myFilter);
        addFilmButton = root.findViewById(R.id.addButton);
        dataMethods = new DataMethodsImpl(getActivity().getBaseContext());
        methods = new DataMethodsInternalStorage(getActivity().getBaseContext());


        try {
            if (films.size() == 0) {
                films = methods.getAll();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try {
            filmAdapter = new SearchableFilmAdapter(getActivity(),
                    R.layout.list_view_items, films,
                    methods);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!(filmAdapter == null)) {
            filmList.setAdapter(filmAdapter);
        }



        filmList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    loadFragment(new FilmsInfoFragment(dataMethods.getFilmById(films.get(position).getImdbID())));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast toast = Toast.makeText(root.getContext(), "Information not found", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });


        addFilmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new FilmAdd(films, methods));
            }
        });

        textFilter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filmAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filmAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FilmsViewModel.class);
        // TODO: Use the ViewModel
    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getParentFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.addToBackStack(getTag()).commit();// save the changes
    }


}