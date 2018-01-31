package com.sujan.traverse.matrix;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class ResultActivity extends AppCompatActivity {
    TextView textViewResult;
    ProgressBar progressBar;
    static int matrix[][];
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textViewResult = findViewById(R.id.textView_Result);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (result == null) {
            Intent i = getIntent();
            matrix = (int[][]) i.getSerializableExtra("matrix");
            Intent serviceIntent = new Intent(this, TraverseService.class);
            startService(serviceIntent);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            textViewResult.setText(result);
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Receives a message via EventBus and display the result.
     *
     * @param message message to acknowledge.
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        result = message;
        textViewResult.setText(message);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("result", result);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        result = savedInstanceState.getString("result");
        super.onRestoreInstanceState(savedInstanceState);
    }
}

class TraverseService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public TraverseService() {
        super("This is intent service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        MatrixTraverse mat = new MatrixTraverse(ResultActivity.matrix);
        final List<String> trList = mat.traverse();
        Log.e("mes", "Inside intent service");
        EventBus.getDefault().post(" " + trList.get(2) + "\n Traversecost:  " + trList.get(0) + " \n Traverse path " + trList.get(1));


    }
}

