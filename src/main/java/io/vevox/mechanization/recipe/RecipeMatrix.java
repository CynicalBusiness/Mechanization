package io.vevox.mechanization.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.vevox.mechanization.Mechanization;

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

        input = new char[rows][Mechanization.ROW_LENGTH];
        output = new char[rows][Mechanization.ROW_LENGTH];

        try {
            parseMatrix(matrix.getAsJsonArray("input"), input);
            parseMatrix(matrix.getAsJsonArray("output"), output);
        } catch (IndexOutOfBoundsException | NullPointerException e){
            throw new IllegalStateException("Bad matrix: "+e.getMessage());
        }
    }

    private void parseMatrix(JsonArray array, char[][] chars){

    }

}
