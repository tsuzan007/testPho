package com.sujan.traverse.matrix;

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
    private EditText editTextRow, editTextCol;
    private Button buttonSubmit;
    private int row = 0, col = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            row=savedInstanceState.getInt("row");
            col=savedInstanceState.getInt("col");
        }
        setContentView(R.layout.activity_main);
        editTextRow =  findViewById(R.id.editText_Row);
        editTextCol = findViewById(R.id.editText_Col);
        buttonSubmit =  findViewById(R.id.button_Submit);

    }

    @Override
    protected void onResume() {
        super.onResume();
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    row = Integer.parseInt(editTextRow.getText().toString());
                    col = Integer.parseInt(editTextCol.getText().toString());
                    if (col > 10)
                        showAlert(MainActivity.this, getString(R.string.alertdialog_sorry), getString(R.string.alertdialog_maxLimit_msg));
                    else if (col >= 1) {
                        Intent i = new Intent(MainActivity.this, TableActivity.class);
                        i.putExtra("row", row);
                        i.putExtra("col", col);
                        startActivity(i);

                    } else if (col < 1)
                        showAlert(MainActivity.this, getString(R.string.invalidMatrix), getString(R.string.alertdialog_invalidnum_msg));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    showAlert(MainActivity.this, getString(R.string.alertdialog_notNumeric), getString(R.string.invalidMatrix));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, getString(R.string.alertdialog_error_msg), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Show alert dialog
     * @param c Context
     * @param title Title of the message.
     * @param message Message to display.
     */

    public void showAlert(Context c, String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(c);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setNeutralButton(getString(R.string.buttontext_ok), null);
        dialog.create().show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("row",row);
        outState.putInt("col",col);
        super.onSaveInstanceState(outState);


    }
}
