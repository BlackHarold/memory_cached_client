package home.blackharold.client.impl;

import home.blackharold.client.Client;

import java.io.IOException;

public class BlackNourClientFactory {

    public static Client clientInstance(String host, int port) throws IOException {
        return new DefaultClient(new DefaultClientConfig(host, port));
    }

    public static Client clientInstance(String host) throws IOException {
        return clientInstance(host, 25666);
    }

    public static Client clientInstance() throws IOException {
        return clientInstance("localhost");
    }
}
