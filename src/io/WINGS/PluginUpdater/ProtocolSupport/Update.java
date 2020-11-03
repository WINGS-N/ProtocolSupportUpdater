package io.WINGS.PluginUpdater.ProtocolSupport;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import io.WINGS.ProtocolSupportUpdater.storage.SS;
import net.md_5.bungee.api.ChatColor;

public class Update {

	public Boolean updateInProgress = false;
	
    public Update(CommandSender s) {
        if(updateInProgress) {
            s.sendMessage(ChatColor.RED + "Update already in progress");
            return;
        }
        
        s.sendMessage(ChatColor.RED + "Please hold, now downloading..");
        
        updateInProgress = true;
        @SuppressWarnings("unused")
		File psFile = null;

        try {
        	Method getFile = JavaPlugin.class.getDeclaredMethod("getFile");
            getFile.setAccessible(true);
            File dest = new File("plugins/" + SS.PSName + SS.ext);

            //Connect
            URL url =
            new URL(SS.JenkinsURL +
                    SS.PSName +
                    SS.ext);
            
            // Creating con
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "WINGS07/ProtocolSupportUpdater");

            // Get input stream
            try (InputStream input = con.getInputStream()) {
            	Files.copy(input, dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
              	}

            s.sendMessage(ChatColor.RED + "Update success! Restart server to finish update!");
        } catch (Exception ex) {
        	ex.printStackTrace();
            s.sendMessage(ChatColor.RED + "Update failed, " + ex.getMessage());
        } finally {
        	updateInProgress = false;
        }
    }
}
