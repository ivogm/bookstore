package com.ivohasablog.bookstore.cache.serializable;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

/**
 * Created by Ivo on 18.11.2017 г..
 */
public class ShoppingCartDSFactory implements DataSerializableFactory {
    @Override
    public IdentifiedDataSerializable create(int typeId) {
        switch (typeId) {
            case 1:
                return new BookCartItem();
            case 2:
                return new ShoppingCart();
            default:
                return null;
        }
    }
}
