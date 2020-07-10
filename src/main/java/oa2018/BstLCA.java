package oa2018;

public class BstLCA {
	
	public static TreeNode createBST(int[] input){
		//
		TreeNode root = null;
		for (int i: input){
			root = insert(root, i);
		}
		return root;
		
	}
	
	private static TreeNode insert(TreeNode root, int i){
		if(root==null) return new TreeNode(i);
		if(root.val>i){
			root.right = insert(root.right, i);
		}else{//Assume no dup
			root.left = insert(root.left, i);
		}
		return root;
		
	}
	
	
	public static TreeNode LCABST(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null || p == root || q == root) return root;
		if((root.val > p.val && root.val < q.val) ||
				(root.val < p.val && root.val > q.val)) {
			return root;
		} else if(root.val > p.val && root.val > q.val) {
			return LCABST(root.left, p, q);
		} else if(root.val < p.val && root.val < q.val) {
			return LCABST(root.right, p, q);
		} else {
			return null;
		}
	}
	
	/**
	 * return distance between p and q
	 * p, q may not exit in the tree
	 * @param lca
	 * @param p
	 * @param q
	 * @return
	 */
	public static int findDistance(TreeNode root,TreeNode p, TreeNode q){
		if(root==null || p==null || q==null) return -1;
		//p or q not exist
		int dis1 = getDistanceFromRoot(root, p);
		int dis2 = getDistanceFromRoot(root, q);
		if (dis1==-1 ||dis2==-1) return -1;
		else{
			TreeNode lca = LCABST(root, p, q);
			int dis3 = getDistanceFromRoot(root, lca);
			return dis1+dis2-2*dis3;
			
		}
		
	}
	
	public static int getDistanceFromRoot(TreeNode root, TreeNode n){
		if (root==null) return -1;
		if (root==n) return 0;
		if (root.val>n.val) {
			int dis =  getDistanceFromRoot(root.left, n);
			if (dis!=-1) return dis+1;
		}
		else if(root.val<n.val){
			int dis =  getDistanceFromRoot(root.right, n);
			if (dis!=-1) return dis+1;
		}
		//won't reach here
		return -1;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
