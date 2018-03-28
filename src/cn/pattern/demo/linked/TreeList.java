package cn.pattern.demo.linked;

/**
 * 二叉树优点：1.有序数组；2.链表。 数值大的在放右边，小的放左边
 *
 */

public class TreeList {
	public static void main(String[] args) {
		TreeList tl = new TreeList();
		tl.insertNode(1);
		tl.insertNode(21);
		tl.insertNode(23);
		tl.insertNode(14);
		tl.insertNode(5);
		tl.insertNode(46);
		tl.insertNode(27);
		tl.insertNode(18);
		tl.insertNode(79);
		tl.insertNode(10);
		tl.inorderTraversal();
	}

	TreeNode root;

	// 插入结点
	public synchronized void insertNode(int insertValue) {
		if (root == null) {
			root = new TreeNode(insertValue);
		} else {
			root.insert(insertValue);
		}
	}

	// 前序遍历
	public synchronized void preorderTraversal() {
		preorderHepler(root);
	}

	private void preorderHepler(TreeNode node) {
		if (node == null) {
			return;
		}
		System.out.println(node.data + " ");
		preorderHepler(node.leftNode);
		preorderHepler(node.rightNode);
	}

	// 中序遍历 值从小到大显示
	public synchronized void inorderTraversal() {
		inorderHepler(root);
	}

	private void inorderHepler(TreeNode node) {
		if (node == null) {
			return;
		}
		inorderHepler(node.leftNode);
		System.out.println(node.data + " ");
		inorderHepler(node.rightNode);
	}
}

class TreeNode {
	TreeNode leftNode;
	TreeNode rightNode;
	int data;

	public TreeNode() {
		super();
	}

	public TreeNode(int nodeData) {
		this.data = nodeData;
		this.leftNode = this.rightNode = null;
	}

	public synchronized void insert(int insertValue) {
		if (insertValue < data) {
			if (leftNode == null) {
				leftNode = new TreeNode(insertValue);
			} else {
				leftNode.insert(insertValue);
			}
		} else if (insertValue > data) {
			if (rightNode == null) {
				rightNode = new TreeNode(insertValue);
			} else {
				rightNode.insert(insertValue);
			}
		}
	}
}