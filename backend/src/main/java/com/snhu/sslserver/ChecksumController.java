package com.snhu.sslserver;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class ChecksumController {

  final int DATA_SIZE_LIMIT = 1000000; //Size limit in bytes. 

  @PostMapping("/hash")
  public String hashData(@RequestBody Map<String, String> payload) {
    String data = payload.get("data");
    if(data == null || data.getBytes(StandardCharsets.UTF_8).length > DATA_SIZE_LIMIT) {
      return "Invalid Data";
    }
    return getSHA(data);
  }

  @GetMapping("/getHash")
  public String hashData(@RequestParam(name = "data", defaultValue = "Phillip Wood") String data) {
    if(data == null || data.getBytes().length > DATA_SIZE_LIMIT) {
      return "Invalid Data";
    }
    final String orgData = HtmlUtils.htmlEscape(data); //Prevents cross-sight scripting by escaping html tags, such as <script> tags. 
    return "<p>Data: " + orgData + "</p><p>Hash: " + getSHA(data) + "</p>";
  }

  private String getSHA(String data) {
    final String algo = "SHA-256";
    String hexHash = "";

    try {
      MessageDigest digest = MessageDigest.getInstance(algo);
      byte[] byteHash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
      hexHash = byteToHex(byteHash);
    } catch (NoSuchAlgorithmException e) {
      return algo + " Not Found!";
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
