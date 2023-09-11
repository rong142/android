package com.example.mycafe.ui.record;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mycafe.databinding.FragmentHomeBinding;
import com.example.mycafe.databinding.FragmentRecordBinding;
import com.example.mycafe.ui.card.HomeViewModel;

public class RecordFragment extends Fragment {

    private FragmentRecordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RecordViewModel recordViewModel =
                new ViewModelProvider(this).get(RecordViewModel.class);

        binding = FragmentRecordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textRecord;
        //recordViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Toast.makeText(getContext(), "Record", Toast.LENGTH_LONG).show();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}