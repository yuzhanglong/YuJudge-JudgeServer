package com.yzl.yujudge.network;

import com.yzl.yujudge.dto.JudgeHostRequestDTO;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author yuzhanglong
 * @description 判题相关网络请求
 */
public class JudgeRequest extends HttpRequest {
    final String testAccessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOiJ5emwiLCJpYXQiOjE1OTM2OTE1Nzl9." +
            "6iXiQWK0l8ysGpjTutltZw1JJdOAjjHSaensgHY9kxE";

    /**
     * @param judgeHostBaseUrl 判题服务器的baseURL
     * @author yuzhanglong
     * @date 2020-7-30 00:24
     * @description 我们可能有多个判题服务器集群，所以我们提供传入baseURL的构造方法
     */
    public JudgeRequest(String judgeHostBaseUrl) {
        super(judgeHostBaseUrl);
    }


    /**
     * @author yuzhanglong
     * @date 2020-7-30 00:24
     * @description 一般不会使用此构造方法，仅用于单个服务器的测试
     */
    public JudgeRequest() {
        super("http://47.106.202.255:8080");
    }


    /**
     * @param judgeHostDTO 判题相关数据传输对象
     * @return String 判题结果的json字符串
     * @throws WebClientResponseException 请求失败时抛出异常
     * @author yuzhanglong
     * @date 2020-7-30 00:17
     * @description 发送用户提交到judgeHost
     */
    public String judgeSubmission(JudgeHostRequestDTO judgeHostDTO) throws WebClientResponseException {
        WebClient webClient = getWebClient();
        Mono<String> responseMono = webClient.post()
                .uri("/judge/run")
                .header("accessToken", testAccessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .body(Mono.just(judgeHostDTO), JudgeHostRequestDTO.class)
                .retrieve()
                .bodyToMono(String.class);
        return responseMono.block();
    }
}
