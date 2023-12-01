package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = "getTable", query = "SELECT p FROM Items p")

//entity class for table items
public class Items {
    private int itemId;
    private String itemName;
    private String itemDescription;
    private byte isDone;

    //constructors
    @Id
    @Column(name = "itemID")
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "itemName")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @Basic
    @Column(name = "itemDescription")
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    @Basic
    @Column(name = "isDone")
    public byte getIsDone() {
        return isDone;
    }

    public void setIsDone(byte isDone) {
        this.isDone = isDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Items items = (Items) o;

        if (itemId != items.itemId) return false;
        if (isDone != items.isDone) return false;
        if (!Objects.equals(itemName, items.itemName)) return false;
        return Objects.equals(itemDescription, items.itemDescription);
    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemDescription != null ? itemDescription.hashCode() : 0);
        result = 31 * result + (int) isDone;
        return result;
    }
}
