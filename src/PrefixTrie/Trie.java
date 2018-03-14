package PrefixTrie;

import java.util.*;

public class Trie {

    class TrieNode {
        char c;
        TrieNode parent;
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        boolean isLeaf;

        TrieNode() {
        }

        TrieNode(char c) {
            this.c = c;
        }
    }

    TrieNode root;
    private ArrayList<String> words;
    private TrieNode prefixRoot;
    private String curPrefix;

    public Trie() {
        root = new TrieNode();
        words = new ArrayList<String>();
    }


    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;

        TrieNode crntparent;

        crntparent = root;


        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            TrieNode t;
            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);
                t.parent = crntparent;
                children.put(c, t);
            }

            children = t.children;
            crntparent = t;


            if (i == word.length() - 1)
                t.isLeaf = true;
        }
    }


    public boolean search(String word) {
        TrieNode t = searchNode(word);
        return t != null && t.isLeaf;
    }


    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }

    TrieNode searchNode(String str) {
        Map<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (children.containsKey(c)) {
                t = children.get(c);
                children = t.children;
            } else {
                return null;
            }
        }

        prefixRoot = t;
        curPrefix = str;
        words.clear();
        return t;
    }
    public void delete(String word) {
        TrieNode node = searchNode(word);
        char character = word.charAt(0);
        int index = 0;

        while (node.children.isEmpty()) {
            TrieNode parent = node.parent;
            parent.children.remove(node.c);
        }
    }
}



