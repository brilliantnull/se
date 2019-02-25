package esmaster.repository;

import esmaster.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Auther: bai
 * @Date: 2018/12/10 16:47
 * @Description:
 */
public interface MessageRepository extends CrudRepository<Message, String> {

    Page<Message> findAll(Pageable pageable);

    List<Message> findMessagesByFlag(Integer flag);
}
