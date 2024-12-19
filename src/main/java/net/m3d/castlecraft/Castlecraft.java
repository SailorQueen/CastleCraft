package net.m3d.castlecraft;

import com.mojang.logging.LogUtils;
import net.m3d.castlecraft.Capabilitys.SoulCapability;
import net.m3d.castlecraft.Capabilitys.SoulDropHandler;
import net.m3d.castlecraft.Capabilitys.SoulHUDRenderer;
import net.m3d.castlecraft.commands.SoulCountCommand;
import net.m3d.castlecraft.item.ModCreativeModTab;
import net.m3d.castlecraft.item.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

import static net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get;

@SuppressWarnings("unused")
@Mod(Castlecraft.MOD_ID)
public class Castlecraft {

    public static final String MOD_ID = "castlecraft";
    public static final Logger LOGGER = LogUtils.getLogger();
@SuppressWarnings("removal")
    public Castlecraft() {
    MinecraftForge.EVENT_BUS.register(SoulCapability.class);
        SoulDropHandler.register();
        SoulHUDRenderer.register();
        IEventBus modEventBus = get().getModEventBus();
        ModCreativeModTab.register(modEventBus);
        ModItems.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(new SoulHUDRenderer());

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        // Registra esta clase para los eventos globales
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Configuración común
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // Agregar elementos a pestañas creativas
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        Castlecraft.LOGGER.info("Registering commands...");
        SoulCountCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Lógica al iniciar el servidor
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Configuración del cliente
        }
    }
}
