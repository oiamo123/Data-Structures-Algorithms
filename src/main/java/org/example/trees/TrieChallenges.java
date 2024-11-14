package org.example.trees;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieChallenges {
    // Trie node
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWordEnd = false;
    }

    private TrieNode root = new TrieNode();

    // insert method
    private void insert(String rootWord) {
        // start at the root node
        TrieNode node = root;

        // loop over characters in array
        for (char ch : rootWord.toCharArray()) {
            // if the node doesn't contain the character, add it and move to that node otherwise just move to that node
            node = node.children.computeIfAbsent(ch, c -> new TrieNode());
        }

        // set the last node as the end of the word
        node.isWordEnd = true;
    }

    // find if a prefix exists
    private String findRoot(String word) {
        TrieNode node = root;
        StringBuilder prefix = new StringBuilder();

        // loop over the word
        for (char ch : word.toCharArray()) {
            // get the child node for a given character ie
            // c -> a -> t/b
            // if c -> a exists, get the next given character else it doesn't exist
            node = node.children.get(ch);
            if (node == null) return word;  // no matching root
            prefix.append(ch); // append the prefix
            if (node.isWordEnd) return prefix.toString();  // found the shortest possible root
        }

        return word;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        for (String rootWord : dictionary) { // add all words to the trie
            insert(rootWord);
        }

        StringBuilder result = new StringBuilder();
        for (String word : sentence.split(" ")) {
            if (result.length() > 0) result.append(" ");
            result.append(findRoot(word)); // add the found prefix to the new sentence
        }

        return result.toString();
    }
}
