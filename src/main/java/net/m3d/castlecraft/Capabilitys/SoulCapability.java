package net.m3d.castlecraft.Capabilitys;

import net.m3d.castlecraft.Castlecraft;
import org.antlr.v4.parse.ANTLRParser.finallyClause_return;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber
@SuppressWarnings("unused")
public class SoulCapability {

    public static final Capability<ISoulData> SOUL_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {

    });


        public interface ISoulData extends INBTSerializable<CompoundTag> {
            int getSouls();
            void addSouls(int amount);
            void removeSouls(int amount);
            void setSouls(int amount);
        }

        public static class SoulData implements ISoulData {

            private int souls;
            @Override

            public int getSouls() {
                return souls;
            }

            @Override
            public void addSouls(int amount) {
                this.souls += amount;
            }

            @Override
            public void removeSouls(int amount) {
                this.souls = Math.max(0, this.souls - amount);
            }

            @Override
            public void setSouls(int amount) {
                this.souls = amount;
            }

            @Override
            public CompoundTag serializeNBT() {
                CompoundTag tag = new CompoundTag();
                tag.putInt("Souls", souls);
                return tag;
            }

            @Override
            public void deserializeNBT(CompoundTag nbt) {
                this.souls = nbt.getInt("Souls");
            }
        }



    public static class Provider implements ICapabilitySerializable<CompoundTag> {
            private final LazyOptional<ISoulData> instance = LazyOptional.of(SoulData::new);

            @Override
            public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
                // Retorna la Capability correcta si coincide
                return cap == SOUL_CAPABILITY ? instance.cast() : LazyOptional.empty();
            }

            @Override
            public CompoundTag serializeNBT() {
                // Serializa la NBT de la instancia
                return instance.orElseThrow(() -> new IllegalStateException("Capability not found!")).serializeNBT();
            }

            @Override
            public void deserializeNBT(CompoundTag nbt) {
                // Deserializa la NBT en la instancia
                instance.orElseThrow(() -> new IllegalStateException("Capability not found!")).deserializeNBT(nbt);
            }
        }
    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Player> event) {
        if (!event.getObject().getCapability(SOUL_CAPABILITY).isPresent()) {
            event.addCapability(
                    new ResourceLocation(Castlecraft.MOD_ID, "soul_capability"),
                    new Provider()
            );
            Castlecraft.LOGGER.info("Soul capability attached to player: " + event.getObject().getName().getString());
        }
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event) {
        Castlecraft.LOGGER.info("Cloning soul data for player: " + event.getEntity().getName().getString());
        event.getOriginal().getCapability(SOUL_CAPABILITY).ifPresent(oldData -> {
            event.getEntity().getCapability(SOUL_CAPABILITY).ifPresent(newData -> {
                newData.setSouls(oldData.getSouls());
                Castlecraft.LOGGER.info("Souls cloned: " + oldData.getSouls());
            });
        });
    }

}

