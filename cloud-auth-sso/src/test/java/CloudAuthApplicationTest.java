import com.auth0.jwt.interfaces.DecodedJWT;
import com.pray.CloudOAuthApplication;
import com.pray.util.JwtUtil;
import com.pray.model.AuthAccount;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * CloudServiceApplicationTest
 *
 * @author 春江花朝秋月夜
 * @since 2024/1/18 12:53
 */
@SpringBootTest(classes = CloudOAuthApplication.class)
@MapperScan(basePackages = "com.pray.mapper")
public class CloudAuthApplicationTest {
    @Resource
    private JwtUtil jwtUtil;


    @Test
    void sendMessage(){
        DecodedJWT decodedJWT = jwtUtil.resolveToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTg5ODE4MDYxMSwiZXhwIjoyNjQ5OTI5MzQxLCJpYXQiOjE3M" +
                "TY4MDkzNDEsInVzZXJuYW1lIjoidXNlciJ9.KkiYaSWlIBJXPEttQ2-kqH7dDDTWJbpAvOZ8qYYoQgU");
        AuthAccount user = jwtUtil.toUser(decodedJWT);
        System.out.println(user.toString());
    }
}
