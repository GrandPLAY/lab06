package g313.martin.lab06;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter <Note> adp;
    int sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adp = new ArrayAdapter <Note> (this, android.R.layout.simple_list_item_1);

        ListView list = findViewById(R.id.list_notes);
        list.setAdapter(adp);

        list.setOnItemClickListener((parent, view, position, id) -> sel = position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if (data != null) {
            int pos = data.getIntExtra("note index", 1);
            String title = data.getStringExtra("note title");
            String content = data.getStringExtra("note content");

            Note n = adp.getItem(pos);
            n.title = title;
            n.content = content;

            adp.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void on_new_click(View v) {
        Note n = new Note();
        n.title = "New note";
        n.content = "Some content";

        adp.add(n);
        int pos = adp.getPosition(n);

        Intent i = new Intent(this, Activity2.class);
        i.putExtra("note index", pos);
        i.putExtra("note title", n.title);
        i.putExtra("note content", n.content);

        startActivityForResult(i, 12345);
    }
    public void on_edit_click(View v) {
        try {
            Note n = adp.getItem(sel);

            Intent i = new Intent(this, Activity2.class);
            i.putExtra("note index", sel);
            i.putExtra("note title", n.title);
            i.putExtra("note content", n.content);

            startActivityForResult(i, 12345);
        }
        catch (Exception e){
            Toast.makeText(this, "Выберите заметку", Toast.LENGTH_SHORT).show();
        }
    }
    public void on_delete_click(View v) {
        try {
            Note n = adp.getItem(sel);

            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            AlertDialog dlg = bld.create();
            dlg.setTitle("Удаление");
            dlg.setMessage("Вы уверены, что хотите удалить заметку?");
            dlg.setButton(Dialog.BUTTON_POSITIVE,"Да", (dialog, id) -> adp.remove(n));
            dlg.setButton(Dialog.BUTTON_NEGATIVE,"Нет", (dialog, id) -> dialog.cancel());
            dlg.show();
        }
        catch (Exception e){
            Toast.makeText(this, "Выберите заметку", Toast.LENGTH_SHORT).show();
        }
    }
}