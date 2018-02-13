package com.sujan.traverse.matrix.Model;

import com.sujan.traverse.matrix.Model.HelperClass.Matrix;
import com.sujan.traverse.matrix.Model.HelperClass.Path;
import com.sujan.traverse.matrix.Model.HelperClass.Position;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by macbookpro on 2/12/18.
 */

public class Traverse implements ITraverse {
    public Path path;
    private boolean conditonBreak = false;

    /*
        Traverses in the matrix and finds the path of lowest cost.
    */
    @Override
    public List<String> traverse(int[][] matrix) {
        int j = 0;
        path=new Path("");
        List<String> result=new ArrayList<>();
        for (int i = 0; i < MatrixTraverse.max_height; i++) {
            findPath(matrix,i, j, 0, "");

        }
        if(MatrixTraverse.resultCost==Integer.MAX_VALUE){
            MatrixTraverse.resultCost=0;

        }
        result.add(MatrixTraverse.resultCost+"");
        result.add("[" + path.getPath().replaceAll(",", " ").trim() + "]");
        return result;
    }

    /**
     * Finds the path of lowest cost.
     * Invoked by traverse method.
     * <p>
     *
     * @param row         current row.
     * @param col         current col.
     * @param currentCost cost till the current position.
     * @param currentPath path till the current position.
     */

    private void findPath(int[][] matrix, int row, int col, int currentCost, String currentPath) {
        List<Position> nextPosiList = null;
        boolean conditionMaxCost = false;
        if (matrix[row][col] + currentCost < MatrixTraverse.MAX_COST) {
            currentPath += (row + 1) + ",";
            currentCost += matrix[row][col];
            nextPosiList = getAdjPosition(matrix,row, col);

        } else {
            conditionMaxCost = true;

        }
        // sort the position
        if (nextPosiList != null) {
            for (Position p : nextPosiList) {
                findPath(matrix,p.getX(), p.getY(), currentCost, currentPath);
            }
        } else {
            // current set Finished
            if (MatrixTraverse.resultCost >= currentCost) {
                if (!conditionMaxCost) {
                    MatrixTraverse.resultCost = currentCost;
                    path.setPath(currentPath);
                    conditonBreak = false;
                } else if (path.getPath().length() < currentPath.length()) {
                    MatrixTraverse.resultCost = currentCost;
                    path.setPath(currentPath);
                   conditonBreak = true;
                }

            } else if (currentPath.length() >= MatrixTraverse.max_width * 2 && conditonBreak) {

                if (!conditionMaxCost) {
                    MatrixTraverse.resultCost = currentCost;
                    path.setPath(currentPath);
                    conditonBreak = false;
                } else {
                    MatrixTraverse.resultCost = currentCost;
                    path.setPath(currentPath);
                    conditonBreak = true;

                }
            } else if (path.getPath().length() < currentPath.length() && (conditionMaxCost)) {
                MatrixTraverse.resultCost = currentCost;
                path.setPath(currentPath);
                conditonBreak = true;
            }

        }
    }

    /**
     * Returns the adjacent position of the current position.
     * Adjacent postions are choices we can travel along the matrix.
     * Invoked by your subclass's constructor.
     * <p>
     *
     * @param row current row.
     * @param col current col.
     */
    public List<Position> getAdjPosition(int[][] matrix, int row, int col) {
        List<Position> list = new ArrayList<>();
        if (row < 0 || col < 0) {
            return list;
        }
        int nexCol = col + 1;

        if (matrix[row].length > nexCol) {// same row next column
            Position p = new Position(row, nexCol);
            list.add(p);
        } else {
            return null; // now nextCol then end of array.
        }

        if (MatrixTraverse.max_height > row + 1) {
            Position p = new Position(row + 1, nexCol);
            list.add(p);
        } else {
            Position p = new Position(0, nexCol);
            list.add(p);
        }

        if (row - 1 >= 0) {
            Position p = new Position(row - 1, nexCol);
            list.add(p);
        } else {
            Position p = new Position(MatrixTraverse.max_height - 1, nexCol);
            list.add(p);
        }

        return list;

    }



}
