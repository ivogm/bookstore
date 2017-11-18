package com.ivohasablog.bookstore.cache.serializable;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivo on 18.11.2017 г..
 */
public class ShoppingCart implements IdentifiedDataSerializable {
    private double totalPrice;
    private Date date;
    private long id;
    private List<BookCartItem> items = new ArrayList<>();

    public void addItem(BookCartItem item) {
        items.add(item);
        totalPrice += item.getCost() * item.getQuantity();
    }

    public void removeItem(int index) {
        BookCartItem item = items.remove(index);
        totalPrice -= item.getCost() * item.getQuantity();
    }

    @Override
    public int getFactoryId() {
        return 1;
    }

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeDouble(totalPrice);
        out.writeLong(date.getTime());
        out.writeLong(id);
        out.writeInt(items.size());
        items.forEach(item -> {
            try{
                item.writeData(out);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        totalPrice = in.readDouble();
        date = new Date(in.readLong());
        id = in.readLong();
        int count = in.readInt();
        items = new ArrayList<>(count);
        for(int i=0; i<count; i++) {
            BookCartItem cartItem = new BookCartItem();
            cartItem.readData(in);
            items.add(cartItem);
        }
    }
}
