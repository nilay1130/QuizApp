package ceng427.quizapp;



import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;

import java.util.Map;



public class TrainingActivity extends AppCompatActivity {

    private TextView txtWelcome;

    private TextView txtEng;
    private TextView txtTr;
    private Button btnBack;
    private Button btnNext;

    private int counter = 0;

    private String wordEng;
    private String wordTr;
    private String preferenceEng;
    private String preferenceTr;
    public static ArrayList<String> shuffledWorlds;
    SharedPreferences prefs;
    private  HashMap<String, String> x;











    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        prefs= getSharedPreferences(AddWordActivity.MY_PREFS_NAME, MODE_PRIVATE);
        Map<String,?> keys = prefs.getAll();


        preferenceEng = prefs.getString(wordEng, wordEng);
        preferenceTr=prefs.getString(wordTr,wordTr);


        if(counter==0) {
            View backButton = findViewById(R.id.btnBack);
            backButton.setVisibility(View.INVISIBLE);
        }

        x= new Gson().fromJson(
                readFile(), new TypeToken<HashMap<String, String>>() {}.getType()
        );

        for(Map.Entry<String,?> entry : keys.entrySet()){
            x.put(entry.getKey(),entry.getValue().toString());

        }




        shuffledWorlds=generateRandomList(x);

        String randomKey =shuffledWorlds.get(counter);
        String value = x.get(randomKey);
        wordEng= randomKey;
        wordTr = value;
        final String s = getIntent().getStringExtra("name");
        txtWelcome = (TextView) findViewById(R.id.txtWelcome);
        txtWelcome.setText("Welcome " + s);
        txtEng = (TextView) findViewById(R.id.txtEng);
        txtTr = (TextView) findViewById(R.id.txtTr);


        txtEng.setText(wordEng);
        txtTr.setText(wordTr);




        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View backButton = findViewById(R.id.btnBack);
                backButton.setVisibility(View.VISIBLE);
                counter++;
                final AlertDialog alertDialog;
                if (counter == 10) {



                     alertDialog = new AlertDialog.Builder(TrainingActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("The training is finished.Do you want to continue to quiz?");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                    Intent intent = new Intent(TrainingActivity.this, QuizActivity.class);



                                    intent.putExtra("map",x);
                                    intent.putExtra("name",s);
                                    startActivity(intent);

                           btnNext.setVisibility(View.INVISIBLE);

                                }
                            });

                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    btnNext.setVisibility(View.INVISIBLE);


                                }
                            });
                    alertDialog.show();

                }


                wordEng = shuffledWorlds.get(counter);
                wordTr = x.get(wordEng);

                txtEng.setText(wordEng);
                txtTr.setText(wordTr);


            }

        });

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            counter--;
                if(counter==0) {
                    View backButton = findViewById(R.id.btnBack);
                    backButton.setVisibility(View.INVISIBLE);
                }
                txtEng.setText(shuffledWorlds.get(counter));
                txtTr.setText(x.get(shuffledWorlds.get(counter)));






            }
        });


    }

    public  static ArrayList<String> generateRandomList(Map<String,String> x) {
        ArrayList<String> keys = new ArrayList<String>(x.keySet());
        Collections.shuffle(keys);
        return keys;
    }




    public   String readFile() {
        String json = null;
        try {

            InputStream is=getAssets().open("dictionary.txt");

            int size = is.available();

            byte[] buffer = new byte[size];



            is.read(buffer);

            is.close();

            json = new String(buffer, "ISO-8859-9");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }



}
