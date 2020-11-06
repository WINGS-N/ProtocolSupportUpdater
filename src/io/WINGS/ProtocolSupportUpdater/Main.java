package io.WINGS.ProtocolSupportUpdater;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.WINGS.PluginUpdater.SelfUpdate;
import io.WINGS.PluginUpdater.ProtocolSupport.Update;
import io.WINGS.ProtocolSupportUpdater.storage.SS;

public class Main extends JavaPlugin implements Listener {

	public Logger log = getLogger();
	private static CommandListener exec = new CommandListener();
	FileConfiguration config = this.getConfig();
	
	public void onEnable() {
		//config
		this.saveDefaultConfig();
		config.addDefault(" ", "");
		config.addDefault("UpdateOnPluginLoad", true);
		config.options().copyDefaults(true);
		this.saveConfig();
		
		log.info(SS.Loading);
		//Reg cmd
		getCommand(SS.maincmd).setExecutor(exec);
		log.info(SS.CoreVer);
		log.info(SS.PluginVer);
		log.info(SS.Author);
		
		new SelfUpdate(Bukkit.getServer().getConsoleSender());
		
		if(config.getBoolean("UpdateOnPluginLoad")) {
			new Update(Bukkit.getServer().getConsoleSender());
		}
		
	}
	
	public void onDisable() {
		log.info(SS.Unloading);
	}
}
