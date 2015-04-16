package io.vevox.mechanization.recipe;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * @author Matthew Struble
 */
public class RecipeMatrix implements Serializable {

    private char[][] input,output;

    public RecipeMatrix(JsonObject matrix, int rows) throws NullPointerException, ClassCastException, IllegalStateException {

    }

}
