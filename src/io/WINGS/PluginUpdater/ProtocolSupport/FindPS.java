package io.WINGS.PluginUpdater.ProtocolSupport;

import java.util.logging.Logger;

import org.bukkit.Bukkit;

import io.WINGS.ProtocolSupportUpdater.Main;
import io.WINGS.ProtocolSupportUpdater.storage.SS;

public class FindPS extends Main {

	Logger log = getLogger();
	
	public FindPS() {
		if(Bukkit.getPluginManager().getPlugin(SS.PSName) == null) {
			log.warning(SS.PSNotFound);
			new Update(Bukkit.getServer().getConsoleSender());
		}
	}
}
