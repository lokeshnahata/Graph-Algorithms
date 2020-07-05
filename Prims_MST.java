/*Name - Lokesh Kumar Nahata
 * Registration - 1841017158
 *  Prims algorithm in Java
 *  Date - 04/07/2020
 */

import java.util.*; 
 
public class Prims_MST { 
 
	private int size;
	private int adjMatrix[][];
 
	Prims_MST(int s){
	    this.size = s;
	    adjMatrix = new int[size][size];
	}
 
    public int[][] getAdjMatrix(){
        return adjMatrix;
    }
 
	// adds a new edge to the graph 
	public void addEdge(int i, int j, int weighted) { 
		adjMatrix[i][j] = weighted;
        adjMatrix[j][i] = weighted;
	} 
 
	// remove a edge to the graph 
	public void removeEdge(int i, int j) { 
		adjMatrix[i][j] = -1;
        adjMatrix[j][i] = -1;
	} 
	
 
	// get graph size
	public int size() { 
	    return this.size;
	} 
 
	// Displays the adjancency matrix of graph. 
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(i + ": ");
            
            for (int j : adjMatrix[i]) {
                s.append((j) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    } 
    
    //find the vertex with minimum weight which is not included in MST
    int getMinVertexIndex(Boolean [] mstArray, int [] indexArray){
        int minIndex = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i <size ; i++) {
            if(mstArray[i]==false && minIndex>indexArray[i]){
                minIndex = indexArray[i];
                index = i;
            }
        }
        return index;
    }    
    // Method create a MST of graph, 
    //it accepts a graph in adjacency matrix.
    // 2d array matrix is only contain values that is not zero
    int[] primMST(int matrix[][]) 
    { 
        //Store Minimum Spanning Tree (MST)
        int resultMst[] = new int[size]; 
  
        //Store minimum weighted value of vertices
        int wData[] = new int[size]; 
  
        //Array marks a vertex in MST (parrent array above) or not.
        Boolean mstArray[] = new Boolean[size]; 
  
        // Initialize all data of vertices as INFINITE 
        // and elements of weighted array to false
        for (int i = 0; i < size; i++) { 
            wData[i] = Integer.MAX_VALUE; 
            mstArray[i] = false; 
        } 
  
        // Put first vertex into array and it is considered is root node
        // so its weighted data is set to 0 
        //and it doesn't have any parent then set its parent = -1
        wData[0] = 0; 
        resultMst[0] = -1;
  
        // Create MST, obviously MST's size is equal to graph's size
        for (int i = 0; i < size - 1; i++) { 
            // Pick thd minimum vertex from the set of vertices 
            // not yet included in MST 
            int minIdex = getMinVertexIndex(mstArray, wData); 
            
            // Add the picked vertex to the MST Set 
            mstArray[minIdex] = true; 
  
            // Update key value and parent index of the adjacent 
            // vertices of the picked vertex. Consider only those 
            // vertices which are not yet included in MST 
            for (int v = 0; v < size; v++) 
                // matrix[minIdex][v] is not zero
                // only process vertex that is not put in mstArray
                // Update resultMst and wData if matrix[minIdex][v] is smaller than wData[v] 
                if (matrix[minIdex][v] != 0 && mstArray[v] == false && matrix[minIdex][v] < wData[v]) { 
                    resultMst[v] = minIdex; 
                    wData[v] = matrix[minIdex][v]; 
                }
        } 
 
        return resultMst;
    }     
    public static void main(String args[]) { 
    	 
		// create new graph instance
		Prims_MST g = new Prims_MST(4);
		// the create an array of vertices
		int[] vertices = new int[g.size()];
		vertices[0] = 20;
		vertices[1] = 30;
		vertices[2] = 10;
		vertices[3] = 40;
		
		// sort vertices array
		// then use binary search algorthim to get the index of vertices
		Arrays.sort(vertices);
		System.out.println("Sorted vertices array: " +Arrays.toString(vertices));
		int index20 = Arrays.binarySearch(vertices,20);
		int index10 = Arrays.binarySearch(vertices,10);
		int index30 = Arrays.binarySearch(vertices,30);
		int index40 = Arrays.binarySearch(vertices,40);
 
        //Now we create a graph from the corresponding indexes of vertices,
		// edges are added. 
		g.addEdge(index10, index20,3); 
		g.addEdge(index10, index40,1); 
		g.addEdge(index20, index30,4); 
		g.addEdge(index20, index40,2); 
		g.addEdge(index30, index40,6); 
 
		// print the graph. 
		System.out.println("Here is the graph:\n" + g.toString()); 
        int[][] graph = g.getAdjMatrix();
		int[] mst = g.primMST(g.getAdjMatrix());
		
		System.out.println("Edge(vertex<->vertex) \t\tWeight"); 
		for (int i = 1; i < g.size(); i++) 
			System.out.println("\t"+vertices[mst[i]] + " - " + vertices[i] + "\t\t\t" + graph[i][mst[i]]);
		
	} 
} 
 


