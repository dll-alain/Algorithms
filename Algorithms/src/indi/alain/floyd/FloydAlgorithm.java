package indi.alain.floyd;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author dll
 * @date 20220509
 */
public class FloydAlgorithm {

    public static void main(String[] args) {
        char[] vertices = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertices.length][vertices.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        Graph graph = new Graph(matrix, vertices);
        graph.floyd();
        graph.show();
    }

    static class Graph {
        /**
         * 1 顶点数组
         * 2 顶点间的距离
         * 3 前驱节点
         * 4 顶点个数
         */
        private char[] vertices;
        private int[][] distance;
        private int[][] preVertex;
        private int numVertices;

        /**
         * @param matrix   邻接矩阵
         * @param vertices 顶点数组
         */
        public Graph(int[] @NotNull [] matrix, char[] vertices) {
            this.numVertices = vertices.length;
            this.vertices = vertices.clone();
            this.distance = matrix.clone();
            this.preVertex = new int[numVertices][numVertices];
            //pre数组，存放的是前驱节点的下标
            for (int i = 0; i < numVertices; i++) {
                Arrays.fill(preVertex[i], i);
            }
        }

        public void show() {
            int i = 0, j = 0;
            System.out.println("前驱节点数组");
            for (int[] link : preVertex) {
                System.out.println(Arrays.toString(link));
            }
            System.out.println("最短距离数组");

            for (int[] link : distance) {
                System.out.println(Arrays.toString(link));
            }
        }

        public void floyd() {
            int dis = 0;
            for (int k = 0; k < numVertices; k++) {
                //1 第一层为中间顶点
                for (int i = 0; i < numVertices; i++) {
                    //2 第二次层为出发顶点
                    for (int j = 0; j < numVertices; j++) {
                        //3 第三层为终点
                        // 从i 出发 到k ，k 到 j 相加求出距离
                        dis = distance[i][k] + distance[k][j];
                        if (dis < distance[i][j]) {
                            //如果i到k k 到j 的距离小于i 直接到j 则更新最小距离
                            distance[i][j] = dis;
                            //距离更新了，前驱节点的数组也需要更新
                            //i为起点，j 为终点 i 就为j 的前驱节点 pre[i][j]
                            //当i j 变为 i k j 时， j 的前驱节点就变为了 k
                            preVertex[i][j] = preVertex[k][j];
                        }
                    }
                }
            }
        }


    }

}
