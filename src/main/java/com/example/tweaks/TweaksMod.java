package com.example.tweaks;

import net.fabricmc.api.ModInitializer;
import com.example.tweaks.config.ModConfig;

public class TweaksMod implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConfig.load();
    }
}
