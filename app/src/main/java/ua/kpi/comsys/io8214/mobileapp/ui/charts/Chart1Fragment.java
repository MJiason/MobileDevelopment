package ua.kpi.comsys.io8214.mobileapp.ui.charts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.annotation.NonNull;

import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.LineChart;

import ua.kpi.comsys.io8214.mobileapp.R;

public class Chart1Fragment extends Fragment {

    private Chart1ViewModel chart1ViewModel;
//    private PieChart pChart;
    private LineChart lChart;
    private Button but;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        chart1ViewModel =
                new ViewModelProvider(this).get(Chart1ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chart1, container, false);

        but = root.findViewById(R.id.change_chart_butt1);
        lChart = root.findViewById(R.id.lChart);

        lChart.setTouchEnabled(true);
        lChart.setPinchZoom(true);
        chart1ViewModel.renderData(lChart, getActivity().getApplicationContext());

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Chart2Fragment());
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