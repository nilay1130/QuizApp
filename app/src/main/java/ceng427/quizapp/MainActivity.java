package ceng427.quizapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txtName;
    private String name;
    private Button btnTraining;
    private Button btnAddWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTraining = (Button) findViewById(R.id.btnTraining);
        btnAddWord=(Button) findViewById(R.id.btnAddWord);

        txtName = (EditText) findViewById(R.id.txtName);

        btnTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txtName.getText().toString();
                Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txtName.getText().toString();
                Intent intent = new Intent(MainActivity.this, AddWordActivity.class);
                intent.putExtra("name", name);

                startActivity(intent);
            }
        });








    }

}
