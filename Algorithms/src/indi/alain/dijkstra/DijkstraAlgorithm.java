package indi.alain.dijkstra;

import java.util.Arrays;

/**
 * @author d
 */
public class DijkstraAlgorithm {


    static class Graph {
        private char[] vertices;
        private int[][] matrix;
        private VisitedVertex visitedVertex;

        public Graph(char[] vertices, int[][] matrix) {
            this.vertices = vertices.clone();
            this.matrix = matrix.clone();
        }

        public void showGraph() {
            for (int[] link : matrix) {
                System.out.println(Arrays.toString(link));
            }
        }

        public void dijkstra(int index) {
            visitedVertex = new VisitedVertex(vertices.length, index);
            //广度优先，邻接矩阵同一层的都是两个顶点直连
            //表示访问所有与index直连的顶点
            update(index);
            for (int j = 1; j < vertices.length; j++) {
                index = visitedVertex.updateArr();
                //更新index顶点到周围顶点的距离和前驱节点
                update(index);
            }
        }

        private void update(int index) {
            int len = 0;
            for (int j = 0; j < matrix[index].length; j++) {
                //len 若g为出发顶点，g到第一层的距离，第二个出发点到第二层的距离
                len = visitedVertex.getDistance(index) + matrix[index][j];
                if ( !visitedVertex.isVisited(j) && len < visitedVertex.getDistance(j)) {
                    //虽然走了多步，但是还是比只走一步距离短
                    visitedVertex.updatePre(j, index);
                    //j 的前驱改为index， index到j的距离改为len
                    visitedVertex.updateDistance(j, len);
                }
            }
        }

        public void show() {
            visitedVertex.show();
        }

    }


    public static void main(String[] args) {
        char[] vertices = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertices.length][vertices.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Graph graph = new Graph(vertices, matrix);
        graph.showGraph();
        graph.dijkstra(6);
        graph.show();
    }

}

class VisitedVertex {
    public int[] alreadyArr;
    public int[] preVisited;
    public int[] distance;

    /**
     *
     * @param num 顶点个数
     * @param index 出发顶点的下标
     */
    public VisitedVertex(int num, int index) {
        this.alreadyArr = new int[num];
        this.preVisited = new int[num];
        this.distance = new int[num];

        //初始化distance
        Arrays.fill(distance, 65535);
        //第一个顶点被访问
        this.alreadyArr[index] = 1;
        //到自己的距离为0
        this.distance[index] = 0;
    }

    public boolean isVisited(int index) {
        //判断该下标节点是否被访问过
        return alreadyArr[index] == 1;
    }

    public void updateDistance(int index, int len) {
        distance[index] = len;
    }

    public void updatePre(int pre, int index) {
        //更新pre这个顶点的前驱节点为index顶点
        preVisited[pre] = index;
    }

    public int getDistance(int index) {
        //得到出发顶点到index顶点的距离
        return distance[index];
    }

    /**
     * 找到下一次开始的顶点
     * 规则是距离最小
     * @return 下一个节点下标
     */
    public int updateArr() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && distance[i] < min) {
                min = distance[i];
                index = i;
            }
        }
        alreadyArr[index] = 1;
        return index;
    }

    public void show() {
        System.out.println("============================");
        System.out.println(Arrays.toString(alreadyArr));
        System.out.println("============================");
        System.out.println(Arrays.toString(preVisited));
        System.out.println("============================");
        System.out.println("G到每个顶点的最短距离分别为 = " + Arrays.toString(distance));
        char[] vertices = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : distance) {
            if (i != 65535) {
                System.out.println(vertices[count] + "(" + i + ")");
            } else {
                System.out.println("N ");
            }
            count++;
        }
    }

}
