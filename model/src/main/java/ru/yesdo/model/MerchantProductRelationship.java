package ru.yesdo.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Created by lameroot on 28.01.15.
 */
@RelationshipEntity(type = "PRODUCT")
public class MerchantProductRelationship {

    @GraphId
    private Long id;
    @StartNode
    private Merchant merchant;
    @EndNode
    private Product product;
    private Long amount;

    public MerchantProductRelationship(){}
    public MerchantProductRelationship(Merchant merchant, Product product, Long amount) {
        this.merchant = merchant;
        this.product = product;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }


}
