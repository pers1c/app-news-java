package app.news.demo.subscription;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlanEntity, Integer> {
    boolean findByIsActiveTrue(String slug);
}
