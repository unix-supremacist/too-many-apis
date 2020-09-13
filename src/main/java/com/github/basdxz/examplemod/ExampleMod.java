package com.github.basdxz.examplemod;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
//Uncomment as part of shadow example
//import reactor.core.publisher.Flux;

import static com.github.basdxz.examplemod.Reference.*;

@Mod(modid = MODID, name = Reference.NAME, version = VERSION)
public class ExampleMod {
    @EventHandler
    public void init(FMLInitializationEvent event) {
        //Uncomment as part of shadow example
        //String e = Flux.class.getSimpleName();
        //System.out.println("DIRT BLOCK, WoaG and Flux! >> " + Blocks.dirt.getUnlocalizedName() + " WOAG " + e);

        //Some example code
        System.out.println("DIRT BLOCK >> " + Blocks.dirt.getUnlocalizedName());
    }
}
