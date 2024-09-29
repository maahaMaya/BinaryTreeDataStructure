package preoder;

public class BinaryTreePreOrder {
	
	static class Node{
		int data;
		Node left;
		Node right;
		
		Node(int data){
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	
	static class BinaryTree {
		static int index = -1;
		public static Node buildTree(int nodes[]) {
			index++;
			if(nodes[index] == -1) {
				return null;
			}
			
			Node newNode = new Node(nodes[index]);
			newNode.left = buildTree(nodes);
			newNode.right = buildTree(nodes);
			
			return newNode;
		}
	}

	public static void main(String[] args) {
		
		int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
		
		BinaryTree binaryTree = new BinaryTree();
		Node root =  binaryTree.buildTree(nodes);
		
		System.out.println("Root is " + root.data);
		System.out.println("Root left child is  " + root.left.data);
		System.out.println("Root right child is  " + root.right.data);
		
		System.out.println("Binary Tree Index is  " + binaryTree.index);

	}

}
