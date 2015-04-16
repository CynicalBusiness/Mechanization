package io.vevox.mechanization.recipe;

import io.vevox.mechanization.Mechanization;

import java.util.HashMap;

/**
 * @author Matthew Struble
 */
public class RecipeController {

    private HashMap<String, Recipe> recipes;

    private Mechanization plugin;
    public RecipeController(Mechanization plugin){
        this.plugin = plugin;
    }



}
