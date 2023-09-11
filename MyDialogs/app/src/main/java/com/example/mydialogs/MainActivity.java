package com.example.mydialogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
        spinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void onClickButton(View view) {
        switch(view.getId()) {
            case R.id.button_missile:
                // < Method 1 > :  Creating a Dialog Fragment
                confirmFireMissiles();    break;

            case R.id.button_listDialog:
                // < Method 1 > :  Creating a Dialog Fragment
                //addList();
                signIn();   break;

            case R.id.button_brightness:
                // < METHOD 2 > :  Creating an Alert Dialog
                AlertDialog dialog = createAlertDialog();
                dialog.show();    break;
        }
    }

    // < Method 1 > :  Creating a Dialog Fragment
    public void confirmFireMissiles() {
        DialogFragment newFragment = new FireMissilesDialogFragment();
        newFragment.show(getSupportFragmentManager(), "missiles");
    }

    public void addList() {
        DialogFragment newFragment = new AddListDialogFragment();
        newFragment.show(getSupportFragmentManager(), "list");
    }

    public void signIn() {
        DialogFragment newFragment = new SigninDialogFragment();
        newFragment.show(getSupportFragmentManager(), "Signin");
    }

    // < Method 2 > :  Creating an Alert Dialog
    private AlertDialog createAlertDialog() {
        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);

        // Add the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                Toast.makeText(getApplicationContext(), "OK !!!", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                Toast.makeText(getApplicationContext(), "Cancel ,,,", Toast.LENGTH_LONG).show();
            }
        });

        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        AlertDialog dialog = builder.create();
        return dialog;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(position)
        String planet = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), planet, Toast.LENGTH_LONG).show();

        // ImageView for Planet images
        ImageView img = (ImageView) findViewById(R.id.planet_imageView);
        // img.setImageResource(R.drawable.mercury);

        switch(position) {
            case 0:
                img.setImageResource(R.drawable.mercury); break;
            case 1:
                img.setImageResource(R.drawable.venus); break;
            case 2:
                img.setImageResource(R.drawable.earth); break;
            /* case 3:
                img.setImageResource(R.drawable.mars); break;
            case 4:
                img.setImageResource(R.drawable.jupiter); break;
            case 5:
                img.setImageResource(R.drawable.saturn); break;
            case 6:
                img.setImageResource(R.drawable.uranus); break;
            case 7:
                img.setImageResource(R.drawable.neptune); break; */
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}