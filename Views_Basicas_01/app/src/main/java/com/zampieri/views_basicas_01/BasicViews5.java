package com.zampieri.views_basicas_01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class BasicViews5 extends AppCompatActivity {
    private Button timeBtn;
    private Button btnDate;
    private Button btnTime;

    DateFormat formatDateTime=DateFormat.getDateTimeInstance();
    Calendar dateTime=Calendar.getInstance();
    private TextView timeLabel;
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_views5);
        timeLabel=(TextView)findViewById(R.id.timeTxt);
        btnDate = (Button) findViewById(R.id.btnDate);
        btnTime = (Button) findViewById(R.id.btnTime);

        btnDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(BasicViews5.this,
                        mDateSetListener, //m�todo callback que deve ser implementado
                        dateTime.get(Calendar.YEAR),
                        dateTime.get(Calendar.MONTH),
                        dateTime.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(BasicViews5.this,
                        mTimeSetListener, //método callback que deve ser implementado
                        dateTime.get(Calendar.HOUR_OF_DAY),
                        dateTime.get(Calendar.MINUTE), true).show();

            }
        });

        updateLabel();
    }

    //método callback DatePickerDialog.OnDateSetListener
    //sinaliza o fim do preenchimento da data
    //ativado quando o usuário pressiona "Done"
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    //seta no objeto dateTime (Calendar) a data escolhida
                    dateTime.set(Calendar.YEAR,year);
                    dateTime.set(Calendar.MONTH, monthOfYear);
                    dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();
                }
            };

    //método callback TimePickerDialog.OnTimeSetListener()
    //sinaliza o fim do preenchimento da hora
    //ativado quando o usuário pressiona "Done"
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {

                public void onTimeSet(TimePicker view, int hourOfDay,
                                      int minute) {
                    //seta no objeto dateTime (Calendar) a hora escolhida
                    dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    dateTime.set(Calendar.MINUTE,minute);
                    updateLabel();
                }
            };

    private void updateLabel() {
        timeLabel.setText(formatDateTime.format(dateTime.getTime()));
    }
}