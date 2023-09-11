package com.example.mycafe.ui.shop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mycafe.CommonData;
import com.example.mycafe.R;
import com.example.mycafe.databinding.FragmentSlideshowBinding;

import java.util.List;

import kotlin.collections.UArraySortingKt;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        binding.planetsSpinner.setOnItemSelectedListener(planetsSpinnerSelected);//監聽
        Toast.makeText(getContext(), "分店查詢", Toast.LENGTH_LONG).show();


        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private final AdapterView.OnItemSelectedListener planetsSpinnerSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String s1 = "";

            switch (position) {
                case 0:
                    s1 = "高雄門市";
                    binding.planetImageView.setImageResource(R.drawable.store01);
                    break;

                case 1:
                    s1 = "澎湖門市";
                    binding.planetImageView.setImageResource(R.drawable.store02);
                    break;

                case 2:
                    s1 = "台中門市";
                    binding.planetImageView.setImageResource(R.drawable.store03);
                    break;
            }
            if (!TextUtils.isEmpty(s1))
                Toast.makeText(getContext(), s1, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

}