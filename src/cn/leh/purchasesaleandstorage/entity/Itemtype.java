package cn.leh.purchasesaleandstorage.entity;

public class Itemtype {
private int id;
private String name;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Itemtype(int id, String name) {
	super();
	this.id = id;
	this.name = name;
}
public Itemtype() {
	super();
	// TODO Auto-generated constructor stub
}

}
