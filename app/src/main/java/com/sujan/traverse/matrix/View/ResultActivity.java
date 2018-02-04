package com.sujan.traverse.matrix.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sujan.traverse.matrix.Model.MyViewModel;
import com.sujan.traverse.matrix.Model.TraverseService;
import com.sujan.traverse.matrix.Presenter.MainPresenter;
import com.sujan.traverse.matrix.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ResultActivity extends AppCompatActivity {
    TextView textViewResult;
    ProgressBar progressBar;
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
            int[][] matrix=(int[][])i.getSerializableExtra("matrix");
            Intent serviceIntent = new Intent(this, TraverseService.class);
            serviceIntent.putExtra("matrix",matrix);
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

