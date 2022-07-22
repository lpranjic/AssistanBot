package hr.fer.assistanbot.features;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface Feature {

    void onMessageReceive(MessageReceivedEvent event);
}
