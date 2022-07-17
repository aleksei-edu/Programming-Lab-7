package com.lapin.common.encrypt;

import com.lapin.common.utility.Pair;
import com.lapin.di.context.ApplicationContext;
import com.lapin.network.log.NetworkLogOutputConsole;
import com.lapin.network.log.NetworkLogger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Encryptor implements Encryptor{
    private static final short RADIX = 16;
    private static final short HASH_TEXT_LENGTH = 32;
    private static final String PAPER = "eP$jq#h@";

    @Override
    public String encrypt(String input) {
        NetworkLogger logger = ApplicationContext.getInstance().getBean(NetworkLogger.class);
        logger.setLogOutput(new NetworkLogOutputConsole());
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashText = new StringBuilder(no.toString(RADIX));

            while (hashText.length() < HASH_TEXT_LENGTH) {
                hashText.insert(0, "0");
            }

            result = hashText.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("Encryption error!");
        }
        return result;
    }
}
