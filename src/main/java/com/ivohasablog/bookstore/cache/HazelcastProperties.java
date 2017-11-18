package com.ivohasablog.bookstore.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ivo on 19.11.2017 г..
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "hazelcast")
public class HazelcastProperties {
    private Cluster cluster;
    private Group group;
    private Network network;
    private Logging logging;
    private String jmx;

    public static class Cluster {
        private String members;

        public String getMembers() {
            return members;
        }

        public void setMembers(String members) {
            this.members = members;
        }
    }

    public static class Group {
        private String name;
        private String password;

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class Network {
        private int port;
        private boolean portAutoIncrement;

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public boolean isPortAutoIncrement() {
            return portAutoIncrement;
        }

        public void setPortAutoIncrement(boolean portAutoIncrement) {
            this.portAutoIncrement = portAutoIncrement;
        }
    }

    public static class Logging {
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
