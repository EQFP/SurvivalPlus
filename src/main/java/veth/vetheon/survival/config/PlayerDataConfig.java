package veth.vetheon.survival.config;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import veth.vetheon.survival.Survival;
import veth.vetheon.survival.data.PlayerData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDataConfig {

	private final Survival plugin;
	private File playerDirectory = null;

    public PlayerDataConfig(Survival plugin) {
		this.plugin = plugin;
		loadPlayerDirectory();
	}

	private void loadPlayerDirectory() {
		if (playerDirectory == null) {
			playerDirectory = new File(plugin.getDataFolder(), "playerData");
		}
		if (!playerDirectory.exists()) {
			//noinspection ResultOfMethodCallIgnored
			playerDirectory.mkdir();
		}
	}

	public boolean needsConversion() {
		File file = new File(playerDirectory, "converted.yml");
		return !file.exists();
	}

	public void createConvertedFile(int conversions) {
		File file = new File(playerDirectory, "converted.yml");
		YamlConfiguration converted = YamlConfiguration.loadConfiguration(file);
		converted.set("converted", conversions);
		List<String> t = new ArrayList<String>();
		t.add("This file is a placeholder, do not delete this file");
		converted.options().setHeader(t);
		saveFile(converted, file);
	}

	public boolean hasPlayerDataFile(OfflinePlayer player) {
		File file = new File(playerDirectory, player.getUniqueId().toString() + ".yml");
		return file.exists();
	}

	public PlayerData getPlayerDataFromFile(OfflinePlayer player) {
		File file = new File(playerDirectory, player.getUniqueId().toString() + ".yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

		return ((PlayerData) config.get("player-data"));
	}

	public void savePlayerDataToFile(PlayerData playerData) {
		File file = new File(playerDirectory, playerData.getUuid().toString() + ".yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		config.set("player-data", playerData);
		saveFile(config, file);
	}

	private void saveFile(YamlConfiguration config, File file) {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
