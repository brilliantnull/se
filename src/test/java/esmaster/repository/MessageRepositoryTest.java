package esmaster.repository;

import esmaster.entity.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: bai
 * @Date: 2018/12/10 16:53
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageRepositoryTest {
    @Autowired
    MessageRepository messageRepository;

    @Test
    public void save(){
        Message message = new Message();
        message.setDate(new Date());
        message.setFlag(0);
        message.setText("textTest");
        message.setTitle("titleTest");
        message.setUrl("www.test.com");
        Message result = messageRepository.save(message);
        assertNotNull(result);
    }

    @Test
    public void get() {
        Iterable<Message> messages = messageRepository.findAll();
        for (Message message : messages) {
            System.out.println(message.toString());
        }
    }

    @Test
    public void findByFlag() {
        int flag = 0;
        List<Message> messages = messageRepository.findMessagesByFlag(flag);
        System.out.println(messages.size());
        assertNotNull(messages);
    }
}