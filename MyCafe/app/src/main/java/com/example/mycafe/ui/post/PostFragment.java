package com.example.mycafe.ui.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mycafe.databinding.FragmentAboutBinding;
import com.example.mycafe.databinding.FragmentPostBinding;
import com.example.mycafe.ui.about.AboutViewModel;

public class PostFragment extends Fragment {

    private FragmentPostBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PostViewModel postViewModel =
                new ViewModelProvider(this).get(PostViewModel.class);

        binding = FragmentPostBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPost;
        postViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Toast.makeText(getContext(), "Upcoming Events", Toast.LENGTH_LONG).show();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}