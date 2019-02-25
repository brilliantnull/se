package esmaster.service.impl;

import esmaster.entity.Message;
import esmaster.repository.MessageRepository;
import esmaster.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Auther: bai
 * @Date: 2018/12/11 11:22
 * @Description:
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;

    @Override
    public Page<Message> findMessages(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return messageRepository.findAll(pageable);
    }
}
