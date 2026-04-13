package app.news.demo.subscription;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "subscriptionHistory")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubscriptionHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private SubscriptionEntity subscription;
    @Enumerated(EnumType.STRING)
    @Column(name = "action")
    private SubscriptionAction action;
    @Column(name = "description")
    private String description;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
}
