package com.Flire2.GUI;

import com.Flire2.GUICommon;
import com.Flire2.ServerManager;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

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
        gui.setItem(12, GUICommon.createItem(Material.YELLOW_CONCRETE, ChatColor.WHITE + "Reload Server", ChatColor.GRAY + "Full", "", ChatColor.GRAY + "> Click to reload server!"));
        gui.setItem(13, GUICommon.createItem(Material.LECTERN, ChatColor.WHITE + "Save Server", "", ChatColor.GRAY + "> Click to save server!"));
        gui.setItem(14, GUICommon.createItem(Material.PINK_CONCRETE, ChatColor.WHITE + "Graceful Shutdown Server", "", ChatColor.GRAY + "> Click to shutdown server!"));
        gui.setItem(15, GUICommon.createItem(Material.BLACK_CONCRETE, ChatColor.WHITE + "Panic / Server Lockdown", ChatColor.GRAY + "Kicks all non op players.", ChatColor.GRAY +  "Stops players from joining.", ChatColor.GRAY +  "Changes MOTD", "", ChatColor.GRAY + "> Click to lockdown server!"));

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

        if (slot == 14) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all");
            Bukkit.getOnlinePlayers().forEach(p -> p.kickPlayer(ChatColor.RED + "Server is shutting down..."));
            Bukkit.shutdown();
            clicker.playSound(clicker.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        }

        if (slot == 15) {
            Bukkit.getServer().setMotd(ChatColor.RED + "⚠️ SERVER LOCKDOWN ACTIVE ⚠️");

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!p.isOp()) {
                    p.kick(Component.text("⚠️ Server is under lockdown."));
                }
            }

            Bukkit.getServer().setWhitelist(true);
            clicker.playSound(clicker.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        }
    }
}
