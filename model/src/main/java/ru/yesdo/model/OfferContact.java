package ru.yesdo.model;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * User: Krainov
 * Date: 13.02.2015
 * Time: 17:54
 */
@NodeEntity
public class OfferContact extends Contact {

    @Fetch
    private Offer offer;

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
