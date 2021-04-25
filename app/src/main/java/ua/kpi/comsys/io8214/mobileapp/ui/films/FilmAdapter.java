package ua.kpi.comsys.io8214.mobileapp.ui.films;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import ua.kpi.comsys.io8214.mobileapp.dao.Film;
import ua.kpi.comsys.io8214.mobileapp.R;

public class FilmAdapter extends ArrayAdapter<Film> {

    ArrayList<Film> films = new  ArrayList();

    public FilmAdapter(Context context, int textViewResourceId, ArrayList<Film> objects) {
        super(context, textViewResourceId, objects);
        films = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.list_view_items, null);

        TextView titleView = (TextView) v.findViewById(R.id.titleText);
        TextView yearView = (TextView) v.findViewById(R.id.yearText);
//        TextView ratingView = (TextView) v.findViewById(R.id.ratingText);
        TextView typeView = (TextView) v.findViewById(R.id.typeText);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);

        titleView.setText(films.get(position).getTitle());
        yearView.setText(films.get(position).getYear());
//        ratingView.setText(films.get(position).getImdbID());
        int test =  getContext().getResources().getIdentifier("poster_03", "drawable", getContext().getPackageName());
        typeView.setText(films.get(position).getType());
        imageView.setImageResource(getDrawableIdByName(films.get(position).getPoster()));
        return v;
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
