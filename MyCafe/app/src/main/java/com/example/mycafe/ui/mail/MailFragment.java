package com.example.mycafe.ui.mail;

import android.content.Intent;
import android.net.Uri;
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
import com.example.mycafe.databinding.FragmentMailBinding;
import com.example.mycafe.ui.about.AboutViewModel;
import com.google.android.material.snackbar.Snackbar;

public class MailFragment extends Fragment {

    private FragmentMailBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MailViewModel mailViewModel =
                new ViewModelProvider(this).get(MailViewModel.class);

        binding = FragmentMailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMail;
        mailViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"C109118142@nkust.edu.tw"}); // 收件人
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
        // You can also attach multiple items by passing an ArrayList of Uris
        startActivity(emailIntent);

        Toast.makeText(getContext(), "Gmail is opening ...", Toast.LENGTH_LONG).show();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}