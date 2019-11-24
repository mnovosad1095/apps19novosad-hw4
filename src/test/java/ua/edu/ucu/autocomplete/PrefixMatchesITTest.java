
package ua.edu.ucu.autocomplete;

import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import ua.edu.ucu.tries.RWayTrie;

/**
 *
 * @author Andrii_Rodionov
 */
public class PrefixMatchesITTest {

    private PrefixMatches pm;
   
    @Before
    public void init() {
        pm = new PrefixMatches(new RWayTrie());
        pm.load("abc", "abce", "abcd", "abcde", "abcdef");
    }


    @Test
    public void testContains() {
        assertTrue(pm.contains("abc"));
    }

    @Test
    public void testWordsWithPrefix_String() {
        String pref = "ab";

        Iterable<String> result = pm.wordsWithPrefix(pref);

        
        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K() {
        String pref = "abc";
        int k = 3;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWordsWithPrefix_String_wrong_arg() {
        Iterable<String> result = pm.wordsWithPrefix("a");
    }

    @Test
    public void testSize() {
        int startSize = pm.size();

        pm.load("zab", "zabd zabe");

        assertEquals(startSize + 3, pm.size());

        pm.delete("zab");

        assertEquals(startSize + 2, pm.size());
    }

    @Test
    public void testDelete() {
        pm.delete("abcd");

        assertFalse(pm.contains("abcd"));
    }
   

}
