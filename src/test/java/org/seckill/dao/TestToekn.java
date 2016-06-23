package org.seckill.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64UrlCodec;
import org.junit.Test;
import org.postgresql.util.Base64;
import sun.security.rsa.RSAPublicKeyImpl;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * 测试数据库连接
 * Created by never615 on 6/15/16.
 */
public class TestToekn {


    @Test
    public void testToken() {
//        String token = "Bearer{eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjcxMywiaXNzIjoiaHR0cDpcL1wvYXBpLmlmZW5nZ3VvLmNvbTo4MVwvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTQ2NjEzNzUyNiwiZXhwIjoxNDczOTEzNTI2LCJuYmYiOjE0NjYxMzc1MjYsImp0aSI6ImEyZDhiMzkxODY4MjU2NzQ5YWY5Yzk5NmQwNDYxYmIxIn0.wfvVmWE9zaEQw-23aM7oQiXiPWwzDubMcG5rsB2ns-4}";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjcxMywiaXNzIjoiaHR0cDpcL1wvYXBpLmlmZW5nZ3VvLmNvbTo4MVwvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTQ2NjEzNzUyNiwiZXhwIjoxNDczOTEzNTI2LCJuYmYiOjE0NjYxMzc1MjYsImp0aSI6ImEyZDhiMzkxODY4MjU2NzQ5YWY5Yzk5NmQwNDYxYmIxIn0.wfvVmWE9zaEQw-23aM7oQiXiPWwzDubMcG5rsB2ns-4";
//          String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI3MTMiLCJleHAiOjE0NjYxMzcsImlhdCI6MTQ2NjEzNywibmJmIjoxNDY2MTM3LCJpc3MiOiJodHRwOlxcL1xcL2FwaS5pZmVuZ2d1by5jb206ODFcXC9hcGlcXC9hdXRoXFwvbG9naW4iLCJqdGkiOiJhMmQ4YjM5MTg2ODI1Njc0OWFmOWM5OTZkMDQ2MWJiMSJ9.y32F0RMQfA9fDEJyCgJSihf0bx5b2oGWtJ6TcVTc5vQ";
//          String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NjYiLCJleHAiOjE0ODU5MjUxOTEsImlhdCI6MTQ2NjM5ODc5MSwibmJmIjoxNDY2Mzk4NzkxLCJpc3MiOiJpc3N1ZXIiLCJhdWQiOiJhdWRpZW5jZSIsImp0aSI6ImlkIn0.cZTWF3Cs34_ES9utd-_GiWgl_6Y1ZPFeuv0FFwRva_E";
//                        eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpZCJ9.Yq2y1bnH0F29oogCs237s411lfVgCnnjQAjIj6Pvf9Q

        String key = "fp5LsFyGIpcvqbtIsU3bwfQZHNFyVsLh";

        System.out.println("---------------------------");

        String[] tokens = token.split("\\.");
        System.out.println("num:" + tokens.length);


        System.out.println(new String(Base64.decode(tokens[0])));
        System.out.println(new String(Base64.decode(tokens[1])));
        System.out.println(tokens[2]);


        String json=new String(Base64.decode(tokens[1]))+"\"}";


        ObjectMapper objectMapper=new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            long userId=jsonNode.get("sub").asLong();

            System.out.println("userId:"+userId);

        } catch (IOException e) {
            System.out.println("e:"+e);

        }


