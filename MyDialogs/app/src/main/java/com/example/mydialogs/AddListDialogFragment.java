package com.example.mydialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddListDialogFragment extends DialogFragment {
    static String color = null;
    // Adding a list into a Dialog
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.pick_color)
                .setItems(R.array.colors_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch(which) {
                            case 0: color = "Red"; break;
                            case 1: color = "Green"; break;
                            case 2: color = "Blue"; break;
                        }
                        Toast.makeText(getActivity(), color, Toast.LENGTH_LONG).show();
                    }
                });
        return builder.create();
    }
}
