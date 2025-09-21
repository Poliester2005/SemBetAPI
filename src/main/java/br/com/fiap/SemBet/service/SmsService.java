package br.com.fiap.SemBet.service;

import org.springframework.stereotype.Service;

@Service
public class SmsService {

    public void sendOtp(String contato, String otp) {
        System.out.println("[SIMULAÇÃO] Enviando OTP para " + contato + ": " + otp);
    }
}
