package com.inbox;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * A class that simulates an inbox folder structure using a binary tree,
 * and organizes emails within each folder using TreeMap for alphabetical sorting.
 */
public class InboxOrganizer {



    /**
     * Represents a folder (binary tree node) containing emails.
     * Inner Class: (used as a helper class)
     */
    static class FolderNode{
        String folderName; //  name of the folder (used to sort in BST).
        // typical BST links.
        FolderNode left;
        FolderNode right;

        // a TreeMap that automatically sorts senders alphabetically.
        TreeMap<String, List<String>> emailsBySender;  // TreeMap keeps sender names sorted

        public FolderNode(String folderName) {
            this.folderName = folderName;
            this.emailsBySender = new TreeMap<>();
        }

        /**
         * Add an email under a specific sender in this folder.
         *
         * @param sender  the sender of the email
         * @param message the email content
         */
        public void addEmail(String sender, String message){
            // Ensures the sender has an email list.
            emailsBySender.putIfAbsent(sender, new ArrayList<>());
            // Adds the new message to their list.
            emailsBySender.get(sender).add(message);
        }

        /**
         * Display emails in this folder, sorted by sender.
         */
        public void listEmails(){
            System.out.println("📂 " + folderName + " Folder:");
            if (emailsBySender.isEmpty()) {
                System.out.println("  (No emails)");
            }

            this.emailsBySender.forEach(
                    (sender, messages) -> {
                        System.out.println("  From: " + sender);
                        messages.forEach(msg -> System.out.println("    - " + msg));
                    }
            );


        }



    }

    /**
     * The root of our binary folder tree.
     */
    private FolderNode root;

    /**
     * Adds a folder to the binary tree based on name (alphabetical order).
     *
     * @param folderName name of the folder to add
     */
    public void addFolder(String folderName) {
        root = insertFolder(root, folderName);
    }
    private FolderNode insertFolder(FolderNode node, String folderName) {
        if (node == null) return new FolderNode(folderName);
        if (folderName.compareTo(node.folderName) < 0)
            node.left = insertFolder(node.left, folderName);
        else
            node.right = insertFolder(node.right, folderName);
        return node;
    }


    /**
     * Find a folder node by name.
     */
    private FolderNode findFolder(FolderNode node, String folderName) {
        if (node == null) return null;
        if (node.folderName.equals(folderName)) return node;
        if (folderName.compareTo(node.folderName) < 0)
            return findFolder(node.left, folderName);
        else
            return findFolder(node.right, folderName);
    }

    /**
     * Adds an email to a specific folder.
     */
    public void addEmailToFolder(String folderName, String sender, String message){
        FolderNode folder = findFolder(root, folderName);
        if (folder != null) {
            folder.addEmail(sender, message);
        } else {
            System.out.println("❌ Folder not found: " + folderName);
        }

    }

    /**
     * List all emails in all folders (in-order).
     */
    public void listAllEmails() {
        traverseAndList(root);
    }

    private void traverseAndList(FolderNode node) {
        if (node != null) {
            traverseAndList(node.left);
            node.listEmails();
            traverseAndList(node.right);
        }
    }





}
