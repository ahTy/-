package bike.com.bike.entity;

/**
 * Created by TY on 2017/5/14.
 */
public class Goods {
    private String brand;
    private String category;
    private String goodId;
    private String goodsName;
    private String count;
    private String price;
    private String size;
    private String description;
    private int resid;

    public Goods(){

    }

    public Goods(String b, String c, String gID, String gN, String cN, String p, String s, String d,int i) {
        brand=b;
        category=c;
        goodId=gID;
        goodsName=gN;
        count=cN;
        price=p;
        size=s;
        description=d;
        resid=i;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }
}