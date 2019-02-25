package esmaster.task;

import esmaster.lucene.indexer.*;
import esmaster.entity.Message;

import esmaster.repository.MessageRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: bai
 * @Date: 2018/12/12 18:36
 * @Description:    定时任务，每隔一段时间对数据库中没有索引的数据添加索引
 */
@Component
@Slf4j
public class IndexerTask {

    @Autowired
    MessageRepository messageRepository;

    public static IndexerTask indexerTask;

    public IndexerTask() { }

    @PostConstruct
    public void init() {
        indexerTask = this;
        indexerTask.messageRepository = this.messageRepository;
    }

    // 定时任务每60s一次
    @Scheduled(cron="*/60 * * * * ?")
    private void process() throws IOException {
        // 获得数据库中未索引的数据
        List<Message> messages = messageRepository.findMessagesByFlag(0);

        // 数据存在
        if (messages.size() != 0) {
            Indexer.createIndex(messages);
        }
        log.info("【定时任务】线程[{}]正在添加索引，索引数据量：[{}]", Thread.currentThread().getName(), messages.size());
    }

}
