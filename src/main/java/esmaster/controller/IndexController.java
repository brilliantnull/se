package esmaster.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: bai
 * @Date: 2018/12/10 11:40
 * @Description:
 */
@Controller
@Slf4j
public class IndexController {

    // 首页
    @GetMapping(path = "/index")
    public String index() {
        log.info("【访问信息】用户[{}]正在访问首页！", System.getenv().get("USERNAME"));
        return "/index";
    }

    // 404
    @GetMapping(path = "/error")
    public String error() { return "/error"; }


}
