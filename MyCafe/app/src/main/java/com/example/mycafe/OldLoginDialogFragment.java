package com.example.mycafe;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

//import com.example.mycafe.ui.about.AboutFragment;

public class OldLoginDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction

        //LayoutInflater inflater = getActivity().getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setMessage("是否創立新帳號");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogs_oldlogin,null);
        EditText username = (EditText) view.findViewById(R.id.username);
        EditText password = (EditText)view.findViewById(R.id.password);

        //builder.setView(inflater.inflate(R.layout.dialogs_oldlogin, null))
        builder.setView(view)
                .setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //登入
                        if(username.getText().toString().equals("") && password.getText().toString().equals("")){
                            Toast.makeText(getContext(), "登入成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(getContext(),MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getContext(), "登入失敗", Toast.LENGTH_SHORT).show();

                        }
                    }
                })

                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //取消


                    }
                })

        ;
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
