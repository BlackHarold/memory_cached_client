package home.blackharold.client;

import home.blackharold.blacknour.model.Status;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public interface Client extends AutoCloseable {

    Status put(String key, Object o) throws IOException;

    Status put(String key, Object o, Integer ttl, TimeUnit timeUnit) throws IOException;

    <T> T get(String key) throws IOException;

    Status remove(String key) throws IOException;

    Status clear() throws IOException;
}
