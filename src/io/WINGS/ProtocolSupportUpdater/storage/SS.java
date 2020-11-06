package io.WINGS.ProtocolSupportUpdater.storage;

import org.bukkit.Bukkit;

public interface SS {

	

		
	
	//Plugin
	String PluginName = "ProtocolSupportUpdater";
	String PSName = "ProtocolSupport";
	
	//Load
	String Loading = "Loading...";
	String Unloading = "Unloading";
	String PSNotFound = "ProtocolSupport not found! Downloading...";
	String CoreVer = "Core Version Detected: " + Bukkit.getVersion();
	String Author = "By WINGS7";
	String PluginVer = "Plugin Version Detected: " + Bukkit.getPluginManager().getPlugin(PluginName).getDescription().getVersion();
	
	//Commands
	String maincmd = "psu";
	
	//Perms
	String updperm = "wings.psu.upd";
	String helpperm = "wings.psu.help";
	
	//Help
	String help1 = "/psu u - update ProtocolSupport";
	
	//GetJAR
	String JenkinsURL = "https://build.true-games.org/job/ProtocolSupport/lastSuccessfulBuild/artifact/target/";
	String ext = ".jar";
}
