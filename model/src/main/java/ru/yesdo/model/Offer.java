package ru.yesdo.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lameroot on 08.02.15.
 */
@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @SequenceGenerator(name = "offer_id_gen", sequenceName = "offer_seq")
    @GeneratedValue(generator = "offer_id_gen", strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_merchant_id", nullable = false)
    private Merchant merchant;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_product_id", nullable = false)
    private Product product;

    @Column
    private Long amount;
    @Column
    private boolean enabled;//доступен или нет.
    @Enumerated(EnumType.STRING)
    private Publicity publicity;//публичность данного продукта, он может быть скрытый, может быть приватный, публичный, только для избранных
    @Transient
    private TimeProduct timeProduct;//время в которое можно воспользоваться услугой
    @Enumerated
    private ProductType productType;//тип продукта
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiration_at")
    private Date expirationAt;//дата истечения возможности использования продуктом

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Publicity getPublicity() {
        return publicity;
    }

    public void setPublicity(Publicity publicity) {
        this.publicity = publicity;
    }

    public TimeProduct getTimeProduct() {
        return timeProduct;
    }

    public void setTimeProduct(TimeProduct timeProduct) {
        this.timeProduct = timeProduct;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Date getExpirationAt() {
        return expirationAt;
    }

    public void setExpirationAt(Date expirationAt) {
        this.expirationAt = expirationAt;
    }
}
