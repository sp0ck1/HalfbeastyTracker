package com.HalfbeastyTracker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher {
    public static void main(String args[]) {

    Client client = new Client();
    Trackers tracker = new Trackers();
    client.registerFeatures();
    client.start();
    tracker.initializeTrackers();
    CloseButton closeButton = new CloseButton();



    }
}


