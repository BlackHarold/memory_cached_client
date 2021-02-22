package home.blackharold.client.impl;

import home.blackharold.blacknour.protocol.impl.DefaultObjectSerializer;
import home.blackharold.blacknour.protocol.impl.DefaultRequestConverter;
import home.blackharold.blacknour.protocol.impl.DefaultResponseConverter;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultClientConfigTest {
    private final DefaultClientConfig defaultClientConfig = new DefaultClientConfig("localhost", 25666);

    @Test
    public void getHost() {
        assertEquals("localhost", defaultClientConfig.getHost());
    }

    @Test
    public void getPort() {
        assertEquals(25666, defaultClientConfig.getPort());
    }

    @Test
    public void getRequestConverter() {
        assertEquals(DefaultRequestConverter.class, defaultClientConfig.getRequestConverter().getClass());
    }

    @Test
    public void getResponseConverter() {
        assertEquals(DefaultResponseConverter.class, defaultClientConfig.getResponseConverter().getClass());
    }

    @Test
    public void getObjectSerializer() {
        assertEquals(DefaultObjectSerializer.class, defaultClientConfig.getObjectSerializer().getClass());
    }
}
