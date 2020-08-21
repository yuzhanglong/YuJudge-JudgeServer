package com.yzl.yujudge.network;

import com.yzl.yujudge.dto.JudgeHostJudgeRequestDTO;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author yuzhanglong
 * @description JudgeHost判题相关网络请求的请求类
 */
public class JudgeHostJudgeRequest extends HttpRequest {
    final String testAccessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOiJ5emwiLCJpYXQiOjE1OTM2OTE1Nzl9." +
            "6iXiQWK0l8ysGpjTutltZw1JJdOAjjHSaensgHY9kxE";

    /**
     * 判题机判题请求的构造方法，传入完整地址
     *
     * @param judgeHostBaseUrl 判题服务器的baseURL
     * @author yuzhanglong
     * @date 2020-7-30 00:24
     */
    public JudgeHostJudgeRequest(String judgeHostBaseUrl) {
        super(judgeHostBaseUrl);
    }

    /**
     * 判题机判题请求的构造方法，传入ip + 端口
     *
     * @param address 地址
     * @param port    端口号
     * @author yuzhanglong
     * @date 2020-8-21 23:04:10
     */
    public JudgeHostJudgeRequest(String address, Integer port) {
        super(address + ":" + port.toString());
    }

    /**
     * @param judgeHostDTO 判题相关数据传输对象
     * @return String 判题结果的json字符串
     * @throws WebClientResponseException 请求失败时抛出异常
     * @author yuzhanglong
     * @date 2020-7-30 00:17
     * @description 发送用户提交到judgeHost
     */
    public String judgeSubmission(JudgeHostJudgeRequestDTO judgeHostDTO) throws WebClientResponseException {
        WebClient webClient = getWebClient();
        Mono<String> responseMono = webClient.post()
                .uri("/judge/run")
                .header("accessToken", testAccessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .body(Mono.just(judgeHostDTO), JudgeHostJudgeRequestDTO.class)
                .retrieve()
                .bodyToMono(String.class);
        return responseMono.block();
    }
}
