package com.Flire2.GUI;

import com.Flire2.GUICommon;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ServerControlsGUI implements Listener {

    private static final String GUI_TITLE = "Server Manager - Server Controls";

    public static void open(Player viewer) {
        String title = GUI_TITLE;
        Inventory gui = Bukkit.createInventory(null, 27, title);

        // Close
        gui.setItem(8, GUICommon.createItem(Material.BARRIER, ChatColor.RED + "Close"));

        // Categories
        gui.setItem(10, GUICommon.createItem(Material.RED_CONCRETE, ChatColor.WHITE + "Stop Server", "", ChatColor.GRAY + "> Click to stop server!"));
        gui.setItem(11, GUICommon.createItem(Material.ORANGE_CONCRETE, ChatColor.WHITE + "Restart Server", "", ChatColor.GRAY + "> Click to restart server!"));
        gui.setItem(12, GUICommon.createItem(Material.YELLOW_CONCRETE, ChatColor.WHITE + "Reload Server", "", ChatColor.GRAY + "> Click to reload server!"));
        gui.setItem(13, GUICommon.createItem(Material.LECTERN, ChatColor.WHITE + "Save Server", "", ChatColor.GRAY + "> Click to save server!"));

        viewer.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player clicker = (Player) e.getWhoClicked();

        if (!e.getView().getTitle().equals(GUI_TITLE)) return;

        e.setCancelled(true);

        int slot = e.getRawSlot();
        ClickType click = e.getClick();

        if (slot == 8) {
            clicker.closeInventory();
            clicker.playSound(clicker.getLocation(), Sound.UI_BUTTON_CLICK, 1f, 1.5f);
        }

        if (slot == 10) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
            clicker.playSound(clicker.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

        }

        if (slot == 11) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
            clicker.playSound(clicker.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

        }

        if (slot == 12) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "reload");
            clicker.playSound(clicker.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

        }

        if (slot == 13) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all");
            clicker.playSound(clicker.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        }
    }
}
