package indi.alain.kruskal;

import java.util.Arrays;

/**
 * @author dll
 * @date 20220508
 */
public class KruskalAlgorithm {

    static class Edge implements Comparable<Edge> {
        private char start;
        private char end;
        private int weight;

        public Edge(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "start=" + start +
                    ", end=" + end +
                    ", weight=" + weight +
                    '}';
        }

    }


    /**
     * 1 边数
     * 2 存放顶点的数组
     * 3 邻接矩阵
     * 4 最大整数表示不可连通
     */
    private int vertexNum;
    private int edgeNum;
    private char[] vertices;
    private int[][] matrix;
    private static final int INF = Integer.MAX_VALUE;

    public KruskalAlgorithm(char[] vertices, int[][] matrix) {
        this.vertexNum = vertices.length;
        this.vertices = vertices.clone();
        this.matrix = matrix.clone();
        //统计边
        for (int i = 0; i < vertexNum; i++) {
            for (int j = i + 1; j < vertexNum; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    private void sortEdge(Edge[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                int cmp = edges[j].compareTo(edges[j + 1]);
                if (cmp > 0) {
                    Edge temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * @param vertex 顶点
     * @return 放回顶点对应的下标
     */
    private int getPosition(char vertex) {
        for (int i = 0; i < vertices.length; i++) {
            if (this.vertices[i] == vertex) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 通过邻接矩阵获取图中边，放到Edge[]中
     * Edge[] 形如 [['a', 'b', 12], [], [], .....]
     *
     * @return 邻接矩阵数组
     */
    private Edge[] getEdges() {
        int index = 0;
        Edge[] edges = new Edge[edgeNum];
        for (int i = 0; i < vertices.length; i++) {
            for (int j = i + 1; j < vertices.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new Edge(vertices[i], vertices[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点下标
     * 用于判断两个顶点的终点是否相同，避免产生回路
     *
     * @param ends 记录了各各顶点的终点,是个动态产生的过程
     * @param i    传入顶点的下标
     * @return 终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            //end[i] = 0 放回自身的下标
            i = ends[i];
        }
        return i;
    }

    public void kruskal() {
        int index = 0;
        //用于保存加入最小生成树顶点对应的终点
        int[] ends = new int[edgeNum];
        Edge[] result = new Edge[vertexNum -1];

        //1 获得所有的边
        Edge[] edges = getEdges();
        System.out.println("边的集合 = " + Arrays.toString(edges) + " 共" + edges.length + "条边");
        //2 排序weight
        sortEdge(edges);
        //3 遍历edges数组，将最小weight的边添加到最小生成树中，并且判断是否构成了回路
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i 条边的起点
            int p1 = getPosition(edges[i].start);
            //获取第i 条边的终点
            int p2 = getPosition(edges[i].end);
            //获取p1 这个点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            if (m != n) {
                ends[m] = n;
                result[index++] = edges[i];
            }
        }
        System.out.println("最小生成树为 = " + Arrays.toString(result));


    }


    public void print() {
        System.out.println("邻接矩阵为： ");
        for (int i = 0; i < vertexNum; i++) {
            for (int j = 0; j < vertexNum; j++) {
                System.out.printf("%-12d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        char[] vertices = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[][]{
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(vertices, matrix);
        kruskalAlgorithm.kruskal();

    }
}
