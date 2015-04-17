package io.vevox.mechanization.factory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.Serializable;

/**
 * @author Matthew Struble
 */
public class FactoryDimensions implements Serializable {

    public final int width,height,breadth,rows;

    public FactoryDimensions(JsonObject dims) throws NullPointerException, ClassCastException, JsonParseException {
        width = dims.get("width").getAsInt();
        height = dims.get("height").getAsInt();
        breadth = dims.get("breadth").getAsInt();
        rows = dims.get("rows").getAsInt();
    }

}
