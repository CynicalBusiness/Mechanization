package io.vevox.mechanization.recipe;

import com.google.gson.JsonObject;
import io.vevox.vevoxel.io.MaterialParser;

import java.io.Serializable;

/**
 * @author Matthew Struble
 */
public class KeyParser implements Serializable {

    public final MaterialParser material;
    public final int amount;

    public KeyParser(JsonObject key) throws NullPointerException, ClassCastException, IllegalStateException {
        material = new MaterialParser(key.get("material").getAsString());
        amount = key.get("amount").getAsInt();
    }

}
