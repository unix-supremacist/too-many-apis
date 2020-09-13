package com.github.basdxz.examplemod;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

import reactor.core.publisher.Flux;

import static com.github.basdxz.examplemod.Reference.*;

@Mod(modid = MODID, name = Reference.NAME, version = VERSION)
public class ExampleMod {
    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        String e = Flux.class.getSimpleName();
        System.out.println("DIRT BLOCK >> " + Blocks.dirt.getUnlocalizedName() + " WOAG " + e);
    }
}
