package com.snhu.sslserver;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChecksumController {

  @PostMapping("/hash")
  public String hashData(@RequestBody Map<String, String> payload) {
    String data = payload.get("data");
    return getSHA(data);
  }

  @GetMapping("/getHash")
  public String hashData(@RequestParam(name = "data", defaultValue = "Phillip Wood") String data) {
    return getSHA(data);
  }

  private String getSHA(String data) {
    String hexHash = "";

    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] byteHash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
      hexHash = byteToHex(byteHash);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return hexHash;
  }

  // Source: https://www.geeksforgeeks.org/java/sha-256-hash-in-java/
  private static String byteToHex(byte[] bytes) {
    BigInteger n = new BigInteger(1, bytes);
    StringBuilder result = new StringBuilder(n.toString(16));

    while (result.length() < 64) {
      result.insert(0, '0');
    }

    return result.toString();
  }
}
