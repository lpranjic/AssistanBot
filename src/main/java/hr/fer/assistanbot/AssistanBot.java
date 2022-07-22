package hr.fer.assistanbot;

import hr.fer.assistanbot.config.Config;
import hr.fer.assistanbot.core.CoreLogic;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Path;

public class AssistanBot {

    private static final Logger logger = LoggerFactory.getLogger(AssistanBot.class);
    private static final String DEFAULT_CONFIG_PATH = "config.json";

    public static void main(String[] args) {
        if (args.length > 1) {
            throw new IllegalArgumentException("Expected no or one argument but " + args.length
                    + " arguments were provided. The first argument is the path to the configuration file. If no argument was provided, '"
                    + DEFAULT_CONFIG_PATH + "' will be assumed");
        }
        Path configPath = Path.of(args.length == 1 ? args[0] : DEFAULT_CONFIG_PATH);
        Config config;
        try {
            config = Config.load(configPath);
        } catch (IOException e) {
            System.out.println(e);
            logger.error("Unable to load the configuration file from path '{}'",
                    configPath.toAbsolutePath(), e);
            return;
        }

        try {
            runBot(config);
        } catch (Exception t) {
            logger.error("Unknown error", t);
        }
    }

    public static void runBot(Config config){
        logger.info("Starting...");

        try {
            JDA jda = JDABuilder.createDefault(config.getToken())
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .build();
            jda.addEventListener(new CoreLogic(jda, config));
            jda.awaitReady();
        } catch (LoginException e) {
            logger.error("Failed to login", e);
        } catch (InterruptedException e) {
            logger.error("Interrupted while waiting for setup to complete", e);
        }

    }
}
