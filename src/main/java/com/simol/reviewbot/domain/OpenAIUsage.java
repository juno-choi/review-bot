package com.simol.reviewbot.domain;

public class OpenAIUsage {
    private int prompt_tokens;  //요청 토큰수
    private int completion_tokens;  //응답 토큰수
    private int total_tokens;  //요청 + 응답 토큰수
}
