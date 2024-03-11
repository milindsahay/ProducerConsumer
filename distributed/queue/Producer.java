package distributed.queue;

import java.util.List;

public class Producer extends Thread{
    private String name;
    private List<Topic> topics;
    public Producer(String name, Topic... topics){
        this.name = name;
        this.topics = List.of(topics);
    }
    @Override
    public void run() {
        while (true){
            for (Topic topic: topics){
                String message = "message_milind:" + topic.getName() + " Producer: "+name + " --> " + System.currentTimeMillis();
                System.out.println("Publishing to topic: " + topic.getName() + " message: "+ message);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                topic.publish(message);
            }
        }

    }
}
