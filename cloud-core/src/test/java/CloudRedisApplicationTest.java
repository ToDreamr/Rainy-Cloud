import com.pray.CloudRedisApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * CloudRedisApplicationTest
 *
 * @author 春江花朝秋月夜
 * @since 2024/5/4 0:31
 */
@SpringBootTest(classes = CloudRedisApplication.class)
public class CloudRedisApplicationTest {
    @Test
    void authorizationToken(){
        System.out.println("/redis/borrow/borrowDetails/".startsWith("/redis"));
    }
}
