package ro.ase.lab;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ro.ase.lab.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final ArrayList<Student> students = new ArrayList<>();
    private ArrayAdapter<Student> adapter;
    private ActivityResultLauncher<Intent> addLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, students);
        binding.listView.setAdapter(adapter);

        addLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Student s = result.getData()
                                .getSerializableExtra("student", Student.class);
                        if (s != null) {
                            students.add(s);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

        binding.fab.setOnClickListener(v ->
                addLauncher.launch(new Intent(this, AddStudentActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_about) {
            Toast.makeText(this, R.string.toast_about, Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.nav_help) {
            Toast.makeText(this, R.string.toast_help, Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}