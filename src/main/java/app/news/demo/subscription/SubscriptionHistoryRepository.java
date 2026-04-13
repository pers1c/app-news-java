package app.news.demo.subscription;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionHistoryRepository extends JpaRepository<SubscriptionHistoryEntity, Integer> {
    SubscriptionHistoryEntity findBySubscriptionOrderByCreatedAtDesc(SubscriptionEntity subscription);

}
