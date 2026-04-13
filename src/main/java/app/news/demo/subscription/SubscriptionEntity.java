package app.news.demo.subscription;

import app.news.demo.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Table(name = "subscriptions")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "plan")
    private SubscriptionPlanEntity plan;
    @Column(name = "subscriptionStatus")
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus subscriptionStatus;
    @Column(name = "startDate")
    private LocalDateTime startDate;
    @Column(name = "endDate")
    private LocalDateTime endDate;
    @Column(name = "stripeSubscriptionId")
    private String stripeSubscriptionId;
    @Column(name = "autoRenew")
    private Boolean autoRenew;
    @Column(name = "createAt")
    private LocalDateTime createAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

}
