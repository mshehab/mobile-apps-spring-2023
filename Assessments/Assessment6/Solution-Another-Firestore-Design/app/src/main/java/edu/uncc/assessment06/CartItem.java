package edu.uncc.assessment06;

public class CartItem {
    public String docId, icon_url, name, uid;
    public Double price;

    public CartItem() {
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "docId='" + docId + '\'' +
                ", icon_url='" + icon_url + '\'' +
                ", name='" + name + '\'' +
                ", uid='" + uid + '\'' +
                ", price=" + price +
                '}';
    }
}
