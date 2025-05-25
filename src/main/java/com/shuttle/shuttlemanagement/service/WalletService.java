package com.shuttle.shuttlemanagement.service;

import com.shuttle.shuttlemanagement.entity.User;
import com.shuttle.shuttlemanagement.entity.Wallet;
import com.shuttle.shuttlemanagement.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet getWallet(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found for user"));
    }

    public double getBalance(User user) {
        return getWallet(user).getBalance();
    }

    public void addFunds(User user, double amount) {
        Wallet wallet = getWallet(user);
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
    }

    public void deductFunds(User user, double amount) {
        Wallet wallet = getWallet(user);
        if (wallet.getBalance() < amount) {
            throw new RuntimeException("Insufficient wallet balance");
        }
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);
    }

    public Wallet createWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(0.0);
        return walletRepository.save(wallet);
    }
    //for Admin to assign monthly or custom points:
    public void assignPoints(User user, double amount) {
        Wallet wallet = getWallet(user);
        wallet.setBalance(wallet.getBalance() + amount);
        wallet.setLastCreditedDate(LocalDate.now()); // if using lastCreditedDate in entity
        walletRepository.save(wallet);
    }
    //for auto-fare deduction on booking
    public boolean deductFare(User user, double fareAmount) {
        Wallet wallet = getWallet(user);
        if (wallet.getBalance() >= fareAmount) {
            wallet.setBalance(wallet.getBalance() - fareAmount);
            walletRepository.save(wallet);
            return true;
        } else {
            return false;
        }
    }
}