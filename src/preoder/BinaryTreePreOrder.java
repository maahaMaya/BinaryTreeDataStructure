package preoder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreePreOrder {

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}

	static class BinaryTree {
		static int index = -1;

		public static Node buildTree(int nodes[]) {
			index++;
			if (nodes[index] == -1) {
				return null;
			}

			Node newNode = new Node(nodes[index]);
			newNode.left = buildTree(nodes);
			newNode.right = buildTree(nodes);

			return newNode;
		}

		public static void printPreOrder(Node root) {
			if (root == null) {
				// System.out.print("-1, ");
				return;
			}

			System.out.print(root.data + " , ");
			printPreOrder(root.left);
			printPreOrder(root.right);
		}

		public static void printInOrder(Node root) {
			if (root == null) {
				System.out.print("-1, ");
				return;
			}

			printPreOrder(root.left);
			System.out.print(root.data + " , ");
			printPreOrder(root.right);
		}

		public static void printPostOrder(Node root) {
			if (root == null) {
				System.out.print("-1, ");
				return;
			}

			printPreOrder(root.left);
			printPreOrder(root.right);
			System.out.print(root.data + " , ");
		}

		public static void printLevelOrder(Node root) {
			if (root == null) {
				return;
			}

			Queue<Node> que = new LinkedList<BinaryTreePreOrder.Node>();
			que.add(root);
			que.add(null);

			while (!que.isEmpty()) {
				Node currNode = que.remove();
				if (currNode == null) {
					System.out.println();
					if (que.isEmpty()) {
						break;
					} else {
						que.add(null);
					}
				} else {
					System.out.print(currNode.data + " ");
					if (currNode.left != null) {
						que.add(currNode.left);
					}
					if (currNode.right != null) {
						que.add(currNode.right);
					}
				}
			}
			
		}
		
		public static int treeHeight(Node root) {
			if(root == null) {
				return 0;
			}
			
			int leftChildHeight = treeHeight(root.left);
			int rightChildHeight = treeHeight(root.right);
			return Math.max(leftChildHeight, rightChildHeight) + 1;
		}
		
		public static int treeNodeSum(Node root) {
			if(root == null) {
				return 0;
			}
			
			int leftChildSum = treeNodeSum(root.left);
			int rightChildSum = treeNodeSum(root.right);
			return leftChildSum + rightChildSum + root.data;
		}
		
		public static int treeDiameter(Node root) { // O(n*2)
			if(root == null) {
				return 0;
			}
			
			int leftDiameter = treeDiameter(root.left);
			int leftHeight = treeHeight(root.left);
			int rightDiameter = treeDiameter(root.right);
			int rightHeight = treeHeight(root.right);
			
			int selfDiameter = leftHeight + rightHeight + 1;
			return Math.max(selfDiameter, Math.max(rightDiameter, rightHeight));
		}
		
		static class InfoTree{
			int treeDiameter;
			int treeHeight;
			
			public InfoTree(int treeDiameter, int treeHeight) {
				this.treeDiameter = treeDiameter;
				this.treeHeight = treeHeight;
			}
		}
		public static InfoTree treeDiameterOptimized(Node root) { // O(n*2)
			if(root == null) {
				return new InfoTree(0, 0);
			}
			
			InfoTree leftTreeInfo = treeDiameterOptimized(root.left);
			InfoTree rightTreeInfo = treeDiameterOptimized(root.right);
			
			int treeDiameter = Math.max(Math.max(leftTreeInfo.treeDiameter, rightTreeInfo.treeDiameter), leftTreeInfo.treeHeight + rightTreeInfo.treeHeight + 1);
			int treeHeight = Math.max(leftTreeInfo.treeHeight, rightTreeInfo.treeHeight) + 1;
			return new InfoTree(treeDiameter, treeHeight);
		}
		
		static class TreeInfo{
			Node node;
			int hd;
			
			public TreeInfo(Node node, int hd) {
				this.node = node;
				this.hd = hd;
			}
		} 
		
		public static void treeTopView(Node node) {
			//Level Order
			Queue<TreeInfo> q = new LinkedList<>();
			HashMap<Integer, Node> map = new HashMap<>();
			
			int min = 0, max = 0;
			q.add(new TreeInfo(node, 0));
			q.add(null);
			
			while(!q.isEmpty()) {
				TreeInfo cuurentTreeInfo = q.remove();
				if(cuurentTreeInfo == null) {
					if(q.isEmpty()) {
						break;
					}else {
						q.add(null);
					}
				}
				else {
					if(!map.containsKey(cuurentTreeInfo.hd)) {
						map.put(cuurentTreeInfo.hd, cuurentTreeInfo.node);
					}
					if(cuurentTreeInfo.node.left != null) {
						q.add(new TreeInfo(cuurentTreeInfo.node.left, cuurentTreeInfo.hd-1));
						min = Math.min(max, cuurentTreeInfo.hd-1);
					}
					if(cuurentTreeInfo.node.right != null) {
						q.add(new TreeInfo(cuurentTreeInfo.node.right, cuurentTreeInfo.hd+1));
						max = Math.max(max, cuurentTreeInfo.hd+1);
					}
				}
			}
			
			for(int i = min; i <= max; i++) {
				System.out.print(map.get(i).data+" ");
			}
			System.out.println();
		}
	}
	
	


	public static void main(String[] args) {

		int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
		
		
		/* 
		 *           1
		 *          / \
		 *         2   3
		 *        / \   \   
		 * 		 4   5   6
		 */

		BinaryTree binaryTree = new BinaryTree();
		Node root = binaryTree.buildTree(nodes);

		System.out.println("Root is " + root.data);
		System.out.println("Root left child is  " + root.left.data);
		System.out.println("Root right child is  " + root.right.data);

		System.out.println("Binary Tree Index is  " + binaryTree.index);

		System.out.print("Pre Order : ");
		binaryTree.printPreOrder(root);

		System.out.println();

		System.out.print("In Order : ");
		binaryTree.printInOrder(root);

		System.out.println();

		System.out.print("Post Order : ");
		binaryTree.printPostOrder(root);

		System.out.println();

		System.out.println("Level Order : ");
		binaryTree.printLevelOrder(root);
		
		System.out.println();

		System.out.println("Tree Height : " + binaryTree.treeHeight(root));
		
		System.out.println("Tree Node Sum : " + binaryTree.treeNodeSum(root));
		
		System.out.println("Tree Diameter is O(n*2): " + binaryTree.treeDiameter(root));
		
		System.out.println("Tree Diameter is O(n): " + binaryTree.treeDiameterOptimized(root).treeDiameter);
		
		binaryTree.treeTopView(root);
	}

}
