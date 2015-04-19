package io.vevox.mechanization.factory;

import com.google.gson.JsonParseException;
import io.vevox.mechanization.Mechanization;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * @author Matthew Struble
 */
public class FactoryController {

    private static HashMap<String, Factory> factories = new HashMap<>();

    private FactoryController(){}

    public static Factory factory(String factoryID){
        return factories.get(factoryID);
    }

    public static void parseFactories(){
        File recipesDir = new File(Mechanization.mechanization().getConfigDirectory(), "factories");
        if (!recipesDir.exists())
            Mechanization.mechanization().saveResource("factories", true);
        parseFactoriesDir("", recipesDir);
    }

    public static void parseFactoriesDir(String prefix, File dir){
        if (!dir.isDirectory()) return;
        for (File file : dir.listFiles()){
            if (file.isDirectory())
                parseFactoriesDir(file.getName() + ".", file);
            else {
                String name = prefix + FilenameUtils.removeExtension(file.getName());
                try {
                    Mechanization.mechanization().getConsole().info("> &e" + name + "&r...");
                    factories.put(name, new Factory(file));
                } catch (NullPointerException | FileNotFoundException | JsonParseException | IllegalStateException e) {
                    Mechanization.mechanization().getConsole().warn("Failed to parse recipe &e" + name);
                    Mechanization.mechanization().getConsole().warn(e.getMessage());
                }
            }
        }
    }


}
