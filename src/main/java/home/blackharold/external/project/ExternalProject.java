package home.blackharold.external.project;

import home.blackharold.client.Client;
import home.blackharold.client.impl.BlackNourClientFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class ExternalProject {


    public static void main(String[] args) {
        try (Client client = BlackNourClientFactory.clientInstance()) {
            client.put("test", "Hello World");
            System.out.println(client.get("test").toString());
            client.remove("test");

            client.put("test", "Hello world");
            client.put("test", new BusinessObject("NAME TEST"));
            System.out.println(client.get("test").toString());
            client.clear();
            System.out.println(client.get("test").toString());

            client.put("test", "example object", 2, TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(5);
            System.out.println(client.get("test").toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class BusinessObject implements Serializable {
        private String name;

        BusinessObject(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "BusinessObject{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
