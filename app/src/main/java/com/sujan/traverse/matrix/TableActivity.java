package com.sujan.traverse.matrix;


import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.functions.Consumer;


public class TableActivity extends AppCompatActivity {

    int row = 0, col = 0;
    List<EditText> editTextList;
    private int matrix[][];
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
            initMatrix();
        } else {
            matrix = (int[][]) savedInstanceState.getSerializable("matrix");
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
                    int value = matrix[i][j];
                    if (value == 0) {
                        editTextList.get(number).setText("");

                    } else {
                        editTextList.get(number).setText(matrix[i][j] + "");

                    }
                    number++;
                }
            }

        }


    }

    /**
     * Initialize matrix with 0's.
     */
    protected void initMatrix() {
        matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = 0;
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
                RxTextView.textChanges(editText).subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        Log.e(".....", editText.getTag() + "");
                        if (!editText.getText().toString().isEmpty()) {
                            Position position = (Position) editText.getTag();
                            matrix[position.getX()][position.getY()] = Integer.parseInt(editText.getText().toString());
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

    protected void bindViews(){
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
                int number = 0;
                boolean error = false;
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        try {
                            matrix[i][j] = Integer.parseInt(editTextList.get(number).getText().toString());
                            number++;
                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                            Toast.makeText(TableActivity.this, "Invalid number", Toast.LENGTH_LONG).show();
                            error = true;
                            break;
                        }
                    }
                    if (error == true)
                        break;
                }

                if (error == false) {
                    Intent intent = new Intent(TableActivity.this, ResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("matrix", matrix);
                    intent.putExtras(bundle);
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
