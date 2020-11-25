package io.WINGS.PluginUpdater.ProtocolSupport;

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
import org.bukkit.plugin.java.JavaPlugin;

import io.WINGS.ProtocolSupportUpdater.storage.SS;
import io.WINGS.ProtocolSupportUpdater.storage.UpdateData;
import net.md_5.bungee.api.ChatColor;

public class UpdateFromBackupServer {

	FileConfiguration config = Bukkit.getPluginManager().getPlugin(SS.PluginName).getConfig();
	public Boolean updateInProgress = false;
	
    public UpdateFromBackupServer(CommandSender s) {
        if(updateInProgress) {
            s.sendMessage(SS.prefix + ChatColor.RED + "Update from backup server already in progress!");
            return;
        }
        
        s.sendMessage(SS.prefix + ChatColor.RED + "Downloading " + SS.PSName + " from backup server...");
        
        updateInProgress = true;
        @SuppressWarnings("unused")
		File psFile = null;

        try {
        	Method getFile = JavaPlugin.class.getDeclaredMethod("getFile");
            getFile.setAccessible(true);
            File dest = new File("plugins/" + SS.PSName + UpdateData.ext);

            //Connect
            URL url =
            new URL(UpdateData.BackupURL +
                    SS.PSName +
                    UpdateData.ext);
            
            // Creating con
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "WINGS07/ProtocolSupportUpdater");

            // Get input stream
            try (InputStream input = con.getInputStream()) {
            	Files.copy(input, dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
              	}

            s.sendMessage(SS.prefix + ChatColor.RED + "Update from backup server success!");
        } catch (Exception ex) {
        	ex.printStackTrace();
            s.sendMessage(SS.prefix + ChatColor.RED + "Update from backup server failed, " + ex.getMessage());
        } finally {
        	updateInProgress = false;
        }
    }
}
