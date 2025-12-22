package com.iugo.demo.service;

import com.iugo.demo.dto.request.email.GenerateEmailProviderRequestDTO;
import com.iugo.demo.dto.request.memo.GenerateMemoRequestDTO;
import com.iugo.demo.dto.response.ApiResponse;
import com.iugo.demo.dto.response.GeneratedTextDTO;
import com.iugo.demo.dto.response.email.GenerateEmailProviderResponseDataDTO;
import com.iugo.demo.dto.response.memo.GenerateMemoResponseDataDTO;
import com.iugo.demo.exception.ContentGenerationException;
import com.iugo.demo.exception.ErrorMessages;
import org.springframework.stereotype.Service;

@Service
public class ContentGeneratorService {

    public ApiResponse<GenerateEmailProviderResponseDataDTO> generateEmailProvider(
            GenerateEmailProviderRequestDTO req
    ) {
        try {
            String email = TextBuilders.buildProviderEmail(req);

            GenerateEmailProviderResponseDataDTO data =
                    new GenerateEmailProviderResponseDataDTO(
                            new GeneratedTextDTO(email)
                    );

            return ApiResponse.ok("CONTENT_GENERATED", data);

        } catch (IllegalArgumentException e) {
            throw new ContentGenerationException(
                    ErrorMessages.GENERATION_FAILED_EMAIL + " Datos inválidos.",
                    e
            );

        } catch (Exception e) {
            throw new ContentGenerationException(
                    ErrorMessages.GENERATION_FAILED_EMAIL,
                    e
            );
        }
    }

    public ApiResponse<GenerateMemoResponseDataDTO> generateMemo(
            GenerateMemoRequestDTO req
    ) {
        try {
            String memo = TextBuilders.buildStandardMemo(req);

            GenerateMemoResponseDataDTO data =
                    new GenerateMemoResponseDataDTO(
                            new GeneratedTextDTO(memo)
                    );

            return ApiResponse.ok("CONTENT_GENERATED", data);

        } catch (IllegalArgumentException e) {
            throw new ContentGenerationException(
                    ErrorMessages.GENERATION_FAILED_MEMO + " Datos inválidos.",
                    e
            );

        } catch (Exception e) {
            throw new ContentGenerationException(
                    ErrorMessages.GENERATION_FAILED_MEMO,
                    e
            );
        }
    }
}
