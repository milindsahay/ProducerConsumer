package distributed.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Topic {

    private String name;
    private List<Consumer> consumers;
    private List<String> messages;

    public Topic(String name){
        consumers = new ArrayList<>();
        messages = new ArrayList<>();
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public void addConsumer(Consumer consumer){
        consumers.add(consumer);
    }

    public void publish(String message){
        synchronized (messages){
            messages.add(message);
        }
    }

    public String consume(AtomicInteger offset) throws InterruptedException {
        Thread.sleep(100);
        synchronized (messages){
            if(offset.get() < messages.size()) return messages.get(offset.getAndIncrement());
            return null;
        }
    }
}
