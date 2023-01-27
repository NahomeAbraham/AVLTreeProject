public class AVLTree extends BST {
  /** Create a default AVL tree */
  public AVLTree() {
  }

  /** Create an AVL tree from an array of objects */
  public AVLTree(BookObject[] objects) {
    super(objects);
  }

  @Override /** Override createNewNode to create an AVLTreeNode */
  protected AVLTreeNode createNewNode(BookObject o) {
    return new AVLTreeNode(o);
  }

  @Override /** Insert an element and rebalance if necessary */
  public boolean insert(BookObject o) {
    boolean successful = super.insert(o);
    if (!successful)
      return false; // o is already in the tree
    else {
      balancePath(o); // Balance from o to the root if necessary
    }

    return true; // o is inserted
  }

  /** Update the height of a specified node */
  private void updateHeight(AVLTreeNode node) {
    if (node.left == null && node.right == null) // node is a leaf
      node.height = 0;
    else if (node.left == null) // node has no left subtree
      node.height = 1 + ((AVLTreeNode)(node.right)).height;
    else if (node.right == null) // node has no right subtree
      node.height = 1 + ((AVLTreeNode )(node.left)).height;
    else
      node.height = 1 +
        Math.max(((AVLTreeNode )(node.right)).height,
        ((AVLTreeNode )(node.left)).height);
  }

  /** Balance the nodes in the path from the specified
   * node to the root if necessary
   */
  private void balancePath(BookObject o) {
    java.util.ArrayList<TreeNode > path = path(o);
    for (int i = path.size() - 1; i >= 0; i--) {
      AVLTreeNode  A = (AVLTreeNode )(path.get(i));
      updateHeight(A);
      AVLTreeNode  parentOfA = (A == root) ? null :
        (AVLTreeNode )(path.get(i - 1));

      switch (balanceFactor(A)) {
        case -2:
          if (balanceFactor((AVLTreeNode )A.left) <= 0) {
            balanceLL(A, parentOfA); // Perform LL rotation
          }
          else {
            balanceLR(A, parentOfA); // Perform LR rotation
          }
          break;
        case +2:
          if (balanceFactor((AVLTreeNode )A.right) >= 0) {
            balanceRR(A, parentOfA); // Perform RR rotation
          }
          else {
            balanceRL(A, parentOfA); // Perform RL rotation
          }
      }
    }
  }

  /** Return the balance factor of the node */
  private int balanceFactor(AVLTreeNode  node) {
    if (node.right == null) // node has no right subtree
      return -node.height;
    else if (node.left == null) // node has no left subtree
      return +node.height;
    else
      return ((AVLTreeNode )node.right).height -
        ((AVLTreeNode )node.left).height;
  }

  /** Balance LL (see Figure 9.1) */
  private void balanceLL(TreeNode  A, TreeNode  parentOfA) {
    System.out.println("Imbalance condition occurred at inserting ISBN " + A.element + "; fixed in Left Left Rotation");
    TreeNode  B = A.left; // A is left-heavy and B is left-heavy

    if (A == root) {
      root = B;
    }
    else {
      if (parentOfA.left == A) {
        parentOfA.left = B;
      }
      else {
        parentOfA.right = B;
      }
    }

    A.left = B.right; // Make T2 the left subtree of A
    B.right = A; // Make A the left child of B
    updateHeight((AVLTreeNode )A);
    updateHeight((AVLTreeNode )B);
  }

  /** Balance LR (see Figure 9.1(c)) */
  private void balanceLR(TreeNode  A, TreeNode  parentOfA) {
    System.out.println("Imbalance condition occurred at inserting ISBN " + A.element + "; fixed in Left Right Rotation");

    TreeNode  B = A.left; // A is left-heavy
    TreeNode  C = B.right; // B is right-heavy

    if (A == root) {
      root = C;
    }
    else {
      if (parentOfA.left == A) {
        parentOfA.left = C;
      }
      else {
        parentOfA.right = C;
      }
    }

    A.left = C.right; // Make T3 the left subtree of A
    B.right = C.left; // Make T2 the right subtree of B
    C.left = B;
    C.right = A;

    // Adjust heights
    updateHeight((AVLTreeNode )A);
    updateHeight((AVLTreeNode )B);
    updateHeight((AVLTreeNode )C);
  }

  /** Balance RR (see Figure 9.1(b)) */
  private void balanceRR(TreeNode  A, TreeNode  parentOfA) {
    System.out.println("Imbalance condition occurred at inserting ISBN " + A.element + "; fixed in Right Right Rotation");

    TreeNode  B = A.right; // A is right-heavy and B is right-heavy

    if (A == root) {
      root = B;
    }
    else {
      if (parentOfA.left == A) {
        parentOfA.left = B;
      }
      else {
        parentOfA.right = B;
      }
    }

    A.right = B.left; // Make T2 the right subtree of A
    B.left = A;
    updateHeight((AVLTreeNode )A);
    updateHeight((AVLTreeNode )B);
  }

  /** Balance RL (see Figure 9.1(d)) */
  private void balanceRL(TreeNode  A, TreeNode  parentOfA) {
    System.out.println("Imbalance condition occurred at inserting ISBN " + A.element + "; fixed in Right Left Rotation");

    TreeNode  B = A.right; // A is right-heavy
    TreeNode  C = B.left; // B is left-heavy

    if (A == root) {
      root = C;
    }
    else {
      if (parentOfA.left == A) {
        parentOfA.left = C;
      }
      else {
        parentOfA.right = C;
      }
    }

    A.right = C.left; // Make T2 the right subtree of A
    B.left = C.right; // Make T3 the left subtree of B
    C.left = A;
    C.right = B;

    // Adjust heights
    updateHeight((AVLTreeNode )A);
    updateHeight((AVLTreeNode )B);
    updateHeight((AVLTreeNode )C);
  }

  @Override /** Delete an element from the binary tree.
   * Return true if the element is deleted successfully
   * Return false if the element is not in the tree */
  public boolean delete(BookObject element) {
    if (root == null)
      return false; // BookObjectlement is not in the tree

    // Locate the node to be deleted and also locate its parent node
    TreeNode  parent = null;
    TreeNode  current = root;
    while (current != null) {
      if (element.compareTo(current.element) < 0) {
        parent = current;
        current = current.left;
      }
      else if (element.compareTo(current.element) > 0) {
        parent = current;
        current = current.right;
      }
      else
        break; // BookObjectlement is in the tree pointed by current
    }

    if (current == null)
      return false; // BookObjectlement is not in the tree

    // Case 1: current has no left children (See Figure 23.6)
    if (current.left == null) {
      // Connect the parent with the right child of the current node
      if (parent == null) {
        root = current.right;
      }
      else {
        if (element.compareTo(parent.element) < 0)
          parent.left = current.right;
        else
          parent.right = current.right;
      }

      // Balance the tree if necessary
      balancePath(parent.book);
    }
    else {
      // Case 2: The current node has a left child
      // Locate the rightmost node in the left subtree of
      // the current node and also its parent
      TreeNode  parentOfRightMost = current;
      TreeNode  rightMost = current.left;

      while (rightMost.right != null) {
        parentOfRightMost = rightMost;
        rightMost = rightMost.right; // Keep going to the right
      }

      // Replace the element in current by the element in rightMost
      current.element = rightMost.element;

      // BookObjectliminate rightmost node
      if (parentOfRightMost.right == rightMost)
        parentOfRightMost.right = rightMost.left;
      else
        // Special case: parentOfRightMost is current
        parentOfRightMost.left = rightMost.left;

      // Balance the tree if necessary
      balancePath(parentOfRightMost.book);
    }

    size--;
    return true; // BookObjectlement inserted
  }

  /** AVLTreeNode is TreeNode plus height */
  protected static class AVLTreeNode
      extends BST.TreeNode {
    int height = 0; // New data field

    public AVLTreeNode(BookObject o) {
      super(o);
    }
  }
}
