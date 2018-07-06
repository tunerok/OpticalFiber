package com.comxa.makemore.opticalfiber;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.Math;
import java.text.DecimalFormat;



/**
 * Created by Artyom on 30.10.2016.
 */
public class fiber_optic_result extends Activity {


    EditText edt_na, edt_v, edt_ots, edt_m_num, edt_sin, edt_angle;
    TextView tv_type , tv_v1, tv_v2, tv_ots1, tv_ots2, tv_num1, tv_num2, tv_na1,tv_na2,tv_angle1,tv_angle2, tv_sin;
    ImageView im_v, im_ots, im_na, im_cr_angle;
    Double n1 = null, n2 = null, d1 = null, d2 = null, lambd = null, v = null, f_temp = null;
    boolean type, in;

    String pattern = "##0.0000";
    DecimalFormat decimalFormat = new DecimalFormat(pattern);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.out_param);
        if (getIntent().getExtras().get("n1")!= null){
           n1 = getIntent().getExtras().getDouble("n1");
        }
        if (getIntent().getExtras().get("n2")!= null){
            n2 = getIntent().getExtras().getDouble("n2");
        }
        if (getIntent().getExtras().get("type")!= null){
            type = getIntent().getExtras().getBoolean("type");
        }
        if (getIntent().getExtras().get("d1")!= null){
            d1 = getIntent().getExtras().getDouble("d1");
        }
        if (getIntent().getExtras().get("d2")!= null){
            d2 = getIntent().getExtras().getDouble("d2");
        }
        if (getIntent().getExtras().get("lamb")!= null){
            lambd = getIntent().getExtras().getDouble("lamb");
        }
        if (getIntent().getExtras().get("v")!= null){
            v = getIntent().getExtras().getDouble("v");
        }
        if (getIntent().getExtras().get("in")!= null){
            in = getIntent().getExtras().getBoolean("in");
        }

        tv_v1 = (TextView)findViewById(R.id.textView10);
        tv_v2 = (TextView)findViewById(R.id.textView11);
        im_v = (ImageView)findViewById(R.id.imageView3);

        tv_angle1 = (TextView)findViewById(R.id.textView15);
        tv_angle2 = (TextView)findViewById(R.id.textView915);
        im_cr_angle = (ImageView)findViewById(R.id.imageView5);

        im_na = (ImageView)findViewById(R.id.imageView2);
        tv_na1 = (TextView)findViewById(R.id.textView9);
        tv_na2 = (TextView)findViewById(R.id.textView6);

        tv_sin = (TextView)findViewById(R.id.textView16);



        tv_ots1 = (TextView)findViewById(R.id.textView14);
        tv_ots2 = (TextView)findViewById(R.id.textView914);
        im_ots = (ImageView)findViewById(R.id.imageView4);

        tv_num1 = (TextView)findViewById(R.id.textView12);
        tv_num2 = (TextView)findViewById(R.id.textView912);


        edt_sin = (EditText)findViewById(R.id.editText15);
        edt_angle = (EditText)findViewById(R.id.editText16);
        edt_na = (EditText)findViewById(R.id.editText11);
        edt_v = (EditText)findViewById(R.id.editText12);
        edt_ots = (EditText)findViewById(R.id.editText14);
        edt_m_num = (EditText)findViewById(R.id.editText13);

        tv_type = (TextView)findViewById(R.id.textView913);

        if (in) {
            f_temp = findSin(n1, n2);
            edt_sin.setText(decimalFormat.format(f_temp));
            edt_angle.setText(decimalFormat.format(Math.asin(f_temp)));

            f_temp = findNA(n1, n2);
            edt_na.setText(decimalFormat.format(f_temp));


            if (d1 != null) {
                if (lambd != null) {
                    edt_ots.setText(decimalFormat.format(findOTS(d1, f_temp)));

                    f_temp = findV(lambd, d1, f_temp);
                    edt_v.setText(decimalFormat.format(f_temp));
                    edt_m_num.setText(decimalFormat.format(findMNUM(f_temp, type)));
                } else {
                    edt_v.setVisibility(View.GONE);
                    tv_v1.setVisibility(View.GONE);
                    tv_v2.setVisibility(View.GONE);
                    im_v.setVisibility(View.GONE);

                    edt_m_num.setVisibility(View.GONE);
                    tv_num1.setVisibility(View.GONE);
                    tv_num2.setVisibility(View.GONE);

                }

            } else {
                edt_v.setVisibility(View.GONE);
                tv_v1.setVisibility(View.GONE);
                tv_v2.setVisibility(View.GONE);
                im_v.setVisibility(View.GONE);

                edt_m_num.setVisibility(View.GONE);
                tv_num1.setVisibility(View.GONE);
                tv_num2.setVisibility(View.GONE);

                edt_ots.setVisibility(View.GONE);
                tv_ots1.setVisibility(View.GONE);
                tv_ots2.setVisibility(View.GONE);
                im_ots.setVisibility(View.GONE);
            }
        }else if (!in)
        {
            edt_v.setText(decimalFormat.format(v));
            if (d1 != null)
            {
                if (lambd != null){

                    f_temp = (v*lambd)/(Math.PI*d1);
                    edt_na.setText(decimalFormat.format(f_temp));
                    edt_m_num.setText(decimalFormat.format(findMNUM(v, type)));
                    edt_ots.setText(decimalFormat.format(findOTS(d1, f_temp)));

                    edt_angle.setVisibility(View.GONE);
                    tv_angle1.setVisibility(View.GONE);
                    tv_angle2.setVisibility(View.GONE);
                    im_cr_angle.setVisibility(View.GONE);



                    edt_sin.setVisibility(View.GONE);
                    tv_sin.setVisibility(View.GONE);
                }else{

                    edt_angle.setVisibility(View.GONE);
                    tv_angle1.setVisibility(View.GONE);
                    tv_angle2.setVisibility(View.GONE);
                    im_cr_angle.setVisibility(View.GONE);



                    edt_sin.setVisibility(View.GONE);
                    tv_sin.setVisibility(View.GONE);

                    edt_na.setVisibility(View.GONE);
                    tv_na1.setVisibility(View.GONE);
                    tv_na2.setVisibility(View.GONE);
                    im_na.setVisibility(View.GONE);


                    edt_m_num.setVisibility(View.GONE);
                    tv_num1.setVisibility(View.GONE);
                    tv_num2.setVisibility(View.GONE);

                    edt_ots.setVisibility(View.GONE);
                    tv_ots1.setVisibility(View.GONE);
                    tv_ots2.setVisibility(View.GONE);
                    im_ots.setVisibility(View.GONE);
                }

            }else{

                edt_na.setVisibility(View.GONE);
                tv_na1.setVisibility(View.GONE);
                tv_na2.setVisibility(View.GONE);
                im_na.setVisibility(View.GONE);


                edt_m_num.setVisibility(View.GONE);
                tv_num1.setVisibility(View.GONE);
                tv_num2.setVisibility(View.GONE);


                edt_ots.setVisibility(View.GONE);
                im_na.setVisibility(View.GONE);
                tv_ots1.setVisibility(View.GONE);
                tv_ots2.setVisibility(View.GONE);

                edt_angle.setVisibility(View.GONE);
                tv_angle1.setVisibility(View.GONE);
                tv_angle2.setVisibility(View.GONE);
                im_cr_angle.setVisibility(View.GONE);



                edt_sin.setVisibility(View.GONE);
                tv_sin.setVisibility(View.GONE);


                edt_na.setVisibility(View.GONE);
                tv_na1.setVisibility(View.GONE);
                tv_na2.setVisibility(View.GONE);
                im_na.setVisibility(View.GONE);


                edt_m_num.setVisibility(View.GONE);
                tv_num1.setVisibility(View.GONE);
                tv_num2.setVisibility(View.GONE);

                edt_ots.setVisibility(View.GONE);
                tv_ots1.setVisibility(View.GONE);
                tv_ots2.setVisibility(View.GONE);
                im_ots.setVisibility(View.GONE);
            }

        }


    }

    public void onBackPressed() {
        openQuitDialog();


    }

    private void openQuitDialog() {

        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                fiber_optic_result.this);
        quitDialog.setTitle("Закрыть результат?");

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


    private Double findSin(Double n1, Double n2){
        Double res = 0d;
        res = n2/n1;
        return res;
    }


    private Double findNA(Double n1, Double n2){
        Double res = 0d;

        res = Math.sqrt(n1 * n1 - n2 * n2);
        return res;
    }


    private Double findV(Double lambd, Double d1, Double na){
        Double res = 0d;

            res = (Math.PI * d1 * na)/lambd;
        return res;
    }

    private Double findOTS(Double d1, Double na){
        Double res = 0d;
            res = (Math.PI * d1 * na)/2.405;
        return res;
    }

    private int findMNUM(Double v, boolean type){
        int res = 0;
        tv_type.setText("Многомодовое");
        if (type) {
            if (v < 2.405) {
                res = 1;
                tv_type.setText("Одномодовое");
            } else if (v < 3.832 && v > 2.405) {
                res = 4;
            } else if (v < 5.136 && v > 3.832) {
                res = 7;
            } else if (v < 5.52 && v > 5.136) {
                res = 9;
            } else if (v < 6.38 && v > 5.52) {
                res = 12;
            } else if (v < 7.02 && v > 6.38) {
                res = 14;
            } else if (v < 7.59 && v > 7.02) {
                res = 17;
            } else if (v < 8.42 && v > 7.59) {
                res = 19;
            }else if (v>8.42){
                double r;
                r = Math.round(Math.pow(v, 2.0)/2);
                res = (int)r;
            }

        }
        if (!type)
        {
            double r;
            r = Math.round(Math.pow(v, 2.0)/4);
            res = (int)r;
            tv_type.setText("Градиентное");
        }



        return res;
    }
}
