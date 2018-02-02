package com.sujan.traverse.matrix.Presenter;


import com.sujan.traverse.matrix.HelperClass.Position;

/**
 * Created by macbookpro on 2/2/18.
 */

public class MainPresenter implements PresenterOps {
    public static String matrix[][];
    public static int int_matrix[][];
    public static int row = 0;
    public static int col = 0;

    @Override
    public boolean onClick_submit() {
        boolean error = false;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                try {
                    int_matrix[i][j] = Integer.parseInt(matrix[i][j]);
                } catch (NumberFormatException nfe) {
                    error = true;
                    break;
                }
            }
            if (error == true)
                break;
        }
        return error;

    }

    @Override
    public void onTableTextChange(Position p, CharSequence c) {
        MainPresenter.matrix[p.getX()][p.getY()] = c.toString();
    }

    @Override
    public void initMatrices() {
        for (int i = 0; i < MainPresenter.row; i++) {
            for (int j = 0; j < MainPresenter.col; j++) {
                MainPresenter.matrix[i][j] = "";
                MainPresenter.int_matrix[i][j] = 0;


            }
        }
    }


}
