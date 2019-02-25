package esmaster.controller;

import esmaster.entity.Message;
import esmaster.lucene.searcher.Searcher;
import esmaster.repository.MessageRepository;
import esmaster.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther: bai
 * @Date: 2018/12/10 13:12
 * @Description:
 */
@Controller
@Slf4j
public class SearchController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageService messageService;

    List<Message> messageList;

    /**
     *
     * @param info      搜索信息
     * @param page      页码
     * @param pageSize  页面显示大小
     * @param modelMap  与 thymeleaf 交互
     *
     *              messages 搜索结果， 应为索引之后的搜索结果  √
     *              info 索引内容   √
     *
     *              TimerTask 设置定时添加索引任务 √
     *
     *
     *              追加任务， 分页， 由于lucene索引的原因之前的分页无法使用， 所以需要更好的分页解决办法。 √
     *
     * @return
     *
     * description: 本方法实现从 web 端搜索关键字 info, 在 controller 中获取数据后返回至前端模板引擎 thymeleaf
     *              将数据显示在前端 search.html 。
     */
    @GetMapping(value="/search")
    public String showResultList(@RequestParam("s") String info,
                                 @RequestParam(value = "p", defaultValue = "1") int page,
                                 @RequestParam(value = "size", defaultValue = "5") int pageSize,
                                 ModelMap modelMap){

        // 从索引库中搜索含有 info 的信息， 返回至 List<Message>
        List<Message> messages = Searcher.search(info);
        // 获取信息数量
        int messagesSize = messages.size();
        int maxPage = 0;
        // 获取最大页数
        maxPage = messagesSize / pageSize;
        if(page == 0) page = 1;
        if(page == maxPage + 1) page = maxPage;
        // 获得截取数据的上下限
        int fromIndex = (page - 1) * pageSize;
        int toIndex = page * pageSize;

        if (fromIndex < 0) {
            messageList = null;
            modelMap.addAttribute("messageSize", 0);
            log.error("【搜索信息】搜索结果[{}]为空！", info);
//            throw new SearchException(ResultEnum.RESULT_NOT_EXIT);
        } else {
            // 获得当前页面数据
            messageList = messages.subList(fromIndex, toIndex);
            modelMap.addAttribute("messageSize", messagesSize);
        }
        // 将与 thymeleaf 交互的数据存入 modelMap
        modelMap.addAttribute("p", page);
        modelMap.addAttribute("maxPage", maxPage);
        modelMap.addAttribute("messages", messageList);
        modelMap.addAttribute("info", info);
        return "/search";
    }
}
