package com.HalfbeastyTracker.Adders;

import com.HalfbeastyTracker.Trackers;
import com.github.philippheuer.events4j.EventManager;
import com.github.philippheuer.events4j.exception.EventBufferOverflowException;
import com.github.twitch4j.chat.events.channel.SubscriptionEvent;

import java.util.Optional;

public class SubscriptionAdder {

    public SubscriptionAdder(EventManager eventManager) throws EventBufferOverflowException {
        try {
            eventManager.onEvent(SubscriptionEvent.class).subscribe(event -> onSubscription(event));
        } catch (EventBufferOverflowException e) {
            e.printStackTrace();
        }
    }

    Trackers tracker = new Trackers();

    public void onSubscription(SubscriptionEvent event) {
        double subscription = 2.5;


        tracker.addToTotal(subscription);
        tracker.addToSubs(subscription);

        Optional<String> subMessage = event.getMessage();

        System.out.println("Subscription Event Triggered");
        System.out.println("$2.50 added.");
        if (subMessage.isPresent()) {
            System.out.println(subMessage.get());
        }
    }


}
