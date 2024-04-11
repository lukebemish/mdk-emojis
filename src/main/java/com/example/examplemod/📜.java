package com.example.examplemod;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = 🛠️.🏷️, bus = Mod.EventBusSubscriber.Bus.MOD)
public class 📜
{
    private static final ModConfigSpec.Builder 👷 = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue 📦🪵🟫 = 👷
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    private static final ModConfigSpec.IntValue 📦🪄🧮 = 👷
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.ConfigValue<String> 📦🪄🧮💬 = 👷
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    // a list of strings that are treated as resource locations for items
    private static final ModConfigSpec.ConfigValue<List<? extends String>> 🧩💬 = 👷
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), 📜::🧩✅);

    static final ModConfigSpec 👓 = 👷.build();

    public static boolean 🪵🟫;
    public static int 🪄🧮;
    public static String 🪄🧮💬;
    public static Set<Item> 🧩;

    private static boolean 🧩✅(final Object 🍎)
    {
        return 🍎 instanceof String ⌨️ && BuiltInRegistries.ITEM.containsKey(new ResourceLocation(⌨️));
    }

    @SubscribeEvent
    static void 📂(final ModConfigEvent 🎉)
    {
        🪵🟫 = 📦🪵🟫.get();
        🪄🧮 = 📦🪄🧮.get();
        🪄🧮💬 = 📦🪄🧮💬.get();

        // convert the list of strings into a set of items
        🧩 = 🧩💬.get().stream()
                .map(🍎 -> BuiltInRegistries.ITEM.get(new ResourceLocation(🍎)))
                .collect(Collectors.toSet());
    }
}
