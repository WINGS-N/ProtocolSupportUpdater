package io.WINGS.PluginUpdater;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.WINGS.PluginUpdater.storage.SelfUpdateData;
import io.WINGS.ProtocolSupportUpdater.storage.SS;
import net.md_5.bungee.api.ChatColor;

public class SelfUpdate {

	
	public Boolean updateInProgress = false;
	Plugin pl = Bukkit.getPluginManager().getPlugin(SS.PluginName);
	FileConfiguration config = pl.getConfig();
	
    public SelfUpdate(CommandSender s) {
        if(updateInProgress) {
            s.sendMessage(ChatColor.RED + "Update already in progress");
            return;
        }
        
        s.sendMessage(SS.prefix + ChatColor.RED + "Self-Updating " + SelfUpdateData.PluginName + "...");
        
        updateInProgress = true;

        try {
        	Method getFile = JavaPlugin.class.getDeclaredMethod("getFile");
            getFile.setAccessible(true);
            File dest = new File("plugins/" + SelfUpdateData.PluginName + SelfUpdateData.ext);

            //Connect
            URL url =
            new URL(SelfUpdateData.GithubURL +
                    SelfUpdateData.PluginName +
                    SelfUpdateData.ext);
            
            // Creating con
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "WINGS07/ProtocolSupportUpdater");

            // Get input stream
            try (InputStream input = con.getInputStream()) {
            	Files.copy(input, dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
              	}

            int suc = config.getInt("SelfUpdateCounter");
            suc++;
            config.set("SelfUpdateCounter", suc);
            pl.saveConfig();
            
            s.sendMessage(SS.prefix + ChatColor.RED + "Update success!");
        } catch (Exception ex) {
        	ex.printStackTrace();
            s.sendMessage(SS.prefix + ChatColor.RED + "Update failed, " + ex.getMessage());
        } finally {
        	updateInProgress = false;
        }
    }
}
