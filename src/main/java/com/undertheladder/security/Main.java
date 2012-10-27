package com.undertheladder.security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException,
            InvalidKeyException, SignatureException, InvalidKeySpecException,
            IOException {

        App app = new App();
        // new App().run();

        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA52wx9JF515uUaLBI02DOuirthvPuLN3/2fYr8JWVhdd/nwQS76ygD04fMK1M/KTvK6YiPal7jRlqOmOTxkBw5CaIGkUaLd6Y5b0HaKyByagxZNa2mbZPPnLL625EHZ6pngVZoLYZu901A+qppjYpy2elF4gCpHxDfbuVkK0lP6LCth5K9YYB7AReXHscFlBi/s6GpjoXGTI8g0Zvm4a5SY61FpH/ew6f42q4y0eaJ3zWBKGQObZYRTRImi54SNIl/Yy1Sx0RCfh5yPVAzx6WrbSEV7TVwVXdDp6usfagnA+SAetNvVVhZPqs2MJP094LDqgCsHjOcUQsVY2+L+v2lQIDAQAB";
        String signature = "test-signature";
        String signedData = "ELkvIjSMu2Ao2e2btAu/xbNGVs/NhLwysC8r2nYbFU7GwXGger5IOaRZonr5Tj5QZnPIpW/qQJWrBy7kjMbJ+P9Ox3E0L1IxoM/vb8oVAThw+fERJ88HKeIqisOpKVZGDzxtvqYjPdWCcKU5NpvB7OGyK4FE1IicgwMO3Hna1uxP4rKx53SZxEH65aDQCPRdPiVac3bMH7YMh/UXHz/NZEmIihoKZocBNE1ZsDlJT9kksCbpuC04t0nTtqCsc392KRStXYDH1WdJyLuKGT2s1EV+p5/QbFTUHcbZCKPo3TJA3NGvTpN3WF99L/f3BjXKjt+Yyh6+ALWBqQlYNiNk8A==";

        app.print("Verified?",
                app.verify(publicKey, signedData, signature) ? "Yes" : "No");
    }

}
