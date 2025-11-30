package xlc;


import sun.misc.Cleaner;

public class MyResource {
    // 注册 cleaner
    private final Cleaner cleaner;

    public MyResource() {
        cleaner = Cleaner.create(this, new CleanupTask());
    }

    // 清理任务
    private static class CleanupTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Cleaning resource...");
            // 在这里执行文件句柄关闭、直接内存释放等动作
        }
    }

    public static void main(String[] args) {
        MyResource resource = new MyResource();
        resource = null;  // 使对象变成可回收
        System.gc();      // 触发 GC，Cleaner 会执行 run()
    }
}

