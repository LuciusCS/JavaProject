package cn.shuangze.assetsms.entity;

public class Assets {

	private String name;
	private String model;
	private String price;
	private int status;
	private String other;
	private String date;
	private int typeID;

	public Assets(String name, String model, String price, int status, String other, String date, int typeID) {
		this.name=name;
		this.model=model;
		this.price=price;
		this.status=status;
		this.other=other;
		this.date=date;
		this.typeID=typeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	
	

}
