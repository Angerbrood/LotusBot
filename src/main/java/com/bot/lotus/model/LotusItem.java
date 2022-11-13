package com.bot.lotus.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;



@Entity
@Table(name = "LotusItem")
public class LotusItem {
    public static final String DELIMITER = ";";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String title;

    @Column
    private long price;

    @Column
    private String url;

    public LotusItem(String title, String price, String url) {
        this.title = title;
        this.price = Long.parseLong(price);
        this.url = url;
    }

    public LotusItem() {}

    public long getId() {
        return id;
    }

    public void setId(long lotusId) {
        this.id = lotusId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public long getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotusItem lotusItem = (LotusItem) o;
        return price == lotusItem.price && Objects.equals(title, lotusItem.title) && Objects.equals(url, lotusItem.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, url);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.title);
        sb.append(DELIMITER);
        sb.append(this.price);
        sb.append(DELIMITER);
        sb.append(url);
        return sb.toString();
    }
}
