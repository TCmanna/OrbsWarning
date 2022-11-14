package com.tcmanna.orbswarning;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = OrbsWarningMod.MODID,
        version = OrbsWarningMod.VERSION,
        name = OrbsWarningMod.NAME,
        acceptedMinecraftVersions = OrbsWarningMod.Versions,
        guiFactory = "com.tcmanna.orbswarning.ConfigGuiFactory"
)
public class OrbsWarningMod
{
    public static final String MODID = "orbswarning";
    public static final String VERSION = "1.0";
    public static final String NAME = "OrbsWarning";
    public static final String Versions = "[1.8.9]";

    public static Configuration configFile;
    public static boolean config_mod_enable;
    public static boolean config_windows_message_enable;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new TickListener());
        MinecraftForge.EVENT_BUS.register(new ConfigChangeListener());

        configFile = new Configuration(event.getSuggestedConfigurationFile());
        syncConfig();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    public static void syncConfig() {
        config_mod_enable = configFile.getBoolean("Enable the Mod", "general", true, "Enable orb alert", "OW.cfg.enable");
        config_windows_message_enable = configFile.getBoolean("Enable Windows Message", "general", true, "Enable Windows message alert for Orb spawn.", "OW.cfg.windows");
        if (configFile.hasChanged())
            configFile.save();
    }

}
