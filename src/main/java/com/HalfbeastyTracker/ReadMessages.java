package com.HalfbeastyTracker;

import com.github.philippheuer.events4j.EventManager;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;



 // This is a testing class in order to find out what permissions are required for certain actions (basic ones, like reading chat)
 // and to confirm that we're actually in a chat


public class ReadMessages {

    public ReadMessages(EventManager eventManager) {
       eventManager.onEvent(ChannelMessageEvent.class).subscribe(event -> onMessage(event));
    }

    public void onMessage(ChannelMessageEvent event) {
        String message = event.getMessage();
        System.out.println(message);
    }
}
