package com.Apothic0n.Winternal;

import net.minecraft.world.level.GameRules;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Winternal.MODID)
public class Winternal {
    public static final String MODID = "winternal";
    public static boolean enabled = false;
    public static GameRules.Key<GameRules.BooleanValue> gameRule = null;

    public Winternal() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            gameRule = GameRules.register("winternal", GameRules.Category.MISC, GameRules.BooleanValue.create(false));
        });
    }
}