package com.HalfbeastyTracker.Adders;

import com.HalfbeastyTracker.Trackers;
import com.github.philippheuer.events4j.EventManager;
import com.github.philippheuer.events4j.exception.EventBufferOverflowException;
import com.github.twitch4j.chat.events.channel.CheerEvent;



public class BitAdder {


    public BitAdder(EventManager eventManager) throws EventBufferOverflowException {
        try {
        eventManager.onEvent(CheerEvent.class).subscribe(event -> onBits(event));
    }        catch (
    EventBufferOverflowException e) {
        e.printStackTrace();
    }
}

    Trackers tracker = new Trackers();

    /* Does a gift sub throw a subscription event for every gift? */

    public void onBits(CheerEvent event) {
        double bits = event.getBits() * .01;
        double rawBits = event.getBits();


        tracker.addToTotal(bits);
        tracker.addToBits(bits);
        System.out.println("Bit Event Triggered");
        System.out.println("$"+bits+" added.");
        System.out.println("Raw bits: " + rawBits);
    }

}
