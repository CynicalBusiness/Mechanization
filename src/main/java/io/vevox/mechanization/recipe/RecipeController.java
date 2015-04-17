package io.vevox.mechanization.recipe;

import com.google.gson.JsonParseException;
import io.vevox.mechanization.Mechanization;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
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

    public void start(){
        File recipesDir = new File(plugin.getConfigDirectory(), "recipes");
        if (!recipesDir.exists())
            plugin.saveResource("recipes",true);
        parseRecipeDir("", recipesDir);
    }

    public void parseRecipeDir(String prefix, File dir){
        if (!dir.isDirectory()) return;
        for (File file : dir.listFiles()){
            if (file.isDirectory())
                parseRecipeDir(file.getName() + ".", file);
            else {
                String name = prefix + FilenameUtils.removeExtension(file.getName());
                try {
                    plugin.getConsole().info("> &e" + name + "&r...");
                    recipes.put(name, new Recipe(file));
                } catch (NullPointerException | FileNotFoundException | JsonParseException | IllegalStateException e) {
                    plugin.getConsole().warn("Failed to parse recipe &e" + name);
                    plugin.getConsole().warn(e.getMessage());
                }
            }
        }
    }

}
