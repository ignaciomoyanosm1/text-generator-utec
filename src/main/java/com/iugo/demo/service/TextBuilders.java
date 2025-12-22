package com.iugo.demo.service;

import com.iugo.demo.dto.request.email.GenerateEmailProviderRequestDTO;


import com.iugo.demo.dto.request.memo.GenerateMemoRequestDTO;
import com.iugo.demo.service.text.EmailTemplateBuilder;
import com.iugo.demo.service.text.MemoTemplateBuilder;


public class TextBuilders {

    private TextBuilders() {
    }

    public static String buildProviderEmail(GenerateEmailProviderRequestDTO req) {
        return EmailTemplateBuilder.buildProviderEmail(req);
    }

    public static String buildStandardMemo(GenerateMemoRequestDTO req) {
        return MemoTemplateBuilder.buildStandardMemo(req);
    }

}


