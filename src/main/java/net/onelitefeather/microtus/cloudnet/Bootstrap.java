package net.onelitefeather.microtus.cloudnet;

import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.minestom.server.MinecraftServer;
import net.minestom.server.extras.velocity.VelocityProxy;

import java.util.function.BiFunction;

public final class Bootstrap {

    private final static ComponentLogger LOGGER = ComponentLogger.logger(Bootstrap.class);
    private final static BiFunction<ArgumentQueue, Context, Tag> VERSION_TAG = (argumentQueue, context) -> Tag.preProcessParsed(MinecraftServer.VERSION_NAME);
    private final static BiFunction<ArgumentQueue, Context, Tag> PROTOCOL_TAG = (argumentQueue, context) -> Tag.preProcessParsed(String.valueOf(MinecraftServer.PROTOCOL_VERSION));

    public static void main(String[] args) {
        var velocitySecret = System.getProperty("minestom-velocity-secret", "");
        if (!velocitySecret.isEmpty()) {
            VelocityProxy.enable(velocitySecret);
        }

        var server = MinecraftServer.init();
        var host = System.getProperty("service.bind.host", "localhost");
        var port = System.getProperty("service.bind.port", "25577");
        LOGGER.info(MiniMessage.miniMessage().deserialize("<gold>Supported Native Minecraft Version: <green><version><gray>(<protocol>)", TagResolver.builder().tag("version", VERSION_TAG).tag("protocol", PROTOCOL_TAG).build()));
        server.start(host, Integer.parseInt(port));
        System.exit(0); // CloudNet Stupid Issue - Cloudnet needs be active wait for this.
    }

}
