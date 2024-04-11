package com.example.examplemod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(🛠️.🏷️)
public class 🛠️
{
    // Define mod id in a common place for everything to reference
    public static final String 🏷️ = "examplemod";
    // Directly reference a slf4j logger
    private static final Logger 🪵 = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister.Blocks 🟥 = DeferredRegister.createBlocks(🏷️);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister.Items 🧩 = DeferredRegister.createItems(🏷️);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> 📑 = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, 🏷️);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final DeferredBlock<Block> ❓🟥 = 🟥.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> ❓🟥🧩 = 🧩.registerSimpleBlockItem("example_block", ❓🟥);

    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> ❓🧩 = 🧩.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build()));

    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ❓📑 = 📑.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.examplemod")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ❓🧩.get().getDefaultInstance())
            .displayItems((📖, 🛒) -> {
                🛒.accept(❓🧩.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public 🛠️(IEventBus 🛠️🎉🚌)
    {
        // Register the commonSetup method for modloading
        🛠️🎉🚌.addListener(this::😀);

        // Register the Deferred Register to the mod event bus so blocks get registered
        🟥.register(🛠️🎉🚌);
        // Register the Deferred Register to the mod event bus so items get registered
        🧩.register(🛠️🎉🚌);
        // Register the Deferred Register to the mod event bus so tabs get registered
        📑.register(🛠️🎉🚌);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        🛠️🎉🚌.addListener(this::🖌️);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, 📜.👓);
    }

    private void 😀(final FMLCommonSetupEvent 🎉)
    {
        // Some common setup code
        🪵.info("HELLO FROM COMMON SETUP");

        if (📜.🪵🟫)
            🪵.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        🪵.info(📜.🪄🧮💬 + 📜.🪄🧮);

        📜.🧩.forEach((🍎) -> 🪵.info("ITEM >> {}", 🍎.toString()));
    }

    // Add the example block item to the building blocks tab
    private void 🖌️(BuildCreativeModeTabContentsEvent 🎉)
    {
        if (🎉.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            🎉.accept(❓🟥🧩);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void 🌐(ServerStartingEvent 🎉)
    {
        // Do something when the server starts
        🪵.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = 🏷️, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class 🖥️🎉
    {
        @SubscribeEvent
        public static void 🖥️(FMLClientSetupEvent 🎉)
        {
            // Some client setup code
            🪵.info("HELLO FROM CLIENT SETUP");
            🪵.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
