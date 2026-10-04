package ro.ase.lab;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ro.ase.lab.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "IMC_APP";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        binding.btnCalc.setOnClickListener(v -> calculeazaImc());
        binding.btnReset.setOnClickListener(v -> reseteaza());
        binding.cbDetails.setOnCheckedChangeListener((buttonView, isChecked) ->
                binding.tvDetails.setVisibility(isChecked ? View.VISIBLE : View.GONE));

        Log.d(TAG, "onCreate");
    }

    private void calculeazaImc() {
        String name = binding.etName.getText().toString().trim();
        String weightStr = binding.etWeight.getText().toString().trim();
        String heightStr = binding.etHeight.getText().toString().trim();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, R.string.err_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        double weight = Double.parseDouble(weightStr);
        double heightM = Double.parseDouble(heightStr) / 100.0;

        if (weight <= 0 || heightM <= 0) {
            Toast.makeText(this, R.string.err_invalid, Toast.LENGTH_SHORT).show();
            return;
        }

        double imc = weight / (heightM * heightM);

        String category;
        if (imc < 18.5) {
            category = getString(R.string.cat_under);
        } else if (imc < 25) {
            category = getString(R.string.cat_normal);
        } else if (imc < 30) {
            category = getString(R.string.cat_over);
        } else {
            category = getString(R.string.cat_obese);
        }

        if (name.isEmpty()) {
            name = getString(R.string.default_name);
        }

        binding.tvResult.setText(getString(R.string.result_format, name, imc, category));
        Log.d(TAG, "IMC calculat: " + imc);
    }

    private void reseteaza() {
        binding.etName.setText("");
        binding.etWeight.setText("");
        binding.etHeight.setText("");
        binding.cbDetails.setChecked(false);
        binding.tvResult.setText("");
        binding.etName.requestFocus();
    }
}