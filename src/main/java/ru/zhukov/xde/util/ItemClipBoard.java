package ru.zhukov.xde.util;

/**
 * Created by Gukov on 30.05.2017.
 */
public class ItemClipBoard {
    private final String item;

    public ItemClipBoard(String item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemClipBoard that = (ItemClipBoard) o;

        return getItem().equals(that.getItem());
    }

    @Override
    public int hashCode() {
        return getItem().hashCode();
    }

    public String getItem() {
        return item;
    }
}
