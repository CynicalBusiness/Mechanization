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
        for (int r = 0; r < chars.length; r++){
            String row = array.get(r).getAsString();
            for (int c = 0; c < chars[r].length; c++)
                chars[r][c] = row.charAt(c);
        }
    }

    public KeyParser inputMaterial(int row, int col){
        return keys.get(input[row][col]);
    }

    public KeyParser outputMaterial(int row, int col){
        return keys.get(output[row][col]);
    }

    public KeyParser[][] inputMatrix(){
        KeyParser[][] matrix = new KeyParser[input.length][Mechanization.ROW_LENGTH];
        for (int r = 0; r < matrix.length; r++)
            for (int c = 0; c < matrix[r].length; c++)
                matrix[r][c] = inputMaterial(r,c);
        return matrix;
    }

    public KeyParser[][] outputMatrix(){
        KeyParser[][] matrix = new KeyParser[input.length][Mechanization.ROW_LENGTH];
        for (int r = 0; r < matrix.length; r++)
            for (int c = 0; c < matrix[r].length; c++)
                matrix[r][c] = outputMaterial(r,c);
        return matrix;
    }

}
