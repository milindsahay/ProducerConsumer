package distributed.queue;


import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer extends Thread{
    private String name;
    private List<Topic> topics;

    private HashMap<String, AtomicInteger> topicToOffsetMap = new HashMap<>();

    public Consumer(String name, Topic... topics){
        this.name = name;
        this.topics = List.of(topics);
        for (Topic topic: this.topics){
            topicToOffsetMap.computeIfAbsent(topic.getName(), (k) -> new AtomicInteger(0));
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (Topic topic : topics) {
                String message;
                try {
                    message = topic.consume(topicToOffsetMap.get(topic.getName()));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (message != null) System.out.println("Consuming message: " + message + " from consumer: " + name);
            }
        }
    }
}
