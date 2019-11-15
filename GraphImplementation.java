import java.util.*;

public class GraphImplementation implements Graph
{
	//initialize the matrix and vertexes for the graph
	int[][]matrix;
	int vertexes;
	
	public GraphImplementation(int v)
	{
		vertexes = v;
		matrix = new int[vertexes][vertexes];
	}
	//adds an edge to the graph
	public void addEdge(int v1, int v2) throws Exception
	{
		//if invalid value, then it throws an exception
		if(v1 >= matrix.length || v1 < 0 || v2 >= matrix.length || v2 < 0)
		{
			throw new Exception("Out of Bounds Exception"); //out of bounds
		}
		matrix[v1][v2] = 1; 
	}

	public List<Integer> topologicalSort()
	{
		//creates a list
		List<Integer> schedule = new ArrayList<Integer>();
		int[] totalAmount = new int[matrix.length];
		//gets the totalAmount from the matrix per column
		for(int i = 0; i < vertexes; i++)
			for(int j = 0; j < vertexes; j++)
				totalAmount[i]+=matrix[j][i];

		//will loop through the matrix until every single vertex is in
		for(int i=0; i<vertexes; i++)
		{
			int n = findZero(totalAmount);
			if(n == -1)
				return schedule;
		
			schedule.add(n);
			totalAmount[n] = -1;

			for(int j = 0; j < vertexes; j++)
				totalAmount[j] -= matrix[n][j];
		
		}
		return schedule;
	}
	
	//finds all 0s
	private int findZero(int[] totalAmount) {


		for(int i = 0; i < vertexes; i++)

			if(totalAmount[i] == 0)
				return i;
		
		return 0;
	}
	
	public List<Integer> neighbors(int vertex) throws Exception
	{
		//Error Handling 
		if(vertex >= vertexes || vertex < 0)
			throw new Exception("Out of Bounds Exception");

		//makes neighbor list
		List<Integer> neighbors = new ArrayList<Integer>();
		//loops through each row iteratively 
		for(int i = 0; i < vertexes; i++)
			if(matrix[vertex][i] > 0)
				neighbors.add(i);

		return neighbors;
	}
}