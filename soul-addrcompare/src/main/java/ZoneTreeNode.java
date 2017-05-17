/**省、市、区树结点*/
import java.util.ArrayList;

public class ZoneTreeNode {
	private String zoneName;	// 省、市、区名
	private ZoneTreeNode parentNode;	// 父结点
	private ArrayList<ZoneTreeNode> childList;	// 子结点
	
	public ZoneTreeNode(String name) {
		zoneName = name;
		parentNode = null;
		childList = new ArrayList<ZoneTreeNode>();
	}
	
	// 当前结点添加子结点
	public void addChild(ZoneTreeNode node) {
		childList.add(node);
		node.parentNode = this;
	}
	
	// 当前结点移除第index个子结点
	public int removeChild(int index) {
		if (index >= 0 && index < childList.size()) {
			childList.remove(index);
			return 0;
		}
		return -1;
	}
	
	// 当前结点移除省、市、区名为name的子结点
	public int removeChild(String name) {
		for (int i = 0; i < childList.size(); i++) {
			if (childList.get(i).zoneName.equals(name)) {
				childList.remove(i);
				return 0;
			}
		}
		return -1;
	}
	
	// 返回省、市、区名
	public String getZoneName() {
		return zoneName;
	}
	
	// 返回父结点
	public ZoneTreeNode getParent() {
		return parentNode;
	}
	
	// 返回当前结点第index个子节点
	public ZoneTreeNode getChild(int index) {
		if (index >= 0 && index < childList.size()) {
			return childList.get(index);
		}
		return null;
	}
	
	// 返回当前结点省、市、区名为name的子结点
	public ZoneTreeNode getChild(String name) {
		for (int i = 0; i < childList.size(); i++) {
			if (childList.get(i).zoneName.equals(name)) {
				return childList.get(i);
			}
		}
		return null;
	}
	
	// 返回当前结点的子结点数
	public int getChildCount() {
		return childList.size();
	}
}