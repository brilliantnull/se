package esmaster.entity;

import lombok.Data;
import lombok.ToString;
import org.apache.lucene.document.IntPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.xml.soap.Text;
import java.util.Date;

/**
 * @Auther: bai
 * @Date: 2018/12/10 13:20
 * @Description:
 */
@Entity
@Data
@Table(name = "message")
@ToString
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", unique = true, nullable = false)
    private Integer id;

    // 爬取时间
    private Date date;

    // 文本链接
    @Column(name = "url", unique = true, nullable = false)
    private String url;

    // 文本标题
    private String title;

    // 文本内容
    @Lob
    @Column(columnDefinition = "text")
    private String text;

    // 0 未索引, 1 已索引
    @Column(name = "flag", columnDefinition = "0")
    private Integer flag = 0;
}
