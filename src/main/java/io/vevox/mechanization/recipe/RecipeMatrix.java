package io.vevox.mechanization.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Matthew Struble
 */
public class RecipeMatrix implements Serializable {

    private HashMap<Character, KeyParser> keys;
    private char[][] input,output;

    public RecipeMatrix(JsonObject matrix, int rows) throws NullPointerException, ClassCastException, IllegalStateException {
        this.keys = new HashMap<>();
        JsonObject keys = matrix.getAsJsonObject("keys");
        for (Map.Entry<String,JsonElement> entry : keys.entrySet())
            this.keys.put(entry.getKey().charAt(0), new KeyParser(entry.getValue().getAsJsonObject()));


    }

}
