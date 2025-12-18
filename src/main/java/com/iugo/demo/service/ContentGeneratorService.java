package com.iugo.demo.service;

import com.iugo.demo.dto.request.email.GenerateEmailProviderRequestDTO;
import com.iugo.demo.dto.request.memo.GenerateMemoRequestDTO;
import com.iugo.demo.dto.response.ApiResponse;
import com.iugo.demo.dto.response.GeneratedTextDTO;
import com.iugo.demo.dto.response.email.GenerateEmailProviderResponseDataDTO;
import com.iugo.demo.dto.response.memo.GenerateMemoResponseDataDTO;
import org.springframework.stereotype.Service;

@Service
public class ContentGeneratorService {


    public ApiResponse<GenerateEmailProviderResponseDataDTO> generateEmailProvider(GenerateEmailProviderRequestDTO req) {

        String email = TextBuilders.buildProviderEmail(req);

        GenerateEmailProviderResponseDataDTO data = new GenerateEmailProviderResponseDataDTO(
                new GeneratedTextDTO(email)
        );

        return new ApiResponse<>("SUCCESS", "CONTENT_GENERATED", null, data);
    }

    public ApiResponse<GenerateMemoResponseDataDTO> generateMemo(GenerateMemoRequestDTO req) {

        String memo = TextBuilders.buildStandardMemo(req);

        GenerateMemoResponseDataDTO data = new GenerateMemoResponseDataDTO(
                new GeneratedTextDTO(memo)
        );

        return new ApiResponse<>("SUCCESS", "CONTENT_GENERATED", null, data);
    }
}
