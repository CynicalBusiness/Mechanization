package io.vevox.mechanization.recipe;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * @author Matthew Struble
 */
public class ToolParser implements Serializable {

    public final KeyParser material;
    public final boolean consume,damage;

    public ToolParser(JsonObject tool) throws NullPointerException, ClassCastException, IllegalStateException {
        material = new KeyParser(tool.getAsJsonObject("item"));
        consume = tool.get("doConsume").getAsBoolean();
        damage = tool.get("doDamage").getAsBoolean();
    }

}
