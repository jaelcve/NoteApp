package info.jianhuang.NoteApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNotesActivity extends AppCompatActivity {
    private EditText et_title;
    private EditText et_description;
    private NumberPicker np_priority;

    public static final String EXTRA_ID = "EXTRA_ID";

    public static final String EXTRA_TITLE = "EXTRA_TITLE";

    public static final String EXTRA_DESC = "EXTRA_DESC";

    public static final String EXTRA_PRIORITY = "EXTRA_PRIORITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        et_title = findViewById(R.id.edit_text_title);
        et_description = findViewById(R.id.edit_text_description);
        np_priority = findViewById(R.id.number_picker_priority);

        np_priority.setMinValue(1);
        np_priority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            et_title.setText(intent.getStringExtra(EXTRA_TITLE));
            et_description.setText(intent.getStringExtra(EXTRA_DESC));
            np_priority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        }else {
            setTitle("Add Note"); //action bar title
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

        private void saveNote() {
            String title = et_title.getText().toString();
            String desc = et_description.getText().toString();
            int priority = np_priority.getValue();

            if (title.trim().isEmpty() || desc.trim().isEmpty()){
                Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent data = new Intent();
            data.putExtra(EXTRA_TITLE, title);
            data.putExtra(EXTRA_DESC, desc);
            data.putExtra(EXTRA_PRIORITY, priority);

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if(id != -1){
                data.putExtra(EXTRA_ID, id);
            }

            setResult(RESULT_OK, data);
            finish();


        }
}
