package app.news.demo.subscription;

import app.news.demo.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubscriptionRepository extends JpaRepository<SubscriptionPlanEntity, Integer> {
//    SubscriptionPlanEntity findByUser(UserEntity user);
//    SubscriptionPlanEntity findByUserAndStatus(UserEntity user, String status);
}
