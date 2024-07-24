package exploration;


import exploration.helpers.graph.DisjointUnionByRank;
import exploration.helpers.graph.DisjointUnionBySize;
import exploration.helpers.graph.helpers.*;

import java.util.*;

public class GraphDSA {

    /*

A. What is Graph...

      A ---5---  B
      |  \       |
    8 |     \    |
      |      \   |
      C         D

In this graph, A, B, C, and D are referred to as nodes or vertices, and the lines or paths connecting them are called edges.
If the connecting lines point to a vertex (i.e., ->), the graph is called a directed graph; otherwise, it’s an undirected graph. These lines represent links or relations between two nodes.

A graph can have a cycle (e.g., A - B - D - A), in which case it’s called a cyclic graph.
A graph without a cycle is called an acyclic graph. If an edge has a number label (e.g., 8 or 5), the graph is referred to as a weighted graph, representing concepts like distance, cost, or quantity.

If a node has a color, it’s called a colored graph. If adjacent nodes have different colors,
 it’s called legal coloring. If adjacent nodes have the same color, it’s called illegal coloring.

A path contains nodes, and each node is reachable from another node. For example, A, B, D, and E form a path,
 and each node can reach the others. However, A, D, E is not a path because A does not have any link to D.
 Also, A, B, D, B, A is not a path because a node (B) appears more than once.
          A -------  B
                     |
                    |
                    |
          C ------- D--- E

   -> Degree of Path

      - For undirected Graph means No of Edges goes IN/OUT.
        e..g
        Degree(D) is 3 as , B, C and E goes IN/OUT
        Degree(A) is 1 as only B goes IN/OUT.

             A -------  B
                         |
                        |
                        |
              C ------- D--- E

      - For Directed Graph  we have
        - InDegree i.e incoming edges
        - OutDegree i.e. outgoing edges.

        Total Degree of Path is 2 x No Of Edges
           e.g. for above graph
           2 x 4 = 8 (if calculate degree of each node 1+2+3+1+1 = 8)


        E..g  InDegree(D) = 1  C->D
              OutDegree(D) = 2  D->E&B

                 A -------> B
                            ^
                            |
                            |
                  C ------->D---> E


       - Non connected Graph :  Graph does not need to be always connected like below.
         It is ONE graph but non connected and comes in 2 part. 0-1-2-3 and 4-5

          1 -------- 2     4-------- 5
          |  \       |
          |    \     |
          |      \   |
          0         3



B. How to Store Graph?

    -> There are 3 ways
        Edge List , List of Adjacent Nodes and Adjacent Matrix.

      1 -------- 2
      |  \       |
      |    \     |
      |      \   |
      0         3


    Edge List = >  List of all edges in Grpah..
            int[] [] grpah = {
                            {1,2},{1,3},{1,0},
                            {0,1}, <--not required as already considered.
                            {2,1},<--not required as already considered.
                            {2,3},
                            {3,2},{3,1} <--not required as already considered.
                            }

    Adjacency List =>  List of Adjacent Nodes. Index rep Nodes and Idx Value rep List Of Neighbour.

            int[] [] grpah =
                           {
                            {1},
                            {0,2,3},
                            {1,3}
                            {1,2}
                        }

                HashMap<Integer , List<Integer> > graph  = new HashMap(){

                        put(0,Arrays.asList(0);
                        put(1,Arrays.asList(0,2,3);
                }

                Hashmap is useful when Node represent String, Object etc.


                Or It could be List<List<?>> where at each index there is list of adjacent nodes.


     Adjacent Matrix =>  where each Row Index rep Nodes and Col Idx other Nodes and value of Matrix rep
                         this Node Row x Col is connected or not.
                           0 i.e No
                           1 i.e Yes

                           int[][] graph = {
                                {0, 1, 0, 0},
                                {1, 0, 1, 1},
                                {0, 1, 0, 1},
                                {0, 1, 1, 0},
                            };


 C. Algorithm :
        - Two ways to traverse Graph. Breadth First or Depth First i.e BFS or DFS.
        - O (N) as N Log N i.e. Slow and as Graph grows large it gets slower in Runtime.
        - BFS :
            - First visit all the immediate children and then children of those each immediate child and so on.
            - Returns shortest path between 2 Nodes.
            - but use more memory or Space.
            - We can pick any Node to start BFS and in any order to work on its immediate children.
            - See Pic of BFS Spanning Tree


        - DFS :
            - It is like choose one path and go till its end and come back and choose next one.
            - Visit first immediate child and its children and so on and then go for next immediate child
            - Use small space or memeory
            - But can not find shortest path always.
            - Start from any Node.
            - See pic of DFS Spanning Tree


   D. Tips:
    - Whenever you have a matrix where you can move between adjacent cells,you should immediately think about graphs.
     - A matrix is a very common format for a graph to take.
     - you can treat each square as a node.
     - The first thing you should think about when it comes to shortest path problems on graphs is BFS.

   E Coding tips

    like any other arrya of int , string , we can do list of array see below.

        int [] d = new int[4]; //  regular int or string

        //array of list.

        List<Integer> [] ltArr = new ArrayList[4];
        ltArr[0]= new ArrayList<>();
        ltArr[0].add(42344);



    * */

    /**
     * This method performs a Breadth-First Search (BFS) on a graph.
     * It uses a queue to keep track of nodes to visit and a boolean array to mark visited nodes.
     *
     * @return A list of integers representing the nodes in the order they were visited.
     */
    public List<Integer> BFSOfGraph() {
        var res = new ArrayList<Integer>();
        boolean[] visited = new boolean[5];
        var adjList = prepGraph();
        Queue<Integer> bfsQ = new LinkedList<>();

        bfsQ.add(0);
        visited[0] = true;

        while (!bfsQ.isEmpty()) {
            var node = bfsQ.poll();
            res.add(node);

            for (var child : adjList.get(node)) {
                if (!visited[child]) {
                    bfsQ.add(child);
                    visited[child] = true;
                }
            }
        }

        return res;
    }

    /**
     * This method performs a Depth-First Search (DFS) on a graph.
     * It uses a recursive approach to visit all nodes in the graph.
     *
     * @return A list of integers representing the nodes in the order they were visited.
     */
    public List<Integer> DFSOfGraph() {
        var res = new ArrayList<Integer>();
        boolean[] visited = new boolean[5];
        var adjList = prepGraph();
        DFSOfGraph(0, visited, adjList, res);
        return res;
    }

    /**
     * This is a helper method for the DFSOfGraph method.
     * It performs the actual DFS traversal using recursion.
     *
     * @param node    The current node.
     * @param visited A boolean array to mark visited nodes.
     * @param adjList The adjacency list of the graph.
     * @param res     The result list to store the order of visited nodes.
     */
    void DFSOfGraph(int node, boolean[] visited, List<List<Integer>> adjList, ArrayList<Integer> res) {
        visited[node] = true;
        res.add(node);

        for (var child : adjList.get(node)) {
            if (!visited[child]) {
                DFSOfGraph(child, visited, adjList, res);
            }
        }
    }

