
/**
 * 多线程一定快吗
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/10/10
 * Time: 14:58
 */
public class ConcurrencyTest {

    private static final long count = Long.MAX_VALUE;

    public static void main(String[] args) throws InterruptedException {
        DynamicExecutorFactory.getExecutorService(true,10);


        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {

        long start = System.currentTimeMillis();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });

        thread.start();
        long b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency :" + time + "ms,b=" + b);
    }

    private static void serial() {

        long start = System.currentTimeMillis();
        long a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        long b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial :" + time + "ms,b=" + b + ",a=" + a);
    }
}
