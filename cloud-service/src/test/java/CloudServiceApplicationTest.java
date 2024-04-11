import com.pray.CloudServiceApplication;
import com.pray.constants.RabbitMqConstants;
import com.pray.entity.po.Book;
import com.pray.feign.CacheClient;
import com.pray.feign.ServiceClient;
import com.pray.mapper.BorrowMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * CloudServiceApplicationTest
 *
 * @author 春江花朝秋月夜
 * @since 2024/1/18 12:53
 */
@SpringBootTest(classes = CloudServiceApplication.class)
@MapperScan(basePackages = "com.pray.mapper")
public class CloudServiceApplicationTest {
    @Resource
    private BorrowMapper borrowMapper;
    @Test
    void userListTest() {
        List<Map<String, Object>> objects = borrowMapper.selectBorrowDetails(8,11);
        List<Map<String, Object>> maps = borrowMapper.selectBorrowUser();
        for (Map<String, Object> o:maps){
            o.entrySet().forEach(System.out::println);
        }
    }
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Test
    void sendMessage(){
        Book book = new Book();
        book.setBookCount(12);
        book.setBookId(1);
        book.setBookName("年后");
        rabbitTemplate.convertAndSend("amq.fanout",RabbitMqConstants.BOOK_BORROW_TOPIC,book);
        System.out.println(rabbitTemplate.getMessageConverter());
        System.out.println(rabbitTemplate);
    }
    @Resource
    private CacheClient client;
    @Resource
    private ServiceClient serviceClient;
    @Test
    void cacheClientTest(){
        List<Map<String, Object>> borrowUsers = client.getBorrowUsers();
    }
}
