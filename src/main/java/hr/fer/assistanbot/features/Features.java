package hr.fer.assistanbot.features;

import hr.fer.assistanbot.commands.PinCommand;
import hr.fer.assistanbot.commands.PingCommand;
import hr.fer.assistanbot.commands.UserInfoCommand;
import hr.fer.assistanbot.config.Config;
import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.Collection;

public enum Features {
    ; //no constants needed

    public static Collection<FeatureAdapter> createFeatures(JDA jda, Config config){
        Collection<FeatureAdapter> features = new ArrayList<>();

        features.add(new PingCommand());
        features.add(new UserInfoCommand());
        features.add(new PinCommand());

        return features;
    }
}
