package com.example.TimerForSwim

import android.graphics.Color
import android.os.*
import android.support.wearable.activity.WearableActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import java.text.DecimalFormat
import java.util.*


class MainActivity : WearableActivity() {

    final var TAG = "MAIN";
    private var baseTimer:Long = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()

        var timer = findViewById<TextView>(R.id.timer)
        timer.setOnClickListener({
            view -> addRow(timer.text.toString())
        })


        initTimer();

//        init()
    }

    fun initTimer(){
        this.baseTimer = SystemClock.elapsedRealtime();

        var timer:TextView = findViewById(R.id.timer);

        var startTimeHandler:Handler = object: Handler(){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when(msg?.what){
//                    null -> { Log.d(TAG, "t="+ msg.data.get("time")) }
                    else -> {
                        Log.d(TAG, "t="+ msg.obj)
                        timer.setText(msg.obj.toString())
                    }
                }
            }
        }

        Timer("秒表").scheduleAtFixedRate(object : TimerTask(){
            override fun run(){
                var time = (SystemClock.elapsedRealtime() - baseTimer)/1000
                var hh: String = DecimalFormat("00").format(time/3600);
                var mm: String = DecimalFormat("00").format(time % 3600 / 60);
                var ss: String = DecimalFormat("00").format(time%60);

                var timeFormat:String = hh+":"+mm+":"+ss;
                var msg = Message();
                msg.obj = timeFormat;
                startTimeHandler.sendMessage(msg)
            }
        },0,1000L)
    }

    fun init(){


    }

    fun addRow(timeString:String){
        var table = findViewById<TableLayout>(R.id.TimerTable);

        var index = table.childCount

        var _tableRow = TableRow(this);
        var _textViewIndex = TextView(this);

        _textViewIndex.setText(("00" + index).takeLast(2));
        _textViewIndex.setTextColor(Color.RED);

        _textViewIndex.width = 48;
        _textViewIndex.gravity = Gravity.CENTER_HORIZONTAL;

        _tableRow.addView(_textViewIndex);

        var _textViewTime = TextView(this);

        _textViewTime.setText(timeString);
        _textViewTime.setTextColor(Color.WHITE);
        _textViewTime.gravity = Gravity.CENTER_HORIZONTAL;
        _textViewTime.layoutDirection = View.LAYOUT_DIRECTION_RTL;

        _tableRow.addView(_textViewTime);

        table.addView(_tableRow);
    }
}
