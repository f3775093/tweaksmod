package com.example.tweaks;

import net.fabricmc.api.ModInitializer;

public class TweaksMod implements ModInitializer {

    public static boolean instantEat = true;
    public static boolean instantBow = true;

    @Override
    public void onInitialize() {
        System.out.println("Tweaks Mod loaded.");
    }
}
