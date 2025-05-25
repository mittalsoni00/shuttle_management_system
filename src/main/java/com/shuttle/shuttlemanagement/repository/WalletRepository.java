package com.shuttle.shuttlemanagement.repository;

import com.shuttle.shuttlemanagement.entity.Wallet;
import com.shuttle.shuttlemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
}