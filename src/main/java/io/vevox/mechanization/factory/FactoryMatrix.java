package io.vevox.mechanization.factory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.vevox.vevoxel.io.MaterialParser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Matthew Struble
 */
public class FactoryMatrix implements Serializable {

    private HashMap<Character, MaterialParser> keys;
    private char[][][] matrix;

    public FactoryMatrix(JsonObject matrix, FactoryDimensions dimensions) throws NullPointerException, JsonParseException, IllegalStateException {
        JsonObject keys = matrix.getAsJsonObject("keys");
        for (Map.Entry<String,JsonElement> entry : keys.entrySet())
            this.keys.put(entry.getKey().charAt(0), new MaterialParser(entry.getValue().getAsString()));

        JsonArray layout = matrix.getAsJsonArray("layout");
        this.matrix = new char[dimensions.width][dimensions.height][dimensions.breadth];
        for (int w = 0; w < dimensions.width; w++)
            for (int h = 0; h < dimensions.height; h++)
                for (int b = 0; b < dimensions.breadth; b++)
                    this.matrix[w][h][b] = layout.get(w).getAsJsonArray().get(h).getAsString().charAt(b);
    }

    public MaterialParser material(int width, int height, int breadth){
        return keys.get(matrix[width][height][breadth]);
    }

    public MaterialParser[][][] matrix(){
        MaterialParser[][][] matrix = new MaterialParser[this.matrix.length][this.matrix[0].length][this.matrix[0][0].length];
        for (int w = 0; w < matrix.length; w++)
            for (int h = 0; h < matrix[w].length; h++)
                for (int b = 0; b < matrix[w][h].length; b++)
                    matrix[w][h][b] = material(w,h,b);
        return matrix;
    }

}
