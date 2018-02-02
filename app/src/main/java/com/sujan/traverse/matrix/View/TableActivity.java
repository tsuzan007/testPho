package com.sujan.traverse.matrix.View;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
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

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.sujan.traverse.matrix.HelperClass.Position;
import com.sujan.traverse.matrix.Model.MyViewModel;
import com.sujan.traverse.matrix.Presenter.MainPresenter;
import com.sujan.traverse.matrix.Presenter.ViewOps;
import com.sujan.traverse.matrix.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class TableActivity extends AppCompatActivity{


    List<EditText> editTextList;
    MainPresenter mainPresenter;
    private Button button_submit;
    private HorizontalScrollView horizontalScrollView;
    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private TableLayout simple_game;
    MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);
        mainPresenter=myViewModel.getMainPresenter();
        MainPresenter.row = getIntent().getIntExtra("row", 0);
        MainPresenter.col = getIntent().getIntExtra("col", 0);
        if (savedInstanceState == null) {
            MainPresenter.matrix = new String[MainPresenter.row][MainPresenter.col];
            MainPresenter.int_matrix = new int[MainPresenter.row][MainPresenter.col];
            mainPresenter.initMatrices();
        } else {
            MainPresenter.matrix = (String[][]) savedInstanceState.getSerializable("matrix");
        }
        createLayout();
        createTable();
        button_submit = new Button(this);
        button_submit.setText(getString(R.string.buttontext_submit));
        bindViews();
        setContentView(horizontalScrollView);
        if (savedInstanceState != null) {
            int number = 0;
            for (int i = 0; i < MainPresenter.row; i++) {
                for (int j = 0; j < MainPresenter.col; j++) {
                    String value = MainPresenter.matrix[i][j];
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
        for (int i = 0; i < MainPresenter.row; i++) {
            TableRow tableRow = new TableRow(this);
            simple_game.addView(tableRow);
            TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
            layoutParams.setMargins(25, 5, 5, 5);
            tableRow.setLayoutParams(layoutParams);
            for (int j = 0; j < MainPresenter.col; j++) {
                final EditText editText = new EditText(this);
                editText.setTag(new Position(i, j));
                RxTextView.textChanges(editText).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        Position position = (Position) editText.getTag();
                        mainPresenter.onTableTextChange(position, charSequence);
                        try {
                            Integer.parseInt(charSequence.toString());
                            editText.getBackground().clearColorFilter();
                        } catch (NumberFormatException n) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                editText.getBackground().setColorFilter(getColor(R.color.red), PorterDuff.Mode.SRC);
                            }
                            else{
                                editText.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC);
                            }
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
                if (mainPresenter.onClick_submit()) {
                    Toast.makeText(TableActivity.this, "Invalid Matrix", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(TableActivity.this, ResultActivity.class);
                    startActivity(intent);
                }
            }
        });


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("matrix", MainPresenter.matrix);
        super.onSaveInstanceState(outState);
    }


}
