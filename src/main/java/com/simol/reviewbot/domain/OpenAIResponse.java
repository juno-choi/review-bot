package com.simol.reviewbot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenAIResponse {
    private String id;
    private String object;
    private int created;
    private String model;
    private List<OpenAIChoices> choices = new ArrayList<>();
    private OpenAIUsage usage;
}
