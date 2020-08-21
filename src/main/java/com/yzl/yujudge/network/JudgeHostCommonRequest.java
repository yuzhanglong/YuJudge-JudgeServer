package com.yzl.yujudge.network;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;


/**
 * judgeHost一般接口的请求类
 *
 * @author yuzhanglong
 * @date 2020-7-30
 */
public class JudgeHostCommonRequest extends HttpRequest {


    /**
     * 构造函数，传入完整地址
     *
     * @author yuzhanglong
     * @date 2020-7-30
     */
    public JudgeHostCommonRequest(String baseUrl) {
        super(baseUrl);
    }

    /**
     * 构造函数，传入ip + 端口号
     *
     * @param address 地址
     * @param port    端口
     * @author yuzhanglong
     * @date 2020-8-21 23:54:54
     */
    public JudgeHostCommonRequest(String address, Integer port) {
        super(address + ":" + port.toString());
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
