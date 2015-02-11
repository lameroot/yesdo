package ru.yesdo.graph.data;

import ru.yesdo.model.Offer;
import ru.yesdo.model.ProductType;
import ru.yesdo.model.Publicity;
import ru.yesdo.model.TimeProduct;

import java.util.Date;

/**
 * Created by lameroot on 11.02.15.
 */
public class OfferData {

    private Long amount;
    private boolean enabled;//доступен или нет.
    private Publicity publicity;//публичность данного продукта, он может быть скрытый, может быть приватный, публичный, только для избранных
    private TimeProduct timeProduct;//время в которое можно воспользоваться услугой
    private ProductType productType;//тип продукта
    private Date expirationAt;//

    public Offer toOffer() {
        Offer offer = new Offer();
        offer.setEnabled(isEnabled());
        offer.setAmount(getAmount());
        offer.setExpirationAt(getExpirationAt());
        offer.setProductType(getProductType());
        offer.setPublicity(getPublicity());
        offer.setTimeProduct(getTimeProduct());

        return offer;
    }

    public Long getAmount() {
        return amount;
    }

    public OfferData setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public OfferData setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Publicity getPublicity() {
        return publicity;
    }

    public OfferData setPublicity(Publicity publicity) {
        this.publicity = publicity;
        return this;
    }

    public TimeProduct getTimeProduct() {
        return timeProduct;
    }

    public OfferData setTimeProduct(TimeProduct timeProduct) {
        this.timeProduct = timeProduct;
        return this;
    }

    public ProductType getProductType() {
        return productType;
    }

    public OfferData setProductType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public Date getExpirationAt() {
        return expirationAt;
    }

    public void setExpirationAt(Date expirationAt) {
        this.expirationAt = expirationAt;
    }
}
