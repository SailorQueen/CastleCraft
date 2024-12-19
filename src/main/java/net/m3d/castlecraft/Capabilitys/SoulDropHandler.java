package net.m3d.castlecraft.Capabilitys;

import net.m3d.castlecraft.Capabilitys.SoulCapability;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@SuppressWarnings("unused")



public class SoulDropHandler {

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new SoulDropHandler());
    }

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        // Verifica si el atacante fue un jugador
        if (event.getSource().getEntity() instanceof Player player) {
            // Incrementa las almas del jugador
            player.getCapability(SoulCapability.SOUL_CAPABILITY).ifPresent(data -> {
                data.addSouls(5); // Añade 5 almas (puedes ajustar este valor)
            });
        }
    }
}





































