package com.pray;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pray.exception.CloudServiceException;
import com.pray.model.AuthAccount;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * JwtUtil
 *
 * @author 春江花朝秋月夜
 * @since 2024/5/27 18:44
 */
@Component
public class JwtUtil {

    public  String SECRET_KEY="Rainy-Heights";//密钥

    private long expireTime=72*60*60;//过期时间
    public DecodedJWT resolveToken(String token){
        //undefined不是null
        if (token==null){
            return null;
        }
        Algorithm algorithm=Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier jwtVerifier= JWT.require(algorithm).build();

        try {
            DecodedJWT verify = jwtVerifier.verify(token);
            Date expires = verify.getExpiresAt();
            //判断token是否过期同时返回
            return verify;
        }catch (JWTDecodeException e){
            //抛出异常交给下一级处理
            throw new CloudServiceException("token解析失败");
        }
    }

    /**
     * 根据传入的登录信息生成token
     * @param username
     * @return
     */
    public String createToken(String username,int id){
        Algorithm algorithm=Algorithm.HMAC256(SECRET_KEY);
        //携带下面的信息
        return JWT.create()
                .withClaim("id",id)
                .withClaim("username",username)
                .withExpiresAt(expireTime())
                .withIssuedAt(new Date())
                .sign(algorithm);//签名
    }

    public String createRefreshToken(){
        Algorithm algorithm=Algorithm.HMAC256(SECRET_KEY);
        //携带下面的信息
        return JWT.create()
                .withExpiresAt(expireTime())
                .withIssuedAt(new Date())
                .sign(algorithm);//签名
    }

    //设置过期时间
    public Date expireTime(){
        long now = System.currentTimeMillis();
        long future = now + TimeUnit.HOURS.toMillis(this.expireTime);
        return new Date(future);
    }

    /**
     * 解析颁发的jwt
     * @param jwt 已经解析的jwt对象
     * @return User
     */
    public AuthAccount toUser(DecodedJWT jwt){
        Map<String, Claim> claims = jwt.getClaims();
        AuthAccount user = new AuthAccount();
        user.setId(claims.get("id").asInt());
        user.setUsername(String.valueOf(claims.get("username")));
        return user;
    }
}
