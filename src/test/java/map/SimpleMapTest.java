package map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleMapTest {
    SimpleMap<Integer, String> sMap;

    @Before
    public void initData() {
        sMap = new SimpleMap<>();
        sMap.put(1, "home");
        sMap.put(2, "office");
        sMap.put(3, "park");
    }

    @Test
    public void whenPutAndOk() {
        Assert.assertTrue(sMap.put(4, "Disney Land"));
    }

    @Test
    public void whenGetElement() {
        Assert.assertEquals(sMap.get(2), "office");
    }

    @Test
    public void whenGetNull() {
        Assert.assertNull(sMap.get(5));
    }

    @Test
    public void whenRemoveThenTrueThenRemoveThenFalse() {
        Assert.assertTrue(sMap.remove(2));
        Assert.assertFalse(sMap.remove(2));
    }

    @Test
    public void whenRemoveAndFalse() {
        Assert.assertFalse(sMap.remove(5));
    }


}