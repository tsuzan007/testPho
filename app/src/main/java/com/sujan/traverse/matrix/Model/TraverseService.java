package com.sujan.traverse.matrix.Model;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Intent service to handle heavy calculations in background
 */
public class TraverseService extends IntentService {
    ITraverse iTraverse;
    IMatrixValidation iMatrixValidation;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public TraverseService() {

        super("This is intent service");
        iMatrixValidation = new MatrixValidation();
        iTraverse = new Traverse();


    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int[][] matrix = (int[][]) intent.getSerializableExtra("matrix");
        MatrixTraverse mat = new MatrixTraverse(matrix, iTraverse, iMatrixValidation);
        final List<String> trList = mat.traverse();
        EventBus.getDefault().post(" " + trList.get(2) + "\n Traversecost:  " + trList.get(0) + " \n Traverse path " + trList.get(1));


    }
}
