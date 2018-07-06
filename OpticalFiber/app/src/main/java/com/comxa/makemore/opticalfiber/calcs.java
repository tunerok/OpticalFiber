package com.comxa.makemore.opticalfiber;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


/**
 * Created by Artyom on 30.10.2016.
 */
public class calcs extends Activity {

    Button back;
    ListView list;
    Context thi = this;
    private int Session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcs_opt);


        list = (ListView)findViewById(R.id.listView);
        back = (Button)findViewById(R.id.button5);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(thi, R.array.calc_first_scr, R.layout.my_list_item);
        Session = 0;
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if ((i == 0) && (Session == 0)) {
                    Intent intent = new Intent(calcs.this, db_to_calc.class);
                    startActivity(intent);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }



    public void onBackPressed() {
        openQuitDialog();


    }

    private void openQuitDialog() {

        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                calcs.this);
        quitDialog.setTitle("Закрыть калькуляторы?");

        quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        quitDialog.show();
    }
}
