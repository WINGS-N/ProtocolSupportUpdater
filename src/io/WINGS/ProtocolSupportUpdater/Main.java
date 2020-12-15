package io.WINGS.ProtocolSupportUpdater;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.WINGS.PluginUpdater.SelfUpdate;
import io.WINGS.PluginUpdater.ProtocolSupport.Update;
import io.WINGS.ProtocolSupportUpdater.metrics.Metrics;
import io.WINGS.ProtocolSupportUpdater.metrics.MetricsData;
import io.WINGS.ProtocolSupportUpdater.storage.SS;

public class Main extends JavaPlugin implements Listener {

	public Logger log = getLogger();
	private static CommandListener exec = new CommandListener();
	FileConfiguration config = this.getConfig();
	Metrics m = new Metrics(this, MetricsData.id);
	
	public void onEnable() {
		//configuration
		this.saveDefaultConfig();
		config.addDefault("UpdateOnPluginLoad", true);
		config.addDefault("UseBackupServer", true);
		config.addDefault("UpdateCounter", 0);
		config.addDefault("BackupUpdateCounter", 0);
		config.addDefault("SelfUpdateCounter", 0);
		config.options().copyDefaults(true);
		this.saveConfig();
		
		log.info(SS.Loading);
		getCommand(SS.maincmd).setExecutor(exec); //register command executor
		log.info(SS.CoreVer);
		log.info(SS.PluginVer);
		log.info(SS.Author);
		
		new SelfUpdate(Bukkit.getServer().getConsoleSender());
		
		if(config.getBoolean("UpdateOnPluginLoad")) {
			new Update(Bukkit.getServer().getConsoleSender());
		}
		
		//Send metrics
		m.addCustomChart(new Metrics.SimplePie(MetricsData.UonStart, () -> (Boolean.toString(config.getBoolean("UpdateOnPluginLoad")))));
		m.addCustomChart(new Metrics.SimplePie(MetricsData.UseBackup, () -> (Boolean.toString(config.getBoolean("UseBackupServer")))));
		m.addCustomChart(new Metrics.SingleLineChart(MetricsData.UPDs, () -> config.getInt("UpdateCounter")));
		m.addCustomChart(new Metrics.SingleLineChart(MetricsData.BUPDs, () -> config.getInt("BackupUpdateCounter")));
		m.addCustomChart(new Metrics.SingleLineChart(MetricsData.SelfUPDs, () -> config.getInt("SelfUpdateCounter")));
	}
	
	public void onDisable() {
		log.info(SS.Unloading);
	}
}
