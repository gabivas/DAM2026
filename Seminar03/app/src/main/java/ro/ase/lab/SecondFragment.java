package ro.ase.lab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondFragment extends Fragment {

    public SecondFragment() {
        super(R.layout.fragment_second);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvGreeting = view.findViewById(R.id.tvGreeting);
        Button btnBack = view.findViewById(R.id.btnBack);

        String name = requireArguments().getString("name");
        tvGreeting.setText(getString(R.string.greeting, name));

        btnBack.setOnClickListener(v ->
                getParentFragmentManager().popBackStack());
    }
}