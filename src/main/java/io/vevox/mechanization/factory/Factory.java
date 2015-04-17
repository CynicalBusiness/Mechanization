package io.vevox.mechanization.factory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
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
public class Factory implements Serializable {

    public final String id;
    public final MetaParser meta;
    public final String[] recipes;
    public final FactoryDimensions size;

    public Factory(File factoryFile)  throws NullPointerException, FileNotFoundException, JsonParseException, IllegalStateException {
        // FILE VALIDATION
        String extension = FilenameUtils.getExtension(factoryFile.getName());
        if (!extension.equalsIgnoreCase("json")) throw new IllegalStateException("Bad file format: "+extension);
        id = FilenameUtils.removeExtension(factoryFile.getName());

        // PARSING
        JsonObject root = new JsonParser().parse(new JsonReader(new FileReader(factoryFile))).getAsJsonObject();
        meta = new MetaParser(root.getAsJsonObject("meta"));

        JsonArray recipes = root.getAsJsonArray("recipes");
        this.recipes = new String[recipes.size()];
        for (int i = 0; i < recipes.size(); i++)
            this.recipes[i] = recipes.get(i).getAsString();

        size = new FactoryDimensions(root.getAsJsonObject("size"));

    }

}
