package com.rsk.android.presentation.ui.reports

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.rsk.android.R

class ReportsFragment: Fragment(R.layout.fragment_reports) {
    @SuppressLint("ResourceAsColor")
    @Nullable
    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reports, container, false)

        val gchart = view.findViewById<CombinedChart>(R.id.GroupChart)

        val relative_layout = view.findViewById<CardView>(R.id.cardViewConsumptionDynamics)

        val xEntryValue = ArrayList<Entry>()

        val barentries2 = ArrayList<BarEntry>()
        barentries2.add(BarEntry(0f, 502545.37f))
        barentries2.add(BarEntry(1f, 508513.68f))
        barentries2.add(BarEntry(2f, 494899.92f))
        barentries2.add(BarEntry(3f, 272285.98f))
        barentries2.add(BarEntry(4f, 249841.46f))


        val larentries = ArrayList<Entry>()
        larentries.add(Entry(0f, 6.9f))
        larentries.add(Entry(1f, 6.91f))
        larentries.add(Entry(2f, 6.9f))
        larentries.add(Entry(3f, 6.87f ))
        larentries.add(Entry(4f, 6.9f ))


        val datasetbar2 = BarDataSet(barentries2, "")
        val datasetline = LineDataSet(larentries, "")

        datasetline.color = ResourcesCompat.getColor(getResources(), R.color.lightpurple, null)
        datasetbar2.color = ResourcesCompat.getColor(getResources(), R.color.greenpotreb, null)


        gchart.setBackgroundColor(
            ResourcesCompat.getColor(
                getResources(),
                R.color.bluelight,
                null
            )
        )


        val bardata2 = BarData(datasetbar2)
        val linedata = LineData(datasetline)


        val data = CombinedData()
        data.setData(bardata2)
        data.setData(linedata)


        gchart.data = data

        gchart.animateY(1000)

        datasetbar2.valueTextSize = 14f



        val quarters = arrayOf("2021-01-18", "2021-02-19", "2021-03-18", "2021-04-30", "2021-04-30")

        val formatter: IAxisValueFormatter = object : IAxisValueFormatter {
            override fun getFormattedValue(value: Float, axis: AxisBase): String {
                return quarters[value.toInt()]
            }

            val decimalDigits: Int
                get() = 0
        }

        val xAxis: XAxis = gchart.getXAxis()
        xAxis.granularity = 1f
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textSize = 11f
        xAxis.valueFormatter = formatter



        gchart.setBackgroundColor(resources.getColor(R.color.white))
        gchart.getXAxis().setDrawGridLines(false)
        gchart.getAxisLeft().setDrawGridLines(false)
        gchart.getAxisRight().setDrawGridLines(false)
        gchart.axisRight.isEnabled = true
        gchart.legend.isEnabled = false
        gchart.invalidate()
        gchart.description.isEnabled = false
        gchart.xAxis.isEnabled = true
        gchart.setDrawBorders(false)
        gchart.getXAxis().setAxisMinimum(-0.5f)
        gchart.getXAxis().setAxisMaximum(data.getXMax() + 0.5f)


        gchart.animateY(1000)

        datasetline.setDrawCircles(false)
        datasetline.setDrawValues(false)
        datasetline.lineWidth = 2f



        return view


    }
}