package ua.kpi.comsys.io8214.mobileapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ua.kpi.comsys.io8214.mobileapp.dao.Film;
import ua.kpi.comsys.io8214.mobileapp.db.DataMethods;
import ua.kpi.comsys.io8214.mobileapp.db.DataMethodsInternalStorage;


public class FilmAdd extends Fragment {

    private EditText title;
    private EditText date;
    private EditText type;
    private Button add;

    private ArrayList<Film> films;
    private DataMethods dataMethods;


    public FilmAdd(ArrayList<Film> films, DataMethods dataMethods) {
        this.films = films;
        this.dataMethods = dataMethods;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_add, container, false);
        title = view.findViewById(R.id.titleInput);
        date = view.findViewById(R.id.dateInput);
        type = view.findViewById(R.id.typeInput);

        add = view.findViewById(R.id.addButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    films.add(getFilm());
                    dataMethods.SaveFilm(films);
                    Toast toast = Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT);
                    toast.show();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();

                }

            }
        });

        return view;
    }

    private Film getFilm() throws IllegalArgumentException {
        Toast toast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);

        if (!isDateValid(date.getText().toString())) {
            toast.setText("Invalid number");
            toast.show();
            throw new IllegalArgumentException();
        }

        if (!isTextValid(title.getText().toString())) {
            toast.setText("Empty title");
            toast.show();
            throw new IllegalArgumentException();
        }

        if (!isTextValid(type.getText().toString())) {
            toast.setText("Empty type");
            toast.show();
            throw new IllegalArgumentException();
        }

        return new Film(title.getText().toString(), date.getText().toString(), type.getText().toString());

    }

    private boolean isDateValid(String data) {
        if (data.isEmpty()) {
            return false;
        }

        if ((data.length() != 4)) {
            return false;
        }

        for (char c : data.toCharArray()) {
            if (!Character.isDigit(c)) {
                return Character.isDigit(c);
            }
        }

        return true;
    }

    private boolean isTextValid(String text) {
        return !text.isEmpty();
    }
}