        System.out.println("---------------------------");

//        String compressionAlgorithm = Jwts.parser().setSigningKey(key).parseClaimsJwt(token).getHeader().getCompressionAlgorithm();
//        System.out.println("compressionAlgorithm:"+compressionAlgorithm);
//
//        String contentType = Jwts.parser().setSigningKey(key).parseClaimsJwt(token).getHeader().getContentType();
//        System.out.println("contentType:"+contentType);

//        Jwts.parser().parseClaimsJws(token);

//        Jwts.parser().setSigningKeyResolver(new SigningKeyResolver() {
//            @Override
//            public Key resolveSigningKey(JwsHeader header, Claims claims) {
//                System.out.println("claims:"+claims.getSubject());
//
//
//                return null;
//            }
//
//            @Override
//            public Key resolveSigningKey(JwsHeader header, String plaintext) {
//
//                System.out.println("plaintext:"+plaintext);
//
//
//                return null;
//            }
//        }).parsePlaintextJwt();


//        String signature = Jwts.parser().setSigningKey("fp5LsFyGIpcvqbtIsU3bwfQZHNFyVsLh").parseClaimsJws(token).getSignature();
//        System.out.println("signature:" + signature);


//        JwsHeader header = Jwts.parser().setSigningKey("fp5LsFyGIpcvqbtIsU3bwfQZHNFyVsLh").parseClaimsJws(token).getHeader();
        Header header = Jwts.parser().setSigningKey("fp5LsFyGIpcvqbtIsU3bwfQZHNFyVsLh").parseClaimsJwt(token).getHeader();
//        System.out.println("header:" + header.getAlgorithm());
        System.out.println("header:" + header.getCompressionAlgorithm());
        System.out.println("header:" + header.getContentType());
        System.out.println("header:" + header.getType());
//        System.out.println("header:" + header.getKeyId());
        System.out.println("header:" + header.get("typ"));


//        String subject = Jwts.parser().setSigningKey("fp5LsFyGIpcvqbtIsU3bwfQZHNFyVsLh").parseClaimsJws(token).getBody().getSubject();
        String subject = Jwts.parser().setSigningKey("fp5LsFyGIpcvqbtIsU3bwfQZHNFyVsLh").parseClaimsJwt(token).getBody().getSubject();
        System.out.println("subject:" + subject);


    }


    @Test
    public void testToken2() {
//        Key key = MacProvider.generateKey();
//        System.out.println("key:"+key.getAlgorithm());
//        System.out.println("key:"+key.getFormat());

//        {"typ":"JWT","alg":"HS256"}
//        {"sub":713,"iss":"http:\/\/api.ifengguo.com:81\/api\/auth\/login","iat":1466137526,"exp":1473913526,"nbf":1466137526,"jti":"a2d8b391868256749af9c996d0461bb1


//        String token = Jwts.builder().setSubject("id").signWith(SignatureAlgorithm.HS256, key).compact();

        Date date = new Date(1466137526);
        System.out.println("date:"+date.getTime());


        String token = Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject("713")
                .setExpiration(new Date(1466137526)) //过期时间
                .setIssuedAt(new Date(1466137526))  //签发时间
                .setNotBefore(new Date(1466137526))
                .setIssuer("http:\\/\\/api.ifengguo.com:81\\/api\\/auth\\/login")
//                .setAudience("接收方")
//                .setPayload("payload")
                .setId("a2d8b391868256749af9c996d0461bb1")
                .signWith(SignatureAlgorithm.HS256, "fp5LsFyGIpcvqbtIsU3bwfQZHNFyVsLh")

                .compact();

        System.out.println("jwt token:" + token);


//        String signature = Jwts.parser().setSigningKey("fp5LsFyGIpcvqbtIsU3bwfQZHNFyVsLh").parseClaimsJws(token).getSignature();
//        System.out.println("signature:" + signature);

//        JwsHeader header = Jwts.parser().setSigningKey("fp5LsFyGIpcvqbtIsU3bwfQZHNFyVsLh").parseClaimsJws(token).getHeader();
//        Header header = Jwts.parser().setSigningKey("fp5LsFyGIpcvqbtIsU3bwfQZHNFyVsLh").parseClaimsJwt(token).getHeader();
//        System.out.println("header:" + header.getAlgorithm());
//        System.out.println("header:" + header.getCompressionAlgorithm());


//        String subject = Jwts.parser().setSigningKey("fp5LsFyGIpcvqbtIsU3bwfQZHNFyVsLh").parseClaimsJws(token).getBody().getSubject();
        String subject = Jwts.parser().setSigningKey("fp5LsFyGIpcvqbtIsU3bwfQZHNFyVsLh").parseClaimsJwt(token).getBody().getSubject();
        System.out.println("subject:" + subject);

    }


}
