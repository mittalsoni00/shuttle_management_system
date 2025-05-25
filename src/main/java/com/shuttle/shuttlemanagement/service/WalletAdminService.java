package com.shuttle.shuttlemanagement.service;

import com.shuttle.shuttlemanagement.entity.User;
import com.shuttle.shuttlemanagement.entity.Wallet;
import com.shuttle.shuttlemanagement.repository.UserRepository;
import com.shuttle.shuttlemanagement.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletAdminService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    private Wallet getOrCreateWallet(User user) {
        return walletRepository.findByUser(user).orElseGet(() -> {
            Wallet wallet = new Wallet();
            wallet.setUser(user);
            wallet.setBalance(0.0);
            return walletRepository.save(wallet);
        });
    }

    public void allocatePoints(Long userId, double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Wallet wallet = getOrCreateWallet(user);
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
    }

    public void deductPoints(Long userId, double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Wallet wallet = getOrCreateWallet(user);
        if (wallet.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);
    }

    public void recharge(Long userId, double amount) {
        // recharge is essentially same as allocate
        allocatePoints(userId, amount);
    }

}
