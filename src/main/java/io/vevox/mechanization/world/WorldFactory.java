package io.vevox.mechanization.world;

import io.vevox.mechanization.Mechanization;
import io.vevox.mechanization.factory.Factory;
import io.vevox.mechanization.factory.FactoryController;
import io.vevox.vevoxel.math.Vector3;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Matthew Struble
 */
public class WorldFactory implements ConfigurationSerializable {

    public final String factoryID;
    public final Vector3 location;
    private WorldFactoryContents contents;
    private Factory factory;
    private Inventory inventory;

    public WorldFactory(String factoryID, Vector3 location){
        this.factoryID = factoryID;
        this.location = location;
        factory = factory();
        contents = new WorldFactoryContents(factory.size.rows);
        inventory = inventory();
    }

    public WorldFactory(Map<String, Object> data){
        factoryID = (String) data.get("factoryID");
        location = new Vector3((Double) data.get("posX"), (Double) data.get("posY"), (Double) data.get("posZ"));
        contents = (WorldFactoryContents) data.get("contents");

        factory = factory();
        inventory = inventory();
    }

    public Factory factory(){
        if (factory == null)
            factory = FactoryController.factory(factoryID);
        return factory;
    }

    public Inventory inventory(){
        if (inventory == null){
            inventory = Bukkit.createInventory(null, factory.size.rows * Mechanization.ROW_LENGTH, factory().meta.strippedName());
            inventory.setContents(contents.contents());
        }
        return inventory;
    }

    @Override
    public Map<String, Object> serialize() {
        contents.contents(inventory.getContents());

        Map<String,Object> data = new HashMap<>();
        data.put("factoryID", factoryID);
        data.put("posX", location.x);
        data.put("posY", location.y);
        data.put("posZ", location.z);
        data.put("contents", contents);
        return data;
    }
}
