package ceng427.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class QuizActivity extends AppCompatActivity {

    private TextView txtEng;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button btnNext;
    private int count = 0;
    private ArrayList<String > list=new ArrayList<String>();
    private HashMap<String, String> hashMap;
    private ArrayList<String> choices=new ArrayList<String>();

    private static double score=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        txtEng=(TextView)findViewById(R.id.txtEng);

        btnNext=(Button)findViewById(R.id.btnNext);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);



        Intent intent = getIntent();
        hashMap = (HashMap<String, String>) intent.getSerializableExtra("map");
         list=TrainingActivity.generateRandomList(hashMap);
        final String s = getIntent().getStringExtra("name");
        txtEng.setText(list.get(count));
        choices.add(hashMap.get(list.get(count)));
        choices.add(hashMap.get(list.get(count+1)));
        choices.add(hashMap.get(list.get(count+2)));
        choices.add(hashMap.get(list.get(count+3)));
        Collections.shuffle(choices);
        button1.setText(choices.get(0));
        button2.setText(choices.get(1));
        button3.setText(choices.get(2));
        button4.setText(choices.get(3));
        choices.clear();






        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button1.setBackgroundColor(Color.WHITE);
                button2.setBackgroundColor(Color.WHITE);
                button3.setBackgroundColor(Color.WHITE);
                button4.setBackgroundColor(Color.WHITE);
                count++;

                txtEng.setText(list.get(count));
                choices.add(hashMap.get(list.get(count)));
                choices.add(hashMap.get(list.get(count+1)));
                choices.add(hashMap.get(list.get(count+2)));
                choices.add(hashMap.get(list.get(count+3)));
                Collections.shuffle(choices);
                button1.setText(choices.get(0));
                button2.setText(choices.get(1));
                button3.setText(choices.get(2));
                button4.setText(choices.get(3));
                choices.clear();

                if(count==10){
                    final AlertDialog alertDialog;
                    alertDialog = new AlertDialog.Builder(QuizActivity.this).create();
                    alertDialog.setTitle("Result");
                    alertDialog.setMessage("Your Score is:"+score);
                    btnNext.setVisibility(View.INVISIBLE);
                    if(score<0){
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "GO TO TRAINING",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                        Intent intent = new Intent(QuizActivity.this, TrainingActivity.class);
                                        intent.putExtra("name",s);
                                        startActivity(intent);



                                    }
                                });
                    }
                    alertDialog.show();
                }


            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button1.getText().equals(hashMap.get(txtEng.getText()))){
                    button1.setBackgroundColor(Color.GREEN);
                    score+=1;
                }
                else{
                    button1.setBackgroundColor(Color.RED);
                    score=score-0.5;
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button2.getText().equals(hashMap.get(txtEng.getText()))){
                    button2.setBackgroundColor(Color.GREEN);
                    score+=1;
                }
                else{
                    button2.setBackgroundColor(Color.RED);
                    score=score-0.5;
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button3.getText().equals(hashMap.get(txtEng.getText()))){
                    button3.setBackgroundColor(Color.GREEN);
                    score+=1;
                }
                else{
                    button3.setBackgroundColor(Color.RED);
                    score=score-0.5;
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button4.getText().equals(hashMap.get(txtEng.getText()))){
                    button4.setBackgroundColor(Color.GREEN);
                    score+=1;
                }
                else{
                    button4.setBackgroundColor(Color.RED);
                    score=score-0.5;
                }
            }
        });




    }
}
