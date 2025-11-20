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

public class ServerManagerGUI implements Listener {

    private static final String GUI_TITLE = "Server Manager";

    public static void open(Player viewer) {
        String title = GUI_TITLE;
        Inventory gui = Bukkit.createInventory(null, 27, title);

        // Close
        gui.setItem(8, GUICommon.createItem(Material.BARRIER, ChatColor.RED + "Close"));

        // Categories
        gui.setItem(10, GUICommon.createItem(Material.LIGHT_GRAY_DYE, ChatColor.WHITE + "Server Controls", ChatColor.GRAY + "Stop, Reload, MOTD", "", ChatColor.GRAY + "> Click to open!"));
        gui.setItem(11, GUICommon.createItem(Material.PLAYER_HEAD, ChatColor.WHITE + "Player Controls", ChatColor.GRAY + "Whitelist, PVP", "", ChatColor.GOLD + "> Under Development!"));
        gui.setItem(12, GUICommon.createItem(Material.PAINTING, ChatColor.WHITE + "Display", ChatColor.GRAY + "Scoreboard, Announcements, Tablist", "", ChatColor.GOLD + "> Under Development!"));
        gui.setItem(13, GUICommon.createItem(Material.IRON_BARS, ChatColor.WHITE + "Security", ChatColor.GRAY + "Rules, Restrictions", "", ChatColor.GOLD + "> Under Development!"));
        gui.setItem(14, GUICommon.createItem(Material.EXPERIENCE_BOTTLE, ChatColor.WHITE + "Stats", ChatColor.GRAY + "TPS, RAM, Player Count", "", ChatColor.GOLD + "> Under Development!"));
        gui.setItem(15, GUICommon.createItem(Material.CHEST, ChatColor.WHITE + "Plugins", ChatColor.GRAY + "Plugin List + Controls", "", ChatColor.GOLD + "> Under Development!"));


        viewer.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player clicker = (Player) e.getWhoClicked();

        e.setCancelled(true);

        int slot = e.getRawSlot();
        ClickType click = e.getClick();

        if (slot == 8) {
            clicker.closeInventory();
            clicker.playSound(clicker.getLocation(), Sound.UI_BUTTON_CLICK, 1f, 1.5f);
        }

        if (slot == 10) {
            ServerControlsGUI.open(clicker);
            clicker.playSound(clicker.getLocation(), Sound.UI_BUTTON_CLICK, 1f, 1.5f);
        }
    }
}
