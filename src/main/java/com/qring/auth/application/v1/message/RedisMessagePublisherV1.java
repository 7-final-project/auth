package com.qring.auth.application.v1.message;

public interface RedisMessagePublisherV1 {

    void publishUserModificationEvent(Long userId);

}
