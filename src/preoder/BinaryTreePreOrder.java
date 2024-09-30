package preoder;

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

	}

}
