package app.news.demo.subscription.dto;

import app.news.demo.subscription.SubscriptionPlanEntity;
import app.news.demo.subscription.SubscriptionStatus;

public record SubscriptionStatusResponse(
        boolean hasSubscription,
        SubscriptionStatus status,
        int daysRemaining,
        SubscriptionPlanEntity plan
) {
}
