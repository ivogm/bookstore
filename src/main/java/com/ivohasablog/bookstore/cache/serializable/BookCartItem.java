package com.ivohasablog.bookstore.cache.serializable;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import lombok.Data;

import java.io.IOException;

/**
 * Created by Ivo on 18.11.2017 г..
 */
@Data
public class BookCartItem implements IdentifiedDataSerializable {
    private String id;
    private String bookId;
    private double cost;
    private int quantity;
    private boolean inStock;

    @Override
    public int getFactoryId() {
        return 1;
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(bookId);
        out.writeDouble(cost);
        out.writeInt(quantity);
        out.writeBoolean(inStock);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        id = in.readUTF();
        bookId = in.readUTF();
        cost = in.readDouble();
        quantity = in.readInt();
        inStock = in.readBoolean();
    }
}
