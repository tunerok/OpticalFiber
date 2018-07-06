package com.comxa.makemore.opticalfiber;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import java.lang.Math;
import java.text.DecimalFormat;

/**
 * Created by Artyom on 30.10.2016.
 */
public class db_to_calc extends Activity {
    RadioButton r_db,r_set,r_set_corr;
    Button btn_calc,btn_clear,btn_cancel;
    EditText e_db,e_set,e_set_corr_input,e_set_corr_out;
    Double m,r,asv;
    private Toast toast;
    boolean cor,db,set;
    boolean st = true;

    String pattern = "##0.0000";
    DecimalFormat decimalFormat = new DecimalFormat(pattern);

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_tocalc);
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);




        img = (ImageView)findViewById(R.id.imageView);
        img.setImageResource(R.drawable.calc_db);
        r_db = (RadioButton)findViewById(R.id.radioButton3);
        r_set = (RadioButton)findViewById(R.id.radioButton4);
        r_set_corr = (RadioButton)findViewById(R.id.radioButton5);
        asv = 0d;
        db = false;
        set = false;
        cor = false;
        btn_calc = (Button)findViewById(R.id.button6);
        btn_clear = (Button)findViewById(R.id.button7);
        btn_cancel = (Button)findViewById(R.id.button8);

        e_db = (EditText)findViewById(R.id.editText6);
        e_set = (EditText)findViewById(R.id.editText7);
        e_set_corr_out = (EditText)findViewById(R.id.editText8);
        e_set_corr_input = (EditText)findViewById(R.id.editText9);

        e_db.setEnabled(!st);
        e_set.setEnabled(!st);
        e_set_corr_input.setEnabled(!st);
        e_set_corr_out.setEnabled(!st);

        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (db) {
                        m = Double.valueOf(e_db.getText().toString());
                        asv = dbCalc(m, false);
                    }
                    if (cor) {
                        m = Double.valueOf(e_set_corr_input.getText().toString());
                        r = Double.valueOf(e_set_corr_out.getText().toString());
                        asv = dbCalc(r / m, true);
                    }
                    if (set) {
                        m = Double.valueOf(e_set.getText().toString());
                        asv = dbCalc(m, true);
                    }
                    openResultDialog();
                } catch (Exception m) {
                    toast.setText("Неправильно заполнены поля");
                    toast.show();
                }
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e_db.setText("");
                e_set.setText("");
                e_set_corr_input.setText("");
                e_set_corr_out.setText("");
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private double dbCalc(double p, boolean lg){
        double res = 0d;
        if (lg) {
            res = 10*Math.log10(p);
        }
        if (!lg) {
            res = Math.pow(10.0, p / 10.0);
        }
        return res;
    }

    private void openResultDialog() {

        AlertDialog.Builder quitDialog = new AlertDialog.Builder(db_to_calc.this);
        if (db) {
            quitDialog.setTitle("Мощность изменилась в "+ decimalFormat.format(asv) + " раз");
        }
        else {
            quitDialog.setTitle("Изменение мощности в " + decimalFormat.format(asv) +" дБ");
        }


        quitDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        quitDialog.show();
    }

    public void onRBclicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        e_db.setEnabled(false);
        e_set.setEnabled(false);
        e_set_corr_input.setEnabled(false);
        e_set_corr_out.setEnabled(false);

        switch (view.getId()){
            case R.id.radioButton3:{
                if(checked) {


                    r_set.setChecked(false);
                    r_set_corr.setChecked(false);
                    e_db.setEnabled(true);
                    db = true;
                    set = false;
                    cor = false;
                    break;
                }
            }
            case R.id.radioButton4:{
                if(checked) {

                    db = false;
                    set = true;
                    cor = false;
                    r_db.setChecked(false);
                    r_set_corr.setChecked(false);
                    e_set.setEnabled(true);
                    break;
                }
            }
            case R.id.radioButton5:{
                if(checked) {

                    db = false;
                    set = false;
                    cor = true;
                    r_db.setChecked(false);
                    r_set.setChecked(false);

                    e_set_corr_input.setEnabled(true);
                    e_set_corr_out.setEnabled(true);
                    break;
                }
            }
        }

    }




}
