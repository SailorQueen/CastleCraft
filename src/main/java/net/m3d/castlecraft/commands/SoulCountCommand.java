package net.m3d.castlecraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.m3d.castlecraft.Capabilitys.SoulCapability;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class SoulCountCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("soulcount").executes(context -> {
                    return getSoulCount(context.getSource());
                })
        );
    }

    private static int getSoulCount(CommandSourceStack source) throws CommandSyntaxException {
        Player player = source.getPlayerOrException();

        if (!source.getLevel().isClientSide()) {
            player.getCapability(SoulCapability.SOUL_CAPABILITY).ifPresent(soulData -> {
                int soulCount = soulData.getSouls();
                source.sendSuccess(() -> Component.literal("You have " + soulCount + " souls."), false);
            });
        }
        return 1;
    }
}
