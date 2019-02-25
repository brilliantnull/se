package esmaster.lucene.searcher;

import esmaster.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: bai
 * @Date: 2018/12/13 11:57
 * @Description:
 */

@Slf4j
public class Searcher {
    private static final String INDEX_PATH = "Indexer";
    // 显示记录数
    private static final int TOP_NUM = Integer.MAX_VALUE;

    private static Message message;

    /**
     *      索引加搜索的显示    前端页面的美化
     *      异常处理     日志显示
     */

    private static List<Message> messageList;

    public static List<Message> search(String queryStr) {
        Directory dir = null;
        try {
            dir = FSDirectory.open(Paths.get(INDEX_PATH));
            IndexReader reader = DirectoryReader.open(dir);
            IndexSearcher searcher = new IndexSearcher(reader);
            Analyzer analyzer = new StandardAnalyzer();
            String fieldString = "text";
            // 按照索引中的 "text" 域搜索， 索引在本项目中有 title 和 text 域
            QueryParser parser = new QueryParser(fieldString, analyzer);
            parser.setDefaultOperator(QueryParser.AND_OPERATOR);
            Query query = parser.parse(queryStr);
            // 查找操作
            TopDocs hits = searcher.search(query, TOP_NUM);
            messageList = new ArrayList<Message>();
            // 获取搜索到的数据
            for (ScoreDoc scoreDoc : hits.scoreDocs) {
                // 根据文档打分得到文档的内容
                Document doc = searcher.doc(scoreDoc.doc);
                message = new Message();
                message.setId(Integer.parseInt(doc.get("id")));
                message.setFlag(Integer.parseInt(doc.get("flag")));
                message.setUrl(doc.get("url"));
                message.setTitle(doc.get("title"));
                String text = doc.get("text").length() > 200 ? doc.get("text").substring(0, 200).concat("...") : doc.get("text");
                message.setText(text);
                message.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(doc.get("date")));
                messageList.add(message);
            }
            log.info("【搜索信息】用户[{}]正在搜索[{}],信息量：[{}]", System.getenv().get("USERNAME"), queryStr, messageList.size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return messageList;
    }
}
