package io.vevox.mechanization.recipe;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import io.vevox.mechanization.MetaParser;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;

/**
 * @author Matthew Struble
 */
public class Recipe implements Serializable {

    public final String id;
    public final MetaParser meta;
    public final KeyParser fuel;
    public final ToolParser tool;
    public final RecipeMatrix matrix;
    public final int time,rows;

    public Recipe(File recipeFile) throws NullPointerException, FileNotFoundException, JsonParseException, IllegalStateException {
        // FILE VALIDATION
        String extension = FilenameUtils.getExtension(recipeFile.getName());
        if (!extension.equalsIgnoreCase("json")) throw new IllegalStateException("Bad file format: "+extension);
        id = FilenameUtils.removeExtension(recipeFile.getName());

        // PARSING
        JsonObject root = new JsonParser().parse(new JsonReader(new FileReader(recipeFile))).getAsJsonObject();
        time = root.get("time").getAsInt();
        rows = root.get("rows").getAsInt();
        meta = new MetaParser(root.getAsJsonObject("meta"));
        fuel = new KeyParser(root.getAsJsonObject("fuel"));
        tool = new ToolParser(root.getAsJsonObject("tool"));
        matrix = new RecipeMatrix(root.getAsJsonObject("matrix"), rows);

    }

}
