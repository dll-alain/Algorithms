package indi.alain.prim;

import java.util.Arrays;

/**
 * @author dll
 */
public class PrimAlgorithm {

    static public void prim(Graph graph, int beginVertex) {
        //标记顶点是否被访问过
        int[] visited = new int[graph.vertices];

        visited[beginVertex] = 1;
        //用h1， h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for (int k = 1; k < graph.vertices; k++) {
            //如果有n个顶点，则生成n-1条边

            //这个for循环结束，找到一对最小的一条边
            for (int i = 0; i < graph.vertices; i++) {
                //i 节点表示被访问过的节点, 快速循环到i 被访问
                for (int j = 0;j < graph.vertices; j++) {
                    //j 节点表示没有访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //这里找到被访问的i
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("边< " + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minWeight);
            visited[h2] = 1;
            minWeight = 10000;
        }
    }

    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertices = data.length;
        int[][] weight = new int[][] {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 100000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };

        Graph graph = new Graph(vertices);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, vertices, data, weight);
        minTree.showGraph(graph);
        prim(graph, 0);

    }

    static class MinTree{
        /**
         *创建图的邻接矩阵
         * @param graph 图
         * @param vertices 顶点数
         * @param data 各个顶点的值
         * @param weight 图的邻接矩阵
         */
        public void createGraph(Graph graph, int vertices, char[] data, int[][] weight) {
            int i, j;
            for (i = 0; i < vertices; i++) {
                graph.data[i] = data[i];
                for (j = 0; j < vertices; j++) {
                    graph.weight[i][j] = weight[i][j];
                }
            }
        }

        public void showGraph(Graph graph) {
            for (int[] link : graph.weight) {
                System.out.println(Arrays.toString(link));
            }
        }


    }

    /**
     * 连接图
     * 1 顶点个数
     * 2 顶点存放的数据
     * 2 存放边，也就是我们的邻接矩阵
     */
    static class Graph {
        private int vertices;
        char[] data;
        int[][] weight;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.data = new char[vertices];
            this.weight = new int[vertices][vertices];
        }


    }

}
