package esmaster.lucene.searcher;

import esmaster.entity.Message;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: bai
 * @Date: 2018/12/13 13:19
 * @Description:
 */
public class SearcherTest {

    @Test
    public void show() {
        List<Message> messages = Searcher.search("MOOC");
        int i = 0;
//        messages.forEach(message -> System.out.println("url: " + message.getUrl() + "\r\n" + "text: " + message.getText()));
        for (Message message : messages) {
            System.out.println(++i + "url: " + message.getUrl() + "\r\n" + "text: " + message.getText());
        }
    }
}