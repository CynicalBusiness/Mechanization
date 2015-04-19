package io.vevox.mechanization.world;

import io.vevox.mechanization.Mechanization;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Matthew Struble
 */
public class WorldFactoryContents implements ConfigurationSerializable {

    public final int rows;
    private ItemStack[] contents;

    public WorldFactoryContents(int rows){
        this.rows = rows;
    }

    public WorldFactoryContents(Map<String, Object> data){
        rows = (Integer) data.get("rows");
        for (String str : data.keySet())
            contents[Integer.parseInt(str)] = (ItemStack) data.get(str);
    }

    public ItemStack[] contents(){
        return contents;
    }

    public void contents(ItemStack[] contents){
        this.contents = contents;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String,Object> data = new HashMap<>();
        data.put("rows", rows);
        for (int i = 0; i < contents.length; i++)
            data.put(String.valueOf(i), contents[i]);
        return data;
    }
}
