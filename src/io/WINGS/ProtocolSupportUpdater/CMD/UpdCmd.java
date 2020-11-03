package io.WINGS.ProtocolSupportUpdater.CMD;

import org.bukkit.command.CommandSender;

import io.WINGS.PluginUpdater.ProtocolSupport.Update;

public class UpdCmd {

	public UpdCmd(CommandSender s) {
		new Update(s);
	}
}
