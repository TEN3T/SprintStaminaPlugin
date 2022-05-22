package com.esther.stamina.command;

import com.esther.stamina.main.StaminaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UserCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equals("스테미나")) {
            if (!commandSender.isOp()) {
                commandSender.sendMessage("[System]: 관리자 전용 명령어 입니다");
                return true;
            }
            try {
                switch (strings[0]) {
                    case "최대치":
                        if (strings.length == 2) {
                            int value = Integer.parseInt(strings[1]);
                            if (value < StaminaPlugin.STAMINA_MIN_VALUE)
                                commandSender.sendMessage("[ERROR]: 스테미나 최대치의 최솟값은 " + StaminaPlugin.STAMINA_MIN_VALUE + "입니다");
                            else {
                                StaminaPlugin.staminaData.get(commandSender.getName()).setSTAMINA_MAX_VALUE(value);
                                ((Player)commandSender).setLevel(value);
                                commandSender.sendMessage("[System]: 스테미나 최대치가 " + value + "으로 설정되었습니다");
                            }
                        } else if (strings.length == 3) {
                            int value = Integer.parseInt(strings[2]);
                            if (value < StaminaPlugin.STAMINA_MIN_VALUE)
                                commandSender.sendMessage("[ERROR]: 스테미나 최대치의 최솟값은 " + StaminaPlugin.STAMINA_MIN_VALUE + "입니다");
                            else {
                                Player temp = Bukkit.getPlayer(strings[1]);
                                if (temp == null)
                                    commandSender.sendMessage("[ERROR]: 없는 플레이어 입니다");
                                else {
                                    StaminaPlugin.staminaData.get(temp.getName()).setSTAMINA_MAX_VALUE(value);
                                    temp.setLevel(value);
                                    temp.sendMessage("[System]: 스테미나 최대치가 " + value + "으로 설정되었습니다");
                                    commandSender.sendMessage("[System]: " + temp.getName() + "님의 스테미나 최대치가 " + value + "으로 설정되었습니다");
                                }
                            }
                        }
                        break;
                    case "회복수치":
                        if (strings.length == 2) {
                            int value = Integer.parseInt(strings[1]);
                            if (value < 1)
                                commandSender.sendMessage("[ERROR]: 1 이상의 숫자를 입력해 주세요");
                            else {
                                StaminaPlugin.staminaData.get(commandSender.getName()).setSTAMINA_INCREASE_VALUE_WORK(value);
                                commandSender.sendMessage("[System]: 초당 회복값이 " + value + "으로 설정되었습니다");
                            }
                        } else if (strings.length == 3) {
                            int value = Integer.parseInt(strings[2]);
                            if (value < 1)
                                commandSender.sendMessage("[ERROR]: 1 이상의 숫자를 입력해 주세요");
                            else {
                                Player temp = Bukkit.getPlayer(strings[1]);
                                if (temp == null)
                                    commandSender.sendMessage("[ERROR]: 없는 플레이어 입니다");
                                else {
                                    StaminaPlugin.staminaData.get(temp.getName()).setSTAMINA_INCREASE_VALUE_WORK(value);
                                    temp.sendMessage("[System]: 초당 회복값이 " + value + "으로 설정되었습니다");
                                    commandSender.sendMessage("[System]: " + temp.getName() + "님의 초당 회복값이 " + value + "으로 설정되었습니다");
                                }
                            }
                        }
                        break;
                    case "감소수치":
                        if (strings.length == 2) {
                            int value = Integer.parseInt(strings[1]);
                            if (value < 1 || value >= StaminaPlugin.staminaData.get(commandSender.getName()).getSTAMINA_MAX_VALUE())
                                commandSender.sendMessage("[ERROR]: 1이상, 스테미나 MAX 수치보다 작은 숫자를 입력해 주세요");
                            else {
                                StaminaPlugin.staminaData.get(commandSender.getName()).setSTAMINA_DECREASE_VALUE(value);
                                commandSender.sendMessage("[System]: 초당 감소값이 " + value + "으로 설정되었습니다");
                            }
                        } else if (strings.length == 3) {
                            int value = Integer.parseInt(strings[2]);
                            Player temp = Bukkit.getPlayer(strings[1]);
                            if (temp == null)
                                commandSender.sendMessage("[ERROR]: 없는 플레이어 입니다");
                            else {
                                if (value < 1 || value >= StaminaPlugin.staminaData.get(temp.getName()).getSTAMINA_MAX_VALUE())
                                    commandSender.sendMessage("[ERROR]: 1이상, 스테미나 MAX 수치보다 작은 숫자를 입력해 주세요");
                                else {
                                    StaminaPlugin.staminaData.get(temp.getName()).setSTAMINA_DECREASE_VALUE(value);
                                    temp.sendMessage("[System]: 초당 감소값이" + value + "으로 설정되었습니다");
                                    commandSender.sendMessage("[System]: " + temp.getName() + "님의 초당 감소값이 " + value + "으로 설정되었습니다");
                                }
                            }
                        }
                        break;
                    case "최대치증가":
                        if (strings.length == 2) {
                            int value = Integer.parseInt(strings[1]);
                            if (value < 1)
                                commandSender.sendMessage("[ERROR]: 1이상의 값을 입력해주세요");
                            else {
                                StaminaPlugin.staminaData.get(commandSender.getName()).setSTAMINA_MAX_VALUE(
                                        StaminaPlugin.staminaData.get(commandSender.getName()).getSTAMINA_MAX_VALUE() + value
                                );

                                ((Player)commandSender).setLevel(StaminaPlugin.staminaData.get(commandSender.getName()).getSTAMINA_MAX_VALUE());
                                commandSender.sendMessage("[System]: 스테미나 최대치가 " + value + "만큼 증가되었습니다");
                            }
                        } else if (strings.length == 3) {
                            int value = Integer.parseInt(strings[2]);
                            if (value < 1)
                                commandSender.sendMessage("[ERROR]: 1이상의 값을 입력해주세요");
                            else {
                                Player temp = Bukkit.getPlayer(strings[1]);
                                if (temp == null)
                                    commandSender.sendMessage("[ERROR]: 없는 플레이어 입니다");
                                else {
                                    StaminaPlugin.staminaData.get(temp.getName()).setSTAMINA_MAX_VALUE(
                                            StaminaPlugin.staminaData.get(temp.getName()).getSTAMINA_MAX_VALUE() + value
                                    );
                                    temp.setLevel(StaminaPlugin.staminaData.get(commandSender.getName()).getSTAMINA_MAX_VALUE());
                                    temp.sendMessage("[System]: 스테미나 최대치가 " + value + "만큼 증가되었습니다");
                                    commandSender.sendMessage("[System]: " + temp.getName() + "님의 스테미나 최대치가 " + value + "만큼 증가되었습니다");
                                }
                            }
                        }
                        break;
                    case "최대치감소":
                        if (strings.length == 2) {
                            int value = Integer.parseInt(strings[1]);
                            if ((value < 1) ||
                                    (StaminaPlugin.staminaData.get(commandSender.getName()).getSTAMINA_MAX_VALUE() - value > StaminaPlugin.STAMINA_MIN_VALUE))
                                commandSender.sendMessage("[ERROR]: 1이상, " +
                                        (StaminaPlugin.staminaData.get(commandSender.getName()).getSTAMINA_MAX_VALUE() - StaminaPlugin.STAMINA_MIN_VALUE) + "보다 작은값을 입력해주세요");
                            else {
                                StaminaPlugin.staminaData.get(commandSender.getName()).setSTAMINA_MAX_VALUE(
                                        StaminaPlugin.staminaData.get(commandSender.getName()).getSTAMINA_MAX_VALUE() - value
                                );
                                ((Player)commandSender).setLevel(StaminaPlugin.staminaData.get(commandSender.getName()).getSTAMINA_MAX_VALUE());
                                commandSender.sendMessage("[System]: 스테미나 최대치가 " + value + "만큼 감소되었습니다");
                            }
                        } else if (strings.length == 3) {
                            int value = Integer.parseInt(strings[2]);
                            if ((value < 1) ||
                                    (StaminaPlugin.staminaData.get(commandSender.getName()).getSTAMINA_MAX_VALUE() - value > StaminaPlugin.STAMINA_MIN_VALUE))
                                commandSender.sendMessage("[ERROR]: 1이상, " +
                                        (StaminaPlugin.staminaData.get(commandSender.getName()).getSTAMINA_MAX_VALUE() - StaminaPlugin.STAMINA_MIN_VALUE) + "보다 작은값을 입력해주세요");
                            else {
                                Player temp = Bukkit.getPlayer(strings[1]);
                                if (temp == null)
                                    commandSender.sendMessage("[ERROR]: 없는 플레이어 입니다");
                                else {
                                    StaminaPlugin.staminaData.get(temp.getName()).setSTAMINA_MAX_VALUE(
                                            StaminaPlugin.staminaData.get(temp.getName()).getSTAMINA_MAX_VALUE() - value
                                    );
                                    temp.setLevel(StaminaPlugin.staminaData.get(commandSender.getName()).getSTAMINA_MAX_VALUE());
                                    temp.sendMessage("[System]: 스테미나 최대치가 " + value + "만큼 감소되었습니다");
                                    commandSender.sendMessage("[System]: " + temp.getName() + "님의 스테미나 최대치가 " + value + "만큼 감소되었습니다");
                                }
                            }
                        }
                        break;
                    case "회복":
                        if (strings.length == 2) {
                            int value = Integer.parseInt(strings[1]);
                            if (value < 1)
                                commandSender.sendMessage("[ERROR]: 1이상의 값을 입력해주세요");
                            else {
                                int currLevel = ((Player) commandSender).getLevel();
                                if ((value + currLevel) >= StaminaPlugin.staminaData.get(commandSender.getName()).getSTAMINA_MAX_VALUE())
                                    ((Player) commandSender).setLevel(StaminaPlugin.staminaData.get(commandSender.getName()).getSTAMINA_MAX_VALUE());
                                ((Player) commandSender).setLevel(currLevel + value);
                            }
                        } else if (strings.length == 3) {
                            int value = Integer.parseInt(strings[2]);
                            if (value < 1)
                                commandSender.sendMessage("[ERROR]: 1이상의 값을 입력해주세요");
                            else {
                                Player temp = Bukkit.getPlayer(strings[1]);
                                if (temp == null)
                                    commandSender.sendMessage("[ERROR]: 없는 플레이어 입니다");
                                else {
                                    int currLevel = temp.getLevel();
                                    if ((value + currLevel) >= StaminaPlugin.staminaData.get(temp.getName()).getSTAMINA_MAX_VALUE())
                                        temp.setLevel(StaminaPlugin.staminaData.get(temp.getName()).getSTAMINA_MAX_VALUE());
                                    temp.setLevel(currLevel + value);
                                }
                            }
                        }
                        break;
                    case "감소중지":
                        if (strings.length == 2) {
                            if (strings[1].equals("전체")){
                                StaminaPlugin.ALL_PLAYER_STAMINA_STATE = !StaminaPlugin.ALL_PLAYER_STAMINA_STATE;
                                for(Player p : Bukkit.getOnlinePlayers())
                                    StaminaPlugin.staminaData.get(p.getName()).setSTAMINA_STATE(StaminaPlugin.ALL_PLAYER_STAMINA_STATE);
                            }else{
                                Player temp = Bukkit.getPlayer(strings[1]);
                                if(temp == null)
                                    commandSender.sendMessage("[ERROR]: 없는 플레이어 입니다");
                                else{
                                    StaminaPlugin.staminaData.get(temp.getName()).setSTAMINA_STATE(
                                            !StaminaPlugin.staminaData.get(temp.getName()).getSTAMINA_STATE()
                                    );
                                }
                            }
                        }
                        break;
                    default:
                        commandSender.sendMessage(ChatColor.GOLD+"[Stamina Help]: ===사용 방법===");
                        commandSender.sendMessage(ChatColor.AQUA+"[주의사항]: 스테미나 최대값은 50이상이여야 합니다");
                        commandSender.sendMessage(ChatColor.AQUA+"[/스테미나 최대치 x]: 스테미나 최대치를 x로 변경합니다");
                        commandSender.sendMessage(ChatColor.AQUA+"[/스테미나 최대치 p x]: 플레이어 p의 스테미나 최대치를 x로 바꿉니다");
                        commandSender.sendMessage(ChatColor.AQUA+"[/스테미나 최대치증가 x]: 스테미나 최대치를 x만큼 증가시킵니다");
                        commandSender.sendMessage(ChatColor.AQUA+"[/스테미나 최대치증가 p x]: 플레이어 p의 스테미나 최대치를 x만큼 증가시킵니다");
                        commandSender.sendMessage(ChatColor.AQUA+"[/스테미나 최대치감소 x]: 스테미나 최대치를 x만큼 감소시킵니다");
                        commandSender.sendMessage(ChatColor.AQUA+"[/스테미나 최대치감소 p x]: 플레이어 p의 스테미나 최대치를 x만큼 감소시킵니다");
                        commandSender.sendMessage(ChatColor.AQUA+"[/스테미나 회복수치 x]: 스테미나 회복수치를 x로 변경합니다");
                        commandSender.sendMessage(ChatColor.AQUA+"[/스테미나 회복수치 p x]: 플레이어 p의 스테미나 회복수치를 x로 변경합니다");
                        commandSender.sendMessage(ChatColor.AQUA+"[/스테미나 감소수치 x]: 스테미나 감소수치를 x로 변경합니다");
                        commandSender.sendMessage(ChatColor.AQUA+"[/스테미나 감소수치 p x]: 플레이어 p의 감소수치를 x로 변경합니다");
                        commandSender.sendMessage(ChatColor.AQUA+"[/스테미나 회복 x]: 스테미나를 x만큼 회복시킵니다");
                        commandSender.sendMessage(ChatColor.AQUA+"[/스테미나 회복 p x]: 플레이어 p의 스테미나를 x만큼 회복시킵니다");
                        commandSender.sendMessage(ChatColor.AQUA+"[/스테미나 감소중지 전체]: 전체 플레이어의 스테미나 감소를 중지시킵니다");
                        commandSender.sendMessage(ChatColor.AQUA+"[/스테미나 감소중지 p]: 플레이어 p의 스테미나 감소를 중지시킵니다");
                }
            } catch (NumberFormatException e) {
                commandSender.sendMessage("[ERROR]: 올바른 값을 입력해 주세요");
            }
        }
        return true;
    }
}
