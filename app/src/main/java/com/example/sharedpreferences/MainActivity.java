package com.example.sharedpreferences;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Calendar cal;
    int current_year;
    String selected_month;
    int count = 1;

    Spinner month_spinner ;
    ArrayAdapter month_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] year_list = new String[12];
        for (int i = 0; i < 12; i++) {
            year_list[i] = String.valueOf(i + 1);
        }

        //年のセレクトボックス
        Spinner year_spinner = (Spinner) findViewById(R.id.year);
        ArrayAdapter year_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, year_list);

        //yearのスピナーのリスナー
        year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year_spinner.setAdapter(year_adapter);
        year_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // スピナー要素の文字列を取得



                if (count != 1) {

                    selected_month = (String) parent.getSelectedItem();
                    cal.set(Calendar.MONTH, Integer.parseInt(selected_month) - 1);
                    int max_day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

                    String[] month_list = new String[max_day];
                    for (int i = 0; i < max_day; i++) {
                        month_list[i] = String.valueOf(i + 1);
                    }

                    month_spinner = (Spinner) findViewById(R.id.month);
                    Context context = getApplicationContext();
                    month_adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, month_list);

                    month_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    month_spinner.setAdapter(month_adapter);



                } else {
                    count += 1;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        selected_month = (String) year_spinner.getSelectedItem();
        cal = Calendar.getInstance();
        current_year = cal.get(Calendar.YEAR);
        int current_month = cal.get(Calendar.MONTH);

        cal.set(Calendar.YEAR, current_year);
        if(count == 1) {
            cal.set(Calendar.MONTH,current_month);
            count+= 1;
        } else {
            cal.set(Calendar.MONTH, Integer.parseInt(selected_month));
        }

        int max_day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        String[] month_list = new String[max_day];
        for (int i = 0; i < max_day; i++) {
            month_list[i] = String.valueOf(i + 1);
        }

        month_spinner = (Spinner) findViewById(R.id.month);
        month_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, month_list);
        month_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month_spinner.setAdapter(month_adapter);

    }

}