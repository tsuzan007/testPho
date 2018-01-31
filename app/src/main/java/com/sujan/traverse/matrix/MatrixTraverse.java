package com.sujan.traverse.matrix;

import java.util.ArrayList;
import java.util.List;


public class MatrixTraverse extends Matrix implements ElementValidation {
    private int max_width, max_height, resultCost = Integer.MAX_VALUE;
    private int maxcost = 50;
    private int MAX_COST;
    private boolean conditonBreak = false;
    private Path path;
    static int finalCost = 0;
    static String finalPath;
    static String isPath;

    /**
     * Constructor of the class. Initialize matrix and other parameters.
     *
     * @param mgrid matrix to traverse
     */
    public MatrixTraverse(int[][] mgrid) {
        super(mgrid);
        path = new Path("");
        max_width = getMax_width();
        max_height = getMax_height();
        if (max_width > 10) {
            maxcost = 21;
        }
        MAX_COST = maxcost;
        if ((max_height == 0) && (max_width == 0)) {
            List<String> trv = new ArrayList<>();
            trv.add("" + -1);
        }

    }


    /*
        Traverses in the matrix and finds the path of lowest cost.
    */
    public void startTraverse() {
        int j = 0;
        for (int i = 0; i < max_height; i++) {
            findPath(i, j, 0, "");

        }
        display();
    }


    /**
     * Prints the final result in the console
     */
    private void display() {
        System.out.println("Final Out!!");
        if (conditonBreak) {
            System.out.println("No");
            isPath = "No";
        } else {
           System.out.println("Yes");
            isPath = "Yes";
        }
        if (Integer.MAX_VALUE == resultCost) resultCost = 0;
        System.out.println(resultCost);
        finalCost = resultCost;
        System.out.println("[" + path.getPath().replaceAll(",", " ").trim() + "]");
        finalPath = "[" + path.getPath().replaceAll(",", " ").trim() + "]";


    }


    /**
     * Main method to find the path of the lowest cost.
     * Traverse along the matrix and find the path
     *
     * @return Lists of integer that represent rows traversed to find the path
     */
    public List<String> traverse() {
        this.startTraverse();
        String validChk = matrixchk(getMgrid());
        if (validChk.equalsIgnoreCase("valid")) {
            System.out.println("TRAVERSE " + finalCost);
            List<String> traverseList = new ArrayList<>();
            String s = Integer.toString(finalCost);
            String cond;
            if (conditonBreak)
                cond = "No";
            else {
                cond = "Yes";
            }

            traverseList.add(s);
            traverseList.add(finalPath);
            traverseList.add(isPath);
            return traverseList;
        } else {
            List<String> traverseList = new ArrayList<>();
            traverseList.add("invalid Matrix");
            String val = Integer.toString(-1);
            traverseList.add(val);
            return traverseList;


        }

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
    public void findPath(int row, int col, int currentCost, String currentPath) {

        List<Position> nextPosiList = null;
        boolean conditionMaxCost = false;
        if (getMgrid()[row][col] + currentCost < MAX_COST) {
            currentPath += (row + 1) + ",";
            currentCost += getMgrid()[row][col];
            nextPosiList = getAdjPosition(row, col);

        } else {
            conditionMaxCost = true;

        }
        // sort the position
        if (nextPosiList != null) {
            for (Position p : nextPosiList) {
                findPath(p.getX(), p.getY(), currentCost, currentPath);
            }
        } else {
            // current set Finished
            if (resultCost >= currentCost) {
                if (!conditionMaxCost) {
                    resultCost = currentCost;
                    path.setPath(currentPath);
                    conditonBreak = false;
                } else if (path.getPath().length() < currentPath.length()) {
                    resultCost = currentCost;
                    path.setPath(currentPath);
                    conditonBreak = true;
                }

            } else if (currentPath.length() >= max_width * 2 && conditonBreak) {
                if (!conditionMaxCost) {
                    resultCost = currentCost;
                    path.setPath(currentPath);
                    conditonBreak = false;
                } else {
                    resultCost = currentCost;
                    path.setPath(currentPath);
                    conditonBreak = true;

                }
            } else if (path.getPath().length() < currentPath.length() && (conditionMaxCost)) {
                resultCost = currentCost;
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
    public List<Position> getAdjPosition(int row, int col) {
        List<Position> list = new ArrayList<>();
        if (row < 0 || col < 0) {
            return list;
        }
        int nexCol = col + 1;

        if (getMgrid()[row].length > nexCol) {// same row next column
            Position p = new Position(row, nexCol);
            list.add(p);
        } else {
            return null; // now nextCol then end of array.
        }

        if (max_height > row + 1) {
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
            Position p = new Position(max_height - 1, nexCol);
            list.add(p);
        }

        return list;

    }

    /**
     * Checks if the matrix is valid or not
     *
     * @param matrixTest matrix to check
     * @return the result valid/not
     */
    public String matrixchk(int[][] matrixTest) {
        boolean nonNumeric;
        String val = "";
        if (matrixTest.length < 0) {
            return "invalid";
        } else {
            int row = matrixTest.length;
            int col = matrixTest[0].length;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    nonNumeric = chkNonNumeric("" + matrixTest[i][j]);
                    if (nonNumeric == false) {
                        val = "invalid";
                        break;
                    } else {
                        val = "valid";
                    }
                }
            }
            return val;
        }
    }


    @Override
    public boolean chkNonNumeric(String num) {

        boolean isNumber = true;
        try {
            Integer.parseInt(num);
        } catch (NumberFormatException ex) {
            isNumber = false;
        }
        return isNumber;
    }


}
