package io.vevox.mechanization.factory;

import io.vevox.mechanization.Mechanization;
import io.vevox.vevoxel.item.serial.SerialItemStack;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

/**
 * @author Matthew Struble
 */
public class FactoryContents implements Serializable {

    private SerialItemStack[][] contents;
    private SerialItemStack fuel;

    public FactoryContents(int rows){
        contents = new SerialItemStack[rows][Mechanization.ROW_LENGTH];
    }

    public int rows(){
        return contents.length;
    }

    public ItemStack fuel(){
        return fuel.getItemStack();
    }

    public ItemStack[] contents(){
        ItemStack[] inv = new ItemStack[rows()*Mechanization.ROW_LENGTH];
        for (int r = 0; r < rows(); r++)
            for (int c = 0; c < contents[r].length; c++)
                inv[c + (Mechanization.ROW_LENGTH * r)] = contents[r][c].getItemStack();
        return inv;
    }

}
