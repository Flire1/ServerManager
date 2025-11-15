package com.Flire2;

import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GUICommon {

    public static ItemStack createItem(Material material, String name, String... loreLines) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(name);
            if (loreLines != null && loreLines.length > 0) {
                meta.setLore(Arrays.asList(loreLines));
            }
            item.setItemMeta(meta);
        }

        return item;
    }
}
