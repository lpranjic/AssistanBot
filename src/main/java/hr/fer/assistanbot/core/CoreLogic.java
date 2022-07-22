package hr.fer.assistanbot.core;

import hr.fer.assistanbot.config.Config;
import hr.fer.assistanbot.features.FeatureAdapter;
import hr.fer.assistanbot.features.Features;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CoreLogic extends ListenerAdapter {

    private Collection<FeatureAdapter> features;
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    private static final Logger logger = LoggerFactory.getLogger(CoreLogic.class);
    public CoreLogic(JDA jda, Config config) {
        features = Features.createFeatures(jda, config);

    }

    @Override
    public void onReady(ReadyEvent event) {
        logger.debug("Bot core is now ready");
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Runnable task = () -> {
            FeatureAdapter fa = getFeature(event);
            if (Objects.isNull(fa)) {
                return;
            } else {
                fa.onMessageReceive(event);
            }
        };

        EXECUTOR_SERVICE.execute(task);
    }

    private FeatureAdapter getFeature(MessageReceivedEvent event){
        String message = event.getMessage().getContentRaw();
        if(!message.startsWith("!")){
            return null;
        }
        message = message.split("\\s+")[0].substring(1);

        for(FeatureAdapter feature : features){
            if(feature.getLiteral().equals(message)){
                return feature;
            }
        }
        return null;
    }
}
