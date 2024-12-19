package net.m3d.castlecraft.Capabilitys;

import net.m3d.castlecraft.Capabilitys.SoulCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;


@SuppressWarnings("unused")
public class SoulHUDRenderer {
    public static void register() {
        MinecraftForge.EVENT_BUS.register(new SoulHUDRenderer());
    }

    @SubscribeEvent
    public void onRenderHUD(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            mc.player.getCapability(SoulCapability.SOUL_CAPABILITY).ifPresent(data -> {
                int souls = data.getSouls();

                // Usa GuiGraphics para dibujar en la pantalla
                event.getGuiGraphics().drawString(
                    mc.font, // Fuente
                    Component.literal("Almas: " + souls), // Texto
                    10, // Coordenada X
                    10, // Coordenada Y
                    0xFFFFFF, // Color blanco
                    false // Sin sombra
                );
            });
        }
    }
}