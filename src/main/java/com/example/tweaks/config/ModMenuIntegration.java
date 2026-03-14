package com.example.tweaks.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import net.minecraft.client.gui.screen.Screen;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return this::createScreen;
    }

    private Screen createScreen(Screen parent) {

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(net.minecraft.text.Text.literal("Tweaks Mod"));

        ConfigCategory general = builder.getOrCreateCategory(
                net.minecraft.text.Text.literal("Tweaks"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startBooleanToggle(
                        net.minecraft.text.Text.literal("Instant Eat"),
                        ModConfig.instantEat)
                .setDefaultValue(true)
                .setSaveConsumer(value -> ModConfig.instantEat = value)
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(
                        net.minecraft.text.Text.literal("Instant Bow"),
                        ModConfig.instantBow)
                .setDefaultValue(true)
                .setSaveConsumer(value -> ModConfig.instantBow = value)
                .build());

        builder.setSavingRunnable(ModConfig::save);

        return builder.build();
    }
}
