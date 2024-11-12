package net.m3d.castlecraft.item;

import net.m3d.castlecraft.Castlecraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =

            DeferredRegister.create(ForgeRegistries.ITEMS, Castlecraft.MOD_ID);



    public static final RegistryObject<Item> SANGUINE = ITEMS.register("sanguine",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TAB = ITEMS.register("tab",
            () -> new Item(new Item.Properties()));



public static void register(IEventBus eventBus) {

    ITEMS.register(eventBus);

}
}
