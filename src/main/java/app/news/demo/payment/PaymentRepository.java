package app.news.demo.payment;

import app.news.demo.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
    PaymentEntity findByUser(UserEntity user);
    PaymentEntity findByStripeSessionId(String stripeSessionId);
}
