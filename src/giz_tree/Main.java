package giz_tree;


public class Main {
	//	static int[][] arr = { 
	//			{ 1, 0 }, 
	//			{ 2, 1 }, 
	//			{ 3, 1 }, 
	//			{ 4, 1 }, 
	//			{ 5, 2 }, 
	//			{ 6, 2 }, 
	//			{ 7, 3 }, 
	//			{ 8, 3 }, 
	//			{ 9, 4 },
	//			{ 10, 7}};
	public static void treeParser(int[][] arr, TNode node){
		for (int i = 0; i < arr.length; i++) {

			if (arr[i][1] == node.id) {
				TNode tmpNode = new TNode();
				tmpNode.id = arr[i][0];
				node.add(tmpNode);
				treeParser(arr ,tmpNode);
			}
			
		}
	}
	public static TNode treeMaker(int[][] arr){
		TNode rootNode = null;
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][1] == 0){
				int rootInd = arr[i][0];
				rootNode = new TNode();
				rootNode.id = rootInd;
				System.out.println(">> We found the root");
			}
		}
		treeParser(arr, rootNode); //parsing array and find childs and childs of childs
		
		return rootNode;
	}
	////////////////////////_M A I N_///////////////////////////////
	public static void main(String[] args) {
		System.out.println(">> Start");
		
		MyFileReader fr = new MyFileReader("src/giz_tree/tree.txt");
		String text = fr.getString();
		MyTextParser tp = new MyTextParser(text);
		
		int[][]arr = tp.arr; // now we have array from the file
		TNode root = treeMaker(arr);
		WebServer ws = new WebServer(root, 8888); //you can chenge the port!!! new WebServer(globalForest, 8181)
	    Thread thread = new Thread(ws);
	    
		System.out.println(">> The array is parsed into TreeNodes [TNode]");
		System.out.println(">> Tree depth : " + root.depth());
		System.out.println(">> Tree max ID : " + root.maxId());
		System.out.println(">> Tree min ID : " + root.minId());
		System.out.println(">> Tree Node ID (with min childs) : " + root.minChildNode().id );
		

	    thread.start();
		
	}

}
