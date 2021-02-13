package home.blackharold.client.impl;

import home.blackharold.blacknour.protocol.ObjectSerializer;
import home.blackharold.blacknour.protocol.RequestConverter;
import home.blackharold.blacknour.protocol.ResponseConverter;
import home.blackharold.blacknour.protocol.impl.DefaultObjectSerializer;
import home.blackharold.blacknour.protocol.impl.DefaultRequestConverter;
import home.blackharold.blacknour.protocol.impl.DefaultResponseConverter;
import home.blackharold.client.ClientConfig;

public class DefaultClientConfig implements ClientConfig {
    private final String host;
    private final int port;
    private final RequestConverter requestConverter;
    private final ResponseConverter responseConverter;
    private final ObjectSerializer objectSerializer;


    public DefaultClientConfig(String host, int port) {
        this.host = host;
        this.port = port;
        this.requestConverter = new DefaultRequestConverter();
        this.responseConverter = new DefaultResponseConverter();
        this.objectSerializer = new DefaultObjectSerializer();
    }

    public DefaultClientConfig(String host, int port, RequestConverter requestConverter, ResponseConverter responseConverter, ObjectSerializer objectSerializer) {
        this.host = host;
        this.port = port;
        this.requestConverter = requestConverter;
        this.responseConverter = responseConverter;
        this.objectSerializer = objectSerializer;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public RequestConverter getRequestConverter() {
        return requestConverter;
    }

    @Override
    public ResponseConverter getResponseConverter() {
        return responseConverter;
    }

    @Override
    public ObjectSerializer getObjectSerializer() {
        return objectSerializer;
    }
}
