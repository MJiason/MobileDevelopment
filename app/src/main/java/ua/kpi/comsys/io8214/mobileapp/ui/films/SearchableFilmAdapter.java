package ua.kpi.comsys.io8214.mobileapp.ui.films;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ua.kpi.comsys.io8214.mobileapp.MainActivity;
import ua.kpi.comsys.io8214.mobileapp.R;
import ua.kpi.comsys.io8214.mobileapp.dao.Film;
import ua.kpi.comsys.io8214.mobileapp.db.DataMethods;
import ua.kpi.comsys.io8214.mobileapp.db.DataMethodsInternalStorage;

public class SearchableFilmAdapter extends ArrayAdapter implements Filterable {

    private ArrayList<Film> originalFilmArray = null;
    private ArrayList<Film> filteredFilmArray = null;
    private LayoutInflater inflater;
    private final FilmFilter filter = new FilmFilter();
    private DataMethods dataMethods;



    public SearchableFilmAdapter(@NonNull Context context, int resource,
                                 ArrayList<Film> originalFilmArray,
                                 DataMethods dataMethods) {
        super(context, resource);
        this.originalFilmArray = originalFilmArray;
        this.filteredFilmArray = originalFilmArray;
        this.dataMethods = dataMethods;
    }

    @Override
    public int getCount() {
        return filteredFilmArray.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredFilmArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.list_view_items, null);

        TextView titleView = v.findViewById(R.id.titleText);
        TextView yearView = v.findViewById(R.id.yearText);
        TextView typeView = v.findViewById(R.id.typeText);
        ImageView imageView = v.findViewById(R.id.imageView);
        ImageView delete = v.findViewById(R.id.deleteButton);

        titleView.setText(filteredFilmArray.get(position).getTitle());
        yearView.setText(filteredFilmArray.get(position).getYear());
        typeView.setText(filteredFilmArray.get(position).getType());
        imageView.setImageResource(getDrawableIdByName(filteredFilmArray.get(position).getPoster()));

        delete.setOnClickListener(v1 -> {
                Toast toast = Toast.makeText(
                        getContext(),
                        originalFilmArray.get(position).getTitle() + " deleted ",
                        Toast.LENGTH_SHORT);
                toast.show();
                originalFilmArray.remove(position);
                dataMethods.SaveFilm(originalFilmArray);
                notifyDataSetChanged();
        });

        return v;

    }

    @Override
    public Filter getFilter() {
        return filter;
    }


    private class  FilmFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();
            final ArrayList<Film> filmsArray = originalFilmArray;

            int count = filmsArray.size();

            final ArrayList<Film> newFilmsArray = new ArrayList<>(count);

            Film filterableFilm;

            for (int i = 0; i < count; i++) {
                filterableFilm = filmsArray.get(i);
                if (filterableFilm.getTitle().toLowerCase().contains(filterString)){
                    newFilmsArray.add(filterableFilm);
                }
            }
            filterResults.values = newFilmsArray;
            filterResults.count = newFilmsArray.size();
            
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredFilmArray = (ArrayList<Film>) results.values;
            notifyDataSetChanged();
            
        }
    }



    private int getDrawableIdByName(String name) {
        if (name == null  || name.isEmpty()){
            return R.drawable.kpi_logo;
        }
        return getContext().getResources().getIdentifier(name.toLowerCase().replace(".jpg", ""),
                "drawable",
                getContext().getPackageName());
    }
}
