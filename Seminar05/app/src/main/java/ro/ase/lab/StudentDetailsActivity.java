package ro.ase.lab;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        Student student = getIntent().getSerializableExtra("student", Student.class);
        if (student == null) {
            finish();
            return;
        }

        TextView tvName = findViewById(R.id.tvName);
        TextView tvFaculty = findViewById(R.id.tvFaculty);
        TextView tvYear = findViewById(R.id.tvYear);

        tvName.setText(student.getName());
        tvFaculty.setText(getString(R.string.faculty_format, student.getFaculty()));
        tvYear.setText(getString(R.string.year_format, student.getYear()));

        Button btnShare = findViewById(R.id.btnShare);
        Button btnWeb = findViewById(R.id.btnWeb);

        btnShare.setOnClickListener(v -> {
            Intent send = new Intent(Intent.ACTION_SEND);
            send.setType("text/plain");
            send.putExtra(Intent.EXTRA_TEXT, student.toString());
            startActivity(Intent.createChooser(send, getString(R.string.share_title)));
        });

        btnWeb.setOnClickListener(v -> {
            Intent view = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ase.ro"));
            try {
                startActivity(view);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, R.string.err_no_app, Toast.LENGTH_SHORT).show();
            }
        });
    }
}