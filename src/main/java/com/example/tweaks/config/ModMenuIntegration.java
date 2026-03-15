package com.example.tweaks.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return this::createScreen;
    }

    private Screen createScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(Text.literal("Tweaks Mod Config"));

        ConfigCategory general = builder.getOrCreateCategory(Text.literal("General"));
        ConfigEntryBuilder entry = builder.entryBuilder();

        general.addEntry(entry.startBooleanToggle(Text.literal("Instant Eat"), ModConfig.instantEat)
            .setDefaultValue(true)
            .setSaveConsumer(val -> ModConfig.instantEat = val)
            .build());

        general.addEntry(entry.startBooleanToggle(Text.literal("Instant Bow"), ModConfig.instantBow)
            .setDefaultValue(true)
            .setSaveConsumer(val -> ModConfig.instantBow = val)
            .build());

        builder.setSavingRunnable(ModConfig::save);
        return builder.build();
    }
}
