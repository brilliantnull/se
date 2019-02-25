package esmaster.lucene.indexer;

import esmaster.entity.Message;
import esmaster.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: bai
 * @Date: 2018/12/13 13:21
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexerTest {

    @Autowired
    MessageRepository messageRepository;


    @Test
    public void createIndex() throws IOException {
        List<Message> messages = messageRepository.findMessagesByFlag(1);
        for (Message message : messages) {
            Indexer.createIndex(messages);
            System.out.println(message.toString());
        }
    }
}