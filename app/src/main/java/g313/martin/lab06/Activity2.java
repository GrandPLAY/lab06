package g313.martin.lab06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {

    EditText et_title;
    EditText et_content;

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);

        Intent i = getIntent();
        pos = i.getIntExtra("note index", -1);
        et_title.setText(i.getStringExtra("note title"));
        et_content.setText(i.getStringExtra("note content"));
    }

    public void  on_cancel_click(View v){
        finish();
    }

    public void on_save_click(View v){
        Intent i = new Intent();
        i.putExtra("note index", pos);
        i.putExtra("note title", et_title.getText().toString());
        i.putExtra("note content", et_content.getText().toString());

        setResult(RESULT_OK, i);
        finish();
    }
}