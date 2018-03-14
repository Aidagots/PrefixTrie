package Test;

import PrefixTrie.Trie;
import org.junit.jupiter.api.Test;

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

        assertTrue("GONG" , prefixTree.search("GONG"));
        assertFalse("KROL" , prefixTree.search("KROL"));
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

        assertFalse("GODAY", prefixTree.search("GODAY"));
        assertFalse("PAKISTAN", prefixTree.search("PAKISTAN"));
        assertTrue("GONG", prefixTree.search("GONG"));
    }
}
