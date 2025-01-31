package com.car.rentalservice.util;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OTPGenerator {
	
	private static final SecureRandom random = new SecureRandom();
    private static final int OTP_LENGTH = 6;
    
    public static String generateOTP() {
        return IntStream.range(0, OTP_LENGTH)
                        .mapToObj(i -> String.valueOf(random.nextInt(9)))
                        .collect(Collectors.joining());
    }
}
