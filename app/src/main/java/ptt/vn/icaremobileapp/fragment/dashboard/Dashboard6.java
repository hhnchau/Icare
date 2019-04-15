package ptt.vn.icaremobileapp.fragment.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Calendar;

import ptt.vn.icaremobileapp.R;


/**
 * Created by kingpes on 9/13/18.
 */

public class Dashboard6 extends Fragment {
    private BarChart barChart;
    private ArrayList<BarEntry> valueSet;
    static int i = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard6_fragment, container, false);

        barChart = view.findViewById(R.id.chart);
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.setDescription(null);
        barChart.animateY(2000);
        barChart.setScaleEnabled(false);
        barChart.setTouchEnabled(false);

        XAxis xaxis = barChart.getXAxis();
        xaxis.setDrawGridLines(false);
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xaxis.setLabelsToSkip(0);

        YAxis yaxis = barChart.getAxisLeft();
        yaxis.setAxisMaxValue(100);
        yaxis.setAxisMinValue(0);

        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("CN");
        xAxis.add("T2");
        xAxis.add("T3");
        xAxis.add("T4");
        xAxis.add("T5");
        xAxis.add("T6");
        xAxis.add("T7");

        valueSet = new ArrayList<>();
        valueSet.add(new BarEntry(25, 0));
        valueSet.add(new BarEntry(26, 1));
        valueSet.add(new BarEntry(24, 2));
        valueSet.add(new BarEntry(29, 3));
        valueSet.add(new BarEntry(10, 4));
        valueSet.add(new BarEntry(30, 5));
        valueSet.add(new BarEntry(40, 6));

        BarDataSet barDataSet = new BarDataSet(valueSet, null);
        barDataSet.setColors(new int[]{getResources().getColor(R.color.dashboard_frame1), getResources().getColor(R.color.dashboard_frame1), getResources().getColor(R.color.dashboard_frame1), getResources().getColor(R.color.dashboard_frame1), getResources().getColor(R.color.dashboard_frame1), getResources().getColor(R.color.dashboard_frame1), getResources().getColor(R.color.dashboard_frame1)});

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);

        BarData barData = new BarData(xAxis, dataSets);

        barChart.setData(barData);


        return view;
    }

    public void updateData(int value) {
        i = i + value;

        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;

        BarEntry barEntry = valueSet.get(day);
        barEntry.setVal(i);
        valueSet.set(day, barEntry);

        barChart.notifyDataSetChanged();
        barChart.invalidate();
    }
}
