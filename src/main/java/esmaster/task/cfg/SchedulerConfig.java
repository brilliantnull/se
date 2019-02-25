package esmaster.task.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Auther: bai
 * @Date: 2018/12/12 18:40
 * @Description:    定时任务配置类
 */
@Configuration
// 启动定时任务， 使 @Schedule 任务生效
@EnableScheduling
public class SchedulerConfig {
}
