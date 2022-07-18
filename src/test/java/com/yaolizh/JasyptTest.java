//package com.yaolizh;
//
// 
//
//import org.jasypt.encryption.StringEncryptor;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
// 
// 
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class JasyptTest {
// 
//    @Autowired
//    StringEncryptor encryptor;
//    //加密
//    @Test
//    public void getPass(){
//    	 String name = encryptor.encrypt("root");
//         String password = encryptor.encrypt("root");
//         System.out.println(name); //BEJsOY+ny6/Bo+B8Rv2f9Q==
//         System.out.println(password); //+w8Jn3Iv1R7nsFa6fO24Dw==
//         String name2 = encryptor.encrypt("x5sys");
//         String password2 = encryptor.encrypt("Dg123456");
//         System.out.println(name2); //BEJsOY+ny6/Bo+B8Rv2f9Q==
//         System.out.println(password2); //+w8Jn3Iv1R7nsFa6fO24Dw==
//    }
//    //解密
//    @Test
//    public void passDecrypt(){
//        String username = encryptor.decrypt("HYTWXJVG9gQ6KrTcxVx7Rg==");
//        String password = encryptor.decrypt("//FAl136Ot3EoF8gWmVPYr+g==");
//        System.out.println(username+"--"+password);
//    }
// 
//}
//
