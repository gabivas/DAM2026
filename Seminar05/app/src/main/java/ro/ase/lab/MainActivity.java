package ro.ase.lab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final ArrayList<Student> students = new ArrayList<>();
    private ArrayAdapter<Student> adapter;
    private ActivityResultLauncher<Intent> addLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            ArrayList<Student> saved =
                    savedInstanceState.getSerializable("students", ArrayList.class);
            if (saved != null) {
                students.addAll(saved);
            }
        }

        ListView listView = findViewById(R.id.listView);
        FloatingActionButton fab = findViewById(R.id.fab);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, students);
        listView.setAdapter(adapter);

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

        fab.setOnClickListener(v ->
                addLauncher.launch(new Intent(this, AddStudentActivity.class)));

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, StudentDetailsActivity.class);
            intent.putExtra("student", students.get(position));
            startActivity(intent);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.dlg_delete_title)
                    .setMessage(getString(R.string.dlg_delete_msg,
                            students.get(position).getName()))
                    .setPositiveButton(R.string.btn_yes, (dialog, which) -> {
                        students.remove(position);
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton(R.string.btn_no, null)
                    .show();
            return true;
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("students", students);
    }

}