package com.comxa.makemore.opticalfiber;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Artyom on 29.10.2016.
 */
public class fiber_optic extends Activity {
    EditText edt_n1, edt_n2, edt_d1, edt_d2, edt_lambd, edt_v;
    Button btn_go, btn_cancel;
    RadioButton rad_gr, rad_st, rad_inp_n, rad_inp_v;
    CheckBox chk_d1, chk_d2, chk_lamd;
    boolean hide1, hide2, hide3, hide4, type_st, inpN, hide_n1;
    Double n1,n2,d1,d2, lamb, v;
    private Toast toast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_param);
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        edt_n1 = (EditText) findViewById(R.id.editText);
        edt_n2 = (EditText) findViewById(R.id.editText2);
        edt_d1 = (EditText) findViewById(R.id.editText3);
        edt_d2 = (EditText) findViewById(R.id.editText4);
        edt_lambd = (EditText) findViewById(R.id.editText5);
        edt_v = (EditText)findViewById(R.id.editText10);

        btn_go = (Button) findViewById(R.id.button4);
        btn_cancel = (Button) findViewById(R.id.button3);

        rad_st = (RadioButton) findViewById(R.id.radioButton2);
        rad_gr = (RadioButton) findViewById(R.id.radioButton);
        rad_inp_n = (RadioButton)findViewById(R.id.radioButton6);
        rad_inp_v = (RadioButton)findViewById(R.id.radioButton4);


        chk_d1 = (CheckBox) findViewById(R.id.checkBox);
        chk_d2 = (CheckBox) findViewById(R.id.checkBox2);
        chk_lamd = (CheckBox) findViewById(R.id.checkBox3);




        hide_n1 = true;
        hide1 = false;
        hide2 = false;
        hide3 = false;
        hide4 = false;
        type_st = true;
        inpN = true;

        edt_d1.setEnabled(hide1);
        edt_d2.setEnabled(hide2);
        edt_lambd.setEnabled(hide3);
        edt_v.setEnabled(hide4);


        chk_d1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                hide1 = !hide1;
                edt_d1.setEnabled(hide1);
            }
        });

        chk_d2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                hide2 = !hide2;
                edt_d2.setEnabled(hide2);
            }
        });

        chk_lamd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                hide3 = !hide3;
                edt_lambd.setEnabled(hide3);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuitDialog();
            }
        });

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totext1();
                Intent intent = new Intent(fiber_optic.this, fiber_optic_result.class);
                if(inpN) {
                    intent.putExtra("n1", n1);
                    intent.putExtra("n2", n2);
                }
                if (!inpN){
                    intent.putExtra("v", v);
                }
                intent.putExtra("in", inpN);
                intent.putExtra("type", type_st);
                if (hide1) {
                    intent.putExtra("d1", d1);
                }
                if (hide2) {
                    intent.putExtra("d2", d2);
                }
                if (hide3) {
                    intent.putExtra("lamb", lamb);
                }

                startActivity(intent);

            }
        });



    }

    public void changeInp(View view){
        switch (view.getId()){
            case R.id.radioButton6:{
                rad_inp_v.setChecked(false);
                hide_n1 = true;
                edt_n1.setEnabled(hide_n1);
                edt_n2.setEnabled(hide_n1);
                rad_inp_n.setTextColor(getResources().getColor(R.color.checked));
                rad_inp_v.setTextColor(getResources().getColor(R.color.unchecked));
                inpN = true;

                if (hide4) {
                    hide4 = !hide4;
                    edt_v.setEnabled(hide4);
                }
                break;
            }
            case R.id.radioButton4:{
                rad_inp_n.setChecked(false);
                hide4 = true;
                edt_v.setEnabled(hide4);
                rad_inp_v.setTextColor(getResources().getColor(R.color.checked));
                rad_inp_n.setTextColor(getResources().getColor(R.color.unchecked));
                if(hide_n1) {
                    hide_n1 = !hide_n1;
                    edt_n1.setEnabled(hide_n1);
                    edt_n2.setEnabled(hide_n1);
                }

                inpN = false;
                break;
            }
        }

    }

    public void changeB(View view){
        switch (view.getId()){
            case R.id.radioButton2:{
                rad_gr.setChecked(false);
                type_st = true;
                break;
            }
            case R.id.radioButton:{
                rad_st.setChecked(false);
                type_st = false;
                break;
            }
        }

    }



    private void totext1(){
        try {
            if (inpN) {
                n1 = Double.valueOf(edt_n1.getText().toString());
                n2 = Double.valueOf(edt_n2.getText().toString());
            }
            if (hide1) {
                d1 = Double.valueOf(edt_d1.getText().toString());
            }
            if (hide2) {
                d2 = Double.valueOf(edt_d2.getText().toString());
            }
            if (hide3) {
                lamb = Double.valueOf(edt_lambd.getText().toString());
            }
            if(!inpN){
                v = Double.valueOf(edt_v.getText().toString());
            }
        }
        catch (Exception e){
            toast.setText("Неправильно заполнены поля");
            toast.show();
        }

    }
    public void onBackPressed() {
        openQuitDialog();


    }

    private void openQuitDialog(){

        AlertDialog.Builder quitDialog = new AlertDialog.Builder(fiber_optic.this);
        quitDialog.setTitle("Выйти в главное меню?");
        quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(fiber_optic.this, MainActivity.class);
                startActivity(intent);
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
