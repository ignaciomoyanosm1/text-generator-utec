package com.iugo.demo.service;

import com.iugo.demo.dto.request.email.GenerateEmailProviderRequestDTO;
import com.iugo.demo.dto.request.memo.GenerateMemoRequestDTO;
import com.iugo.demo.dto.response.ApiResponse;
import com.iugo.demo.dto.response.GeneratedTextDTO;
import com.iugo.demo.dto.response.email.GenerateEmailProviderResponseDataDTO;
import com.iugo.demo.dto.response.memo.GenerateMemoResponseDataDTO;
import com.iugo.demo.exception.ContentGenerationException;
import com.iugo.demo.exception.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class ContentGeneratorService {

    public ApiResponse<GenerateEmailProviderResponseDataDTO> generateEmailProvider(
            GenerateEmailProviderRequestDTO req
    ) {
        String proc = req != null && req.getContext() != null ? req.getContext().getProcedureNumber() : null;

        log.info("GenerateEmailProvider start procedureNumber={}", proc);

        try {
            String email = TextBuilders.buildProviderEmail(req);

            log.info("GenerateEmailProvider success procedureNumber={} length={}", proc, email.length());

            GenerateEmailProviderResponseDataDTO data =
                    new GenerateEmailProviderResponseDataDTO(
                            new GeneratedTextDTO(email)
                    );

            return ApiResponse.ok("CONTENT_GENERATED", data);

        } catch (IllegalArgumentException e) {

            log.warn("GenerateEmailProvider invalid input procedureNumber={} msg={}", proc, e.getMessage());

            throw new ContentGenerationException(
                    ErrorMessages.GENERATION_FAILED_EMAIL + " Datos inválidos.",
                    e
            );

        } catch (Exception e) {

            log.error("GenerateEmailProvider failed procedureNumber={}", proc, e);

            throw new ContentGenerationException(
                    ErrorMessages.GENERATION_FAILED_EMAIL,
                    e
            );
        }
    }

    public ApiResponse<GenerateMemoResponseDataDTO> generateMemo(
            GenerateMemoRequestDTO req
    ) {
        String proc = req != null && req.getContext() != null ? req.getContext().getProcedureNumber() : null;

        log.info("GenerateMemo start procedureNumber={}", proc);

        try {
            String memo = TextBuilders.buildStandardMemo(req);

            log.info("GenerateMemo success procedureNumber={} length={}", proc, memo.length());

            GenerateMemoResponseDataDTO data =
                    new GenerateMemoResponseDataDTO(
                            new GeneratedTextDTO(memo)
                    );

            return ApiResponse.ok("CONTENT_GENERATED", data);

        } catch (IllegalArgumentException e) {

            log.warn("GenerateMemo invalid input procedureNumber={} msg={}", proc, e.getMessage());

            throw new ContentGenerationException(
                    ErrorMessages.GENERATION_FAILED_MEMO + " Datos inválidos.",
                    e
            );

        } catch (Exception e) {

            log.error("GenerateMemo failed procedureNumber={}", proc, e);

            throw new ContentGenerationException(
                    ErrorMessages.GENERATION_FAILED_MEMO,
                    e
            );
        }
    }
}
