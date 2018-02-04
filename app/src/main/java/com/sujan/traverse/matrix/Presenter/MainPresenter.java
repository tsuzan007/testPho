package com.sujan.traverse.matrix.Presenter;


import com.sujan.traverse.matrix.HelperClass.Position;

/**
 * Created by macbookpro on 2/2/18.
 */

public class MainPresenter implements PresenterOps {
    private   String matrix[][];
    private   int int_matrix[][];
    private   int row = 0;
    private   int col = 0;

    public String[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] getInt_matrix() {
        return int_matrix;
    }

    public void setInt_matrix(int[][] int_matrix) {
        this.int_matrix = int_matrix;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

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
        matrix[p.getX()][p.getY()] = c.toString();
    }

    @Override
    public void initMatrices() {
        matrix = new String[this.row][this.col];
        int_matrix = new int[this.row][this.col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = "";
                int_matrix[i][j] = 0;


            }
        }
    }


}
