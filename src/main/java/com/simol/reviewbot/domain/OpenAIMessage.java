package com.simol.reviewbot.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OpenAIMessage {
    private String role;
    private String content;
}
