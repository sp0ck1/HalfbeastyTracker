package com.HalfbeastyTracker.Adders;

import com.HalfbeastyTracker.Trackers;
import com.github.philippheuer.events4j.EventManager;
import com.github.philippheuer.events4j.exception.EventBufferOverflowException;
import com.github.twitch4j.chat.events.channel.DonationEvent;

public class DonationAdder {

    public DonationAdder(EventManager eventManager) throws EventBufferOverflowException {
        try {
        eventManager.onEvent(DonationEvent.class).subscribe(event -> onDonation(event));
            }
         catch (EventBufferOverflowException e) {
            e.printStackTrace();
            }
    }

    Trackers tracker = new Trackers();

    public void onDonation(DonationEvent event) {

        double donation = event.getAmount();
        tracker.addToTotal(donation);
        tracker.addToDonations(donation);
        System.out.println("Donation Event Triggered");
        System.out.println("$"+donation+" added.");
    }

}


