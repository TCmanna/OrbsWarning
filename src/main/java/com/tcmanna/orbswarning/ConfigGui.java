package com.tcmanna.orbswarning;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ConfigGui extends GuiConfig {
    public ConfigGui(GuiScreen parent) {
        super(parent, (new ConfigElement(OrbsWarningMod.configFile.getCategory("general"))).getChildElements(), OrbsWarningMod.MODID, false, false, "OrbsWarning Settings");
    }
}
