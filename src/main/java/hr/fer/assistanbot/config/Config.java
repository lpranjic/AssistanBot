package hr.fer.assistanbot.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;

public class Config {

    private final String token;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    private Config(@JsonProperty("token") String token){
        this.token = token;
    }

    public static Config load(Path configPath) throws IOException {
        return new ObjectMapper().readValue(configPath.toFile(), Config.class);
    }

    public String getToken() {
        return token;
    }
}
