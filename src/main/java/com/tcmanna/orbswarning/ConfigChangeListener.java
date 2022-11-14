package com.tcmanna.orbswarning;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigChangeListener {
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.modID.equals(OrbsWarningMod.MODID)) {
            OrbsWarningMod.syncConfig();
        }
    }
}
