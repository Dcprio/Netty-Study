package xlc;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 *  强软弱虚
 */
public class Qrrx {

    public static HashMap hashMap = new HashMap();

    public static void main(String[] args) {
        ReferenceQueue queue = new ReferenceQueue();
        hashMap.put("11","11");
        WeakReference<Map> softReference = new WeakReference(new HashMap<>(),queue);
        System.gc();
        System.out.println("1111");

    }



}
