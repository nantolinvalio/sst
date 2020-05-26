package cl.abcdin.sst.model.vo;

import java.util.List;

public class TreeView {
	private String id;
	private Metadata metadata;
	private String data;
	private String state;
	private String attr;
	private String checked;
	private List<TreeView> children;
	
	public TreeView() {}
	
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public List<TreeView> getChildren() {
		return children;
	}
	public void setChildren(List<TreeView> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
	
}
