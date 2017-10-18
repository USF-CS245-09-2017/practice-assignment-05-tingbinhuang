import java.util.*;

/**
 * BSTree class which contains add,remove,insert methods as well as inner
 * class,Node.
 * 
 * @author TingbinHuang CS 245,section 1, practice assignment 5.
 *
 */

public class BSTree {

	/**
	 * inner class Node which initializes Node for the tree.
	 * 
	 */
	class Node {
		public String data;
		Node root;
		Node left;
		Node right;

		public Node(String data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	/**
	 * initialize a class member Node 'root' and arrayList arr;
	 */
	private Node root;
	private ArrayList<String> arr_inOrder;
	private ArrayList<String> arr_preOrder;

	/**
	 * Constructor to initialize BSTree class.
	 */
	public BSTree() {
		root = null;
		arr_inOrder = new ArrayList<String>();
		arr_preOrder = new ArrayList<String>();
	}

	/**
	 * insert item to the tree
	 * 
	 * @param String
	 *            item
	 */
	public void insert(String item) {

		if (root == null) {
			root = new Node(item);
			return;
		}

		Node newNode = new Node(item);
		Node temp = root;
		boolean flag = true;

		while (flag) {
			if (temp.data.compareTo(item) >= 0) {
				if (temp.left == null) {
					temp.left = newNode;
					flag = false;
				}
				temp = temp.left;

			} else {
				if (temp.right == null) {
					temp.right = newNode;
					flag = false;
				}
				temp = temp.right;
			}
		}

	}

	/**
	 * look through tree to see if it has the target.
	 * 
	 * @param String
	 *            target
	 * @return true if target is found false otherwise.
	 */
	public boolean find(String target) {
		Node temp = root;
		while (temp != null) {
			if (temp.data.compareTo(target) == 0) {
				return true;
			} else if (temp.data.compareTo(target) > 0) {
				temp = temp.left;
			} else {
				temp = temp.right;
			}
		}
		return false;
	}

	/**
	 * delete target node in the tree.
	 * 
	 * @param String
	 *            target
	 */
	public void delete(String target) {
		Node pre_node = null;
		Node next_node = null;
		Node parents = null;

		Node temp_root = root;

		// if root equals to target.
		if (root.data.compareTo(target) == 0) {

			if (root.left != null) {
				Node tp = temp_root.right;
				root = temp_root.left;
				temp_root = root;
				while (temp_root.right != null) {
					temp_root = temp_root.right;
				}
				temp_root.right = tp;

			} else {
				if (root.right == null) {
					root = null;
				} else {
					root = root.right;
					root.left = null;
				}

			}

			return; // break and return.
		}

		// when target node does not equal to root.
		while (temp_root != null) {

			// if target node is at the left side.
			if (temp_root.data.compareTo(target) > 0) {

				while (temp_root.left != null) {
					pre_node = temp_root;
					temp_root = temp_root.left;
					if (temp_root.data == target) {
						next_node = temp_root.left;
						if (temp_root.right != null) {

							temp_root = temp_root.right;
							parents = temp_root;

							while (temp_root.left != null) {
								temp_root = temp_root.left;
							}
							pre_node.left = parents;
							temp_root.left = next_node;

						} else {
							pre_node.left = next_node;
						}

						return; // break and return.

					}
				}
			}
			// if target node is at the right side.
			else {
				while (temp_root.right != null) {
					pre_node = temp_root; // save the previous node
					temp_root = temp_root.right;
					if (temp_root.data == target) {
						next_node = temp_root.right; // save the next node

						if (temp_root.left != null) {

							temp_root = temp_root.left;

							parents = temp_root;

							while (temp_root.right != null) {
								temp_root = temp_root.right;
							}
							pre_node.right = parents;
							temp_root.right = next_node;
						}

						else {
							pre_node.right = next_node;
						}

						return; // break and return.
					}

				}
			}
		}

	}

	/**
	 * print root.
	 */
	public void printRoot() {
		System.out.println("The root: " + root.data);
	}

	/**
	 * print an in-order tree.
	 */
	public String toStringInOrder() {
		String result = "";
		String space = " ";

		// Call 'printInOrder' function
		printInOrder(root);

		for (int i = 0; i < arr_inOrder.size(); i++) {
			// space will be "" when i = size() - 1.
			space = i == arr_inOrder.size() - 1 ? "" : " ";
			result += arr_inOrder.get(i) + space;
		}

		return result;
	}

	/**
	 * print an pre-oder tree.
	 * 
	 */
	public String toStringPreOrder() {
		String result = "";
		String space = " ";

		// Call 'printPreOrder' function
		printPreOder(root);

		for (int i = 0; i < arr_preOrder.size(); i++) {
			// space will be "" when i = size() - 1.
			space = i == arr_preOrder.size() - 1 ? "" : " ";
			result += arr_preOrder.get(i) + space;
		}
		return result;
	}

	/*****************************************************************/
	/**
	 * Helper methods for 'toStringInOrder()' and 'toStringPreOrder()'.
	 */

	/**
	 * print an in-order tree with root of tree recursively.
	 * 
	 * @param root
	 */
	public void printInOrder(Node root) {
		if (root == null) {
			return;
		}

		printInOrder(root.left);

		arr_inOrder.add(root.data);

		printInOrder(root.right);

	}

	/**
	 * print an pre-order tree with root of tree recursively.
	 * 
	 * @param root
	 */
	public void printPreOder(Node root) {
		if (root == null) {
			return;
		}

		arr_preOrder.add(root.data);

		printPreOder(root.left);

		printPreOder(root.right);

	}

}
