package com.snhu.sslserver;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChecksumController {

  @GetMapping("/hash")
  public String hashData(String data) {
      data = "Phillip Wood";
    	String hexHash = "";
    	
    	try {
    		MessageDigest digest = MessageDigest.getInstance("SHA-256");
    		byte[] byteHash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
    		hexHash = byteToHex(byteHash);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
        return "<p>data:"+data + "</p>" + "<p>Chiper Used: SHA-256" + " Checksum Value: " + hexHash + "</p>";
  }

    //Source: https://www.geeksforgeeks.org/java/sha-256-hash-in-java/
    private static String byteToHex(byte[] bytes) {
    	BigInteger n = new BigInteger(1, bytes);
    	StringBuilder result = new StringBuilder(n.toString(16));
    	
    	while(result.length() < 64) {
    		result.insert(0, '0');
    	}
    	
    	return result.toString();
    }
}
