package com.tcmanna.orbswarning;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;

public class TickListener {
    static int c = 0;
    static boolean HasOrb = false;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (OrbsWarningMod.config_mod_enable) {
            if (!Minecraft.getMinecraft().isGamePaused() && Minecraft.getMinecraft().thePlayer != null) {
                checkOrbSpawnOnTitle();
            }
        }
    }

    private static void checkOrbSpawnOnTitle() {
        final Minecraft mc = Minecraft.getMinecraft();
        final EntityPlayer thePlayer = mc.thePlayer;
        String title;
        String subTitle;
        try {
            title = (String) ReflectionHelper.findField(GuiIngame.class, "displayedTitle", "field_175201_x").get(Minecraft.getMinecraft().ingameGUI);
            subTitle = (String) ReflectionHelper.findField(GuiIngame.class, "displayedSubTitle", "field_175200_y").get(Minecraft.getMinecraft().ingameGUI);

            if (title != null) {
                if (title.contains("警告") || title.contains("WARN"))
                {
                    HasOrb = true;
                    if (c < 1) {
                        thePlayer.addChatMessage(new ChatComponentText(title));
                        thePlayer.addChatMessage(new ChatComponentTranslation("OW.getOrbMsg"));
                        if (SystemTray.isSupported() && OrbsWarningMod.config_windows_message_enable) {
                            try { displayTray(); }
                            catch (AWTException e) { e.printStackTrace(); }
                        }
                    }
                    c++;
                }

                else
                {
                    if (c != 0)
                        c = 0;
                    if (HasOrb) {
                        HasOrb = false;
                        thePlayer.addChatMessage(new ChatComponentText(title + " " + subTitle));
                    }
                }

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void displayTray() throws AWTException {

        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        if (Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getLanguageCode().equals("zh_CN")) {
            TrayIcon trayIcon = new TrayIcon(image, "宝珠警告");

            trayIcon.setImageAutoSize(true);

            trayIcon.setToolTip("宝珠警告");

            tray.add(trayIcon);

            trayIcon.displayMessage("已钓上宝珠", "你有3秒时间来钝化", TrayIcon.MessageType.INFO);

            tray.remove(trayIcon);
        }
        else {
            TrayIcon trayIcon = new TrayIcon(image, "OrbWarning");

            trayIcon.setImageAutoSize(true);

            trayIcon.setToolTip("OrbWarning");

            tray.add(trayIcon);

            trayIcon.displayMessage("you got an Orb", "You have 3 seconds to process", TrayIcon.MessageType.INFO);

            tray.remove(trayIcon);
        }
    }
}
