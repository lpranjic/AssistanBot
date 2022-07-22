package hr.fer.assistanbot.commands;

import hr.fer.assistanbot.features.FeatureAdapter;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PinCommand extends FeatureAdapter {
    public PinCommand() {
        super("pin");
    }

    @Override
    public void onMessageReceive(MessageReceivedEvent event) {
        Message pinMessage = event.getMessage().getMessageReference().getMessage();
        if(pinMessage.isPinned()){
            pinMessage.unpin().queue();
        }
        else{
            pinMessage.pin().queue();
        }
    }
}
