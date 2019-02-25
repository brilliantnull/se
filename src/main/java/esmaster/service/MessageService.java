package esmaster.service;

import esmaster.entity.Message;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Auther: bai
 * @Date: 2018/12/11 10:18
 * @Description:
 */
public interface MessageService {
    Page<Message> findMessages(Integer page, Integer size);
}
