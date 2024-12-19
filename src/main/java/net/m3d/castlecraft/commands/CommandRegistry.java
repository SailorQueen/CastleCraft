package net.m3d.castlecraft.commands;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.m3d.castlecraft.Castlecraft;

@Mod.EventBusSubscriber
public class CommandRegistry {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        Castlecraft.LOGGER.info("Registering commands...");
        SoulCountCommand.register(event.getDispatcher());
    }
}
