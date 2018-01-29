package com.example.macbookpro.photon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class ResultActivity extends AppCompatActivity {
    TextView textView_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView_cost = (TextView) findViewById(R.id.cost);
        seeTraverse();
    }

    public void seeTraverse() {
        MatrixTraverse mat = new MatrixTraverse(TableActivity.arr);
        List<String> trList = mat.traverse();
        textView_cost.setText(" " + trList.get(2) + "\n Traversecost:  " + trList.get(0) + " \n Traverse path " + trList.get(1));
    }
}
