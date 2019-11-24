package ua.edu.ucu.autocomplete;

import java.util.ArrayList;
import java.util.Collections;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches() {
        trie = new RWayTrie();
    }

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int startSize = trie.size();

        for (String string : strings) {
            String[] s = string.split("\\s+");
            for (String str : s) {

                if (!trie.contains(str))
                    trie.add(new Tuple(str, str.length()));
            }
        }

        return trie.size() - startSize;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> res = new ArrayList<String>();
        int i = 0;
        String currWord;
        String newWord;

        for (String string : wordsWithPrefix(pref)) {
            words.add(string);
        }
        Collections.sort(words, new MyComparator());

        while (k != 0) {
            currWord = words.get(i);
            res.add(currWord);
            newWord = words.get(i + 1);
            if (newWord.length() > currWord.length()) k--;
            i++;
        }

        return res;
    }

    public int size() {
        return trie.size();
    }
}
