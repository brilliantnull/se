package esmaster.lucene.indexer;

import esmaster.entity.Message;
import esmaster.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Auther: bai
 * @Date: 2018/12/13 11:22
 * @Description:
 */
@Component
@Slf4j
public class Indexer {
    public static final String INDEX_PATH = "Indexer";

    @Autowired
    MessageRepository messageRepository;

    public static Indexer indexer;

    @PostConstruct
    public void init() {
        indexer = this;
        indexer.messageRepository = this.messageRepository;
    }

    public Indexer() { }

    public static IndexWriter writer;


    public static void createIndex(List<Message> messages) throws IOException {
        indexer.init();
        try {
            // 使用了nio，存储索引的路径
            Directory dir = FSDirectory.open(Paths.get(INDEX_PATH));
            Analyzer analyzer = new IKAnalyzer();
            // 新的IndexWriter配置类
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            // 创建模式打开, 追加模式添加索引
            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            // 设置内存缓存的大小，提高索引效率，不过如果修改过大的值，需要修改JVM的内存值
//            iwc.setRAMBufferSizeMB(256.0);
            // 创建IndexWriter
            writer = new IndexWriter(dir, indexWriterConfig);

            Document document;
            // 遍历未索引数据为其添加索引
            for (Message message : messages) {
                document = new Document();
                document.add(new TextField("id", message.getId().toString(), Field.Store.YES));
                document.add(new TextField("flag", message.getFlag().toString(), Field.Store.YES));
                document.add(new TextField("title", message.getTitle(), Field.Store.YES));
                document.add(new TextField("url", message.getUrl(), Field.Store.YES)); // 不做analyze
                document.add(new TextField("text", message.getText(), Field.Store.YES)); // 不做analyze
                document.add(new TextField("date", message.getDate().toString(), Field.Store.YES));
                writer.addDocument(document);

                // 添加完索引将 flag 设为1
                message.setFlag(1);
                indexer.messageRepository.save(message);
            }
            writer.commit();
            log.info("【索引信息】已索引[{}]条数据！", messages.size());
        } catch (Exception ex) {
            ex.printStackTrace();
            writer.rollback();
            log.error("【索引信息】索引添加出错，已回滚！");
        } finally {
            writer.close();
        }
    }
}