    /**
     * This method counts the number of connected components in a graph.
     * It uses a Depth-First Search (DFS) approach to visit all nodes in the graph.
     *
     * @param n     The number of nodes in the graph.
     * @param edges The edges of the graph.
     * @return The number of connected components in the graph.
     */
    public int countGraphComponents(int n, int[][] edges) {
        int parts = 0;
        int[] visited = new int[n];

        var adjList = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int e = 0; e < edges.length; e++) {
            int p1 = edges[e][0];
            int p2 = edges[e][1];
            adjList.get(p1).add(p2);
            adjList.get(p2).add(p1);
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                parts++;
                countGraphComponents_DFSOfGraph(i, visited, adjList);
            }
        }
        return parts;
    }


    /**
     * This method performs a Depth-First Search (DFS) on a graph to count the number of connected components.
     *
     * @param node    The starting node for the DFS.
     * @param visited An array to keep track of visited nodes.
     * @param adjList The adjacency list representation of the graph.
     *                <p>
     *                Example:
     *                Suppose we have a graph with 5 nodes and the edges are [(1, 2), (2, 3), (4, 5)].
     *                The method will return 3 because there are 3 connected components in the graph.
     */
    void countGraphComponents_DFSOfGraph(int node, int[] visited, List<List<Integer>> adjList) {
        visited[node] = 1;

        for (var child : adjList.get(node)) {
            if (visited[child] == 0) {
                countGraphComponents_DFSOfGraph(child, visited, adjList);
            }
        }
    }

    /**
     * This method counts the number of provinces (i.e., connected components) in a graph represented by an adjacency matrix.
     *
     * @param adjMatrix The adjacency matrix representation of the graph.
     * @return The number of provinces.
     * <p>
     * Example:
     * Suppose we have a graph with 5 nodes and the edges are [(1, 2), (2, 3), (4, 5)].
     * The method will return 3 because there are 3 provinces in the graph.
     */
    public int numberOfProvinces(int[][] adjMatrix) {
        int parts = 0;
        boolean[] visited = new boolean[adjMatrix.length];

        for (int i = 0; i < adjMatrix.length; i++) {
            if (!visited[i]) {
                parts++;
                numberOfProvinces_DFSOfGraph(i, visited, adjMatrix);
            }
        }
        return parts;
    }

    void numberOfProvinces_DFSOfGraph(int node, boolean[] visited, int[][] adjMatrix) {
        visited[node] = true;

        for (int child = 0; child < adjMatrix[0].length; child++) {
            if (adjMatrix[node][child] == 1 && !visited[child]) {
                numberOfProvinces_DFSOfGraph(child, visited, adjMatrix);
            }
        }
    }

    /**
     * This method calculates the minimum time required to rot all oranges in a grid.
     * It uses a Breadth-First Search (BFS) approach.
     *
     * @param adjMatrix The grid represented as a 2D array.
     * @return The minimum time required to rot all oranges. If it's not possible to rot all oranges, return -1.
     * <p>
     * Example:
     * Suppose we have a grid with fresh oranges (1) and rotten oranges (2) as follows:
     * [[2,1,1],
     * [1,1,0],
     * [0,1,1]]
     * The method will return 4 because that's the minimum time required to rot all oranges.
     */
    public int orangesRotting_BFS(int[][] adjMatrix) {
        int totalFreshOranges = 0;
        Queue<int[]> rottonOrageQueue = new LinkedList<>();
        int totalMinSpent = -1;

        for (int r = 0; r < adjMatrix.length; r++) {
            for (int c = 0; c < adjMatrix[0].length; c++) {
                if (adjMatrix[r][c] == 2) {
                    rottonOrageQueue.offer(new int[]{r, c});
                } else if (adjMatrix[r][c] == 1) {
                    totalFreshOranges++;
                }
            }
        }

        rottonOrageQueue.offer(new int[]{-1, -1});

        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!rottonOrageQueue.isEmpty()) {
            var position = rottonOrageQueue.poll();
            var rowNum = position[0];
            var colNum = position[1];

            if (rowNum == -1 && colNum == -1) {
                totalMinSpent++;
                if (!rottonOrageQueue.isEmpty()) {
                    rottonOrageQueue.offer(new int[]{-1, -1});
                }
            } else {
                for (int[] dir : directions) {
                    int nextRowNum = rowNum + dir[0];
                    int nextColNum = colNum + dir[1];

                    if (nextRowNum >= 0 && nextRowNum < adjMatrix.length && nextColNum >= 0 && nextColNum < adjMatrix[0].length) {
                        if (adjMatrix[nextRowNum][nextColNum] == 1) {
                            adjMatrix[nextRowNum][nextColNum] = 2;
                            totalFreshOranges--;
                            rottonOrageQueue.offer(new int[]{nextRowNum, nextColNum});
                        }
                    }
                }
            }
        }

        return totalFreshOranges == 0 ? totalMinSpent : -1;
    }

    /**
     * This method performs a flood fill on an image represented by a 2D array.
     *
     * @param image    The input image represented as a 2D array.
     * @param sr       The row index of the starting pixel.
     * @param sc       The column index of the starting pixel.
     * @param newColor The new color to be filled.
     * @return The image after performing the flood fill.
     * <p>
     * Example:
     * Suppose the input image is [[1,1,1],[1,1,0],[1,0,1]], the starting pixel is at (1, 1), and the new color is 2.
     * The method will return [[2,2,2],[2,2,0],[2,0,1]].
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int orgColor = image[sr][sc];
        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        if (orgColor != newColor) {
            floodFill_DFS(image, sr, sc, orgColor, newColor, directions);
        }

        return image;
    }

    /**
     * This method performs a flood fill operation on an image using Depth-First Search (DFS).
     * It changes the color of all connected pixels of the original color to the new color.
     *
     * @param image      The 2D array representing the image.
     * @param sr         The row index of the starting pixel.
     * @param sc         The column index of the starting pixel.
     * @param orgColor   The original color.
     * @param newColor   The new color.
     * @param directions The 2D array representing the four possible directions (up, down, left, right).
     */
    void floodFill_DFS(int[][] image, int sr, int sc, int orgColor, int newColor, int[][] directions) {
        // Assign the new color to the starting pixel.
        image[sr][sc] = newColor;

        // Iterate over each direction.
        for (int[] dir : directions) {
            int nextRowNum = sr + dir[0];
            int nextColNum = sc + dir[1];

            // Check if the next pixel is within the image boundaries.
            if (nextRowNum >= 0 && nextRowNum < image.length && nextColNum >= 0 && nextColNum < image[0].length) {
                // If the next pixel has the same color as the original color, perform a flood fill operation on it.
                if (image[nextRowNum][nextColNum] == orgColor) {
                    floodFill_DFS(image, nextRowNum, nextColNum, orgColor, newColor, directions);
                }
            }
        }
    }

    /**
     * This method detects if there is a cycle in an undirected graph.
     * It uses Breadth-First Search (BFS) for the traversal.
     *
     * @return True if a cycle is detected, false otherwise.
     */
    public boolean detectCycle_UnDirectedGraph() {
        // Prepare the adjacency list and the visited array.
        int size = 4;
        var adjList = prepGraph2();
        boolean[] visited = new boolean[size];

        // Handle disconnected graphs by checking each node.
        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                boolean foundCycle = detectCycle_UnDirectedGraph(adjList, visited, i);
                if (foundCycle) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method detects if there is a cycle in an undirected graph starting from a specific node.
     * It uses Breadth-First Search (BFS) for the traversal.
     *
     * @param adjList   The adjacency list of the graph.
     * @param visited   The array to keep track of visited nodes.
     * @param startNode The starting node for the traversal.
     * @return True if a cycle is detected, false otherwise.
     */
    boolean detectCycle_UnDirectedGraph(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, int startNode) {
        Queue<Node> bfsQ = new LinkedList<>();

        bfsQ.add(new Node(startNode, -1));
        visited[startNode] = true; // Mark the starting node as visited.

        // Traverse the graph.
        while (!bfsQ.isEmpty()) {
            var obj = bfsQ.poll();
            var node = obj.first;
            var parent = obj.second;

            // Check each adjacent node.
            for (var child : adjList.get(node)) {
                if (!visited[child]) {
                    bfsQ.add(new Node(child, node)); // Add the child node to the queue.
                    visited[child] = true; // Mark the child node as visited.
                }
                // If the child node is visited and is not the parent of the current node, a cycle is detected.
                else if (child != parent) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This method detects if there is a cycle in an undirected graph.
     * It uses Depth-First Search (DFS) for the traversal.
     *
     * @return True if a cycle is detected, false otherwise.
     */
    public boolean detectCycle_UnDirectedGraph_DFS() {
        // Prepare the adjacency list and the visited array.
        int size = 4;
        var adjList = prepGraph2();
        boolean[] visited = new boolean[size];

        // Handle disconnected graphs by checking each node.
        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                int parentNode = -1;
                boolean foundCycle = detectCycle_UnDirectedGraph_DFS(adjList, visited, i, parentNode);
                if (foundCycle) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method detects if there is a cycle in an undirected graph starting from a specific node.
     * It uses Depth-First Search (DFS) for the traversal.
     *
     * @param adjList    The adjacency list of the graph.
     * @param visited    The array to keep track of visited nodes.
     * @param startNode  The starting node for the traversal.
     * @param parentNode The parent node of the starting node.
     * @return True if a cycle is detected, false otherwise.
     */
    boolean detectCycle_UnDirectedGraph_DFS(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, int startNode, int parentNode) {
        visited[startNode] = true; // Mark the starting node as visited.

        // Check each adjacent node.
        for (var child : adjList.get(startNode)) {
            if (!visited[child]) {
                visited[child] = true;
                if (detectCycle_UnDirectedGraph_DFS(adjList, visited, child, startNode)) {
                    return true;
                }
            }
            // If the child node is visited and is not the parent of the current node, a cycle is detected.
            else if (child != parentNode) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method calculates the distance of the nearest 0 from each cell in a matrix.
     * It uses Breadth-First Search (BFS) to traverse the matrix.
     *
     * @param adjMatrix The adjacency matrix.
     * @return The matrix with the distances of the nearest 0 from each cell.
     */
    public int[][] Zero_One_Matrix_BFS(int[][] adjMatrix) {
        // Preparation
        boolean[][] visited = new boolean[adjMatrix.length][adjMatrix[0].length];
        int[][] result = new int[adjMatrix.length][adjMatrix[0].length];
        Queue<StepNode> bfsQ = new LinkedList<>();

        // Find positions of Zeros and add them to the queue
        for (int r = 0; r < adjMatrix.length; r++) {
            for (int c = 0; c < adjMatrix[0].length; c++) {
                if (adjMatrix[r][c] == 0) {
                    bfsQ.offer(new StepNode(r, c, 0)); // Position added as e.g. 1,2 with step 0 as itself is 0.
                    visited[r][c] = true;
                }
            }
        }

        // Directions
        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // Poll queue and operate on node
        while (!bfsQ.isEmpty()) {
            var qObj = bfsQ.poll();
            var rowNum = qObj.row;
            var colNum = qObj.col;
            var stepCount = qObj.steps;

            // Assign steps to result
            result[rowNum][colNum] = stepCount;

            // Go in all 4 directions from current position
            for (int[] dir : directions) {
                int nextRowNum = rowNum + dir[0];
                int nextColNum = colNum + dir[1];

                // Check if not crossing boundary after direction added
                if (nextRowNum >= 0 && nextRowNum < adjMatrix.length && nextColNum >= 0 && nextColNum < adjMatrix[0].length) {
                    // Go in each direction if not already visited
                    if (!visited[nextRowNum][nextColNum]) {
                        // Mark visited
                        visited[nextRowNum][nextColNum] = true;
                        // Increase step regardless as when it moves to next cell from previously valid step it counted as 1 step
                        var nextStepCount = stepCount + 1;
                        // Add to queue
                        bfsQ.offer(new StepNode(nextRowNum, nextColNum, nextStepCount));
                    }
                }
            }
        }
        return result;
    }

    /**
     * This method finds and marks regions surrounded by 'X' in a matrix.
     * It uses Depth-First Search (DFS) to traverse the matrix.
     *
     * @param adjMatrix The adjacency matrix.
     * @return The matrix with the regions surrounded by 'X' marked.
     */
    public char[][] Surrounded_Regions(char[][] adjMatrix) {
        // Preparation
        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        boolean[][] visited = new boolean[adjMatrix.length][adjMatrix[0].length];

        // Pick row boundary
        for (int c = 0; c < adjMatrix[0].length; c++) {
            // First rows
            if (adjMatrix[0][c] == 'O') {
                // Call method that marks this position visited and from there goes in direction to find any more child which are zeros and cannot be marked 'X'
                Surrounded_Regions_DFS(adjMatrix, visited, 0, c, directions);
            }

            // Last rows
            if (adjMatrix[adjMatrix.length - 1][c] == 'O') {
                // Call method that marks this position visited and from there goes in direction to find any more child which are zeros and cannot be marked 'X'
                Surrounded_Regions_DFS(adjMatrix, visited, adjMatrix.length - 1, c, directions);
            }
        }

        // Pick column boundary
        for (int r = 0; r < adjMatrix.length; r++) {
            // First column
            if (adjMatrix[r][0] == 'O') {
                // Call method that marks this position visited and from there goes in direction to find any more child which are zeros and cannot be marked 'X'
                Surrounded_Regions_DFS(adjMatrix, visited, r, 0, directions);
            }

            // Last column
            if (adjMatrix[r][adjMatrix[0].length - 1] == 'O') {
                // Call method that marks this position visited and from there goes in direction to find any more child which are zeros and cannot be marked 'X'
                Surrounded_Regions_DFS(adjMatrix, visited, r, adjMatrix[0].length - 1, directions);
            }
        }

        // Mark rest not visited with 'X'
        for (int r = 0; r < adjMatrix.length; r++) {
            for (int c = 0; c < adjMatrix[0].length; c++) {
                if (!visited[r][c]) {
                    adjMatrix[r][c] = 'X';
                }
            }
        }

        return adjMatrix;
    }

    /**
     * This method traverses the matrix using Depth-First Search (DFS) and marks visited positions.
     *
     * @param adjMatrix  The adjacency matrix.
     * @param visited    The matrix that keeps track of visited positions.
     * @param row        The current row index.
     * @param col        The current column index.
     * @param directions The possible directions to move in the matrix.
     */
    void Surrounded_Regions_DFS(char[][] adjMatrix, boolean[][] visited, int row, int col, int[][] directions) {
        // Mark visited
        visited[row][col] = true;

        // Now go each 4 directions
        for (int[] dir : directions) {
            int nextRowNum = row + dir[0];
            int nextColNum = col + dir[1];

            // Check boundaries
            if (nextRowNum >= 0 && nextRowNum < adjMatrix.length && nextColNum >= 0 && nextColNum < adjMatrix[0].length) {
                // If child not visited and is zero, call its child by DFS and mark visited.
                if (!visited[nextRowNum][nextColNum] && adjMatrix[nextRowNum][nextColNum] == 'O') {
                    Surrounded_Regions_DFS(adjMatrix, visited, nextRowNum, nextColNum, directions);
                }
            }
        }
    }

    /**
     * This method counts the number of enclaves in a matrix.
     * It uses the same idea as surrounded regions.
     *
     * @param adjMatrix The adjacency matrix.
     * @return The number of enclaves in the matrix.
     */
    public int num_of_Enclaves(int[][] adjMatrix) {
        // Preparation
        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        boolean[][] visited = new boolean[adjMatrix.length][adjMatrix[0].length];

        // Pick row boundary
        for (int c = 0; c < adjMatrix[0].length; c++) {
            // First rows
            if (adjMatrix[0][c] == 1) {
                // Call method that marks this position visited and from there goes in direction to find any more child which are zeros and cannot be marked 'X'
                num_of_Enclaves_DFS(adjMatrix, visited, 0, c, directions);
            }

            // Last rows
            if (adjMatrix[adjMatrix.length - 1][c] == 1) {
                // Call method that marks this position visited and from there goes in direction to find any more child which are zeros and cannot be marked 'X'
                num_of_Enclaves_DFS(adjMatrix, visited, adjMatrix.length - 1, c, directions);
            }
        }

        // Pick column boundary
        for (int r = 0; r < adjMatrix.length; r++) {
            // First column
            if (adjMatrix[r][0] == 1) {
                // Call method that marks this position visited and from there goes in direction to find any more child which are zeros and cannot be marked 'X'
                num_of_Enclaves_DFS(adjMatrix, visited, r, 0, directions);
            }

            // Last column
            if (adjMatrix[r][adjMatrix[0].length - 1] == 1) {
                // Call method that marks this position visited and from there goes in direction to find any more child which are zeros and cannot be marked 'X'
                num_of_Enclaves_DFS(adjMatrix, visited, r, adjMatrix[0].length - 1, directions);
            }
        }

        int count = 0;
        // Just count number of 1 left in matrix.
        for (int r = 0; r < adjMatrix.length; r++) {
            for (int c = 0; c < adjMatrix[0].length; c++) {
                if (!visited[r][c] && adjMatrix[r][c] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * This method performs a Depth-First Search (DFS) on a matrix to count the number of enclaves.
     *
     * @param adjMatrix  The adjacency matrix representation of the graph.
     * @param visited    An array to keep track of visited nodes.
     * @param row        The row index of the current node.
     * @param col        The column index of the current node.
     * @param directions The possible directions to move in the matrix.
     *                   <p>
     *                   Example:
     *                   Suppose we have a matrix with 1's representing land and 0's representing water.
     *                   The method will perform a DFS to count the number of enclaves (i.e., regions of land that are fully surrounded by water).
     */
    void num_of_Enclaves_DFS(int[][] adjMatrix, boolean[][] visited, int row, int col, int[][] directions) {
        visited[row][col] = true;

        for (int[] dir : directions) {
            int nextRowNum = row + dir[0];
            int nextColNum = col + dir[1];

            if (nextRowNum >= 0 && nextRowNum < adjMatrix.length && nextColNum >= 0 && nextColNum < adjMatrix[0].length) {
                if (!visited[nextRowNum][nextColNum] && adjMatrix[nextRowNum][nextColNum] == 1) {
                    num_of_Enclaves_DFS(adjMatrix, visited, nextRowNum, nextColNum, directions);
                }
            }
        }
    }

    /**
     * This method counts the number of islands (i.e., connected components) in a matrix.
     *
     * @param adjMatrix The adjacency matrix representation of the graph.
     * @return The number of islands.
     * <p>
     * Example:
     * Suppose we have a matrix with 1's representing land and 0's representing water.
     * The method will return the number of islands in the matrix.
     */
    public int num_Islands(char[][] adjMatrix) {
        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        boolean[][] visited = new boolean[adjMatrix.length][adjMatrix[0].length];
        int numOfIsland = 0;

        for (int r = 0; r < adjMatrix.length; r++) {
            for (int c = 0; c < adjMatrix[0].length; c++) {
                if (adjMatrix[r][c] == '1' && !visited[r][c]) {
                    numOfIsland++;
                    num_Islands_DFS(adjMatrix, visited, r, c, directions);
                }
            }
        }
        return numOfIsland;
    }

    /**
     * This method performs a Depth-First Search (DFS) on a matrix to find all the land connected to the current node.
     *
     * @param adjMatrix  The adjacency matrix representation of the graph.
     * @param visited    An array to keep track of visited nodes.
     * @param row        The row index of the current node.
     * @param col        The column index of the current node.
     * @param directions The possible directions to move in the matrix.
     */
    void num_Islands_DFS(char[][] adjMatrix, boolean[][] visited, int row, int col, int[][] directions) {
        visited[row][col] = true;

        for (int[] dir : directions) {
            int nextRowNum = row + dir[0];
            int nextColNum = col + dir[1];

            if (nextRowNum >= 0 && nextRowNum < adjMatrix.length && nextColNum >= 0 && nextColNum < adjMatrix[0].length) {
                if (!visited[nextRowNum][nextColNum] && adjMatrix[nextRowNum][nextColNum] == '1') {
                    num_Islands_DFS(adjMatrix, visited, nextRowNum, nextColNum, directions);
                }
            }
        }
    }

    /**
     * This method counts the number of distinct islands in a matrix.
     * An island is considered distinct if it has a different shape or size from other islands.
     *
     * @param adjMatrix The adjacency matrix representation of the graph.
     * @return The number of distinct islands.
     * <p>
     * Example:
     * Suppose we have a matrix with 1's representing land and 0's representing water.
     * The method will return the number of distinct islands in the matrix.
     */
    public int num_Distinct_Islands(int[][] adjMatrix) {
        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        boolean[][] visited = new boolean[adjMatrix.length][adjMatrix[0].length];
        List<int[]> currIsLand = new ArrayList<>();
        List<List<int[]>> uniqueIsLandList = new ArrayList<>();

        for (int r = 0; r < adjMatrix.length; r++) {
            for (int c = 0; c < adjMatrix[0].length; c++) {
                if (!visited[r][c] && adjMatrix[r][c] == 1) {
                    visited[r][c] = true;
                    currIsLand.add(new int[]{r, c});
                    num_Distinct_Islands_DFS(adjMatrix, visited, r, c, directions, currIsLand);
                }

                if (currIsLand.isEmpty()) {
                    continue;
                }

                int minCol = adjMatrix[0].length - 1;
                for (int x = 0; x < currIsLand.size(); x++) {
                    minCol = Math.min(currIsLand.get(x)[1], minCol);
                }

                for (int[] cell : currIsLand) {
                    int localRow = cell[0] - r;
                    int localCol = cell[1] - minCol;

                    cell[0] = localRow;
                    cell[1] = localCol;
                }

                if (isUnique(currIsLand, uniqueIsLandList)) {
                    uniqueIsLandList.add(currIsLand);
                }
                currIsLand = new ArrayList<>();
            }
        }

        return uniqueIsLandList.size();
    }


    /**
     * This method performs a Depth-First Search (DFS) on a given adjacency matrix to find distinct islands.
     * It uses a recursive approach to visit all nodes in the graph.
     *
     * @param adjMatrix  The input adjacency matrix.
     * @param visited    A boolean array to mark visited nodes.
     * @param row        The current row index.
     * @param col        The current column index.
     * @param directions The possible directions to move in the matrix.
     * @param currIsLand The current island being visited.
     */
    void num_Distinct_Islands_DFS(int[][] adjMatrix, boolean[][] visited, int row, int col, int[][] directions, List<int[]> currIsLand) {
        for (int[] dir : directions) {
            int nextRowNum = row + dir[0];
            int nextColNum = col + dir[1];

            if (nextRowNum >= 0 && nextRowNum < adjMatrix.length && nextColNum >= 0 && nextColNum < adjMatrix[0].length) {
                if (!visited[nextRowNum][nextColNum] && adjMatrix[nextRowNum][nextColNum] == 1) {
                    currIsLand.add(new int[]{nextRowNum, nextColNum});
                    visited[nextRowNum][nextColNum] = true;
                    num_Distinct_Islands_DFS(adjMatrix, visited, nextRowNum, nextColNum, directions, currIsLand);
                }
            }
        }
    }

    /**
     * This method checks if a given island is unique among a list of islands.
     *
     * @param currIsLand       The current island to check.
     * @param uniqueIsLandList The list of unique islands.
     * @return A boolean indicating whether the current island is unique.
     */
    private boolean isUnique(List<int[]> currIsLand, List<List<int[]>> uniqueIsLandList) {
        for (var eachLand : uniqueIsLandList) {
            if (eachLand.size() != currIsLand.size()) {
                continue;
            }

            boolean equalIsland = true;
            for (int p = 0; p < eachLand.size(); p++) {
                if (eachLand.get(p)[0] != currIsLand.get(p)[0] || eachLand.get(p)[1] != currIsLand.get(p)[1]) {
                    equalIsland = false;
                    break;
                }
            }
            if (equalIsland) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method finds the minimum number of transformations required to convert a begin word to a target word.
     * Each transformation is a single character change and the intermediate word must be in the given word list.
     *
     * @param beginWord  The word to start from.
     * @param targetWord The target word to reach.
     * @param wordList   The list of allowable words.
     * @return The minimum number of transformations required.
     */
    public int world_ladder_1(String beginWord, String targetWord, List<String> wordList) {
        var wordSet = new HashSet<String>();
        for (var it : wordList) {
            wordSet.add(it);
        }
        wordSet.remove(beginWord);

        Queue<Pair> bfsQ = new LinkedList<>();
        bfsQ.offer(new Pair(beginWord, 1));

        while (!bfsQ.isEmpty()) {
            var wordP = bfsQ.poll();
            var steps = wordP.count;
            var gvnWord = wordP.str;

            if (gvnWord.equals(targetWord)) {
                return steps;
            }

            for (int i = 0; i < gvnWord.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    var firstPart = gvnWord.substring(0, i);
                    var lastPart = gvnWord.substring(i + 1, gvnWord.length());
                    var changedWord = firstPart + ch + lastPart;
                    if (wordSet.contains(changedWord)) {
                        bfsQ.offer(new Pair(changedWord, steps + 1));
                        wordSet.remove(changedWord);
                    }
                }
            }
        }

        return 0;
    }


    /**
     * This method finds all shortest transformation sequences from a given word to a target word.
     * It uses a Breadth-First Search (BFS) approach and assumes that transformations must be valid words in the word list.
     *
     * @param beginWord  The starting word.
     * @param targetWord The target word.
     * @param wordList   The list of valid words for transformations.
     * @return A list of all shortest transformation sequences.
     * <p>
     * Example:
     * Suppose the starting word is "hit", the target word is "cog", and the word list is ["hot","dot","dog","lot","log","cog"].
     * The method will return [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]] because these are the two shortest transformation sequences.
     */
    public List<List<String>> world_ladder_2_GOOD_lEET_TLE(String beginWord, String targetWord, List<String> wordList) {
        var wordSet = new HashSet<String>();
        for (var it : wordList) {
            wordSet.add(it);
        }
        wordSet.remove(beginWord);
        Queue<WLaderTwo> bfsQ = new LinkedList<>();
        List<List<String>> resultList = new ArrayList<>();

        TreeMap<Integer, List<List<String>>> resMap = new TreeMap<>();

        var obj1 = new WLaderTwo(beginWord, 1);
        obj1.setPrevs(new ArrayList<>() {
            {
                add(beginWord);
            }
        });

        bfsQ.offer(obj1);

        while (!bfsQ.isEmpty()) {
            var quObj = bfsQ.poll();
            var stepsSoFar = quObj.count;
            var quWord = quObj.str;

            if (quWord.equals(targetWord)) {
                if (resMap.containsKey(stepsSoFar)) {
                    var bigLt = resMap.get(stepsSoFar);
                    bigLt.add(quObj.getPrevs());
                    resMap.put(stepsSoFar, bigLt);
                } else {
                    var bigLt = new ArrayList<List<String>>();
                    bigLt.add(quObj.getPrevs());
                    resMap.put(stepsSoFar, bigLt);
                }
                continue;
            }

            for (int i = 0; i < quWord.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    var firstPart = quWord.substring(0, i);
                    var lastPart = quWord.substring(i + 1, quWord.length());
                    var changedWord = firstPart + ch + lastPart;
                    if (wordSet.contains(changedWord) && !quObj.getUsed().contains(changedWord)) {
                        var curlist = quObj.getPrevs();

                        List<String> listForChangeword = new ArrayList<>(curlist);
                        listForChangeword.add(changedWord);

                        var objChangeWord = new WLaderTwo(changedWord, stepsSoFar + 1);
                        objChangeWord.setPrevs(listForChangeword);

                        bfsQ.offer(objChangeWord);
                    }
                }
            }
        }

        var finalLsit = resMap.size() > 0 ? resMap.firstEntry().getValue() : resultList;
        return finalLsit;
    }

    /**
     * This method checks if a graph is bipartite.
     * A graph is bipartite if its vertices can be divided into two disjoint and independent sets U and V such that every edge connects a vertex in U to one in V.
     *
     * @param adjMatrix The adjacency matrix representation of the graph.
     * @return True if the graph is bipartite, false otherwise.
     * <p>
     * Example:
     * Suppose we have a graph with 5 nodes and the edges are [(1, 2), (2, 3), (4, 5)].
     * The method will return true because the graph is bipartite.
     */
    public boolean isBipartite(int[][] adjMatrix) {
        int[] colored = new int[adjMatrix.length];
        Arrays.fill(colored, -1);

        for (int node = 0; node < adjMatrix.length; node++) {
            if (colored[node] == -1) {
                if (!isBipartite_DFSOfGraph(node, 0, colored, adjMatrix)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method checks if a graph is bipartite using Depth-First Search (DFS).
     * A graph is bipartite if its vertices can be divided into two disjoint sets such that every edge connects two vertices from different sets.
     *
     * @param node      The starting node for the DFS.
     * @param color     The color to assign to the starting node.
     * @param colored   The array to keep track of the colors of the nodes.
     * @param adjMatrix The adjacency matrix of the graph.
     * @return True if the graph is bipartite, false otherwise.
     */
    boolean isBipartite_DFSOfGraph(int node, int color, int[] colored, int[][] adjMatrix) {
        // Assign the given color to the starting node.
        colored[node] = color;

        // Iterate over each child of the current node.
        for (int chd = 0; chd < adjMatrix[node].length; chd++) {
            int child = adjMatrix[node][chd];
            // If the child node is not colored, assign it the opposite color and continue the DFS.
            if (colored[child] == -1) {
                if (!isBipartite_DFSOfGraph(child, 1 - color, colored, adjMatrix)) {
                    return false; // If a contradiction is found, stop the DFS and return false.
                }
            }
            // If the child node is colored the same as the current node, the graph is not bipartite.
            else if (colored[child] == color) {
                return false;
            }
        }
        return true; // If all nodes can be colored without contradiction, the graph is bipartite.
    }

    /**
     * This method detects if there is a cycle in a directed graph.
     * It uses Depth-First Search (DFS) for the traversal.
     *
     * @return True if a cycle is detected, false otherwise.
     */
    public boolean detectCycle_DirectedGraph_DFS() {
        int size = 11;
        var adjList = prepGraph3();

        // Initialize the visited and pathVisited arrays.
        boolean[] visited = new boolean[size];
        boolean[] pathVisited = new boolean[size];

        // Handle disconnected graphs by checking each node.
        for (int node = 0; node < size; node++) {
            if (!visited[node]) {
                boolean foundCycle = detectCycle_DirectedGraph_DFS(node, adjList, visited, pathVisited);
                if (foundCycle) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method detects if there is a cycle in a directed graph starting from a specific node.
     * It uses Depth-First Search (DFS) for the traversal.
     *
     * @param node        The starting node for the DFS.
     * @param adjList     The adjacency list of the graph.
     * @param visited     The array to keep track of visited nodes.
     * @param pathVisited The array to keep track of the nodes in the current path.
     * @return True if a cycle is detected, false otherwise.
     */
    private boolean detectCycle_DirectedGraph_DFS(int node, ArrayList<ArrayList<Integer>> adjList, boolean[] visited, boolean[] pathVisited) {
        visited[node] = true; // Mark the starting node as visited.
        pathVisited[node] = true; // Add the starting node to the current path.

        // Iterate over each adjacent node.
        for (var child : adjList.get(node)) {
            if (!visited[child]) {
                if (detectCycle_DirectedGraph_DFS(child, adjList, visited, pathVisited)) {
                    return true;
                }
            }
            // If the adjacent node is visited and is in the current path, a cycle is detected.
            else if (pathVisited[child]) {
                return true;
            }
        }

        // After visiting all adjacent nodes, remove the starting node from the current path.
        pathVisited[node] = false;
        return false;
    }

    /**
     * This method performs topological sorting on a directed graph.
     * Topological sorting means node u will always appear before node v if there is a directed edge from node u towards node v(u -> v).
     * Topological sorting can only be done on Directed Graph with NO Cycle present.
     *
     * @return The topologically sorted list of nodes.
     */
    public List<Integer> topologicalSort() {
        int size = 6;
        var adjList = prepGraph4();

        // Preparation
        boolean[] visited = new boolean[size];
        Stack<Integer> st = new Stack<>();

        // Handle like if non-connected graphs
        for (int node = 0; node < size; node++) {
            if (!visited[node]) {
                topologicalSort_DFS(node, adjList, visited, st);
            }
        }

        var lt = new ArrayList<Integer>();
        while (!st.isEmpty()) {
            lt.add(st.pop());
        }

        return lt;
    }

    /**
     * This method performs Depth-First Search (DFS) for topological sorting.
     *
     * @param node    The current node.
     * @param adjList The adjacency list of the graph.
     * @param visited The array that keeps track of visited nodes.
     * @param st      The stack that stores the nodes in topological order.
     */
    private void topologicalSort_DFS(int node, ArrayList<ArrayList<Integer>> adjList, boolean[] visited, Stack<Integer> st) {
        // Mark visited
        visited[node] = true;
        for (var child : adjList.get(node)) {
            if (!visited[child]) {
                topologicalSort_DFS(child, adjList, visited, st);
            }
        }
        // This is when node no child left to work upon so backtracking there keep storing in stack for ordering.
        st.push(node);
    }

    /**
     * This method performs topological sorting on a directed graph using Kahn's algorithm.
     * Topological Ordering is a linear ordering of vertices such that if there is an edge between node u and v(u -> v), node u appears before v in that ordering.
     *
     * @return The topologically sorted list of nodes.
     */
    public List<Integer> topologicalSort_Kahn_Algo() {
        int size = 6;
        var adjList = prepGraph4();
        int[] inDegree = new int[size];

        // Find all incoming edges of each Vertex
        for (var p : adjList) {
            for (var c : p) {
                inDegree[c]++;
            }
        }

        // Put all Vertex into Queue which has 0 incoming edges.
        Queue<Integer> qu = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            if (inDegree[i] == 0) {
                qu.offer(i);
            }
        }

        var sortedTopo = new ArrayList<Integer>();
        while (!qu.isEmpty()) {
            int vtx = qu.poll();
            sortedTopo.add(vtx); // Put in ans

            for (int ch : adjList.get(vtx)) {
                inDegree[ch]--; // Reduce by 1
                if (inDegree[ch] == 0) {
                    qu.offer(ch); // Put in queue
                }
            }
        }
        return sortedTopo;
    }


    /**
     * This method checks if it is possible to finish all courses given a list of prerequisites.
     * It uses a Breadth-First Search (BFS) approach to find a topological ordering of the courses.
     *
     * @param numCourses    The number of courses.
     * @param prerequisites The prerequisites for each course.
     * @return A boolean indicating whether it is possible to finish all courses.
     */
    public boolean canFinish_courseSchedule_1(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int x = 0; x < numCourses; x++) {
            adjList.add(new ArrayList<>());
        }

        for (int x = 0; x < prerequisites.length; x++) {
            int before = prerequisites[x][1];
            int after = prerequisites[x][0];
            adjList.get(before).add(after);
        }

        int[] inDegree = new int[numCourses];
        for (var p : adjList) {
            for (var c : p) {
                inDegree[c]++;
            }
        }

        Queue<Integer> qu = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                qu.offer(i);
            }
        }

        int nodeVisited = 0;
        while (!qu.isEmpty()) {
            int vtx = qu.poll();
            nodeVisited++;

            for (int ch : adjList.get(vtx)) {
                inDegree[ch]--;
                if (inDegree[ch] == 0) {
                    qu.offer(ch);
                }
            }
        }

        return nodeVisited == numCourses;
    }

    /**
     * This method finds a valid order in which to take all courses given a list of prerequisites.
     * It uses a Breadth-First Search (BFS) approach to find a topological ordering of the courses.
     *
     * @param numCourses    The number of courses.
     * @param prerequisites The prerequisites for each course.
     * @return An array representing a valid order to take all courses, or an empty array if no valid order exists.
     */
    public int[] canFinish_courseSchedule_2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int x = 0; x < numCourses; x++) {
            adjList.add(new ArrayList<>());
        }

        for (int x = 0; x < prerequisites.length; x++) {
            int before = prerequisites[x][1];
            int after = prerequisites[x][0];
            adjList.get(before).add(after);
        }

        int[] inDegree = new int[numCourses];
        for (var p : adjList) {
            for (var c : p) {
                inDegree[c]++;
            }
        }

        Queue<Integer> qu = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                qu.offer(i);
            }
        }

        int nodeCount = 0;
        int[] res = new int[numCourses];

        while (!qu.isEmpty()) {
            int vtx = qu.poll();
            res[nodeCount] = vtx;
            nodeCount++;

            for (int ch : adjList.get(vtx)) {
                inDegree[ch]--;
                if (inDegree[ch] == 0) {
                    qu.offer(ch);
                }
            }
        }

        if (nodeCount != numCourses) {
            return new int[0];
        }
        return res;
    }


    /**
     * This method finds all nodes in a graph that are eventually safe.
     * A node is eventually safe if it can reach a terminal node (i.e., a node that has no outgoing edges).
     *
     * @param graph The adjacency list representation of the graph.
     * @return A list of all nodes that are eventually safe, in ascending order.
     * <p>
     * Example:
     * Suppose we have a graph with 5 nodes and the edges are [(0, 1), (1, 2), (2, 3), (3, 4)].
     * The method will return [0, 1, 2, 3, 4] because all nodes are eventually safe.
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<List<Integer>> adjList = new ArrayList<>();
        int[] incomingEdges = new int[graph.length];

        for (int x = 0; x < graph.length; x++) {
            adjList.add(new ArrayList<>());
        }

        for (int node = 0; node < graph.length; node++) {
            for (int ch : graph[node]) {
                adjList.get(ch).add(node);
                incomingEdges[node]++;
            }
        }

        Queue<Integer> qu = new LinkedList<>();
        for (int i = 0; i < incomingEdges.length; i++) {
            if (incomingEdges[i] == 0) {
                qu.offer(i);
            }
        }

        boolean[] safeNodes = new boolean[graph.length];
        while (!qu.isEmpty()) {
            int vtx = qu.poll();
            safeNodes[vtx] = true;

            for (int ch : adjList.get(vtx)) {
                incomingEdges[ch]--;
                if (incomingEdges[ch] == 0) {
                    qu.offer(ch);
                }
            }
        }

        var res = new ArrayList<Integer>();
        for (int nd = 0; nd < graph.length; nd++) {
            if (safeNodes[nd]) {
                res.add(nd);
            }
        }
        return res;
    }

    /**
     * This method determines the order of characters in an alien language.
     * It uses a topological sort approach and assumes that the order of characters can be determined from a list of words in the alien language.
     *
     * @param words The list of words in the alien language.
     * @return A string representing the order of characters in the alien language. If the order cannot be determined, return an empty string.
     * <p>
     * Example:
     * Suppose the list of words is ["baa","abcd","abca","cab","cad"].
     * The method will return "bdac" because 'b' must be before 'd', 'a', and 'c', 'd' must be before 'a', and 'a' must be before 'c'.
     */
    public String alienOrder2(String[] words) {
        Map<Character, Integer> charWInComingEdges = new HashMap<>();
        for (var wd : words) {
            for (var ch : wd.toCharArray()) {
                charWInComingEdges.put(ch, 0);
            }
        }

        Map<Character, List<Character>> adjList = new HashMap<>();
        for (var wd : words) {
            for (var ch : wd.toCharArray()) {
                adjList.put(ch, new ArrayList<>());
            }
        }

        for (int w = 0; w < words.length - 1; w++) {
            String s1 = words[w];
            String s2 = words[w + 1];

            if (s1.length() > s2.length() && s1.startsWith(s2)) {
                return "";
            }

            int len = Math.min(s1.length(), s2.length());
            int pt = 0;
            while (pt < len) {
                char ch1 = s1.charAt(pt);
                char ch2 = s2.charAt(pt);

                if (ch1 != ch2) {
                    adjList.get(ch1).add(ch2);
                    break;
                }

                pt++;
            }
        }

        for (Character node : adjList.keySet()) {
            for (char child : adjList.get(node)) {
                charWInComingEdges.put(child, charWInComingEdges.get(child) + 1);
            }
        }

        Queue<Character> qu = new LinkedList<>();
        for (Character node : charWInComingEdges.keySet()) {
            if (charWInComingEdges.get(node) == 0) {
                qu.offer(node);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!qu.isEmpty()) {
            Character vtx = qu.poll();
            sb.append(vtx);

            for (Character child : adjList.get(vtx)) {
                charWInComingEdges.put(child, charWInComingEdges.get(child) - 1);
                if (charWInComingEdges.get(child) == 0) {
                    qu.offer(child);
                }
            }
        }

        if (sb.length() < charWInComingEdges.size()) {
            return "";
        }

        return sb.toString();
    }


    /**
     * This method calculates the shortest path from a source node to all other nodes in a graph using Breadth-First Search (BFS).
     * It works by visiting each node and its children, updating the shortest distance to each child node if a shorter path is found.
     *
     * @param edges      2D array representing the edges between nodes in the graph.
     * @param totalNodes The total number of nodes in the graph.
     * @return An array of shortest distances from the source node (node 0) to all other nodes.
     */
    public int[] shortestPath_BFS(int[][] edges, int totalNodes) {
        // Prepare adjacency list from edges
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < totalNodes; i++) {
            adjList.add(new ArrayList<>());
        }

        // Populate adjacency list
        for (int e = 0; e < edges.length; e++) {
            // Undirected graph so both ways
            adjList.get(edges[e][0]).add(edges[e][1]);
            adjList.get(edges[e][1]).add(edges[e][0]);
        }

        // Store shortest distance of each node from source node
        int[] distance = new int[totalNodes];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // Queue for BFS
        Queue<Integer> bfsQ = new LinkedList<>();

        // Start from node 0
        bfsQ.add(0);
        distance[0] = 0; // Distance from source to itself is 0

        // BFS
        while (!bfsQ.isEmpty()) {
            int node = bfsQ.poll();

            // Check each child of the current node
            for (int child : adjList.get(node)) {
                // Calculate new distance to child
                int newDistance = distance[node] + 1;
                int existingDistance = distance[child];

                // Only update if new distance is shorter
                if (newDistance < existingDistance) {
                    bfsQ.add(child);
                    distance[child] = newDistance;
                }
            }
        }

        return distance;
    }

    /**
     * This method calculates the shortest path from a source node to all other nodes in a Directed Acyclic Graph (DAG) using Topological Sort and Dynamic Programming.
     * It first performs a topological sort on the nodes, then checks each node's children, updating the shortest distance if a shorter path is found.
     *
     * @param edges      2D array representing the edges between nodes in the graph.
     * @param totalNodes The total number of nodes in the graph.
     * @return An array of shortest distances from the source node (node 0) to all other nodes.
     */
    public int[] shortestPath_DAG(int[][] edges, int totalNodes) {
        // Prepare adjacency list from edges
        List<ArrayList<Pair_A>> adjList = new ArrayList<>();
        for (int i = 0; i < totalNodes; i++) {
            adjList.add(new ArrayList<Pair_A>());
        }

        // Populate adjacency list
        for (int e = 0; e < edges.length; e++) {
            // Directed graph so one way
            int u = edges[e][0];
            int v = edges[e][1];
            int weight = edges[e][2];

            Pair_A childWtPair = new Pair_A(v, weight);
            adjList.get(u).add(childWtPair); // u --> v & weight
        }

        // Perform Topological Sort via DFS
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[totalNodes];

        // Handle disconnected graphs
        for (int node = 0; node < totalNodes; node++) {
            if (!visited[node]) {
                topologicalSort_DFS_shortestPath_DAG(node, adjList, visited, st);
            }
        }

        // Initialize distances
        int[] distance = new int[totalNodes];
        Arrays.fill(distance, 1000); // Do not use Integer.MAX_VALUE to avoid overflow

        // Start from node 0
        distance[0] = 0;

        // Process nodes in topological order
        while (!st.isEmpty()) {
            int node = st.pop();
            List<Pair_A> listOfPairs = adjList.get(node);

            // Check each child of the current node
            for (Pair_A pair : listOfPairs) {
                int child = pair.node;
                int weight = pair.weight;

                // Calculate new distance to child
                int newDistance = distance[node] + weight;
                int existingDistance = distance[child];

                // Only update if new distance is shorter
                if (newDistance < existingDistance) {
                    distance[child] = newDistance;
                }
            }
        }

        // Assign -1 to unreachable nodes
        for (int d = 0; d < distance.length; d++) {
            if (distance[d] == Integer.MAX_VALUE) {
                distance[d] = -1;
            }
        }

        return distance;
    }

    /**
     * Helper method for performing Topological Sort via Depth-First Search (DFS).
     *
     * @param node    The current node.
     * @param adjList The adjacency list of the graph.
     * @param visited An array to keep track of visited nodes.
     * @param st      The stack to store the nodes in topological order.
     */
    private void topologicalSort_DFS_shortestPath_DAG(int node, List<ArrayList<Pair_A>> adjList, boolean[] visited, Stack<Integer> st) {
        visited[node] = true; // Mark node as visited

        // Visit all children of the current node
        for (Pair_A pair : adjList.get(node)) {
            if (!visited[pair.node]) {
                topologicalSort_DFS_shortestPath_DAG(pair.node, adjList, visited, st);
            }
        }

        // Push node to stack when all its children are visited
        st.push(node);
    }

    /**
     * This method calculates the shortest path from a source node to all other nodes in a graph using Dijkstra's algorithm.
     * It uses a priority queue to always select the node with the shortest distance from the source node.
     *
     * @return An array of shortest distances from the source node (node 0) to all other nodes.
     */
    public int[] dijkstra_shortestPath() {
        int totalNodes = 5;
        int sourceNode = 0;

        // Prepare adjacency list
        List<List<Pair_A>> adjList = new ArrayList<>();
        for (int i = 0; i < totalNodes; i++) {
            adjList.add(new ArrayList<Pair_A>());
        }

        // Populate adjacency list
        adjList.get(0).add(new Pair_A(1, 9));
        adjList.get(0).add(new Pair_A(2, 6));
        adjList.get(0).add(new Pair_A(3, 5));
        adjList.get(0).add(new Pair_A(4, 3));

        adjList.get(2).add(new Pair_A(1, 2));
        adjList.get(2).add(new Pair_A(3, 4));

        return dijkstra_shortestPath_PQ(adjList, totalNodes, sourceNode);
    }


    /**
     * This method implements Dijkstra's algorithm to find the shortest path in a graph.
     * It uses a priority queue to select the next node with the smallest distance.
     *
     * @param adjList    The adjacency list representing the graph.
     * @param totalNodes The total number of nodes in the graph.
     * @param sourceNode The starting node for the path.
     * @return An array of shortest distances from the source node to all other nodes.
     */
    int[] dijkstra_shortestPath_PQ(List<List<Pair_A>> adjList, int totalNodes, int sourceNode) {
        // Store all shortest distances.
        int[] distance = new int[totalNodes];
        Arrays.fill(distance, (int) 1e9); // Fill with MAX

        // Stores shortest distance at a given node from the source.
        PriorityQueue<Pair_A> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight); // Min heap based on weight

        // Start with the source node
        distance[sourceNode] = 0;
        pq.add(new Pair_A(sourceNode, 0)); // Add source to the priority queue

        while (!pq.isEmpty()) {
            var pr = pq.poll();
            int nodeDistance = pr.weight;
            int node = pr.node;

            // Go to each child and find its distance
            for (var childPair : adjList.get(node)) {
                int childDistance = childPair.weight;
                int child = childPair.node;

                int newDistance = nodeDistance + childDistance;
                int childCurrShortDistance = distance[child];

                // If shorter distance then add child with new distance into PQ.
                if (newDistance < childCurrShortDistance) {
                    pq.offer(new Pair_A(child, newDistance));
                    distance[child] = newDistance; // Also record in distance result.
                }
            }
        }

        return distance;
    }

    /**
     * This method finds the shortest path in a binary matrix using Breadth-First Search (BFS).
     * It considers all 8 directions from a given cell.
     *
     * @param adjMatrix The adjacency matrix representing the graph.
     * @return The shortest path distance. If the path is not possible, it returns -1.
     */
    public int shortestPathBinaryMatrix(int[][] adjMatrix) {
        // Condition to see if start and end is 0 so that we can complete path if not return -1;
        if (adjMatrix[0][0] != 0 || adjMatrix[adjMatrix.length - 1][adjMatrix[0].length - 1] != 0) {
            return -1;
        }

        // Prep
        boolean[][] visited = new boolean[adjMatrix.length][adjMatrix[0].length];
        Queue<int[]> bfsQ = new LinkedList<>();
        int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        // Add into queue row, col & distance
        bfsQ.offer(new int[]{0, 0, 1}); // Each cell is 1 distance as per question.

        // Mark visited
        visited[0][0] = true;

        // Poll queue and operate on node
        while (!bfsQ.isEmpty()) {
            var qObj = bfsQ.poll();
            var rowNum = qObj[0];
            var colNum = qObj[1];
            var distance = qObj[2];

            // Return result if this is the last cell matrix.
            if (rowNum == adjMatrix.length - 1 && colNum == adjMatrix[0].length - 1) {
                return distance;
            }

            // Go in all 8 directions from current position and operate.
            for (int[] dir : directions) {
                int nextRowNum = rowNum + dir[0];
                int nextColNum = colNum + dir[1];

                // Just check if not crossing boundary after direction added and path is not blocked by 1
                if (nextRowNum >= 0 && nextRowNum < adjMatrix.length && nextColNum >= 0 && nextColNum < adjMatrix[0].length) {
                    // Just go in each direction if not already visited and..
                    if (!visited[nextRowNum][nextColNum] && adjMatrix[nextRowNum][nextColNum] != 1) {
                        // Mark visited and..
                        visited[nextRowNum][nextColNum] = true;
                        // Add 1 into distance
                        var distanceAtCell = distance + 1;
                        // Add into queue
                        bfsQ.offer(new int[]{nextRowNum, nextColNum, distanceAtCell});
                    }
                }
            }
        }
        return -1;
    }

    /**
     * This method finds the minimum effort path in a matrix using Dijkstra's algorithm.
     * The effort is defined as the maximum difference in heights between two consecutive cells in the path.
     *
     * @param heights The matrix representing the heights of each cell.
     * @return The minimum effort to travel from the top-left cell to the bottom-right cell. If the path is not possible, it returns -1.
     */
    public int minimumEffortPath(int[][] heights) {
        // Prep
        int[][] results = new int[heights.length][heights[0].length];
        for (var rows : results) {
            Arrays.fill(rows, (int) 1e9); // Fill with MAX
        }
        int directions[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Stores shortest distance at a given node from the source.
        PriorityQueue<Pair_B> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist); // Min heap based on weight

        // Start with 0 distance or no effort
        results[0][0] = 0;
        pq.add(new Pair_B(0, 0, 0)); // Add source to the priority queue

        // Poll and operate
        while (!pq.isEmpty()) {
            var pr = pq.poll();
            int maxEffortSoFar = pr.dist; // Store max effort so far.
            int row = pr.row;
            int col = pr.col;

            // Why here and not wait till all done?
            // Because in PQ we only work off selected result and it may not be always stored in matrix last position.
            // Rather it's in PQ with last row/col position
            if (row == heights.length - 1 && col == heights[0].length - 1) {
                return maxEffortSoFar;
            }
            // Go in all 4 directions from current position and operate.
            for (int[] dir : directions) {
                int nextRowNum = row + dir[0];
                int nextColNum = col + dir[1];

                // Just check if not crossing boundary
                if (nextRowNum >= 0 && nextRowNum < heights.length && nextColNum >= 0 && nextColNum < heights[0].length) {
                    int distOrHeightAtNextCell = heights[nextRowNum][nextColNum];
                    int distOrHeightAtPolledCell = heights[row][col];

                    int diff = Math.abs(distOrHeightAtNextCell - distOrHeightAtPolledCell); // Find difference between two cells
                    // Find if this difference is max effort
                    // E.g. say previously we had 5 efforts and this step difference is 0 that
                    // does not mean we say 0 efforts instead keep 5 as it was done in past on this path.
                    int newMaxEffort = Math.max(maxEffortSoFar, diff);

                    // Check result and if this is smaller effort than previously stored since we were asked to find min efforts.
                    // E.g. we had 10 effort previously to reach this cell from some different path
                    // but now it only takes 5 from good path so this will be recorded.
                    if (newMaxEffort < results[nextRowNum][nextColNum]) {
                        // Store min effort in result and put in queue
                        results[nextRowNum][nextColNum] = newMaxEffort;
                        pq.offer(new Pair_B(nextRowNum, nextColNum, newMaxEffort));
                    }
                }
            }
        }

        return -1;
    }


    /**
     * This method finds the cheapest price for a flight with a maximum number of stops.
     * It uses Dijkstra's algorithm to find the shortest path in a graph.
     *
     * @param totalCities      The total number of cities.
     * @param flights          The 2D array representing the flights, where flights[i] = [from, to, price].
     * @param originalSource   The original source city.
     * @param finalDestination The final destination city.
     * @param maxStop          The maximum number of stops.
     * @return The cheapest price for the flight. If there is no route within the maxStop, return -1.
     * <p>
     * Example:
     * int totalCities = 3;
     * int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
     * int originalSource = 0;
     * int finalDestination = 2;
     * int maxStop = 1;
     * The method returns 200, as the cheapest flight from city 0 to city 2 with up to 1 stop costs 200.
     */
    public int findCheapestPrice(int totalCities, int[][] flights, int originalSource, int finalDestination, int maxStop) {
        // Preparation: Create adjacency list
        List<List<Pair_Flights>> adjList = new ArrayList<>();
        for (int i = 0; i < totalCities; i++) {
            adjList.add(new ArrayList<Pair_Flights>());
        }

        // Go over edges matrix and prepare adjacency list
        for (int i = 0; i < flights.length; i++) {
            int cityNode = flights[i][0];
            int destNode = flights[i][1];
            int costNode = flights[i][2];
            // For each city --> destination and cost
            adjList.get(cityNode).add(new Pair_Flights(destNode, costNode));
        }

        // Stores minimum cost at given city as destinations
        int[] minCostAtDestinations = new int[totalCities];
        Arrays.fill(minCostAtDestinations, (int) 1e9);

        // Stores stops from source node, destination and cost from source
        // Min heap based on stops
        PriorityQueue<Pair_Ticket> pq = new PriorityQueue<>((a, b) -> a.stops - b.stops);
        pq.add(new Pair_Ticket(0, originalSource, 0)); // Start with source as 0 stop and 0$

        // Poll minimum stop based items
        while (!pq.isEmpty()) {
            var x = pq.poll();
            int stopAtNode = x.stops;
            int node = x.destination;
            int costAtNode = x.cost;

            // Go for each child of given node
            for (var childNodes : adjList.get(node)) {
                int child = childNodes.dest;
                int costAtChild = childNodes.cost;

                // Calculate by adding new cost at destination
                int totalCost = costAtNode + costAtChild;

                // Only go for cheap ticket if stops <= max stop required
                if (totalCost < minCostAtDestinations[child] && stopAtNode <= maxStop) {
                    pq.offer(new Pair_Ticket(stopAtNode + 1, child, totalCost));
                    minCostAtDestinations[child] = totalCost;
                }
            }
        }

        return minCostAtDestinations[finalDestination] == (int) (1e9) ? -1 : minCostAtDestinations[finalDestination];
    }

    /**
     * This method calculates the time it will take for all nodes to receive the signal.
     * It uses Dijkstra's algorithm to find the shortest path in a graph.
     *
     * @param times      The 2D array representing the times, where times[i] = [from, to, time].
     * @param totalNodes The total number of nodes.
     * @param sourceNode The source node.
     * @return The minimum time for all nodes to receive the signal. If any node doesn't receive the signal, return -1.
     * <p>
     * Example:
     * int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
     * int totalNodes = 4;
     * int sourceNode = 2;
     * The method returns 2, as the network delay time is 2.
     */
    public int networkDelayTime(int[][] times, int totalNodes, int sourceNode) {
        // Preparation: Create adjacency list
        List<List<Pair_A>> adjList = new ArrayList<>();
        for (int i = 0; i <= totalNodes; i++) {
            adjList.add(new ArrayList<Pair_A>());
        }

        // Go over edges matrix and prepare adjacency list
        for (int i = 0; i < times.length; i++) {
            int from = times[i][0];
            int to = times[i][1];
            int travelTime = times[i][2];
            // For each node --> destination and weight
            adjList.get(from).add(new Pair_A(to, travelTime));
        }

        // Stores minimum travel time at given node as destinations
        int[] minTravelTimeAtDestinations = new int[totalNodes + 1]; // +1 because 0 based
        Arrays.fill(minTravelTimeAtDestinations, (int) 1e9);

        // Stores travel time in PQ for given node
        // As it is the minimum time so far at given node
        PriorityQueue<Pair_A> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Pair_A(sourceNode, 0)); // Start with source as 0 travel time
        minTravelTimeAtDestinations[sourceNode] = 0;

        // Poll minimum travel time based items
        while (!pq.isEmpty()) {
            var nd = pq.poll();
            int currNode = nd.node;
            int traveledTimeSoFar = nd.weight;

            // Go for each child of given node
            for (var childNodes : adjList.get(currNode)) {
                int child = childNodes.node;
                int travelTimeAtChild = childNodes.weight;

                int totalTravelTime = traveledTimeSoFar + travelTimeAtChild;

                if (totalTravelTime < minTravelTimeAtDestinations[child]) {
                    pq.offer(new Pair_A(child, totalTravelTime));
                    minTravelTimeAtDestinations[child] = totalTravelTime;
                }
            }
        }

        // Now we need to see if any node is untouched and if not then what is the minimum time to reach all nodes
        // Here the trick is even if it is minimum time but to reach all nodes so we go over each node and find
        // maximum value as that would be the last node in graph reached with travel time.
        // If we pick any other time it may be minimum but that node may not be the last node.
        int minTravel = Integer.MIN_VALUE;
        // Start with 1 because we don't have 0 node and it is set as max will give wrong answer if we start with 0
        for (int i = 1; i <= totalNodes; i++) {
            minTravel = Math.max(minTravel, minTravelTimeAtDestinations[i]);
        }
        return minTravel == (int) 1e9 ? -1 : minTravel;
    }


    /**
     * This method implements the Bellman-Ford algorithm to find the shortest distance from a given source node to all other nodes.
     * It is different from Dijkstra's algorithm in that it can handle graphs with negative edges and negative cycles.
     *
     * @return An array of shortest distances from the source node to all other nodes. If a negative cycle is detected, it returns an array with -1.
     */
    public int[] Bellman_Ford_Algorithm() {
        int totalNodes = 6;
        int sourceNode = 0;

        // Declaring edges matrix as list of list
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>() {
            {
                add(new ArrayList<Integer>(Arrays.asList(3, 2, 6)));
                add(new ArrayList<Integer>(Arrays.asList(5, 3, 1)));
                add(new ArrayList<Integer>(Arrays.asList(0, 1, 5)));
                add(new ArrayList<Integer>(Arrays.asList(1, 5, -3)));
                add(new ArrayList<Integer>(Arrays.asList(1, 2, -2)));
                add(new ArrayList<Integer>(Arrays.asList(3, 4, -2))); // Comment this to make cycle.
                // add(new ArrayList<Integer>(Arrays.asList(4, 3, -12))); // Uncomment this to make cycle.
                add(new ArrayList<Integer>(Arrays.asList(2, 4, 3)));
            }
        };

        // Store distance
        int[] distance = new int[totalNodes];
        Arrays.fill(distance, (int) 1e9);
        distance[sourceNode] = 0;

        // Relax all the edges for N-1 times (N = no. of nodes)
        // After N-1 iterations, we should have minimized the distance to every node.
        for (int i = 0; i < totalNodes; i++) {
            // Find distance for each edge
            for (var edg : edges) {
                var u = edg.get(0);
                var v = edg.get(1);
                var wt = edg.get(2);

                int currDistanceAtNodeU = distance[u];
                int currDistanceAtNodeV = distance[v];
                int newDistance = currDistanceAtNodeU + wt;

                if (newDistance < currDistanceAtNodeV) {
                    distance[v] = newDistance;
                }
            }
        }

        // Check Negative Cycle by looking if node previously got relaxed in N-1 iteration.
        // Find distance for each edge
        for (var edg : edges) {
            var u = edg.get(0);
            var v = edg.get(1);
            var wt = edg.get(2);

            int currDistanceAtNodeU = distance[u];
            int currDistanceAtNodeV = distance[v];
            int newDistance = currDistanceAtNodeU + wt;

            // Check if previously found distance via relaxed node
            // and again it is trying to further find min due to cycle.
            if (newDistance < currDistanceAtNodeV) {
                return new int[]{-1};
            }
        }

        return distance;
    }


    /**
     * This method implements the Floyd Warshall Algorithm.
     * The Floyd Warshall Algorithm is used to find the shortest path from multiple sources to multiple destinations.
     * It supports finding cycles and negative values.
     * In simple terms, it finds the minimum distance from each node to all other nodes via every node.
     * Cycle detection is based on the logic that if any node say B -> B via B is always 0 and if some iteration
     * tries to make it less than 0, it means it has hit a cycle and tries to further reduce the distance.
     */
    public void Floyd_Warshall_Algorithm() {

        // Initialize total number of nodes
        int totalNodes = 4;

        // Initialize the matrix with -1
        int[][] matrix = new int[totalNodes][totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            for (int j = 0; j < totalNodes; j++) {
                matrix[i][j] = -1;
            }
        }

        // Assign values to the matrix
        matrix[0][1] = 2;
        matrix[1][0] = 1;
        matrix[1][2] = 3;
        matrix[3][0] = 3;
        matrix[3][1] = 5;
        matrix[3][2] = 4;

        // Assign 0 where from and to is the same node and everything else is MAX.
        for (int i = 0; i < totalNodes; i++) {
            for (int j = 0; j < totalNodes; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else if (matrix[i][j] == -1) {
                    matrix[i][j] = (int) 1e9;
                }
            }
        }

        // Find the shortest distance from each node to all nodes via every node.
        for (int via = 0; via < totalNodes; via++) { // via every node
            for (int fromNode = 0; fromNode < totalNodes; fromNode++) { // for each node
                for (int toNode = 0; toNode < totalNodes; toNode++) { // to all nodes

                    // Previously calculated & stored distance
                    int curDistance = matrix[fromNode][toNode];

                    // Newly calculated distance with via node
                    int newDistanceVia = matrix[fromNode][via] + matrix[via][toNode];

                    // Pick only the better or minimum distance from the two.
                    int resDistance = Math.min(curDistance, newDistanceVia);

                    // Assign the distance.
                    matrix[fromNode][toNode] = resDistance;
                }
            }
        }
    }

    /**
     * This method finds the city that has the smallest number of neighbors within a given maximum distance.
     * It uses the Floyd Warshall algorithm to find the shortest path in a graph.
     *
     * @param totalCities       The total number of cities.
     * @param edges             The 2D array representing the edges, where edges[i] = [from, to, distance].
     * @param distanceThreshold The maximum distance threshold.
     * @return The city that has the smallest number of neighbors within the given maximum distance. If there are multiple such cities, return the one with the higher rank.
     * <p>
     * Example:
     * int totalCities = 4;
     * int[][] edges = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
     * int distanceThreshold = 4;
     * The method returns 3, as the city 3 has 1 neighbor (city 2) within a distance of 4.
     */
    public int findTheCity_Has_SmallestNeighbour_At_MaxDistance(int totalCities, int[][] edges, int distanceThreshold) {
        // Assign distance to all cities as MAX
        // i.e., assign 0 where from and to is the same node and everything else as MAX
        int[][] distance = new int[totalCities][totalCities];
        for (int i = 0; i < totalCities; i++) {
            for (int j = 0; j < totalCities; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // Populate distance[][] with given edges/connections
        for (int z = 0; z < edges.length; z++) {
            int cityA = edges[z][0];
            int cityB = edges[z][1];
            int dist_A_B = edges[z][2];

            // Unidirectional distance assigned
            distance[cityA][cityB] = dist_A_B;
            distance[cityB][cityA] = dist_A_B;
        }

        // Find short distance from each node to all nodes via every node
        for (int via = 0; via < totalCities; via++) {
            for (int fromNode = 0; fromNode < totalCities; fromNode++) {
                for (int toNode = 0; toNode < totalCities; toNode++) {
                    // If we hit MAX just skip as it does not make sense to find new distance
                    if (distance[fromNode][via] == Integer.MAX_VALUE || distance[via][toNode] == Integer.MAX_VALUE) {
                        continue;
                    }

                    // Previously calculated & stored distance
                    int curDistance = distance[fromNode][toNode];

                    // Newly calculated distance with via node
                    int newDistanceVia = distance[fromNode][via] + distance[via][toNode];

                    // Pick only better or min distance from two
                    int resDistance = Math.min(curDistance, newDistanceVia);

                    // Assign distance
                    distance[fromNode][toNode] = resDistance;
                }
            }
        }

        // Now for each city find its neighbor city within the given max threshold
        int minReachableNeighbourCities = totalCities;
        int cityFromWhereMinNbrFound = -1;

        for (int ct = 0; ct < totalCities; ct++) {
            int count = 0;
            for (int nbr = 0; nbr < totalCities; nbr++) {
                if (distance[ct][nbr] <= distanceThreshold) {
                    count++;
                }
            }

            if (count <= minReachableNeighbourCities) {
                minReachableNeighbourCities = count;
                cityFromWhereMinNbrFound = ct;
            }
        }
        return cityFromWhereMinNbrFound;
    }

    /**
     * This method calculates the minimum spanning tree using the Prime's algorithm.
     *
     * @return The weight of the minimum spanning tree.
     * <p>
     * Example:
     * Consider the following graph:
     * <p>
     * (assume it is weighted also)
     * 1 -------- 2
     * |  \       |
     * |    \     |
     * |      \   |
     * 0         3
     * <p>
     * Spanning Tree 1 (total weight = 40)
     * 1 -------- 2
     * |         |
     * |          |
     * |         |
     * 0         3
     * <p>
     * Spanning Tree 2 (total weight = 30)
     * 1           2
     * |  \       |
     * |    \     |
     * |      \   |
     * 0         3
     * <p>
     * The Minimum Spanning Tree (MST) is the spanning tree which total weight of all edges is minimum.
     * In this case, Spanning Tree 2 is the MST because its total weight (30) is less than the total weight of Spanning Tree 1 (40).
     */
    public int minSpanningTree_PrimeAlgorithm() {

        // Number of vertices in the graph
        int V = 5;

        // Adjacency list representation of the graph
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();

        // Edges of the graph. Each edge is represented as an array of three integers [u, v, w] where u and v are the vertices and w is the weight of the edge.
        int[][] edges = {{0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}};

        // Initialize the adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<ArrayList<Integer>>());
        }

        // Add the edges to the adjacency list
        for (int i = 0; i < 6; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            ArrayList<Integer> tmp1 = new ArrayList<Integer>();
            ArrayList<Integer> tmp2 = new ArrayList<Integer>();
            tmp1.add(v);
            tmp1.add(w);

            tmp2.add(u);
            tmp2.add(w);

            adj.get(u).add(tmp1);
            adj.get(v).add(tmp2);
        }

        // Call the method to calculate the minimum spanning tree
        return minSpanningTree_PrimeAlgorithm(adj, V);
    }


    private int minSpanningTree_PrimeAlgorithm(ArrayList<ArrayList<ArrayList<Integer>>> adjList, int totalNodes) {

        //prep
        boolean[] visited = new boolean[totalNodes];
        //stores short distance @ given node from source.
        PriorityQueue<Pair_A> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight); //min heap based on Weight

        //1. start with 0 node and add in to PQ.
        pq.add(new Pair_A(0, 0));
        int totalWeight = 0; // keep MST weight


        //2 poll and work
        while (!pq.isEmpty()) {

            var pr = pq.poll();// get min wt node.
            int wt = pr.weight;
            int node = pr.node;

            //MUST why? Since this is UDG node can be a child of multiple Nodes i.e.
            // and if this node was visited & its weight counted but PQ still has same node from its another parent
            // then we want to ignore else it will be recounted.
            if (visited[node]) {
                continue;
            }

            visited[node] = true;

            //increase wt.
            totalWeight = totalWeight + wt;


            // go to each child and find its distance
            for (int i = 0; i < adjList.get(node).size(); i++) {
                int childWt = adjList.get(node).get(i).get(1);
                int childNode = adjList.get(node).get(i).get(0);
                //if not visited put in to PQ.
                if (!visited[childNode]) {
                    pq.add(new Pair_A(childNode, childWt));
                }
            }
        }

        return totalWeight;
    }

    public void disJointSet_UnionByRank_DataStructure() {

        /*
        This DataStructure helps to identify if nodes are connected or not in O(1) times.
        Q :But Why need to identify it? Bcs it uses with mostly dynamic Graph.

        A : Dynamic Graph is where it keeps on changing its configuration so at some point two graphs are not connected
        but after certian point it may be.

         1 -------- 2
            |         |
            |          |
            |         |
            0         3
            After few iteration it become connected.
            1           2
            |  \       |
            |    \     |
            |      \   |
            0         3

           And while it keep changing we need to find if any two nodes are conencted or not then
           Disjoin can provide that info in O1 while by keeping graph nodes in relating with ulitmpate parent.

           Other Brute force methods like BFS/DFS takes OV + OE = ON

        --Note : This DS is not meant to store Graph it self.

        There are two flavor of this DS
            - Union by Rank and
            - Union by Size

        Union by Rank Tips:

        When two nodes ULTIMATE  parent is same do nothing..
        when ULTIMATE parent is not same and
            1. rank is same then one connect to other and where it Connects that node`s ULTIMATE parent rank got increased.
            2. when one rank < second rank then smaler rank node attach to higher rank node and
                NO ONE get rank increased.

        ULTIMATE  parent i.e. each child points to its Root Node as parent no immediate parent.
            - Process to find Root node and point is called Path Compression.

        * */

        DisjointUnionByRank disjointUnionByRank = new DisjointUnionByRank(7);

        disjointUnionByRank.unionbyRank(1, 2);
        disjointUnionByRank.unionbyRank(2, 3);
        disjointUnionByRank.unionbyRank(4, 5);
        disjointUnionByRank.unionbyRank(6, 7);
        disjointUnionByRank.unionbyRank(5, 6);

        // if 3 and 7 same or not
        if (disjointUnionByRank.getUlimateParent(3) == disjointUnionByRank.getUlimateParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }


        //add more edge to graph and check again
        disjointUnionByRank.unionbyRank(3, 7);


        // if 3 and 7 same or not
        if (disjointUnionByRank.getUlimateParent(3) == disjointUnionByRank.getUlimateParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }

    }


    public void disJointSet_UnionBySIZE_DataStructure() {

        /*
        Idea: Same as Union by Rank but instead of maintaining and increasing Rank , Size will be.

        - Size is based on How big is the Graph/Tree from given Node.
        - Each node size is 1.

         Union by Size Tips:

        When two nodes ULTIMATE  parent is same do nothing..
        when ULTIMATE parent is not same and
            1. SIZE is same then one connect to other and where it Connects that node`s ULTIMATE parent size will add up by what is connecting.
            2. when one size < second size then smaller size node attach to higher size node and higher node will add up by smaller node size.

        ULTIMATE  parent i.e. each child points to its Root Node as parent no immediate parent.
            - Process to find Root node and point is called Path Compression.


        = fyi. By Size is more intuitive as it shows what it is and Rank is kind of distorted ie. it got changed over the time and
        not easy to relate.

        *****Note : Here Size of Node say * V * to which another Node say * U * added  will be increased
            unlike Rank where it only increases when both Ranks are same. Reason is in either case when someting
             get connected to another Size will be always get  increased.

        * */

        DisjointUnionBySize disjointUnionBySize = new DisjointUnionBySize(7);

        disjointUnionBySize.unionbySize(1, 2);
        disjointUnionBySize.unionbySize(2, 3);
        disjointUnionBySize.unionbySize(4, 5);
        disjointUnionBySize.unionbySize(6, 7);
        disjointUnionBySize.unionbySize(5, 6);

        // if 3 and 7 same or not
        if (disjointUnionBySize.getUlimateParent(3) == disjointUnionBySize.getUlimateParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }


        //add more edge to graph and check again
        disjointUnionBySize.unionbySize(3, 7);


        // if 3 and 7 same or not
        if (disjointUnionBySize.getUlimateParent(3) == disjointUnionBySize.getUlimateParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }

    }


    public int minSpanningTree_KrusKalAlgorithm() {

        /*
        Idea :

           Tree which is UDG , weighted, connected and reachable by every node to every other node can be spanning tree
           if we remove some edges then also its every node is reachable.


          (assume it is weighted also)
            1 -------- 2
            |  \       |
            |    \     |
            |      \   |
            0         3

           Spanning Tree 1 (total wt = 40)
           1 -------- 2
            |         |
            |          |
            |         |
            0         3

            Spanning Tree 2 (total wt = 30)
            1           2
            |  \       |
            |    \     |
            |      \   |
            0         3


            Minimum Spanning Tree i.e MST is the spanning tree which total weight of all edges is min
            e..g above  SP2 OF 30 < 40(SP1) I.E. SP2 is MST.

          - Kruskal aglo is used to identify MST and its edges.
          - use DisJoint By Rank or By Size to identify MST.

          ****** Basically, First sort edges by  weight/distance/cost  whatever gives but ascending that will ensure
            min weight is picked up. Once that min is on hand check its source and target/destination nodes are of same parent if yes move on
            BUT if NO then connect them using DS, while doing that keep addig weight in total weight and
            when all the nodes are connected the final sum of totla weight is the min weight for tree
            which is also now MST.

            See here we dynamically created graph using DS join and by adding min weight first we achieved MST.

        * */

        int V = 5;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
        int[][] edges = {{0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}};

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<ArrayList<Integer>>());
        }

        for (int i = 0; i < 6; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            ArrayList<Integer> tmp1 = new ArrayList<Integer>();
            ArrayList<Integer> tmp2 = new ArrayList<Integer>();
            tmp1.add(v);
            tmp1.add(w);

            tmp2.add(u);
            tmp2.add(w);

            adj.get(u).add(tmp1);
            adj.get(v).add(tmp2);
        }

        return minSpanningTree_KrusKalAlgorithm(adj, V);
    }

    private int minSpanningTree_KrusKalAlgorithm(ArrayList<ArrayList<ArrayList<Integer>>> adjList, int totalNodes) {

        //1. Sort Graph by its Weight.
        // prep EDGE List
        // O(N + E)
        List<MST_EDGE> edgeList = new ArrayList<>();
        for (int node = 0; node < totalNodes; node++) {
            for (int i = 0; i < adjList.get(node).size(); i++) {
                int childWt = adjList.get(node).get(i).get(1);
                int childNode = adjList.get(node).get(i).get(0);
                var mstEdg = new MST_EDGE(node, childNode, childWt);
                edgeList.add(mstEdg);
            }
        }
        // N LOG (N)
        Collections.sort(edgeList);

        //2 go over each sorted weighted Node and find its parent using O(1)
        // if parent is not same it gets added in MST and weight counted and if
        // belong to same parent will ignore that edge to avoid unnecessary weight

        //Initialize DSJoint
        DisjointUnionBySize disjointUnionBySize = new DisjointUnionBySize(totalNodes);
        int mstWeight = 0;

        for (int w = 0; w < edgeList.size(); w++) {

            int weight = edgeList.get(w).weight;
            int source = edgeList.get(w).source;
            int destination = edgeList.get(w).destination;

            //O(1) based on algo O M x 4 X alpha X2 == O (1)
            int prtSrc = disjointUnionBySize.getUlimateParent(source);
            int prtDest = disjointUnionBySize.getUlimateParent(destination);

            //not connected so now connected in MST
            if (prtSrc != prtDest) {
                mstWeight = mstWeight + weight;  //captured MST weight.
                disjointUnionBySize.unionbySize(prtSrc, prtDest);// U--> V connection.
            }

        }
        return mstWeight;
    }

    public int make_NetWork_Connected_noOfOperation(int totalNodes, int[][] connections) {

        /*
        Idea: Basically we need to find MST and  for that we could use Disjoint DS
        Not everytime we need to find weight sometime we need diff info

        Here we need to find no of connection required to connect all nodes and also sametime
        while applying MST we remove unwanted extra connection.

        Note : If there are N= 4 total Nodes we need at lease E =3 edges to connect them
        we if have anythig <3 thne not enough edges are there to connect all components.

        *** Recall Basic idea for MST + DS Join use that her by dynamically crated graph and connect using DS
        see how many wires needed.

        * */

        // common sense if 6 computer then need 5 wires at lease less than that
        // 6-1 =5 > any lesser no of wires won`t do..
        int totalEdges = connections.length;
        if (totalEdges < totalNodes - 1) {
            return -1; // not enough edges
        }

        //now do MST via Disjoint and count against expected connection
        // or use against expected connection

        DisjointUnionBySize disjointUnionBySize = new DisjointUnionBySize(totalNodes);
        int noOfConnectionExpected = totalNodes - 1;// RECALL need that many edges/connection.

        for (int[] eachConn : connections) {
            int src = eachConn[0];
            int dst = eachConn[1];

            // if not connected then use connection and also connect them
            if (disjointUnionBySize.getUlimateParent(src) != disjointUnionBySize.getUlimateParent(dst)) {
                disjointUnionBySize.unionbySize(src, dst);
                noOfConnectionExpected--;// for same parent i.e. already connected this wont deduct
            }
        }

        // return unused or left connection as no of connection requried to connect all component.
        return noOfConnectionExpected;
    }


    public int Max_Stone_Remove_DFS_EASYToUnderStand(int[][] stones) {

        /*
        Idea:
        The hard part here is to find connected graph. Once found just deduct from total nodes is the answer.
        Arrival on this solution to create connected graph is tricky for that visualize the stone by putting at
        given coordinate and see how many can be remvoed and once find that reverse that logic like, If we have only one component graph
        and for one component it can be observed that we can remove all the stones except the last one.

        Rest is DFS
        * */
        // Adjacency list to store edges
        List<Integer>[] adj = new ArrayList[stones.length];
        for (int i = 0; i < stones.length; i++) {
            adj[i] = new ArrayList<>();
        }

        //PREPARING GRAPH by comparing each stone coordination with rest of stone coordination
        //this is also given as when this has to be on matrix before check if they share R or C .
        for (int i = 0; i < stones.length; i++) {
            for (int j = i + 1; j < stones.length; j++) {
                if (shareSameRowOrColumn(stones[i], stones[j])) { //given in Q as a condition
                    adj[i].add(j);//bcs UDF graph
                    adj[j].add(i); //bcs UDF graph
                }
            }
        }

        /*
        Above creates for given question like , 1 component graph.

            1 -------- 2
            |         |
            |          |
            |         |
            2         4
            |         |
            |          |
            |         |
            3----------5

        * */

        // Array to mark visited stones
        int[] visited = new int[stones.length];
        // Counter for connected components
        int componentCount = 0;
        for (int i = 0; i < stones.length; i++) {
            if (visited[i] == 0) {
                // If the stone is not visited yet,
                // Start the DFS and increment the counter
                componentCount++;
                Max_Stone_Remove_DFS_EASYToUnderStand_DFS(stones, adj, visited, i);
            }
        }

        // Return the maximum stone that can be removed
        return stones.length - componentCount;
    }

    boolean shareSameRowOrColumn(int[] a, int[] b) {
        return a[0] == b[0] || a[1] == b[1];
    }

    void Max_Stone_Remove_DFS_EASYToUnderStand_DFS(int[][] stones, List<Integer>[] adj, int[] visited, int src) {
        // Mark the stone as visited
        visited[src] = 1;

        // Iterate over the adjacent, and iterate over it if not visited yet
        for (int adjacent : adj[src]) {
            if (visited[adjacent] == 0) {
                Max_Stone_Remove_DFS_EASYToUnderStand_DFS(stones, adj, visited, adjacent);
            }
        }
    }

    public int Max_Stone_Remove(int[][] stones) {

        /*
        Idea:

         Basically from Edge matrix we need to derive connected graph ( to connect we could use Disjoin)
         once we its found that how many are connected graphs via checking its parent the formula is
         Len of Edges - connected graphs = Total stone removed

         Now Q is how to read edge matrix and make graph connected there it uses Row + col + 1 thing
         basically converting 2D to 1D array

        * */

        //find max Row and col it has to help 2D to 1D
        int maxRow = 0;
        int maxCol = 0;
        for (int i = 0; i < stones.length; i++) {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }

        //gets total Nodes and prep graph using DS
        int totalNodes = maxRow + maxCol + 1;
        DisjointUnionBySize dSz = new DisjointUnionBySize(totalNodes);
        HashMap<Integer, Integer> stoneNodes = new HashMap<>(); // need unique node in list so using map no other reason.

        //**VIMP The best I could understand logic is we have say
        // total 6 stones at diff cooridnation and since we need to create graph and join them
        // we need node so by getting node Row and node Col we prep nodes
        //(logic for node Col is based on total sotnes needd to fit 0 -5 for 6 stones and
        // once we have node join then by DS .

        //go over stones edge and make graph using DS.
        for (int x = 0; x < stones.length; x++) {
            int nodeRw = stones[x][0];
            int nodeCol = stones[x][1] + maxRow + 1;//recall after 2d to 1d OR Curr Row * Col Len + curr Col
            dSz.unionbySize(nodeRw, nodeCol);// this connects nodes.
            //to me this  map just collect nodes from row and colum.
            stoneNodes.put(nodeRw, 1);
            stoneNodes.put(nodeCol, 1);
        }

        int connectedGraphCount = 0;

        //go over each node added in Graph and find total ultimate parents and that many are connected graph.
        for (var item : stoneNodes.entrySet()) {
            int node = item.getKey();
            int ultimateParent = dSz.getUlimateParent(node);
            //when found node which is also ulimate parent tell us that it is one big connected graph i..e
            // this node who is also parent has big tree or its child connected.
            if (node == ultimateParent) {
                connectedGraphCount++;
            }

        }

        // as per given formula
        int res = stones.length - connectedGraphCount;

        return res;

    }


    public List<List<String>> accounts_Merge_DFS(List<List<String>> accountsList) {

        /*
        Idea:
        - Treat Email List as Nodes
        - In a collection first email as main Node and others are child
          then build adj list where it make sure that parent email if appears twice in diff list then other email in that diff list appears as child of
          first mail in adj list
        - Single Node i.e List has only one email will not be part of adj list and should be treated as non connected graph.
        - Just do DFS like other problem and prep merged list and put that list in mail all email list .

          Tech tip:


        List<String> lt1  = new ArrayList<>(Arrays.asList("ab"));
        List<List<String>> lt2 = new ArrayList<>(){
            {
                add(new ArrayList<>("ab","ce"));
            }
        };


        * */


        HashMap<String, List<String>> adjList = new HashMap<>();

        //prep adj list from given list for only those node has child.
        for (var eachActList : accountsList) {


            // first email as a Node having edges to other node in list as adjNode
            // basically singe node will be treated as not connected graph.
            String firstEmail = eachActList.get(1);

            //build adj List now
            for (int e = 2; e < eachActList.size(); e++) {

                var adjEmail = eachActList.get(e);

                // Basically adjlist.get(firstEmail).add(secondmail, third email etc.)
                // Choose Map to build adj list for as our Node is not an index rather object
                if (!adjList.containsKey(firstEmail)) {
                    adjList.put(firstEmail, new ArrayList<>());
                    adjList.get(firstEmail).add(adjEmail);
                } else {
                    adjList.get(firstEmail).add(adjEmail);
                }

                //now we did u-v so do v-u since its UDG
                if (!adjList.containsKey(adjEmail)) {
                    adjList.put(adjEmail, new ArrayList<>());
                    adjList.get(adjEmail).add(firstEmail);
                } else {
                    adjList.get(adjEmail).add(firstEmail);
                }
            }
        }

        //for each email list aka nodes which are not connected
        //traverse each node component in DFS and add in to main list

        List<List<String>> allEmailActList = new ArrayList<>();

        HashSet<String> visitedEmailAsNode = new HashSet<>();

        for (var eachActList : accountsList) {
            //need fresh list for each unique user.
            List<String> mergedEmailListBelongToUniqueUser = new ArrayList<>();

            //prep  list for a user.
            String userName = eachActList.get(0);
            String firstEmailAsNode = eachActList.get(1);

            //if not visited this email in different non connected graph
            if (!visitedEmailAsNode.contains(firstEmailAsNode)) {
                //start adding item in list for a user
                mergedEmailListBelongToUniqueUser.add(userName);
                account_Merge_DFS(firstEmailAsNode, mergedEmailListBelongToUniqueUser, visitedEmailAsNode, adjList);

                //sort the accounts as per req except UserName
                Collections.sort(mergedEmailListBelongToUniqueUser.subList(1, mergedEmailListBelongToUniqueUser.size()));
                //add in to mail list
                allEmailActList.add(mergedEmailListBelongToUniqueUser);
            }
        }

        return allEmailActList;

    }

    void account_Merge_DFS(String emailAsNode, List<String> mergedEmailListBelongToUniqueUser, HashSet<String> visitedEmailAsNode, HashMap<String, List<String>> adjList) {

        //mark node as visited
        visitedEmailAsNode.add(emailAsNode);

        //put in result
        mergedEmailListBelongToUniqueUser.add(emailAsNode);

        //since we never had adj list created for single node we need to check before start for each loop
        if (!adjList.containsKey(emailAsNode)) {
            return;
        }
        //for each child Node of this email
        for (String childEmailAsNode : adjList.get(emailAsNode)) {
            //if child was not visited before
            if (!visitedEmailAsNode.contains(childEmailAsNode)) {
                account_Merge_DFS(childEmailAsNode, mergedEmailListBelongToUniqueUser, visitedEmailAsNode, adjList);
            }
        }

    }

    public List<List<String>> accounts_Merge_DSJointUnion(List<List<String>> accountsList) {

        /*
        Idea:
        - Treat Email List as Nodes with Index of List as Parent Node and same defiend in DS Union
        - prepare map with Eamil -> Node and for duplicate email use DS to union its new parent node to exisiting parent node
        - Using DS Parent prep map of Node - > List of related emails.
        - Iterate it and sort + add user name
        * */


        // Build Graph or Connect Graph using DSJoint
        // where each index of Main list is Parent Node under all email from each list of unique user build up/connected.
        DisjointUnionBySize dsZ = new DisjointUnionBySize(accountsList.size());
        HashMap<String, Integer> emailToNodeMapping = new HashMap<>();

        for (int mainList = 0; mainList < accountsList.size(); mainList++) {
            var eachList = accountsList.get(mainList);
            //just consider email not username to build graph.
            for (int item = 1; item < eachList.size(); item++) {
                //email account
                String emailAct = eachList.get(item);

                //map email to say node 0
                // if this email comes first time then map to its Node
                if (!emailToNodeMapping.containsKey(emailAct)) {
                    emailToNodeMapping.put(emailAct, mainList);
                }
                //since email comes again then union this email to its existing group.
                else {
                    //u -> v and u= Node and v is also Node of incoming email account.
                    // may be this email comes under Node 2 and previously came under Node 1
                    // then here union of 1 and 2 happens by using DisJoint.
                    dsZ.unionbySize(mainList, emailToNodeMapping.get(emailAct));
                }

            }
        }

        //At this point in DS Join all related address shares same ulitmate parent.
        // single email attach to its Node and as its ulimate parent. recall non connected graph node.

        //prep Node as Parent to Eamil list as all related child / email
        HashMap<Integer, List<String>> nodeToEmailsMapping = new HashMap<>();

        for (var item : emailToNodeMapping.entrySet()) {
            String email = item.getKey();
            int node = item.getValue();
            int parent = dsZ.getUlimateParent(node);

            //this is where it collects related emails based on DS Union
            if (!nodeToEmailsMapping.containsKey(parent)) {
                nodeToEmailsMapping.put(parent, new ArrayList<>());
            }
            nodeToEmailsMapping.get(parent).add(email);

        }

        //prep final list with UserName and sorted email

        List<List<String>> allEmailActList = new ArrayList<>();

        for (var item : nodeToEmailsMapping.entrySet()) {

            int node = item.getKey();
            List<String> emailActs = item.getValue();

            Collections.sort(emailActs);//sort

            String userName = accountsList.get(node).get(0);
            emailActs.add(0, userName);//add name and make list complete

            allEmailActList.add(emailActs); // add in to mail result list
        }


        return allEmailActList;

    }

    public List<Integer> num_Of_Island_2(int m, int n, int[][] positions) {

        /*
        Idea:
        - Make island via visiting each give position and while doing it make sure
            1. Island counter is gets increase for non visited cell
            2. keep mark cell visited
            3. Go each 4 dir to see if this cell can be connected and if it can then make bigger island but decrease its count.
            4. Use DSJoint and
            5. conver 2D to 1D for DS total node.

        -  Since we are making land dynamically it can be  treated as dynamic graph which can be connected or not and for that
        DS Union is the way to go.
        - Go over comments for details.

        * */


        //Prep
        int rowLen = m;
        int colLen = n;
        int[][] visited = new int[rowLen][colLen];
        // count len of 2D in to 1D and assign to DS as total nodes.
        DisjointUnionBySize dsZ = new DisjointUnionBySize(rowLen * colLen);

        int isLandCounter = 0;
        var isLandList = new ArrayList<Integer>();

        //Iterate over given position and build island
        for (int ps = 0; ps < positions.length; ps++) {

            //1 find R/C from position 2D
            // e.g pos = {2,4} i.e 2nd row and 4th col
            int row = positions[ps][0];
            int col = positions[ps][1];

            //2 check if  previously visited
            if (visited[row][col] == 1) {
                // since it was visited before current island counter at this positio
                // does not change and recorded in result
                isLandList.add(isLandCounter);
                continue;// move to next position

            }

            //3 mark visited
            visited[row][col] = 1;
            //4 increase counter of island as found 1 island
            isLandCounter++;

            //5 go in each 4 dir to see if this island can be connected to others or not.
            int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

            for (int[] dir : directions) {

                int nextRowNum = row + dir[0];// added dir to go above or below based from cur pos
                int nextColNum = col + dir[1];// added dir to go left  or right based from cur pos

                //just check if not crossing boundary after dir added
                // we could have picked given m or n len but instead pick visited 2D as it same from len prospective.
                if (nextRowNum >= 0 && nextRowNum < visited.length && nextColNum >= 0 && nextColNum < visited[0].length) {

                    // to find connection see if it was visited before and made island
                    if (visited[nextRowNum][nextColNum] == 1) {

                        // if parent of both cell row/col and next row/col is not same
                        // it can be connected as it had never been part of connected island before.
                        // as soon as it connected over all counter decrease since this island is getting
                        // bigger via connection but loosing its individual identity and count as a single island.

                        // now to find parent we need 1D array and to find its position in 1D array we need
                        // to convert 2D position in to 1D via formual
                        // 1D pos = current row * total column + current Col
                        //e.g. for {1,0} in n=4 & m = 5 2D array , 1D position = 1* 5 + 0 = 5th position.

                        int nodeFromCurRowCol = row * colLen + col;
                        int nodeFromNextCurRowCol = nextRowNum * colLen + nextColNum;

                        //if not connected
                        if (dsZ.getUlimateParent(nodeFromCurRowCol) != dsZ.getUlimateParent(nodeFromNextCurRowCol)) {
                            //connect and decrease its counter
                            dsZ.unionbySize(nodeFromCurRowCol, nodeFromNextCurRowCol);
                            isLandCounter--;
                        }
                    }

                }

            }
            // add counter to result list as at this point from current position all 4 dir evaluated.
            isLandList.add(isLandCounter);
        }

        return isLandList;
    }

    public int make_largest_Island(int[][] grid) {

        /*
        Idea:
           - Concept is same as rely on DS Union
           - First find all land by looking and connecting all '1' by going in all 4 dir.
           - this point DS Size and Parent stored for land
           - Now going and pick each zero/water and go in each 4 dir and if that next block is a land
           then get its size and make a total and if that is max total so far then record it.
           but when it goes to next block just use hash set to avoid duplicate block belongs to same parent as it will
           unnecessarily add up twice.
           - Last if there was no water then just go over DsZ and get max size which is max land.

        * */
        //Prep

        int rowLen = grid.length;
        int colLen = grid[0].length;
        // count len of 2D in to 1D and assign to DS as total nodes.
        DisjointUnionBySize dsZ = new DisjointUnionBySize(rowLen * colLen);

        //1 First connect all land '1'  by traversing whole matrix
        for (int rw = 0; rw < rowLen; rw++) {
            for (int col = 0; col < colLen; col++) {

                //only when land found
                if (grid[rw][col] == 1) {

                    //go each dir and try to connect land
                    int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
                    for (int[] dir : directions) {
                        int nextRowNum = rw + dir[0];
                        int nextColNum = col + dir[1];

                        //just check if not crossing boundary after dir added
                        if (nextRowNum >= 0 && nextRowNum < rowLen && nextColNum >= 0 && nextColNum < colLen) {

                            // //only when land found
                            if (grid[nextRowNum][nextColNum] == 1) {

                                //Just connect land and record DS parent/size
                                // to convert 2D position in to 1D via formual
                                // 1D pos = current row * total column + current Col
                                //e.g. for {1,0} in n=4 & m = 5 2D array , 1D position = 1* 5 + 0 = 5th position.

                                int nodeFromCurRowCol = rw * colLen + col;
                                int nodeFromNextCurRowCol = nextRowNum * colLen + nextColNum;
                                dsZ.unionbySize(nodeFromCurRowCol, nodeFromNextCurRowCol);
                            }

                        }

                    }
                }

            }
        }

        //2 Now by traversing whole matrix look for 0 and from that position find max land
        int maxIsLand = 0;

        for (int rw = 0; rw < rowLen; rw++) {
            for (int col = 0; col < colLen; col++) {

                //only when water found
                if (grid[rw][col] == 0) {

                    //go each dir and try to connect land
                    int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
                    HashSet<Integer> uniqueStore = new HashSet<>();

                    for (int[] dir : directions) {
                        int nextRowNum = rw + dir[0];
                        int nextColNum = col + dir[1];

                        //just check if not crossing boundary after dir added
                        if (nextRowNum >= 0 && nextRowNum < rowLen && nextColNum >= 0 && nextColNum < colLen) {

                            // only when land found
                            if (grid[nextRowNum][nextColNum] == 1) {

                                //Just connect land and record DS parent/size
                                // to convert 2D position in to 1D via formual
                                // 1D pos = current row * total column + current Col
                                //e.g. for {1,0} in n=4 & m = 5 2D array , 1D position = 1* 5 + 0 = 5th position.

                                int nodeFromNextCurRowCol = nextRowNum * colLen + nextColNum;

                                //find parent of adjacent cell and put in bucket
                                int ultimateParent = dsZ.getUlimateParent(nodeFromNextCurRowCol);

                                //store in hash set to avid duplicate if next cell /row pos still touching
                                // same block or island which it had touched in previous dir.
                                uniqueStore.add(ultimateParent);
                            }
                        }
                    }
                    // Now calculate size of land and find ma
                    int sizeWithCurrZeroPos = 0;
                    for (var parent : uniqueStore) {
                        sizeWithCurrZeroPos += dsZ.size.get(parent);
                    }
                    //for current water turns in to Land i.e. 0 -> 1 so count 1 for it self.
                    sizeWithCurrZeroPos = sizeWithCurrZeroPos + 1;
                    // record if it is max
                    maxIsLand = Math.max(maxIsLand, sizeWithCurrZeroPos);
                }
            }
        }

        //3 Edge case when there was no 0 present so #2 step will be skipped.
        // Just go over each parent and find max size from DS Unique.

        for (int cellNum = 0; cellNum < rowLen * colLen; cellNum++) {

            int parent = dsZ.getUlimateParent(cellNum);
            int sizeOfParent = dsZ.size.get(parent);
            maxIsLand = Math.max(maxIsLand, sizeOfParent);
        }

        return maxIsLand;
    }

    public int swim_In_RisingWater_TimeIncrease(int[][] grid) {

        /*
        Idea:

        Question has condition that we can only move and work in cell with time increase sequentially e..g 1, 2, 3 etc..
        Now we need to jump from cell to cell postion based on time increase and try to connect other cells with smaller time
        and by doing that we need to make sure 0 i.e. starts reach to end by comparing parent and for that when we find smaller time
        we need to keep connecting cells by DS joint.


        * */
        //Prep
        //edge case
        if (grid.length == 1) return 0;// as it is already there.

        int rowLen = grid.length;
        int colLen = grid[0].length;
        // count len of 2D in to 1D and assign to DS as total nodes.
        DisjointUnionBySize dsZ = new DisjointUnionBySize(rowLen * colLen);


        // Map to store time and related pos for easy jumping.
        HashMap<Integer, int[]> map = new HashMap<>();

        for (int r = 0; r < rowLen; r++) {
            for (int c = 0; c < colLen; c++) {
                int time = grid[r][c];
                int[] position = new int[]{r, c};
                map.put(time, position);
            }
        }

        int time = 0;//start sequentially from 0.

        while (dsZ.getUlimateParent(0) != dsZ.getUlimateParent(rowLen * colLen - 1)) {

            //get row and col from given time.
            int[] pos = map.get(time);
            int rw = pos[0];
            int col = pos[1];

            //go each dir and try to connect land
            int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

            for (int[] dir : directions) {
                int nextRowNum = rw + dir[0];
                int nextColNum = col + dir[1];

                //just check if not crossing boundary after dir added
                if (nextRowNum >= 0 && nextRowNum < rowLen && nextColNum >= 0 && nextColNum < colLen) {

                    //if next cell is min
                    if (grid[nextRowNum][nextColNum] < time) {
                        int nodeFromCurRowCol = rw * colLen + col;
                        int nodeFromNextCurRowCol = nextRowNum * colLen + nextColNum;
                        //connect
                        dsZ.unionbySize(nodeFromCurRowCol, nodeFromNextCurRowCol);
                    }
                }
            }
            time++;// increase sequentially i.e. 1.2.3 etc..
        }


        //after the time T where it connected cells it got incrased to T++
        // and since that cell was last in puzzle to make it start - end connected
        // loop stopped with T++ so result will be T-- to get actual time caputred.
        return --time;
    }

    public Node_G cloneGraph(Node_G node) {

        /*
        Idea:
            -- Use BFS as basic algorithm
            -- Keep building new Object with value given by original object
            -- visited is the way stores org vs cloned objects.
        * */

        //prep
        HashMap<Node_G, Node_G> visited = new HashMap<>();
        LinkedList<Node_G> bfsQ = new LinkedList<>();

        //1 add in Q and mark visited
        bfsQ.add(node);
        visited.put(node, new Node_G(node.val));

        while (!bfsQ.isEmpty()) {

            //2 poll and work
            var orgNode = bfsQ.poll();

            //3 go for each child
            for (var orgChild : orgNode.neighbours) {
                //4 if not visited already then add in Q and mark visited
                if (!visited.containsKey(orgChild)) {
                    bfsQ.add(orgChild);
                    visited.put(orgChild, new Node_G(orgChild.val));
                }
                //add  child to cloned object
                var clonedN1 = visited.get(orgNode);
                var clonedChild = visited.get(orgChild);
                clonedN1.neighbours.add(clonedChild);
            }
        }

        var clonedNode = visited.get(node);
        return clonedNode;
    }


    private List<List<Integer>> prepGraph() {

        /*

         1 -------- 2
         |  \       |
         |    \     |
         |      \   |
        0         3
        |
        |
        4

        * */

        var adjList = new ArrayList<List<Integer>>();

        for (int x = 0; x < 5; x++) {
            adjList.add(new ArrayList<>());
        }


        //0 - 1, 4
        adjList.get(0).add(1);
        adjList.get(0).add(4);

        // 1 - 0 ,2 ,3
        adjList.get(1).add(0);
        adjList.get(1).add(2);
        adjList.get(1).add(3);

        //2- 1
        adjList.get(2).add(1);
        // 3- 1
        adjList.get(3).add(1);

        //4--0
        adjList.get(4).add(0);

        return adjList;

    }

    private ArrayList<ArrayList<Integer>> prepGraph2() {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(1).add(3); //comment this to break cycle.

        adj.get(2).add(1);
        adj.get(2).add(3);

        adj.get(3).add(2);
        adj.get(3).add(1); //comment this to break cycle.

        return adj;

    }

    private ArrayList<ArrayList<Integer>> prepGraph3() {
        int V = 11;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(3).add(7);
        adj.get(4).add(5);
        adj.get(5).add(6);
        adj.get(7).add(5);
        adj.get(8).add(9);
        adj.get(9).add(10);
        adj.get(10).add(8); // COMMENT to break cycle.

        return adj;

    }

    private ArrayList<ArrayList<Integer>> prepGraph4() {


        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(2).add(3);
        adj.get(3).add(1);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(5).add(0);
        adj.get(5).add(2);

        return adj;
    }


    public String alienOrder_WORKED_BUTNOTINUSED(String[] words) {

        /*
       Idea:
       The hard part of this problem is to make Directed Graph from given input and once that is done
       we just need to toposort adn return result

       Given string array is

       baa
       abcd
       abca
       cab
       cad

       we compare pair of words in given list to identify ? -- > ?? who comes before ---> who??
       how to compare? just go each char and when hit diff char @ pos i < char @ pos+1 i..e --> established.

        * */


        //prep

        //1
        //find unique char and its count
        HashSet<Character> uniqueChars = new HashSet<>();
        for (var wd : words) {
            for (var ch : wd.toCharArray()) {
                uniqueChars.add(ch);
            }
        }

        //2
        //for each char prep adj list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int x = 0; x < uniqueChars.size(); x++) {
            adjList.add(new ArrayList<>());
        }

        // Now build DS graph aka DS adjList with which comes >> before >>> which
        // treat char as integer vai ch-'a' so that it behaves like we got int[][] graph
        for (int w = 0; w < words.length - 1; w++) {
            //gets first pair
            String s1 = words[w];
            String s2 = words[w + 1];

            // compare via checking each char .. always pick smaller len to go
            int len = Math.min(s1.length(), s2.length());
            int pt = 0;
            while (pt < len) {
                //compare each char.
                char ch1 = s1.charAt(pt);
                char ch2 = s2.charAt(pt);

                //when not matched.
                if (ch1 != ch2) {
                    adjList.get(ch1 - 'a').add(ch2 - 'a'); // making b->a but with int conversion via - 'a'
                    break;//stop and move to next pair
                }

                pt++;
            }

        }

        //NOW Routine Kahn Topo Sort algo to find u --> v.
        int[] incomingEdges = new int[uniqueChars.size()];
        for (int node = 0; node < adjList.size(); node++) {
            for (int ch : adjList.get(node)) {
                incomingEdges[ch]++;
            }
        }

        Queue<Integer> qu = new LinkedList<>();
        for (int i = 0; i < incomingEdges.length; i++) {
            if (incomingEdges[i] == 0) {
                qu.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!qu.isEmpty()) {
            int vtx = qu.poll();
            char orderCh = (char) (vtx + 'a');
            sb.append(orderCh);

            for (int ch : adjList.get(vtx)) {
                incomingEdges[ch]--; // reduce by 1
                if (incomingEdges[ch] == 0) {
                    qu.offer(ch);// put in qu
                }
            }
        }

        return sb.toString();
    }


    public int swim_In_RisingWater_MinPath_Idea_NOTWorking(int[][] grid) {

        /*
        Idea:

        I tried to go with Min path to reach from start 0 to last position and stop when both start and end parent matches
        this works with many use case but question is not what it asked see actual solution.

        * */
        //Prep

        int rowLen = grid.length;
        int colLen = grid[0].length;
        // count len of 2D in to 1D and assign to DS as total nodes.
        DisjointUnionBySize dsZ = new DisjointUnionBySize(rowLen * colLen);
        boolean[][] vst = new boolean[rowLen][colLen];

        int rw = 0;
        int col = 0;
        int time = 0;

        for (int r = 0; r < rowLen; r++) {
            for (int c = 0; c < colLen; c++) {
                if (grid[r][c] == 0) {
                    rw = r;
                    col = c;
                    break;
                }
            }
        }

        int startNodeFromRowCol = rw * colLen + col;

        while (dsZ.getUlimateParent(0) != dsZ.getUlimateParent(rowLen * colLen - 1)) {


            //go each dir and try to connect land
            int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            int minRow = 1000;
            int minCol = 1000;
            int minTime = 1000;

            for (int[] dir : directions) {
                int nextRowNum = rw + dir[0];
                int nextColNum = col + dir[1];

                //just check if not crossing boundary after dir added
                if (nextRowNum >= 0 && nextRowNum < rowLen && nextColNum >= 0 && nextColNum < colLen && !vst[nextRowNum][nextColNum]) {

                    //if next cell is min
                    if (grid[nextRowNum][nextColNum] < minTime) {

                        int nodeFromCurRowCol = rw * colLen + col;
                        int nodeFromNextCurRowCol = nextRowNum * colLen + nextColNum;

                        int ultPtCurr = dsZ.getUlimateParent(nodeFromCurRowCol);
                        int ultPtNext = dsZ.getUlimateParent(nodeFromNextCurRowCol);

                        // and not already connected
                        if (ultPtCurr != ultPtNext) {

                            //then caputre it.
                            minTime = grid[nextRowNum][nextColNum];
                            minRow = nextRowNum;
                            minCol = nextColNum;

                        }
                    }
                }

            }

            ///then connect it
            int nodeFromCurRowCol = rw * colLen + col;
            int nodeFromNextCurRowCol = minRow * colLen + minCol;
            dsZ.unionbySize(nodeFromCurRowCol, nodeFromNextCurRowCol);
            vst[minRow][minCol] = true;


            rw = minRow;
            col = minCol;
            time = Math.max(time, minTime);
        }
        return time;
    }
}

