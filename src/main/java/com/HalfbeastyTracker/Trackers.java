package com.HalfbeastyTracker;

import java.io.File;
import java.io.IOException;

public class Trackers {

    String currentDirectory = BeastyUtils.getCurrentDir();

    public Trackers() {
    if (!totalDonationDollars.exists()) {
        try
        {
            totalDonationDollars.createNewFile();
            System.out.println("Creating totalDonationDollars.txt in " + currentDirectory + ". The value of incoming donations will be stored here.");
        } catch (IOException ignored) {}
    }

    if ( !SubsBitsDons.exists()) {
        try {
            SubsBitsDons.createNewFile();
            System.out.println("Creating SubsBitsDons.txt in " + currentDirectory + ". The combined value of subs, bits, and donations will be stored here.");
        } catch (IOException ignored) {}
        }

    if ( !totalBitDollars.exists()) {
        try {
        totalBitDollars.createNewFile();
        System.out.println("Creating totalBitDollars.txt in " + currentDirectory + ". The value of incoming bits will be stored here.");
    } catch (IOException ignored) {}
    }

    if ( !totalSubDollars.exists()) {
        try {
            totalSubDollars.createNewFile();
            System.out.println("Creating totalSubDollars.txt in " + currentDirectory + ". The value of incoming subs will be stored here.");
        } catch (IOException ignored) {}
        }
    }

    public void initializeTrackers(){
        initializeDonations();
        initializeBits();
        initializeSubs();
        initializeTotal();
        printStartingValues();
    }

    static double tracker;
    static double subs;
    static double bits;
    static double donations;

    File SubsBitsDons = new File("SubsBitsDons.txt");
    File totalBitDollars = new File("totalBitDollars.txt");
    File totalSubDollars = new File("totalSubDollars.txt");
    File totalDonationDollars = new File("totalDonationDollars.txt");


    private void initializeSubs() {
      if (BeastyUtils.readLine(totalSubDollars) != null) {

          double addSubs = Double.parseDouble(BeastyUtils.readLine(totalSubDollars));
          subs = addSubs;
      }
  }

      private void initializeDonations() {
          if (BeastyUtils.readLine(totalDonationDollars) != null) {

              double addDonations = Double.parseDouble(BeastyUtils.readLine(totalDonationDollars));
              donations = addDonations;
          }
          }

      private void initializeBits() {
          if (BeastyUtils.readLine(totalBitDollars) != null) {

              double addBits = Double.parseDouble(BeastyUtils.readLine(totalBitDollars));
              bits = addBits;
          }
      }

      private void initializeTotal() {
          if (BeastyUtils.readLine(SubsBitsDons) != null) {

              double addTotal = Double.parseDouble(BeastyUtils.readLine(SubsBitsDons));
              tracker = addTotal;
          }
      }

    private void printStartingValues() {
      System.out.println("Starting value of subs is: " + subs);
      System.out.println("Starting value of donations is: " + donations);
      System.out.println("Starting value of bits is: " + bits);
      System.out.println("Total current value is: " + tracker);
    }


    public void addToTotal(double amountToAdd) {
        tracker = tracker + amountToAdd;
        BeastyUtils.writeLine(SubsBitsDons,Double.toString(tracker));
        System.out.println("New total value: $" + tracker);
    }

    public void addToSubs(double amountToAdd) {
        subs = subs + amountToAdd;
        BeastyUtils.writeLine(totalSubDollars,Double.toString(subs));
    }

    public void addToBits(double amountToAdd) {
        bits = bits + amountToAdd;
        BeastyUtils.writeLine(totalBitDollars,Double.toString(bits));
    }

    public void addToDonations(double amountToAdd) {
        donations = donations + amountToAdd;
        BeastyUtils.writeLine(totalDonationDollars,Double.toString(donations));
    }

}
