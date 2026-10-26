package ro.ase.lab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etYear;
    private Spinner spFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etName = findViewById(R.id.etName);
        etYear = findViewById(R.id.etYear);
        spFaculty = findViewById(R.id.spFaculty);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> save());
    }

    private void save() {
        String name = etName.getText().toString().trim();
        String yearStr = etYear.getText().toString().trim();

        if (name.isEmpty() || yearStr.isEmpty()) {
            Toast.makeText(this, R.string.err_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        int year = Integer.parseInt(yearStr);
        if (year < 1 || year > 3) {
            Toast.makeText(this, R.string.err_year, Toast.LENGTH_SHORT).show();
            return;
        }

        String faculty = spFaculty.getSelectedItem().toString();

        Student student = new Student(name, faculty, year);
        Intent data = new Intent();
        data.putExtra("student", student);

        setResult(RESULT_OK, data);
        finish();
    }
}