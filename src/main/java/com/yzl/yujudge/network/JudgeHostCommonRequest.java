package com.yzl.yujudge.network;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;


/**
 * @author yuzhanglong
 * @description judgeHost一般接口的请求类
 */
public class JudgeHostCommonRequest extends HttpRequest{
    public JudgeHostCommonRequest(String baseUrl) {
        super(baseUrl);
    }

    /**
     * @return String 测试连接结果的json字符串
     * @throws WebClientResponseException 请求失败时抛出异常
     * @author yuzhanglong
     * @date 2020-7-30 23:17
     * @description 发送用户提交到judgeHost
     */
    public String testJudgeConnection() {
        WebClient webClient = getWebClient();
        Mono<String> responseMono = webClient.get()
                .uri("/common/test_connection")
                .retrieve()
                .bodyToMono(String.class);
        return responseMono.block();
    }
}
