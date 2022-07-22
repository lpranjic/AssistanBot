package hr.fer.assistanbot.commands;

import hr.fer.assistanbot.features.FeatureAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PingCommand extends FeatureAdapter {

    public PingCommand() {
        super("ping");
    }

    @Override
    public void onMessageReceive(MessageReceivedEvent event) {

        event.getMessage().reply("Pong!").queue();
    }
}
