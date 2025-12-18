package com.iugo.demo;

import com.iugo.demo.dto.request.email.GenerateEmailProviderRequestDTO;
import com.iugo.demo.dto.request.memo.GenerateMemoRequestDTO;
import com.iugo.demo.dto.response.ApiResponse;
import com.iugo.demo.dto.response.email.GenerateEmailProviderResponseDataDTO;
import com.iugo.demo.dto.response.memo.GenerateMemoResponseDataDTO;
import com.iugo.demo.service.ContentGeneratorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/content")
public class ContentGeneratorController {


    private final ContentGeneratorService service;

    public ContentGeneratorController(ContentGeneratorService service) {
        this.service = service;
    }


    @PostMapping("/generate/email-provider")
    public ResponseEntity<ApiResponse<GenerateEmailProviderResponseDataDTO>> generateEmailProvider(
            @Valid @RequestBody GenerateEmailProviderRequestDTO request
    ) {
        return ResponseEntity.ok(service.generateEmailProvider(request));
    }

    @PostMapping("/generate/memo")
    public ResponseEntity<ApiResponse<GenerateMemoResponseDataDTO>> generateMemo(
            @Valid @RequestBody GenerateMemoRequestDTO request
    ) {
        return ResponseEntity.ok(service.generateMemo(request));
    }
}
