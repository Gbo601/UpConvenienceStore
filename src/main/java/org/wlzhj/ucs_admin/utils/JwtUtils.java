package org.wlzhj.ucs_admin.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName: jwtUtils
 * @Author: Gbo601
 * @Date: 2021-2021/10/24 19:20
 * @Description: 生成和校验jwt
 */

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "gbo601.jwt")
public class JwtUtils {

    private String secret;
    private long expire;
    private String header;

    /**
     * @Describe: 生成jwt,token
     * @Param: [userId]
     * @Return: java.lang.String
     * @Author: Gbo601
     * @Date: 2021/10/24 19:34
     */
    public String generateToken(long userId){

        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime()+expire*1000);

        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(userId+"")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }
    /**
     * @Describe: 获取jwt信息
     * @Param: [token]
     * @Return: io.jsonwebtoken.Claims
     * @Author: Gbo601
     * @Date: 2021/10/28 19:07
     */
    public Claims getClaimByToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.debug("token验证错误",e);
            return null;
        }
    }



    /**
     * @Describe: 验证token是否过期,返回true则为过期,false没过期
     * @Param: [expiration]
     * @Return: boolean
     * @Author: Gbo601
     * @Date: 2021/10/24 19:32
     */
    public boolean isTokenExpired(Date expiration){

        return expiration.before(new Date());
    }

}
