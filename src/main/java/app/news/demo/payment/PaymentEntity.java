package app.news.demo.payment;

import app.news.demo.subscription.SubscriptionEntity;
import app.news.demo.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "payment")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "subscription")
    private SubscriptionEntity subscription;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "paymentStatus")
    private String paymentStatus;
    @Column(name = "stripePaymentIntentId")
    private String stripePaymentIntentId;
    @Column(name = "stripeSessionId")
    private String stripeSessionId;
    @Column(name = "stripeCustomerId")
    private String stripeCustomerId;
    @Column(name = "description")
    private String description;
    @Column(name = "createAt")
    private LocalDateTime createAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "processedAt")
    private LocalDateTime processedAt;
}
