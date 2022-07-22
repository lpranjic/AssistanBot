package hr.fer.assistanbot.commands;

import hr.fer.assistanbot.features.FeatureAdapter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoCommand extends FeatureAdapter {

    public UserInfoCommand() {
        super("userinfo");
    }

    @Override
    public void onMessageReceive(MessageReceivedEvent event) {
        Member member;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //member = event.getMessage().getMentions().getMembers().get(0);
        member = event.getMessage().getMentionedMembers().get(0);
        EmbedBuilder eb = new EmbedBuilder().setColor(Color.RED)
                .setThumbnail(member.getAvatarUrl())
                .setAuthor("Info about " + member.getUser().getName(), "http://www.google.com", member.getUser().getAvatarUrl())
                .setDescription(member.getUser().getName() + " joined this server on " + member.getTimeJoined().format(dtf))
                .addField("Status: ", member.getOnlineStatus().toString(), true)
                .addField("Roles: ", getAssignedRolesAsString(member.getRoles()), true)
                .addField("Server nickname: ", member.getNickname() == null ? "No nickname" : member.getNickname(), true);
        event.getMessage().replyEmbeds(eb.build()).queue();

    }

    private String getAssignedRolesAsString(List<Role> roles) {
        if(roles.isEmpty()){
            return "User has no roles";
        } else {
            return roles.stream().map(Role::getName).collect(Collectors.joining(", ")).trim();
        }

    }
}
