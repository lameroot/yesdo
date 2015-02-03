package ru.yesdo.graph.data;

import ru.yesdo.model.Location;
import ru.yesdo.model.ProductType;
import ru.yesdo.model.TimeProduct;

import java.util.Date;

/**
 * Created by lameroot on 28.01.15.
 */
public class ProductData {

    private String title;

    private Location location;
    private ProductType productType;
    private TimeProduct timeProduct;
    private Long amount;
    private Date createdAt;

    //add user, raitings, friends


    public String getTitle() {
        return title;
    }

    public ProductData setTitle(String title) {
        this.title = title;
        return this;
    }



    public Location getLocation() {
        return location;
    }

    public ProductData setLocation(Location location) {
        this.location = location;
        return this;
    }

    public ProductType getProductType() {
        return productType;
    }

    public ProductData setProductType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public TimeProduct getTimeProduct() {
        return timeProduct;
    }

    public ProductData setTimeProduct(TimeProduct timeProduct) {
        this.timeProduct = timeProduct;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public ProductData setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ProductData setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
