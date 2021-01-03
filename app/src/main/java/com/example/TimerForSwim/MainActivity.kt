package com.example.TimerForSwim

import android.graphics.Color
import android.icu.number.NumberFormatter
import android.icu.text.DateTimePatternGenerator
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.text.Layout
import android.util.LayoutDirection
import android.util.Log
import android.view.Gravity
import android.view.PixelCopy
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast


class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()

        var timer = findViewById<TextView>(R.id.timer)
        timer.setOnClickListener({
            view -> init()
        })

//        init()
    }



    fun init(){
        var table = findViewById<TableLayout>(R.id.TimerTable);

        var _tableRow = TableRow(this);
        var _textViewIndex = TextView(this);

        _textViewIndex.setText("00");
        _textViewIndex.setTextColor(Color.RED);

        _textViewIndex.width = 48;
        _textViewIndex.gravity = Gravity.CENTER_HORIZONTAL;

        _tableRow.addView(_textViewIndex);

        var _textViewTime = TextView(this);

        _textViewTime.setText("00:00:00");
        _textViewTime.setTextColor(Color.WHITE);
        _textViewTime.gravity = Gravity.CENTER_HORIZONTAL;
        _textViewTime.layoutDirection = View.LAYOUT_DIRECTION_RTL;

        _tableRow.addView(_textViewTime);

        table.addView(_tableRow);
    }
}
