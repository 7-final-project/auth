package com.qring.auth.application.message;

public interface RedisMessagePublisherV1 {

    void publishUserModificationEvent(Long userId);

}
