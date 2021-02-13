package home.blackharold.client.impl;

import home.blackharold.blacknour.model.Command;
import home.blackharold.blacknour.model.Request;
import home.blackharold.blacknour.model.Response;
import home.blackharold.blacknour.model.Status;
import home.blackharold.blacknour.protocol.ObjectSerializer;
import home.blackharold.blacknour.protocol.RequestConverter;
import home.blackharold.blacknour.protocol.ResponseConverter;
import home.blackharold.client.Client;
import home.blackharold.client.ClientConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

class DefaultClient implements Client {

    private final RequestConverter requestConverter;
    private final ResponseConverter responseConverter;
    private final ObjectSerializer objectSerializer;

    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public DefaultClient(ClientConfig clientConfig) throws IOException {
        this.objectSerializer = clientConfig.getObjectSerializer();
        this.requestConverter = clientConfig.getRequestConverter();
        this.responseConverter = clientConfig.getResponseConverter();
        this.socket = createSocket(clientConfig);
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
    }

    Socket createSocket(ClientConfig clientConfig) throws IOException {
        Socket socket = new Socket(clientConfig.getHost(), clientConfig.getPort());
        socket.setKeepAlive(true);
        return socket;
    }

    Response makeRequest(Request request) throws IOException {
        requestConverter.writeRequest(outputStream, request);
        return responseConverter.readResponse(inputStream);
    }

    @Override
    public Status put(String key, Object o) throws IOException {
        return put(key, o, null, null);
    }

    @Override
    public Status put(String key, Object o, Integer ttl, TimeUnit timeUnit) throws IOException {
        byte[] data = objectSerializer.toByteArray(o);
        Long requestTtl = ttl != null && timeUnit != null ? timeUnit.toMillis(ttl) : null;
        Response response = makeRequest(new Request(Command.PUT, key, requestTtl, data));
        return response.getStatus();
    }

    @Override
    public <T> T get(String key) throws IOException {
        Response response = makeRequest(new Request(Command.GET, key));
        return (T) objectSerializer.fromByteArray(response.getData());
    }

    @Override
    public Status remove(String key) throws IOException {
        Response response = makeRequest(new Request(Command.GET, key));
        return response.getStatus();
    }

    @Override
    public Status clear() throws IOException {
        Response response = makeRequest(new Request(Command.CLEAR));
        return response.getStatus();
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }
}
