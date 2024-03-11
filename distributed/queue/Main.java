package distributed.queue;

public class Main {
    public static void main(String[] args){
        DistributedQueue distributedQueue = new DistributedQueue();
        Topic t1 = new Topic("topic_1");
        Topic t2 = new Topic("topic_2");
        Producer p1 = new Producer("producer_1", t1, t2);
        Producer p2 = new Producer("producer_2", t2);

        Consumer c1 = new Consumer("consumer_1", t1);
        Consumer c2 = new Consumer("consumer_2", t2);
        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}
