package com.simol.reviewbot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simol.reviewbot.controller.requst.ReviewRequest;
import com.simol.reviewbot.domain.OpenAIBody;
import com.simol.reviewbot.domain.OpenAIMessage;
import com.simol.reviewbot.domain.OpenAIResponse;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final Environment env;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public ReviewServiceImpl(Environment env, ObjectMapper objectMapper) {
        this.env = env;

        String secretKey = env.getProperty("openai.api_key");
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization", "Bearer " + secretKey)
                .defaultHeader("Content-Type", "application/json")
                .build();

        this.objectMapper = objectMapper;
    }
    @Override
    public OpenAIResponse review(ReviewRequest reviewRequest){
        String prompt = reviewRequest.getPrompt();

        OpenAIMessage system = OpenAIMessage.builder()
                .role("system")
                .content("you are a helpful code review. Please provide feedback.")
                .build();

        OpenAIMessage user = OpenAIMessage.builder()
                .role("user")
                .content(prompt)
                .build();

        List<OpenAIMessage> messages = List.of(system, user);

        OpenAIBody body = OpenAIBody.builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .build();
        String bodyAsString = "";
        try{
            bodyAsString = objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String res = webClient.post()
                .uri("/chat/completions")
                .bodyValue(bodyAsString)
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(throwable -> {
                    throw new RuntimeException("에러 내가 만듦");
                })
                .block();
        OpenAIResponse response = null;

//        OpenAIResponse response = webClient.post()
//                .uri("/chat/completions")
//                .bodyValue(bodyAsString)
//                .retrieve()
//                .bodyToMono(OpenAIResponse.class)
//                .doOnError(throwable -> {
//                    throw new RuntimeException("에러 내가 만듦");
//                })
//                .block();

        return response;
    }
}
