package net.onelitefeather.microtus.cloudnet;

import net.minestom.server.MinecraftServer;

public final class Bootstrap {

    public static void main(String[] args) {
        var server = MinecraftServer.init();
        var host = System.getProperty("service.bind.host", "localhost"); //
        var port = System.getProperty("service.bind.port", "25577");
        server.start(host, Integer.parseInt(port));
    }

}
