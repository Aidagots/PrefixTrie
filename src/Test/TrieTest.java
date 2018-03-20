package Test;

import PrefixTrie.Trie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

class TrieTest {
    @Test
    void insert_search() {
        Trie prefixTree;

        prefixTree = new Trie();
        prefixTree.insert("GOING");
        prefixTree.insert("GONG");
        prefixTree.insert("PAKISTAN");
        prefixTree.insert("SHANGHAI");
        prefixTree.insert("GONDAL");
        prefixTree.insert("GODAY");
        prefixTree.insert("GODZILLA");

        assertTrue("GONG", prefixTree.contains("GONG"));
        assertFalse("KROL", prefixTree.contains("KROL"));
        assertTrue("GOING", prefixTree.contains("GOING"));
        assertFalse("LOR", prefixTree.contains("LOR"));
        assertTrue("PAKISTAN", prefixTree.contains("PAKISTAN"));
        assertFalse("JOB", prefixTree.contains("JOB"));
    }

    @Test
    void delete() {
        Trie prefixTree;

        prefixTree = new Trie();
        prefixTree.insert("GOING");
        prefixTree.insert("GONG");
        prefixTree.insert("PAKISTAN");
        prefixTree.insert("SHANGHAI");
        prefixTree.insert("GONDAL");
        prefixTree.insert("GODAY");
        prefixTree.insert("GODZILLA");
        prefixTree.delete("PAKISTAN");
        prefixTree.delete("GODAY");

        assertFalse("GODAY", prefixTree.contains("GODAY"));
        assertFalse("PAKISTAN", prefixTree.contains("PAKISTAN"));
        assertTrue("GONG", prefixTree.contains("GONG"));

        prefixTree.insert("JOB");
        prefixTree.insert("LOR");
        prefixTree.delete("GOING");
        prefixTree.delete("GONDAL");

        assertTrue("JOB", prefixTree.contains("JOB"));
        assertFalse("GOING", prefixTree.contains("GOING"));
        assertTrue("LOR", prefixTree.contains("LOR"));
        assertFalse("GONDAL", prefixTree.contains("GONDAL"));
    }

    @Test
    void wordsFinderTraversal() {
        Trie prefixTree;

        ArrayList<String> list = new ArrayList<String>();

        list.add("GOA");
        list.add("GODAY");
        list.add("GODZILLA");
        list.add("GOING");
        list.add("GONDAL");
        list.add("GONG");

        ArrayList<String> list1 = new ArrayList<String>();

        list1.add("GONDAL");

        ArrayList<String> list2 = new ArrayList<String>();

        list2.add("GAD");
        list2.add("GOA");
        list2.add("GODAY");
        list2.add("GODZILLA");
        list2.add("GOING");
        list2.add("GONDAL");


        prefixTree = new Trie();
        prefixTree.insert("GAD");
        prefixTree.insert("GOA");
        prefixTree.insert("GOING");
        prefixTree.insert("GONG");
        prefixTree.insert("PAKISTAN");
        prefixTree.insert("SHANGHAI");
        prefixTree.insert("GONDAL");
        prefixTree.insert("GODAY");
        prefixTree.insert("GODZILLA");

        assertEquals(list, prefixTree.wordsFinderTraversal("GO"));

        prefixTree.delete("GONG");

        list.remove("GONG");

        assertEquals(list, prefixTree.wordsFinderTraversal("GO"));
        assertEquals(list1, prefixTree.wordsFinderTraversal("GON"));
        assertEquals(list2, prefixTree.wordsFinderTraversal("G"));
    }

}