package com.qring.auth.infrastructure.messaging.redis;

import com.qring.auth.application.v1.message.RedisMessagePublisherV1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisMessagePublisherImplV1 implements RedisMessagePublisherV1 {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public void publishUserModificationEvent(Long userId) {

        String channel = "user-modification-channel";
        String message = String.valueOf(userId);

        reactiveRedisTemplate.convertAndSend(channel, message)
                .doOnSuccess(result -> System.out.println("Redis 채널로 메시지 발행 성공: " + message))
                .doOnError(error -> System.err.println("Redis 채널 메시지 발행 실패: " + error.getMessage()))
                .subscribe();
    }
}
