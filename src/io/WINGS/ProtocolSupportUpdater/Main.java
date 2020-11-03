package io.WINGS.ProtocolSupportUpdater;

import java.util.logging.Logger;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.WINGS.ProtocolSupportUpdater.storage.SS;

public class Main extends JavaPlugin implements Listener {

	public Logger log = getLogger();
	private static CommandListener exec = new CommandListener();
	
	public void onEnable() {
		log.info(SS.Loading);
		//Reg cmd
		getCommand(SS.maincmd).setExecutor(exec);
		log.info(SS.CoreVer);
		log.info(SS.PluginVer);
		log.info(SS.Author);
	}
	
	public void onDisable() {
		log.info(SS.Unloading);
	}
}
