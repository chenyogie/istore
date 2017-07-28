package cn.itsource.istore.entity;

import java.io.Serializable;

/**
 * 商品实体类
 */
@SuppressWarnings("serial")
public class Product implements Serializable {

	private String id;
	private String name;
	private String category;
	private double price;
	private int productnumber;
	private String imgurl;
	private String description;

	public Product() {

	}

	public Product(String id, String name, String category, double price, int productnumber, String imgurl,
			String description) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.productnumber = productnumber;
		this.imgurl = imgurl;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getProductnumber() {
		return productnumber;
	}

	public void setProductnumber(int productnumber) {
		this.productnumber = productnumber;
	}

	public String getImgurl() {
		return imgurl;
	}

	public String getImgurls() {

		return imgurl.substring(0, imgurl.lastIndexOf(".")) + "_2" + imgurl.substring(imgurl.lastIndexOf("."));
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imgurl == null) ? 0 : imgurl.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + productnumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (null == obj) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (obj instanceof Product) {
			Product prod = (Product) obj;

			if (this.id.equals(prod.getId())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price
				+ ", productnumber=" + productnumber + ", imgurl=" + imgurl + ", description=" + description + "]";
	}

}
