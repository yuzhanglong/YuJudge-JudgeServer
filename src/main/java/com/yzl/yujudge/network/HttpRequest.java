package com.yzl.yujudge.network;


import org.springframework.web.reactive.function.client.WebClient;


/**
 * @author yuzhanglong
 * @description 网络请求的封装
 * @date 2020-7-29 21:23
 */
public class HttpRequest {
    private String baseUrl;
    private WebClient webClient;


    public HttpRequest(String baseUrl) {
        this.baseUrl = baseUrl;
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public WebClient getWebClient() {
        return webClient;
    }

    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }
}
