package com.esther.stamina.events;

import com.esther.stamina.main.StaminaPlugin;
import com.esther.stamina.player.PlayerObject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerEvents implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        if(!StaminaPlugin.staminaData.containsKey(e.getPlayer().getName())) {
            StaminaPlugin.staminaData.put(e.getPlayer().getName(), new PlayerObject());
        }
        e.getPlayer().setLevel(StaminaPlugin.staminaData.get(e.getPlayer().getName()).getSTAMINA_MAX_VALUE());
    }

    @EventHandler
    public void playerDead(PlayerRespawnEvent e){
        e.getPlayer().setLevel(StaminaPlugin.staminaData.get(e.getPlayer().getName()).getSTAMINA_MAX_VALUE());
    }

    @EventHandler
    public void playerMove(PlayerMoveEvent e){
        if((e.getFrom().getX() != e.getTo().getX())||(e.getFrom().getZ() != e.getTo().getZ()))
            StaminaPlugin.staminaData.get(e.getPlayer().getName()).setMOVE_STATE(true);
    }
}
