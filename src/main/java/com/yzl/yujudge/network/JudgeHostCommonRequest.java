package com.yzl.yujudge.network;

import com.yzl.yujudge.dto.SetWorkingAmountDTO;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


/**
 * judgeHost一般接口的请求类
 *
 * @author yuzhanglong
 * @date 2020-7-30
 */
public class JudgeHostCommonRequest extends HttpRequest {
    public static final String CONNECT_TEST_URL = "/common/connection_test";
    public static final String SET_MAX_WORKING_AMOUNT = "/common/set_max_working_amount";


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
     * 发送用户提交到judgeHost
     *
     * @return String 测试连接结果的json字符串
     * @throws WebClientResponseException 请求失败时抛出异常
     * @author yuzhanglong
     * @date 2020-7-30 23:17
     */
    public String testJudgeConnection() {
        WebClient webClient = getWebClient();
        Mono<String> responseMono = webClient.get()
                .uri(CONNECT_TEST_URL)
                .retrieve()
                .bodyToMono(String.class);
        return responseMono.block();
    }

    /**
     * 设置判题机最大并行工作数量
     *
     * @param setWorkingAmountDTO 请求数据传输对象
     * @return String 测试连接结果的json字符串
     * @throws WebClientResponseException 请求失败时抛出异常
     * @author yuzhanglong
     * @date 2020-8-29 18:41:53
     */
    public String setJudgeMaxWorkingAmount(SetWorkingAmountDTO setWorkingAmountDTO) {
        WebClient webClient = getWebClient();
        Mono<String> responseMono = webClient.put()
                .uri(SET_MAX_WORKING_AMOUNT)
                .contentType(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .body(Mono.just(setWorkingAmountDTO), SetWorkingAmountDTO.class)
                .retrieve()
                .bodyToMono(String.class);
        return responseMono.block();
    }
}