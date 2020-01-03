package com.HalfbeastyTracker;

import com.HalfbeastyTracker.Adders.BitAdder;
import com.HalfbeastyTracker.Adders.DonationAdder;
import com.HalfbeastyTracker.Adders.SubscriptionAdder;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.helix.domain.StreamList;
import com.github.twitch4j.helix.domain.UserList;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;


public class Client {


    /**
     * Twitch4J API
     */
    public TwitchClient twitchClient;


    public String accessToken = "";


    /**
     * Constructor
     */
    public Client() {


        File credentialFile = new File("oAuth2Credential.txt");

        // Create oAuth2Credential.txt if it doesn't exist
        if (!credentialFile.exists()) {
            System.out.println("Creating oAuth2Credential.txt in current directory");
            try {

                credentialFile.createNewFile();
                BeastyUtils.writeLine(credentialFile, "Input credential");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Read credential currently stored in file
        accessToken = BeastyUtils.readLine(credentialFile);

        // Check if credential in txt file is valid
        ValidateCredential validate = new ValidateCredential();
        boolean isCredentialValid = validate.isCredentialValid(accessToken);

        // If the credential is not valid, ask for a new one
        while (!isCredentialValid) {

            // Ask for OAuth2 Credential if one has not been provided from a file
            JTextArea OAuth2CredentialDialog = new JTextArea();
            OAuth2CredentialDialog.requestFocus();
            accessToken = JOptionPane.showInputDialog(OAuth2CredentialDialog, "Enter a valid OAuth2 Credential from https://www.twitchapps.com/tmi", "OAuth2Credential Entry", JOptionPane.PLAIN_MESSAGE);

            isCredentialValid = validate.isCredentialValid(accessToken);

        }

        System.out.println("Valid credential present.");

        // Write correct credential to file so that it won't ask next time
        BeastyUtils.writeLine(credentialFile, accessToken);

        // Use validated access token to create an OAuth2Credential
        TwitchClientBuilder clientBuilder = TwitchClientBuilder.builder();
        OAuth2Credential credential = new OAuth2Credential(
                "twitch",
                accessToken
        );

        //region TwitchClient
        twitchClient = clientBuilder
                .withChatAccount(credential)
                .withEnableHelix(true)
                .withEnableChat(true)
                .build();
        //endregion
    }

    /**
     * Method to register all features
     */
    public void registerFeatures() {

        // Register Event-based features
        BitAdder BitAdder = new BitAdder(twitchClient.getEventManager());
        DonationAdder DonationAdder = new DonationAdder(twitchClient.getEventManager());
        SubscriptionAdder SubscriptionAdder = new SubscriptionAdder(twitchClient.getEventManager());
        //   ReadMessages ReadMessages = new ReadMessages(twitchClient.getEventManager());

    }


    public void start() {
        String channel = "";

        // Get and join top 20 streams for debug purposes
/*
          StreamList resultList = twitchClient.getHelix().getStreams(accessToken,"", "", 20, null, null, null, null, null).execute();
        resultList.getStreams().forEach(stream -> {
            String userID = stream.getUserId();

            UserList userList = twitchClient.getHelix().getUsers(accessToken, Arrays.asList(userID), null).execute();
            userList.getUsers().forEach(user -> {
                  String channel = user.getLogin();
               // System.out.println(user.getLogin());
                twitchClient.getChat().joinChannel(channel);
                System.out.println("Joining " + channel + " - Title: " + stream.getTitle());
            });
        });
*/
        File channelName = new File("channelName.txt");
        if (!channelName.exists()) {
            System.out.println("Creating file channelName.txt in " + BeastyUtils.getCurrentDir());
            try {
                channelName.createNewFile();
                BeastyUtils.writeLine(channelName, "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (BeastyUtils.readLine(channelName) != null) {
            channel = BeastyUtils.readLine(channelName);
            twitchClient.getChat().joinChannel(channel);
            System.out.println("Joining channel " + channel);
        } else {

            JTextArea chatToEnter = new JTextArea();
            chatToEnter.requestFocus();
            channel = JOptionPane.showInputDialog(chatToEnter, "What channel do I enter?", "Username Entry", JOptionPane.PLAIN_MESSAGE);
            twitchClient.getChat().joinChannel(channel);
            System.out.println("Joining channel " + channel);
            System.out.println("Writing channel to channelName.txt for next time.");
            BeastyUtils.writeLine(channelName, channel);

        }
    }
}





