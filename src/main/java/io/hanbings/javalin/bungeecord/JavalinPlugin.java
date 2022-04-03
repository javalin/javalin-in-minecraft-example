package io.hanbings.javalin.bungeecord;

import io.javalin.Javalin;
import net.md_5.bungee.api.plugin.Plugin;

@SuppressWarnings("SpellCheckingInspection unused")
public class JavalinPlugin extends Plugin {
    @Override
    public void onEnable() {
        // Temporarily switch the plugin classloader to load Javalin.
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
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
