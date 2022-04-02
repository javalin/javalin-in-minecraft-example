package io.hanbings.javalin.bukkit;

import io.javalin.Javalin;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("SpellCheckingInspection unused")
public class JavalinPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // Temporarily switch the plugin classloader to load Javalin.
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(this.getClassLoader());
        // Create a Javalin instance.
        Javalin app = Javalin.create().start(8080);
        // Restore default loader.
        Thread.currentThread().setContextClassLoader(classLoader);
        // The created instance can be used outside the class loader.
        app.get("/", ctx -> ctx.result("Hello World!"));
        // log
        getLogger().info("JavalinPlugin is enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("JavalinPlugin is disabled");
    }
}
