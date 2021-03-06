package PrefixTrie;

import java.util.*;

public class Trie {
    class TrieNode {
        char c;
        TrieNode parent;
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isLeaf;

        TrieNode() {
        }

        TrieNode(char c) {
            this.c = c;
        }
    }

    TrieNode root;
    private Set<String> words;
    private TrieNode prefixRoot;
    private String curPrefix;

    public Trie() {
        root = new TrieNode();
        words = new HashSet<>();
    }


    public void insert(String word) {
        Map<Character, TrieNode> children = root.children;

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


    public boolean contains(String word) {
        TrieNode t = searchNode(word);
        return t != null && t.isLeaf;
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


    private Set<String> wordsFinderTraversal(TrieNode node, int offset) {
        if (node.isLeaf) {
            TrieNode altair;
            altair = node;

            Stack<String> hstack = new Stack<>();

            while (altair != prefixRoot) {
                hstack.push(Character.toString(altair.c));
                altair = altair.parent;
            }

            StringBuilder wrd = new StringBuilder(curPrefix);

            while (!hstack.empty()) {
                wrd.append(hstack.pop());
            }

            words.add(wrd.toString());
        }

        Set<Character> kset = node.children.keySet();

        Iterator itr = kset.iterator();
        List<Character> aloc = new ArrayList<>();

        while (itr.hasNext()) {
            Character ch = (Character) itr.next();
            aloc.add(ch);
        }


        for (Character anAloc : aloc) {
            wordsFinderTraversal(node.children.get(anAloc), offset + 2);
        }

        return words;
    }

    public Set<String> wordsFinderTraversal(String str) {
        TrieNode t = searchNode(str);
        return wordsFinderTraversal(t, 0);
    }

    public void delete(String word) {
        TrieNode node = searchNode(word);
        char character = word.charAt(0);
        int index = 0;

        while (node.children.isEmpty()) {
            TrieNode parent = node.parent;
            parent.children.remove(node.c);
            node = parent;
        }
    }
}