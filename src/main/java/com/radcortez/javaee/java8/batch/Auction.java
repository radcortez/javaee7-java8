package com.radcortez.javaee.java8.batch;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Description.
 *
 * @author Roberto Cortez
 */
@Entity
public class Auction {
    @Id
    private Long id;
    private String item;
    private Long bid;
    private Long buyout;
    private Integer quantity;

    public Auction() {
    }

    public Auction(final Long id, final String item, final Long bid, final Long buyout, final Integer quantity) {
        this.id = id;
        this.item = item;
        this.bid = bid;
        this.buyout = buyout;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(final String item) {
        this.item = item;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(final Long bid) {
        this.bid = bid;
    }

    public Long getBuyout() {
        return buyout;
    }

    public void setBuyout(final Long buyout) {
        this.buyout = buyout;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }
}
