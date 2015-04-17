package io.vevox.mechanization.factory;

import io.vevox.mechanization.Mechanization;
import io.vevox.vevoxel.math.Vector3;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.io.Serializable;

/**
 * @author Matthew Struble
 */
public class FactoryData implements Serializable {

    public final String factoryID;
    public final FactoryContents contents;
    public final Vector3 location;
    public transient final Factory factory;

    public FactoryData(String factoryID, Vector3 location){
        this.factoryID = factoryID;
        this.location = location;
    }

}
