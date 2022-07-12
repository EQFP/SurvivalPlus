package veth.vetheon.survival.commands;

import org.bukkit.entity.Player;
import veth.vetheon.survival.Survival;
import veth.vetheon.survival.config.Lang;
import veth.vetheon.survival.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {

	private final Survival plugin;
	private final Lang lang;

	public Reload(Survival plugin) {
		this.plugin = plugin;
		this.lang = plugin.getLang();
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		plugin.loadSettings(sender);
		sender.sendMessage(Utils.getColoredString(lang.prefix + "&aReload complete"));
		return true;
	}

}