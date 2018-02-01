import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;
import java.util.Scanner;
import java.util.Collection;

class Neighbor {
	public int vertexNum;
	public String edge;//Edge between the nodes
	public Neighbor next;
	public Neighbor(int vnum,Neighbor nxt, String ch){
		this.vertexNum=vnum;
		this.next=nxt;
		this.edge=ch;
	}
}
class Vertex{
	String name;//Vertex name
	Neighbor adjList;
	public Vertex(String nm, Neighbor ng){
		this.name=nm;
		this.adjList=ng;
	}
}	
public class DFS{
	Vertex[] adjLists;
	int[] parents;
	String[] paths;
	boolean undirected;
	public DFS(String file) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(file));
		 String graphType = sc.next();
		 undirected=true;
	        if (graphType.equals("directed")) {
	            undirected=false;
	        }
	        int z=sc.nextInt();
	   adjLists = new Vertex[z];
	   parents= new int[z];
	   paths= new String[z];
	   for (int v=0; v < adjLists.length; v++) {
           adjLists[v] = new Vertex(sc.next(), null);
       }
	   while (sc.hasNext()) {
           
           // read vertex names and translate to vertex numbers
           int v1 = indexForName(sc.next());
           int v2 = indexForName(sc.next());
           String ed=sc.next();
            
           // add v2 to front of v1's adjacency list and
           // add v1 to front of v2's adjacency list
           adjLists[v1].adjList = new Neighbor(v2, adjLists[v1].adjList,ed);
           if (undirected) {
               adjLists[v2].adjList = new Neighbor(v1, adjLists[v2].adjList,ed);
           }
       }   
	}
	
	
	int indexForName(String name) {
        for (int v=0; v < adjLists.length; v++) {
            if (adjLists[v].name.equals(name)) {
                return v;
            }
        }
        return -1;
    } 
	
	
	 private void dfs(int v, boolean[] visited) {
	        visited[v] = true;
	        System.out.println("visiting " + adjLists[v].name);
	        for (Neighbor nbr=adjLists[v].adjList; nbr != null; nbr=nbr.next) {
	            if (!visited[nbr.vertexNum]) {
	            	parents[nbr.vertexNum]=v;
	            	paths[nbr.vertexNum]=nbr.edge;
	                System.out.println("\n" + adjLists[v].name + "--" + adjLists[nbr.vertexNum].name+"--edge---"+nbr.edge);
	                dfs(nbr.vertexNum, visited);
	            }
	        }
	    }
	 
	 
	 public void dfs() {
	        boolean[] visited = new boolean[adjLists.length];
	        for(int i=0;i<parents.length;i++)
	              parents[i]=Integer.MAX_VALUE;
	        for (int v=0; v < visited.length; v++) {
	            if (!visited[v]) {
	                System.out.println("\nSTARTING AT " + adjLists[v].name);
	                dfs(v, visited);
	            }
	        }
	    }
	 
	 
	 public void print() {
	        System.out.println();
	        for (int v=0; v < adjLists.length; v++) {
	            System.out.print(adjLists[v].name);
	            for (Neighbor nbr=adjLists[v].adjList; nbr != null;nbr=nbr.next) {
	                System.out.print(" --> " + adjLists[nbr.vertexNum].name);
	            }
	            System.out.println("\n");
	        }
	    }
	 public void path(String a,String b){
		 int v1=indexForName(a);
		 int v2=indexForName(b);
		 int z=v2;
		 Stack<String> st=new Stack();
		 boolean pathExist=false;
		 for(int i=0;i<parents.length;i++)
		 { System.out.println(parents[i]);
		   System.out.println(paths[i]);
		 }
		 while(z!=Integer.MAX_VALUE ||z!=v1){
			 if(parents[z]==Integer.MAX_VALUE){
				 break;
			 }
			 else
				 z=parents[z];
			 if(z==v1){
				 System.out.println("There is a path between "+a+" and " + b);
				 pathExist=true;
				 break;
			 }
			 if(z==Integer.MAX_VALUE){
				 System.out.println("There is no path between "+a+" and "+ b);
			 }
		 }
		 int k=v2;
		 if(pathExist==true){
			 System.out.println("The reverse Edges are:");
			 while(k!=v1){
				 System.out.println(paths[k]);
				 st.push(paths[k]);
				 k=parents[k];
			 }
			 
			 
		 }
		 System.out.println("The value of the path exist is:"+pathExist);
		 if(pathExist==true){
			 while(!st.empty()){
				 System.out.println(st.pop());
			 }
		 }
		 }
		 	 
	 
	 
	   public static void main(String[] args) 
	    throws IOException {
	        // TODO Auto-generated method stub
	        Scanner sc = new Scanner(System.in);
	        System.out.print("Enter graph input file name: ");
	        String file = sc.nextLine();
	        DFS graph = new DFS(file);
	        System.out.println("The Graph Information is");
	        graph.print();
	        System.out.println("The DFS Algorithm");
	        graph.dfs();
	        graph.path("A","N");
	    }
	 
	 
}

  
