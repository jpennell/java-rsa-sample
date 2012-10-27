package com.undertheladder.security;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public class App {
    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIGN_ALGORITHM = "SHA1withRSA";
    private static final String SIGNATURE = "test-signature";

    public void run() throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException, InvalidKeySpecException, IOException {
        KeyPair keyPair = this.generateNewKeyPair(App.KEY_ALGORITHM);
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String publicKeyEncoded = Base64.encodeBase64String(publicKey
                .getEncoded());

        Signature signature = Signature.getInstance(App.SIGN_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(App.SIGNATURE.getBytes());

        byte[] signedBytes = signature.sign();
        String signedData = Base64.encodeBase64String(signedBytes);

        this.print("Public Key", publicKeyEncoded);
        this.print("Signed Data", signedData);
        this.print("Verified?", this.verify(publicKeyEncoded, signedData,
                App.SIGNATURE) ? "Yes" : "No");

        writeFile("public.txt", publicKeyEncoded);
        writeFile("signed.txt", signedData);
        writeFile("signature.txt", App.SIGNATURE);
    }

    private void writeFile(String filename, String data) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(filename));
        out.write(data);
        out.close();
    }

    public boolean verify(String publicKeyEncoded, String signedDataEncoded,
            String signature) throws InvalidKeySpecException,
            NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        KeyFactory keyFactory = KeyFactory.getInstance(App.KEY_ALGORITHM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
                Base64.decodeBase64(publicKeyEncoded));
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        Signature sig = Signature.getInstance(App.SIGN_ALGORITHM);
        sig.initVerify(publicKey);
        sig.update(signature.getBytes());

        boolean isVerified = sig.verify(Base64.decodeBase64(signedDataEncoded));

        return isVerified;
    }

    private KeyPair generateNewKeyPair(String algorithm)
            throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(algorithm);
        kpg.initialize(2048);
        KeyPair kp = kpg.genKeyPair();
        return kp;
    }

    public void print(String name, String value) {
        System.out.println(name);
        System.out.println("-------------------------");
        System.out.println(value + "\n");
    }
}
