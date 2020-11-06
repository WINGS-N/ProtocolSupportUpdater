package io.WINGS.ProtocolSupportUpdater;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import io.WINGS.ProtocolSupportUpdater.CMD.HelpCmd;
import io.WINGS.ProtocolSupportUpdater.CMD.UpdCmd;
import io.WINGS.ProtocolSupportUpdater.storage.SS;

public class CommandListener implements Listener, CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase(SS.maincmd)) {

            if(args.length == 0) {
            	if(s.hasPermission(SS.helpperm)) {
            		new HelpCmd(s);
            	}
            }
            else {
            	switch(args[0].toLowerCase()) {
            	case "u":
            		if(s.hasPermission(SS.updperm)) {
            			new UpdCmd(s);
            		}
            		break;
            	case "upd":
            		if(s.hasPermission(SS.updperm)) {
            			new UpdCmd(s);
            		}
            		break;
            	case "update":
            		if(s.hasPermission(SS.updperm)) {
            			new UpdCmd(s);
            		}
            		break;
            	case "h":
            		if(s.hasPermission(SS.helpperm)) {
            			new HelpCmd(s);
            		}
            		break;
            	case "?":
            		if(s.hasPermission(SS.helpperm)) {
            			new HelpCmd(s);
            		}
            		break;
            	case "help":
            		if(s.hasPermission(SS.helpperm)) {
            			new HelpCmd(s);
            		}
            		break;
            	default:
            		if(s.hasPermission(SS.helpperm)) {
            			new HelpCmd(s);
            		} else {
            			s.sendMessage(SS.noperms);
            		}
            	}
            }
        }
        return true;
    }
}
