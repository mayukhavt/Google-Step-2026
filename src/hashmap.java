import java.util.LinkedList;
// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
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
     * Place a description of your method here.
     * @param key
     * @param value
     * @return
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

    // -------------------------
    // Get
    // -------------------------
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

    // -------------------------
    // Delete
    // -------------------------
    public boolean delete(K key)
    {
        int index = hash(key);

        LinkedList<Entry> bucket = buckets[index];

        for (int i = 0; i < bucket.size(); i++)
        {
            if (bucket.get(i).key.equals(key))
            {
                bucket.remove(i);
                size--;
                return true;
            }
        }

        return false;
    }

    // -------------------------
    // Size
    // -------------------------
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
