package ro.ase.lab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ro.ase.lab.databinding.ActivityAddStudentBinding;

public class AddStudentActivity extends AppCompatActivity {

    private ActivityAddStudentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSave.setOnClickListener(v -> save());
    }

    private void save() {
        String name = binding.etName.getText().toString().trim();
        String yearStr = binding.etYear.getText().toString().trim();

        if (name.isEmpty() || yearStr.isEmpty()) {
            Toast.makeText(this, R.string.err_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        int year = Integer.parseInt(yearStr);
        if (year < 1 || year > 3) {
            Toast.makeText(this, R.string.err_year, Toast.LENGTH_SHORT).show();
            return;
        }

        String faculty = binding.spFaculty.getSelectedItem().toString();

        Student student = new Student(name, faculty, year);
        Intent data = new Intent();
        data.putExtra("student", student);

        setResult(RESULT_OK, data);
        finish();
    }
}