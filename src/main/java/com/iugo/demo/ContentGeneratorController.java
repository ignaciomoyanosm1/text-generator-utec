package com.iugo.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iugo.demo.dto.request.email.GenerateEmailProviderRequestDTO;
import com.iugo.demo.dto.request.memo.GenerateMemoRequestDTO;
import com.iugo.demo.dto.response.ApiResponse;
import com.iugo.demo.dto.response.email.GenerateEmailProviderResponseDataDTO;
import com.iugo.demo.dto.response.memo.GenerateMemoResponseDataDTO;
import com.iugo.demo.service.ContentGeneratorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ContentGeneratorController {

   private final ContentGeneratorService service;

   public ContentGeneratorController(ContentGeneratorService service) {
      this.service = service;
   }

   @PreAuthorize("hasRole('ADMIN')")
   @PostMapping("/generate/email-provider")
   public ResponseEntity<ApiResponse<GenerateEmailProviderResponseDataDTO>> generateEmailProvider(
         @Valid @RequestBody GenerateEmailProviderRequestDTO request) {
      return ResponseEntity.ok(service.generateEmailProvider(request));
   }

   @PreAuthorize("hasRole('ADMIN')")
   @PostMapping("/generate/memo")
   public ResponseEntity<ApiResponse<GenerateMemoResponseDataDTO>> generateMemo(@Valid @RequestBody GenerateMemoRequestDTO request) {
      return ResponseEntity.ok(service.generateMemo(request));
   }

}
