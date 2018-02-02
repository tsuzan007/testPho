package com.sujan.traverse.matrix;


import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class TableActivity extends AppCompatActivity {

    int row = 0, col = 0;
    List<EditText> editTextList;
    private String matrix[][];
    public static int int_matrix[][];
    private Button button_submit;
    private HorizontalScrollView horizontalScrollView;
    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private TableLayout simple_game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        row = getIntent().getIntExtra("row", 0);
        col = getIntent().getIntExtra("col", 0);
        if (savedInstanceState == null) {
            matrix = new String[row][col];
            int_matrix=new int[row][col];
            initMatrices();
        } else {
            matrix = (String[][]) savedInstanceState.getSerializable("matrix");
        }
        createLayout();
        createTable();
        button_submit = new Button(this);
        button_submit.setText(getString(R.string.buttontext_submit));
        bindViews();
        setContentView(horizontalScrollView);
        if (savedInstanceState != null) {
            int number = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    String value = matrix[i][j];
                    try {
                        editTextList.get(number).setText(value);
                    } catch (NullPointerException e) {
                        editTextList.get(number).setText("");

                    }
                    number++;

                }

            }


        }
    }

    public void initMatrices(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j]="";
                int_matrix[i][j]=0;


            }
        }

    }


    /**
     * Create layout that holds table view
     */

    protected void createLayout() {
        horizontalScrollView = new HorizontalScrollView(this);
        horizontalScrollView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);


    }

    /**
     * Set up and create table view for the matrix, handles configuration changes.
     */
    protected void createTable() {
        simple_game = new TableLayout(this);
        simple_game.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT));
        editTextList = new ArrayList<EditText>();
        for (int i = 0; i < row; i++) {
            TableRow tableRow = new TableRow(this);
            simple_game.addView(tableRow);
            TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
            layoutParams.setMargins(25, 5, 5, 5);
            tableRow.setLayoutParams(layoutParams);
            for (int j = 0; j < col; j++) {
                final EditText editText = new EditText(this);
                editText.setTag(new Position(i, j));
                RxTextView.textChanges(editText).observeOn(Schedulers.io()).subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                            Position position = (Position) editText.getTag();
                            matrix[position.getX()][position.getY()] = charSequence.toString();
                            try{
                                Integer.parseInt(charSequence.toString());
                                editText.getBackground().clearColorFilter();
                            }catch (NumberFormatException n){
                                editText.getBackground().setColorFilter(getColor(R.color.red),PorterDuff.Mode.SRC);
                            }
                    }
                });
                editTextList.add(editText);
                tableRow.addView(editText);
                TableRow.LayoutParams tableRow_layoutParams = new TableRow.LayoutParams();
                tableRow_layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                tableRow_layoutParams.setMargins(25, 5, 5, 5);
                tableRow_layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                editText.setLayoutParams(tableRow_layoutParams);
            }
        }

    }

    protected void bindViews() {
        linearLayout.addView(simple_game);
        linearLayout.addView(button_submit);
        scrollView.addView(linearLayout);
        horizontalScrollView.addView(scrollView);

    }


    @Override
    protected void onResume() {
        super.onResume();
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        try {
                            int_matrix[i][j] = Integer.parseInt(matrix[i][j]);
                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                            Toast.makeText(TableActivity.this, "Invalid Matrix", Toast.LENGTH_SHORT).show();
                            error = true;
                            break;
                        }
                    }
                    if (error == true)
                        break;
                }

                if (error == false) {
                    Intent intent = new Intent(TableActivity.this, ResultActivity.class);
                    startActivity(intent);
                }
            }
        });


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("matrix", matrix);
        super.onSaveInstanceState(outState);
    }

}
