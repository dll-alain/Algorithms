package indi.alain.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author dll
 * @date 20220505
 */
public class Graph {
    /**
     * 1 存放顶点的集合
     * 2 存储对应的领结矩阵
     * 3 边的数目
     * 4 BFS 记录某个节点是否被访问
     */
    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges;
    private boolean[] isVisited;

    public Graph(int numOfVertex) {
        this.edges = new int[numOfVertex][numOfVertex];
        this.vertexList = new ArrayList<>(numOfVertex);
        this.numOfEdges = 0;
    }

    /**
     * 得到第一个邻接点的下标
     * 邻接节点一定是有边相连， 也就是为1
     * @param index 输入点的下标
     * @return 返回输入点的右边的第一个点
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 找到第一个邻接节点的下一个邻接节点
     * @param v1 开始遍历的节点
     * @param v2 第一个邻接节点
     * @return 放回第二个邻接节点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2+1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * DFS
     * @param isVisited
     * @param beginVertex 开始遍历的节点，可以是0
     */
    private void dfs(boolean[] isVisited, int beginVertex) {
        System.out.print(getValueByIndex(beginVertex) + " -> ");
        isVisited[beginVertex] = true;
        //找到第一个邻接节点
        int firstNei = getFirstNeighbor(beginVertex);
        while (firstNei != -1) {
            //说明有邻接节点
            if (!isVisited[firstNei]) {
                //且该邻接节点没有被访问
                dfs(isVisited, firstNei);
            }
            //如果该领结节点被访问过了, 或者继续下一步
            //就找第一个邻接节点的下一个邻接节点
            firstNei = getNextNeighbor(beginVertex, firstNei);
        }
    }

    public void depthFirstSearch() {
        this.isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 对一个节点进行广度优先遍历的方法
     * @param isVisited
     * @param beginVertex
     */
    private void bfs(boolean[] isVisited, int beginVertex) {
        /**
         * 1 队列的头节点
         * 2 邻接节点
         * 3 记录节点访问顺序的队列
         * 队列的特点： 尾部加入，头部取出
         * 先进先出
         */
        int head;
        int neighbor;
        LinkedList<Integer> queue = new LinkedList();
        //访问节点
        System.out.print(getValueByIndex(beginVertex) + " -> ");
        isVisited[beginVertex] = true;
        queue.addLast(beginVertex);

        while (!queue.isEmpty()) {
            //取出头结点下标
            head = queue.removeFirst();
            //得到第一个邻接节点下标
            neighbor = getFirstNeighbor(head);
            while (neighbor != -1) {
                //有领结节点
                if (!isVisited[neighbor]) {
                    //邻接节点没有访问
                    System.out.print(getValueByIndex(neighbor) + " -> ");
                    //标记为以访问
                    isVisited[neighbor] = true;
                    //入队列
                    queue.addLast(neighbor);
                }
                //继续找beginVertex的下一个neighbor
                neighbor = getNextNeighbor(head, neighbor);
            }
        }
    }

    public void broadFirstSearch() {
        this.isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    /**
     * 插入节点
     *
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 插入边
     * 无向图，一条边双向添加
     *
     * @param v1     vertex1
     * @param v2     vertex2
     * @param weight 1 or 0
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public int getNumOfEdges() {
        return this.numOfEdges;
    }

    /**
     * 获取节点，按照顺序
     * 0 - A, 1 - B, 2 - C
     *
     * @param index 传入的顺序
     * @return 获得相应的节点
     */
    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 显示图
     */
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    public static void main(String[] args) {
        int n = 8;
        //String[] vertexValue = {"A", "B", "C", "D", "E"};
        String[] vertexValue = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Graph graph = new Graph(n);
        //添加顶点
        for (String vertex : vertexValue) {
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B, A-C, B-C, B-D, B-E
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);

        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(5,6,1);

        graph.showGraph();


        System.out.println("深度优先");
        graph.depthFirstSearch();
        System.out.println("");
        System.out.println("广度优先");
        graph.broadFirstSearch();

    }

}
