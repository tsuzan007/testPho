package com.example.macbookpro.photon;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Context context;
    EditText editTextRow, editTextCol;
    Button b1;
    int row = 0, col = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextRow = (EditText) findViewById(R.id.et1);
        editTextCol = (EditText) findViewById(R.id.et2);
        b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    row = Integer.parseInt(editTextRow.getText().toString());
                    col = Integer.parseInt(editTextCol.getText().toString());
                    if (col > 10)
                        showAlert(MainActivity.this, "Sorry!", "Max col size is 10 because the int value can hold upto 32500 approx. only.");
                    else if (col >= 1) {
//                        move to next activity for further processing
                        Intent i = new Intent(MainActivity.this, TableActivity.class);
                        i.putExtra("row", row);
                        i.putExtra("col", col);
                        startActivity(i);

                    } else if (col < 1)
                        showAlert(MainActivity.this, "No input!", "Improper value detected. Please enter a number greater than 0.");
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                     showAlert(MainActivity.this, "Non numeric!", "Invalid Matrix.");
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Something went wrong.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void showAlert(Context c, String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(c);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setNeutralButton("OK", null);
        dialog.create().show();
    }
}
