package io.vevox.mechanization;

import io.vevox.vevoxel.VevoxelAPI;
import io.vevox.vevoxel.api.VevoxelPlugin;
import io.vevox.vevoxel.io.PluginConsole;

/**
 * @author Matthew Struble
 */
public class Mechanization extends VevoxelPlugin {

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

    }

    @Override
    protected void disabled() {

    }

    /**
     * Gets the mechanization inistance.
     * @return The mechanization instance.
     */
    public static Mechanization getMechanization(){
        return instance;
    }
}
