package com.example.macbookpro.photon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity {

    int row = 0, col = 0;
    List<EditText> editTextList;
    private int matrix[][];
    private Button button_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        row = getIntent().getIntExtra("row", 0);
        col = getIntent().getIntExtra("col", 0);
        matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = 0;
            }
        }
        HorizontalScrollView horizontalScrollView= new HorizontalScrollView(this);
        horizontalScrollView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TableLayout simple_game = new TableLayout(this);
        simple_game.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT));

        editTextList = new ArrayList<EditText>();
        int tot = 0;
        for (int i = 0; i < row; i++) {
            TableRow tableRow = new TableRow(this);
            simple_game.addView(tableRow);
            TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
            layoutParams.setMargins(25, 5, 5, 5);
            tableRow.setLayoutParams(layoutParams);

            for (int j = 0; j < col; j++) {
                EditText editText= new EditText(this);
                editText.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                tot++;
                editTextList.add(editText);
                tableRow.addView(editText);
                TableRow.LayoutParams tableRow_layoutParams = new TableRow.LayoutParams();
                tableRow_layoutParams.width = 150;
                tableRow_layoutParams.setMargins(25, 5, 5, 5);
                tableRow_layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                editText.setLayoutParams(tableRow_layoutParams);
            }
        }
        button_submit = new Button(this);
        button_submit.setText("Submit");
        linearLayout.addView(simple_game);
        linearLayout.addView(button_submit);
        scrollView.addView(linearLayout);
        horizontalScrollView.addView(scrollView);
        setContentView(horizontalScrollView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tot = 0, nas = 0;
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        try {
                            matrix[i][j] = Integer.parseInt(editTextList.get(tot).getText().toString());
                            tot++;
                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                            Toast.makeText(TableActivity.this, "Invalid number", Toast.LENGTH_LONG).show();
                            nas = 1;
                            break;
                        }
                    }
                    if (nas == 1)
                        break;
                }
                if (nas == 0) {
                    Intent intent=new Intent(TableActivity.this,ResultActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("matrix",matrix);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

    }
}
