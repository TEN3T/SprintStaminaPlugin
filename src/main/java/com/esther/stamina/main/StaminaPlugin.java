package com.esther.stamina.main;

import com.esther.stamina.command.UserCommand;
import com.esther.stamina.config.ConfigManager;
import com.esther.stamina.events.PlayerEvents;
import com.esther.stamina.player.PlayerObject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.logging.Logger;

public class StaminaPlugin extends JavaPlugin {

    public final static int STAMINA_MIN_VALUE = 1;
    public final static int STAMINA_RELOAD_TIME = 1;
    public ConfigManager config;

    public static boolean ALL_PLAYER_STAMINA_STATE = true; //default value : true(on)

    public static Logger logger;

    public static HashMap<String, PlayerObject> staminaData;

    public void loadConfigFile() {
        config = new ConfigManager(this);
        staminaData = new HashMap<>();
        for(String key : config.getConfig().getKeys(false)) {
            getLogger().severe(key);
            PlayerObject temp = new PlayerObject();
            temp.setSTAMINA_DECREASE_VALUE(config.getConfig().getInt(key+".decrease"));
            temp.setSTAMINA_INCREASE_VALUE_WORK(config.getConfig().getInt(key+".work"));
            temp.setSTAMINA_INCREASE_VALUE_STOP(config.getConfig().getInt(key+".stop"));
            temp.setSTAMINA_MAX_VALUE(config.getConfig().getInt(key+".max"));
            temp.setSTAMINA_STATE(config.getConfig().getBoolean(key+".state"));
            temp.setMOVE_STATE(config.getConfig().getBoolean(key+".move"));
            staminaData.put(key, temp);
        }
    }

    public void saveConfigFile(){
        for(String key : staminaData.keySet()){
            config.getConfig().set(key+".decrease", staminaData.get(key).getSTAMINA_DECREASE_VALUE());
            config.getConfig().set(key+".work", staminaData.get(key).getSTAMINA_INCREASE_VALUE_WORK());
            config.getConfig().set(key+".stop", staminaData.get(key).getSTAMINA_INCREASE_VALUE_STOP());
            config.getConfig().set(key+".max", staminaData.get(key).getSTAMINA_MAX_VALUE());
            config.getConfig().set(key+".state", staminaData.get(key).getSTAMINA_STATE());
            config.getConfig().set(key+".move", staminaData.get(key).getMOVE_STATE());
        }
        config.saveConfig();
    }

    @Override
    public void onEnable() {

        this.loadConfigFile();

        logger = Bukkit.getLogger(); //new logger();
        logger.info("===============================================================");
        logger.info("### 스테미나 플러그인 ###");
        logger.info("** Developed by ES4HER **");
        logger.info("** contact > email: esther78944@gmail.com  kakaoTalk: es4her");
        logger.info("===============================================================");

        getCommand("스테미나").setExecutor(new UserCommand());
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            Player[] arrPlayer = Bukkit.getOnlinePlayers().toArray(new Player[0]);

            for (Player p : arrPlayer) {
                if (staminaData.get(p.getName()).getSTAMINA_STATE()) {
                    if (p.isSprinting()) {
                        if (p.getLevel() < STAMINA_MIN_VALUE) {
                            p.setSprinting(false);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 4 * 20, 3));
                        } else {
                            int value = p.getLevel() - staminaData.get(p.getName()).getSTAMINA_DECREASE_VALUE();
                            if (value < 0)
                                p.setLevel(0);
                            else
                                p.setLevel(p.getLevel() - staminaData.get(p.getName()).getSTAMINA_DECREASE_VALUE());
                        }
                    } else if (staminaData.get(p.getName()).getMOVE_STATE()) {
                        int value = p.getLevel() + staminaData.get(p.getName()).getSTAMINA_INCREASE_VALUE_WORK();
                        if (value >= staminaData.get(p.getName()).getSTAMINA_MAX_VALUE())
                            p.setLevel(staminaData.get(p.getName()).getSTAMINA_MAX_VALUE());
                        else
                            p.setLevel(p.getLevel() + staminaData.get(p.getName()).getSTAMINA_INCREASE_VALUE_WORK());
                        staminaData.get(p.getName()).setMOVE_STATE(false);
                    } else {
                        int value = p.getLevel() + staminaData.get(p.getName()).getSTAMINA_INCREASE_VALUE_STOP();
                        if (value >= staminaData.get(p.getName()).getSTAMINA_MAX_VALUE())
                            p.setLevel(staminaData.get(p.getName()).getSTAMINA_MAX_VALUE());
                        else
                            p.setLevel(p.getLevel() + staminaData.get(p.getName()).getSTAMINA_INCREASE_VALUE_STOP());
                    }
                    p.setExp(((p.getLevel() / (float) staminaData.get(p.getName()).getSTAMINA_MAX_VALUE() > 1.0F) ? 1.0F : (((float) p.getLevel() / (staminaData.get(p.getName()).getSTAMINA_MAX_VALUE())))));
                }
            }
        }, 1, 20 * STAMINA_RELOAD_TIME);
    }

    @Override
    public void onDisable() {
        this.saveConfigFile();
        getLogger().info("플러그인 비활성화");
    }

}
