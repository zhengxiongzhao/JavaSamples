/**省、市、区树*/
import java.util.ArrayList;

public class ZoneTree {
	private ZoneTreeNode zoneRootNode;
	
	public ZoneTree(ZoneTreeNode node) {
		zoneRootNode = node;
	}
	
	public void printTree() {
		printTree(zoneRootNode);
	}
	
	public ZoneTreeNode getZoneRootNode() {
		return zoneRootNode;
	}
	
	// 打印整棵地区树
	private void printTree(ZoneTreeNode node) {
		if (node != null) {
			//System.out.println(node.getZoneName());
			for (int i = 0; i < node.getChildCount(); i++) {
				printTree(node.getChild(i));
			}
		}
	}
	
	// 查找地区名字为name的结点，查找成功返回1，否则返回0
	public int findTreeNode(String name) {
		return findTreeNode(name, zoneRootNode);
	}
	
	private int findTreeNode(String name, ZoneTreeNode node) {
		if (node != null && name.equals(node.getZoneName())) {
			return 1;
		} else {
			for (int i = 0; i < node.getChildCount(); i++) {
				if (findTreeNode(name, node.getChild(i)) == 1) {
					return 1;
				}
			}
			return 0;
		}
	}
	
	// 查找地区名字为name的结点，查找成功返回该结点，否则返回null
	public ZoneTreeNode getTreeNode(String name) {
		return getTreeNode(name, zoneRootNode);
	}
	
	private ZoneTreeNode getTreeNode(String name, ZoneTreeNode node) {
		ZoneTreeNode tnode = null;
		
		if (node != null && name.equals(node.getZoneName())) {
			return node;
		} else {
			for (int i = 0; i < node.getChildCount(); i++) {
				if ((tnode = getTreeNode(name, node.getChild(i))) != null) {
					return tnode;
				}
			}
			return null;
		}
	}
	
	// 查找地区名字为name的所有结点
	public ArrayList<ZoneTreeNode> getAllTreeNode(String name) {
		ArrayList<ZoneTreeNode> node_list = new ArrayList<ZoneTreeNode>();
		
		getAllTreeNode(name, zoneRootNode, node_list);
		
		return node_list;
	}
	
	private void getAllTreeNode(String name, ZoneTreeNode node, ArrayList<ZoneTreeNode> node_list) {
		if (node != null && name.equals(node.getZoneName())) {
			node_list.add(node);
		} else {
			for (int i = 0; i < node.getChildCount(); i++) {
				getAllTreeNode(name, node.getChild(i), node_list);
			}
		}
	}
	
	// 返回node下名字为name的子结点
	public ZoneTreeNode getChildNode(ZoneTreeNode node, String name) {
		return node.getChild(name);
	}
	
	// 返回rootNode下名字为name的子结点
	public ZoneTreeNode getChildNode(String name) {
		return zoneRootNode.getChild(name);
	}
}
