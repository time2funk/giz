package giz_tree;

import java.util.ArrayList;

public class TNode {
	public int id;
	private ArrayList<TNode> childs;
	
	public TNode(){
		childs = new ArrayList<>();
	}
	
	public String toString(){
		String tmp = "<li>";
		tmp += this.id;
		if(this.childs.size() > 0)
		{
			tmp += "<ul>";
			for(int i = 0; i < this.childs.size(); i++)
				tmp += this.childs.get(i);
			tmp += "</ul>";
		}
		tmp += "</li>";
		return tmp;
	} 
	public ArrayList<TNode> getChilds(){
		return childs;
	}
	public void add(TNode node){
		childs.add(node);
	}
	public int size(){
		return childs.size();
	}
//	public TNode get(int id){
//		return childs.get(id);
//	}
	public int depth() {
		int depth = 0;

		for (int i = 0; i < childs.size(); i++) {
				int new_depth = 1 + childs.get(i).depth();
				if(new_depth > depth)
					depth = new_depth;
		}
		return depth;
	}
	public int maxId(){
		int id = this.id;
		for(int i = 0; i < childs.size(); i++ ){
			if(id < childs.get(i).maxId())
				id = childs.get(i).maxId();
		}
		return id;
	}
	public int minId(){
		int id = this.id;
		for(int i = 0; i < childs.size(); i++ ){
			if(id > childs.get(i).maxId())
				id = childs.get(i).maxId();
		}
		return id;
	}
	public TNode minChildNode(){
		TNode node = this;
		for(int i = 0; i < childs.size(); i++ ){
			if(childs.get(i).childs.size() == 0)
				continue;
			if(node.childs.size() > childs.get(i).minChildNode().childs.size())
				node = childs.get(i);
		}
		return node;
	}
	
}
