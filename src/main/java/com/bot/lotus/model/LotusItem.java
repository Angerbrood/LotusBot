package com.bot.lotus.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "LotusItem")
public class LotusItem {
    public static final String DELIMITER = ";";

    @DatabaseField(generatedId = true)
    private long lotusId;

    @DatabaseField
    private String title;

    @DatabaseField
    private long price;

    @DatabaseField
    private String url;

    public LotusItem(String title, String price, String url) {
        this.title = title;
        this.price = Long.parseLong(price);
        this.url = url;
    }

    public LotusItem() {}

    public long getLotusId() {
        return lotusId;
    }

    public void setLotusId(long lotusId) {
        this.lotusId = lotusId;
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
