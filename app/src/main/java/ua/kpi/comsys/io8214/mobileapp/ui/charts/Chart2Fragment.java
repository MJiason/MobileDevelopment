package ua.kpi.comsys.io8214.mobileapp.ui.charts;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;

import ua.kpi.comsys.io8214.mobileapp.R;

public class Chart2Fragment extends Fragment {

    private Chart2ViewModel chart2ViewModel;
    private PieChart pChart;
    private Button butt;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        chart2ViewModel = new ViewModelProvider(this).get(Chart2ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chart2, container, false);

        butt = root.findViewById(R.id.change_chart_butt2);
        pChart = root.findViewById(R.id.pChart);

        chart2ViewModel.initPieChart(pChart);
        chart2ViewModel.showPieChart(pChart);

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new  Chart1Fragment());
            }
        });

        return root;
    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getParentFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.addToBackStack(getTag()).commit(); // save the changes
    }


}