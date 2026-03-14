package com.example.tweaks.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import net.fabricmc.loader.api.FabricLoader;

public class ModConfig {

    public static boolean instantEat = true;
    public static boolean instantBow = true;

    private static final File FILE =
            new File(FabricLoader.getInstance().getConfigDir().toFile(), "tweaksmod.json");

    private static final Gson GSON =
            new GsonBuilder().setPrettyPrinting().create();

    public static void load() {

        try {

            if (FILE.exists()) {

                ModConfig data = GSON.fromJson(new FileReader(FILE), ModConfig.class);

                instantEat = data.instantEat;
                instantBow = data.instantBow;

            } else {
                save();
            }

        } catch (Exception ignored) {}

    }

    public static void save() {

        try {

            GSON.toJson(new ModConfig(), new FileWriter(FILE));

        } catch (Exception ignored) {}

    }
}
