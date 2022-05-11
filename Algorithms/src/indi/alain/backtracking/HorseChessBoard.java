package indi.alain.backtracking;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author d
 */
public class HorseChessBoard {
    private int row;
    private int column;
    private boolean[] visited;
    private boolean finished;
    private int imperativeStep;

    public HorseChessBoard(int row, int column) {
        this.row = row;
        this.column = column;
        this.imperativeStep = row * column - 1;
        this.finished = false;
//        this.visited = new boolean[row][column];
//        for (boolean[] link : this.visited) {
//            Arrays.fill(link, false);}
        this.visited = new boolean[this.row * this.column];
        //Arrays.fill(visited, false);
    }

    /**
     * @param row0    当前行
     * @param column0 当前列
     * @param step   目前第几步
     */
    public void traversalChessBoard(int[][] chessBoard, int row0, int column0, int step) {
        chessBoard[row0][column0] = step;
        visited[row0 * this.row + column0] = true;
        //row , column 与y，x对应
        ArrayList<Point> pointArrayList = next(new Point(column0, row0));
        while (!pointArrayList.isEmpty()) {
            //取出下一个可以走的位置
            Point point = pointArrayList.get(0);
            pointArrayList.remove(1);
            if (!visited[point.y * this.row + point.x]) {
                //判断当前位置是否访问过
                traversalChessBoard(chessBoard, point.y, point.x, step + 1);
            }
        }
        if (step < this.imperativeStep && !finished) {
            chessBoard[row0][column0] = 0;
            visited[row0 * this.row + column0] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前位置，计算马儿还能走哪些位置，并放入Arraylist集合中
     *
     * @param currentPoint 当前位置
     * @return 所有可能走位置的数组
     */
    public  static ArrayList<Point> next(Point currentPoint) {
        ArrayList<Point> pointArrayList = new ArrayList<>();
        int row = 8;
        int column = 8;
        Point point = new Point();
        //5
        if ((point.x = currentPoint.x - 2) >= 0 && (point.y = currentPoint.y - 1) >= 0) {
            //深拷贝
            pointArrayList.add(new Point(point));
        }
        //6
        if ((point.x = currentPoint.x - 1) >= 0 && (point.y = currentPoint.y - 2) >= 0) {
            pointArrayList.add(new Point(point));
        }
        //7
        if ((point.x = currentPoint.x + 1) < row && (point.y = currentPoint.y - 2) >= 0) {
            pointArrayList.add(new Point(point));
        }
        //0
        if ((point.x = currentPoint.x + 2) < row && (point.y = currentPoint.y - 1) >= 0) {
            pointArrayList.add(new Point(point));
        }
        //1
        if ((point.x = currentPoint.x + 2) < row && (point.y = currentPoint.y + 1) < column) {
            pointArrayList.add(point);
        }
        //2
        if ((point.x = currentPoint.x + 1) < row && (point.y = currentPoint.y + 2) < column) {
            pointArrayList.add(new Point(point));
        }
        //3
        if ((point.x = currentPoint.x - 1) >= 0 && (point.y = currentPoint.y + 2) < column) {
            pointArrayList.add(new Point(point));
        }
        //4
        if ((point.x = currentPoint.x - 2) >= 0 && (point.y = currentPoint.y + 1) < column) {
            pointArrayList.add(new Point(point));
        }
        return pointArrayList;
    }

    public static void sort(ArrayList<Point> pointArrayList) {
        pointArrayList.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size() - next(o2).size();
            }
        });
    }

    public static void main(String[] args) {
        int[][] chessboard = new int[8][8];
        int a = 3;
        int b = 3;
        HorseChessBoard horseChessBoard = new HorseChessBoard(8, 8);
        horseChessBoard.traversalChessBoard(chessboard,a  ,b, 1);
    }

}
