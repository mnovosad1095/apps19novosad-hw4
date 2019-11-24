package ua.edu.ucu.tries;

import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import ua.edu.ucu.tries.RWayTrie;

public class TriesTest {

    private RWayTrie pm;
   
    @Before
    public void init() {
        pm = new RWayTrie();
        String[] words = {"abc", "abce", "abcd", "abcde", "abcdef"};
        for (String string : words) {
            pm.add(new Tuple(string, string.length()));
        }
    }

    @Test
    public void testWords() {
        Iterable<String> result = pm.words();
        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
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
    public void testSize() {
        int startSize = pm.size();

        pm.add(new Tuple("term", 4));

        assertEquals(startSize + 1, pm.size());

        pm.delete("term");

        assertEquals(startSize, pm.size());
    }

    @Test
    public void testDelete() {
        pm.delete("abcd");
        
        assertFalse(pm.contains("abcd"));
    }

    @Test
    public void testRepeatedAdds() {
        int startSize = pm.size();

        for (int i = 0; i < 4; i++) {
            pm.add(new Tuple("term", 4));
        }
        
        assertEquals(startSize + 1, pm.size());
    }
   

}
