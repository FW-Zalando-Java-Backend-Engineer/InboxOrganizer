package com.inbox;

/**
 * Live demo runner
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        InboxOrganizer organizer = new InboxOrganizer();

        // Step 1: Add folders to simulate a Binary Tree
        organizer.addFolder("Work");
        organizer.addFolder("Family");
        organizer.addFolder("Promotions");

        // Step 2: Add emails into folders, sorted inside by TreeMap
        organizer.addEmailToFolder("Work", "boss@company.com", "Meeting at 10AM");
        organizer.addEmailToFolder("Work", "hr@company.com", "Policy update");
        organizer.addEmailToFolder("Family", "mom@home.com", "Dinner at 7?");
        organizer.addEmailToFolder("Family", "dad@home.com", "Don't forget your keys!");
        organizer.addEmailToFolder("Promotions", "news@store.com", "50% off today!");

        // Step 3: List all emails sorted by folder (binary tree) and sender (TreeMap)
        organizer.listAllEmails();
    }
}
