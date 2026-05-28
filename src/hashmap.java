import java.util.LinkedList;
import java.util.ListIterator;
// -------------------------------------------------------------------------
/**
 *  implements a hashmap (generic)
 * 
 *  @author mayakkrla
 *  @version 28 May 2026
 */
public class hashmap <K, V>
{
 // -------------------------
    // Entry class
    // -------------------------
    private class Entry
    {
        K key;
        V value;

        Entry(K k, V v)
        {
            key = k;
            value = v;
        }
    }

    // -------------------------
    // Fields
    // -------------------------
    private LinkedList<Entry>[] buckets;
    private int size;
    private int capacity;

    private static final double LOAD_FACTOR = 0.75;

    // ----------------------------------------------------------
    /**
     * Create a new hashmap object.
     */
    // -------------------------
    // Constructor
    // -------------------------
    @SuppressWarnings("unchecked")
    public hashmap()
    {
        capacity = 16;
        buckets = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++)
        {
            buckets[i] = new LinkedList<>();
        }

        size = 0;
    }

    // -------------------------
    // Hash function
    // -------------------------
    private int hash(K key)
    {
        return Math.abs(key.hashCode()) % capacity;
    }

    // ----------------------------------------------------------
    /**
     * Setter
     * @param key key in hashmap
     * @param value value corresponding to key
     * @return true if a new key-value pair was added, false if key existed
     * (value gets updated)
     * 
     */
    
    public boolean put(K key, V value)
    {
        int index = hash(key);

        LinkedList<Entry> bucket = buckets[index];

        // Update existing key
        for (Entry entry : bucket)
        {
            if (entry.key.equals(key))
            {
                entry.value = value;
                return false;
            }
        }

        // Add new key
        bucket.add(new Entry(key, value));
        size++;

        // Rehash if needed
        if ((double)size / capacity >= LOAD_FACTOR)
        {
            rehash();
        }

        return true;
    }

  
   

    // ----------------------------------------------------------
    /**
     * Getter
     * @param key
     * @return returns value if found
     */
    
    
    public V get(K key)
    {
        int index = hash(key);

        LinkedList<Entry> bucket = buckets[index];

        for (Entry entry : bucket)
        {
            if (entry.key.equals(key))
            {
                return entry.value;
            }
        }

        return null;
    }

    // ----------------------------------------------------------
    /**
     * Delete method
     * @param key
     * @return true if the key was found and removed
     */
    // -------------------------
    
    // -------------------------
    public boolean delete(K key)
    {
        int index = hash(key);

        LinkedList<Entry> bucket = buckets[index];
        ListIterator<Entry> iterator = bucket.listIterator();
        while (iterator.hasNext())
        {
            
            Entry entry = iterator.next();
   
        
            if (entry.key.equals(key))
            {
                iterator.remove();
                size--;
                return true;
            }
        }

        return false;
    }
    

    
    /**
     * Returns the number of key-value pairs currently
     * stored in the hash table.
     *
     * @return the total number of items in the hash table
     */
    
    public int size()
    {
        return size;
    }

    // -------------------------
    // Rehash
    // -------------------------
    @SuppressWarnings("unchecked")
    private void rehash()
    {
        LinkedList<Entry>[] oldBuckets = buckets;

        capacity *= 2;

        buckets = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++)
        {
            buckets[i] = new LinkedList<>();
        }

        int oldSize = size;
        size = 0;

        for (LinkedList<Entry> bucket : oldBuckets)
        {
            for (Entry entry : bucket)
            {
                put(entry.key, entry.value);
            }
        }

        size = oldSize;
    }
}
