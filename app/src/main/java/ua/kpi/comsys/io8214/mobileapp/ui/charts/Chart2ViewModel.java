package ua.kpi.comsys.io8214.mobileapp.ui.charts;

import android.graphics.Color;

import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class Chart2ViewModel extends ViewModel {
    public Chart2ViewModel() {
    }

    public void showPieChart(PieChart pieChart) {

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "type";

        //initializing data

        ArrayList<Integer> entries = new ArrayList<>();
        entries.add(30);
        entries.add(30);
        entries.add(40);

        //initializing colors for the entries
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FD5D2B"));
        colors.add(Color.parseColor("#16B310"));
        colors.add(Color.parseColor("#000000"));


        //input data and fit data into pie chart entry
        for (Integer entry : entries) {
            pieEntries.add(new PieEntry(entry.floatValue()));
        }

        //collecting the entries with label name
        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);
        //setting text size of the value
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setValueTextColor(Color.WHITE);
        //providing color list for coloring different entries
        pieDataSet.setColors(colors);
        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    public void initPieChart(PieChart pieChart) {
        //using percentage as values instead of amount
        pieChart.setUsePercentValues(true);

        //remove the description label on the lower left corner, default true if not set
        pieChart.getDescription().setEnabled(false);

        //enabling the user to rotate the chart, default true
        pieChart.setRotationEnabled(true);
        //adding friction when rotating the pie chart
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        //setting the first entry start from right hand side, default starting from top
        pieChart.setRotationAngle(0);

        //highlight the entry when it is tapped, default true if not set
        pieChart.setHighlightPerTapEnabled(true);
        //adding animation so the entries pop up from 0 degree
//        pChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
//        //setting the color of the hole in the middle, default white
        pieChart.setHoleColor(Color.parseColor("#B8E9FF"));
        pieChart.setHoleRadius(40f);

    }
}