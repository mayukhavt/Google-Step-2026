import static org.junit.Assert.*;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * test file for hashmap
 * 
 *  @author mayakkrla
 *  @version 28 May 2026
 */
public class hashmapTest
{



    

        
        /**
         * copy pasted from python code
         */
        @Test
        public void functionalTest()
        {
            hashmap<String, Object> hashTable =
                new hashmap<>();

            // -------------------------
            // Basic insert/get
            // -------------------------
            assertTrue(hashTable.put("aaa", 1));
            assertEquals(1, hashTable.get("aaa"));
            assertEquals(1, hashTable.size());

            // -------------------------
            // Multiple inserts
            // -------------------------
            assertTrue(hashTable.put("bbb", 2));
            assertTrue(hashTable.put("ccc", 3));
            assertTrue(hashTable.put("ddd", 4));

            assertEquals(1, hashTable.get("aaa"));
            assertEquals(2, hashTable.get("bbb"));
            assertEquals(3, hashTable.get("ccc"));
            assertEquals(4, hashTable.get("ddd"));

            assertNull(hashTable.get("a"));
            assertNull(hashTable.get("aa"));
            assertNull(hashTable.get("aaaa"));

            assertEquals(4, hashTable.size());

            // -------------------------
            // Update existing key
            // -------------------------
            assertFalse(hashTable.put("aaa", 11));

            assertEquals(11, hashTable.get("aaa"));
            assertEquals(4, hashTable.size());

            // -------------------------
            // Delete existing
            // -------------------------
            assertTrue(hashTable.delete("aaa"));

            assertNull(hashTable.get("aaa"));
            assertEquals(3, hashTable.size());

            // -------------------------
            // Delete non-existing
            // -------------------------
            assertFalse(hashTable.delete("a"));
            assertFalse(hashTable.delete("aa"));
            assertFalse(hashTable.delete("aaa"));
            assertFalse(hashTable.delete("aaaa"));

            // -------------------------
            // Delete remaining
            // -------------------------
            assertTrue(hashTable.delete("ddd"));
            assertTrue(hashTable.delete("ccc"));
            assertTrue(hashTable.delete("bbb"));

            assertNull(hashTable.get("aaa"));
            assertNull(hashTable.get("bbb"));
            assertNull(hashTable.get("ccc"));
            assertNull(hashTable.get("ddd"));

            assertEquals(0, hashTable.size());

            // -------------------------
            // Collision test
            // -------------------------
            assertTrue(hashTable.put("abc", 1));
            assertTrue(hashTable.put("acb", 2));
            assertTrue(hashTable.put("bac", 3));
            assertTrue(hashTable.put("bca", 4));
            assertTrue(hashTable.put("cab", 5));
            assertTrue(hashTable.put("cba", 6));

            assertEquals(1, hashTable.get("abc"));
            assertEquals(2, hashTable.get("acb"));
            assertEquals(3, hashTable.get("bac"));
            assertEquals(4, hashTable.get("bca"));
            assertEquals(5, hashTable.get("cab"));
            assertEquals(6, hashTable.get("cba"));

            assertEquals(6, hashTable.size());

            // -------------------------
            // Collision deletes
            // -------------------------
            assertTrue(hashTable.delete("abc"));
            assertTrue(hashTable.delete("cba"));
            assertTrue(hashTable.delete("bac"));
            assertTrue(hashTable.delete("bca"));
            assertTrue(hashTable.delete("acb"));
            assertTrue(hashTable.delete("cab"));

            assertEquals(0, hashTable.size());

            // -------------------------
            // Rehash test
            // -------------------------
            for (int i = 0; i < 100; i++)
            {
                hashTable.put(String.valueOf(i),
                    String.valueOf(i));
            }

            for (int i = 0; i < 100; i++)
            {
                assertEquals(
                    String.valueOf(i),
                    hashTable.get(String.valueOf(i)));
            }

            for (int i = 0; i < 100; i++)
            {
                assertTrue(
                    hashTable.delete(String.valueOf(i)));
            }

            hashTable.put("abc", 1);
            hashTable.put("acb", 2);

            assertEquals(1, hashTable.get("abc"));
            assertEquals(2, hashTable.get("acb"));
        }
    }

