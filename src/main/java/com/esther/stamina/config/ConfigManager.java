package com.esther.stamina.config;

import com.esther.stamina.main.StaminaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class ConfigManager {

    private final static String fileName = "playerData.yml";
    private StaminaPlugin plugin = null;
    private FileConfiguration config = null;
    private File file = null;

    public ConfigManager(StaminaPlugin plugin){
        this.plugin = plugin;
        this.saveDefaultConfig();
    }

    public void reloadConfig(){
        if(this.file == null)
            this.file = new File(this.plugin.getDataFolder(), fileName);
        this.config = YamlConfiguration.loadConfiguration(this.file);
        InputStream stream = this.plugin.getResource(fileName);
        if(stream != null){
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(new InputStreamReader(stream));
            this.config.setDefaults(yaml);
        }
    }

    public FileConfiguration getConfig(){
        if(this.config == null)
            this.reloadConfig();
        return this.config;
    }

    public void saveConfig(){
        if(this.config == null || this.file == null)
            return;
        try{
            this.getConfig().save(this.file);
        }catch(IOException e){
            this.plugin.getLogger().log(Level.SEVERE, "파일을 저장할 수 없습니다", e);
        }
    }

    public void saveDefaultConfig(){
        if(this.file == null)
            this.file = new File(this.plugin.getDataFolder(), fileName);
        if(!this.file.exists())
            this.plugin.saveResource(fileName, true);
    }

}
