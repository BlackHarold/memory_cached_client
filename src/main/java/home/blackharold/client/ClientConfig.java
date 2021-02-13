package home.blackharold.client;

import home.blackharold.blacknour.protocol.ObjectSerializer;
import home.blackharold.blacknour.protocol.RequestConverter;
import home.blackharold.blacknour.protocol.ResponseConverter;

public interface ClientConfig {

    String getHost();

    int getPort();

    RequestConverter getRequestConverter();

    ResponseConverter getResponseConverter();

    ObjectSerializer getObjectSerializer();
}
