package com.sujan.traverse.matrix.Model;

import com.sujan.traverse.matrix.Model.HelperClass.Position;

import java.util.List;

/**
 * Created by macbookpro on 2/12/18.
 */

public interface ITraverse {
    List<String> traverse(int[][] matrix);

    List<Position> getAdjPosition(int[][] matrix, int row, int col);

}
