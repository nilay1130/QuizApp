package ceng427.quizapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;



import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




public class AddWordActivity extends AppCompatActivity {

    private TextView txtWelcome;
    private EditText editTextEng;
    private EditText editTextTr;
    private Button btnBack;
    private Button btnAdd;
    private String wordEng;
    private String wordTr;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    SharedPreferences.Editor editor ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        editor= getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        final String s = getIntent().getStringExtra("name");
        txtWelcome = (TextView) findViewById(R.id.txtWelcome);
        txtWelcome.setText("Welcome " + s);
        editTextEng=(EditText)findViewById(R.id.editEng);
        editTextTr=(EditText)findViewById(R.id.editTr);
        btnBack=(Button)findViewById(R.id.btnBack);
        btnAdd=(Button)findViewById(R.id.btnAdd);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddWordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordEng=editTextEng.getText().toString();
                wordTr=editTextTr.getText().toString();
                editor.putString(wordEng,wordTr);

                editor.commit();
                Toast.makeText(AddWordActivity.this,"Saved!",Toast.LENGTH_SHORT).show();




            }
        });

    }
    /*private void writeToFile(String wordEng,String wordTr) {

        try {


            JSONObject obj=new JSONObject();
            obj.put(wordEng,wordTr);

            FileOutputStream fileout=openFileOutput("dictionary.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(obj.toString());
            outputWriter.close();
            Toast.makeText(AddWordActivity.this,"Saved!",Toast.LENGTH_SHORT).show();


        }
         catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }*/


}
