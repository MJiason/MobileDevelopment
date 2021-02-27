package ua.kpi.comsys.io8214.mobileapp.ui.charts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;


import ua.kpi.comsys.io8214.mobileapp.R;

public class Chart1ViewModel extends ViewModel {


    public Chart1ViewModel() {
    }


    public void renderData(LineChart lineChart, Context context) {
        LimitLine ll2 = new LimitLine(0f, "Y");
        ll2.setLineWidth(1f);
        ll2.setLineColor(Color.BLUE);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.addLimitLine(ll2);
        xAxis.setAxisMaximum(6f);
        xAxis.setAxisMinimum(-6f);
        xAxis.setDrawLimitLinesBehindData(false);

        LimitLine ll1 = new LimitLine(0f, "X");
        ll1.setLineWidth(1f);
        ll1.setLineColor(Color.BLUE);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);


        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(400f);
        leftAxis.setAxisMinimum(-120f);
        leftAxis.addLimitLine(ll1);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(false);

        lineChart.getAxisRight().setEnabled(false);
        setData(lineChart, context);
    }

    private void setData(LineChart lineChart, Context context) {

        ArrayList<Entry> values = new ArrayList<>();
        float pointVal = -6;
        for (int i = 0; i < 50; i++) {

            values.add(new Entry(pointVal, (float) Math.exp(pointVal)));
            pointVal += 0.25;
        }

        LineDataSet set1;
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, "y = exp(x)");
            set1.setDrawIcons(true);
            set1.setColor(Color.RED);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawValues(false);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.fade_blue);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.DKGRAY);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            LineData data = new LineData(dataSets);
            lineChart.setData(data);
        }
    }
    
}