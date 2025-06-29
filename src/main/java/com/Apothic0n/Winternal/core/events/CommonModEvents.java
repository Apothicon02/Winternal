package com.Apothic0n.Winternal.core.events;

import com.Apothic0n.Winternal.Winternal;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Winternal.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonModEvents {

    @SubscribeEvent
    public static void worldLoaded(WorldEvent.Load event) {
        if (Winternal.gameRule != null) {
            LevelAccessor world = event.getWorld();
            MinecraftServer server = world.getServer();
            if (server != null) {
                Winternal.enabled = server.getGameRules().getRule(Winternal.gameRule).get();
            }
        }
    }
}
