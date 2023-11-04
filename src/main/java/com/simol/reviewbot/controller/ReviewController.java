package com.simol.reviewbot.controller;

import com.simol.reviewbot.controller.requst.ReviewRequest;
import com.simol.reviewbot.domain.OpenAIResponse;
import com.simol.reviewbot.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<Mono<?>> review(@RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.ok(
                Mono.just(reviewService.review(reviewRequest))
        );
    }
}
