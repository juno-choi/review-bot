package com.simol.reviewbot.service;

import com.simol.reviewbot.controller.requst.ReviewRequest;
import com.simol.reviewbot.domain.OpenAIResponse;

public interface ReviewService {
    OpenAIResponse review(ReviewRequest reviewRequest);
}
