import com.pray.CloudCacheApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * CloudCacheApplicationTest
 *
 * @author 春江花朝秋月夜
 * @since 2024/5/4 0:31
 */
@SpringBootTest(classes = CloudCacheApplication.class)
public class CloudCacheApplicationTest {
    @Test
    void authorizationToken(){
        System.out.println("/redis/borrow/borrowDetails/".startsWith("/redis"));
    }
}
