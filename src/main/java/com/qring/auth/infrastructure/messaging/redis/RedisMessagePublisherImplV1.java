package com.qring.auth.infrastructure.messaging.redis;

import com.qring.auth.application.v1.message.RedisMessagePublisherV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "RedisMessagePublisherV1 Log")
public class RedisMessagePublisherImplV1 implements RedisMessagePublisherV1 {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public void publishUserModificationEvent(Long userId) {

        String channel = "user-modification-channel";
        String message = String.valueOf(userId);

        reactiveRedisTemplate.convertAndSend(channel, message)
                .doOnSuccess(result -> log.info("Redis 채널로 메시지 발행 성공: 채널={}, 메시지={}", channel, message))
                .doOnError(error -> log.error("Redis 채널 메시지 발행 실패: {}", error.getMessage(), error))
                .subscribe();
    }
}
