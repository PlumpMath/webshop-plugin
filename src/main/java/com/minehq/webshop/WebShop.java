package com.minehq.webshop;

import com.minehq.webshop.util.Exceptions;
import com.minehq.webshop.util.Log;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class WebShop extends JavaPlugin {
    private static WebShop plugin = null;
    public static Economy economy = null;

    @Override
    public void onEnable() {
        WebShop.plugin = this;

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException ex) {
            Log.info("I couldn't submit statistics about WebShop. :(");
            Exceptions.printFriendlyStackTrace(ex);
        }

        new Updater(this, "webshop", this.getFile(), Updater.UpdateType.DEFAULT, false);

        if (!setupPermissions()) {
            Log.severe("I couldn't find Vault! Since I need vault, I guess I'll have to disable now... :'(");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Log.info(String.format("Successfully enabled version %s", getDescription().getVersion()));
    }

    @Override
    public void onDisable() {
        WebShop.plugin = this;


    }

    public static WebShop getPlugin() {
        return WebShop.plugin;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    private void setupCommands() {

    }

    private void setupListeners() {

    }

    private void resolveDependencies() {

    }
}
