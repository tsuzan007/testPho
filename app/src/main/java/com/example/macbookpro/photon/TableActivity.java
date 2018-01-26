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
    List<EditText> allETs;
    public static int arr[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        row = getIntent().getIntExtra("row", 0);
        col = getIntent().getIntExtra("col", 0);
        arr = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i][j] = 0;
            }
        }
        HorizontalScrollView svH = new HorizontalScrollView(this);
        svH.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ScrollView svV = new ScrollView(this);
        svV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout llV = new LinearLayout(this);
        llV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        llV.setOrientation(LinearLayout.VERTICAL);

        TableLayout simple_game = new TableLayout(this);
        simple_game.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT));

        allETs = new ArrayList<EditText>();
        int tot = 0;
        for (int i = 0; i < row; i++) {
            TableRow tr = new TableRow(this);
            simple_game.addView(tr);
            TableLayout.LayoutParams trPara = new TableLayout.LayoutParams();
            trPara.setMargins(25, 5, 5, 5);
            tr.setLayoutParams(trPara);

            for (int j = 0; j < col; j++) {
                EditText iv = new EditText(this);
                tot++;
                allETs.add(iv);
                tr.addView(iv);
                TableRow.LayoutParams trPara2 = new TableRow.LayoutParams();
                trPara2.width = 150;
                trPara2.setMargins(25, 5, 5, 5);
                trPara2.height = ViewGroup.LayoutParams.MATCH_PARENT;
                iv.setLayoutParams(trPara2);
            }
        }
        Button b1 = new Button(this);
        b1.setText("Submit");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tot = 0, nas = 0;
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        try {
                            arr[i][j] = Integer.parseInt(allETs.get(tot).getText().toString());
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
                    startActivity(new Intent(TableActivity.this, ResultActivity.class));
                }
            }
        });
        llV.addView(simple_game);
        llV.addView(b1);
        svV.addView(llV);
        svH.addView(svV);
        setContentView(svH);
    }
}
