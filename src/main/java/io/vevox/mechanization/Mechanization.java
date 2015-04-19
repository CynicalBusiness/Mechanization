package io.vevox.mechanization;

import io.vevox.mechanization.factory.FactoryController;
import io.vevox.mechanization.world.WorldFactoryContents;
import io.vevox.mechanization.world.WorldFactory;
import io.vevox.mechanization.recipe.RecipeController;
import io.vevox.vevoxel.VevoxelAPI;
import io.vevox.vevoxel.api.VevoxelPlugin;
import io.vevox.vevoxel.io.PluginConsole;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

/**
 * @author Matthew Struble
 */
public class Mechanization extends VevoxelPlugin {

    static {
        ConfigurationSerialization.registerClass(WorldFactoryContents.class);
        ConfigurationSerialization.registerClass(WorldFactory.class);
    }

    public static final int ROW_LENGTH = 9;

    private static Mechanization instance;
    private PluginConsole console;

    @Override
    protected void loaded() {
        // Nothing
    }

    @Override
    protected void enabled() {
        instance = this;
        console = getConsole();
        console.setDebugEnabled(VevoxelAPI.isDebugEnabled());

        RecipeController.parseRecipes();
        FactoryController.parseFactories();

    }

    @Override
    protected void disabled() {

    }

    /**
     * Gets the mechanization instance.
     * @return The mechanization instance.
     */
    public static Mechanization mechanization(){
        return instance;
    }
}
