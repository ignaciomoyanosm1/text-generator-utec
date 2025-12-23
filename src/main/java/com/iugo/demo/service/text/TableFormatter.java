package com.iugo.demo.service.text;

import com.iugo.demo.dto.request.email.EmailProviderDTO;
import com.iugo.demo.dto.request.email.TechnicalSpecificationDTO;

public final class TableFormatter {
    private TableFormatter() {}

    public static String monospaceTechnicalSpecsTable(EmailProviderDTO e) {
        int leftWidth = 69;
        int rightWidth = 11;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-" + leftWidth + "s | %-" + rightWidth + "s%n",
                "Especificación Técnica", "Puntaje"));
        sb.append("-".repeat(leftWidth)).append("-+-").append("-".repeat(rightWidth)).append("\n");

        if (e.getTechnicalSpecifications() != null) {
            for (TechnicalSpecificationDTO item : e.getTechnicalSpecifications()) {
                sb.append(String.format("%-" + leftWidth + "s | %-" + rightWidth + "s%n",
                        TextUtils.truncate(TextUtils.clean(item.getDescription()), leftWidth),
                        TextUtils.truncate(TextUtils.clean(item.getRating()), rightWidth)));
            }
        }

        return sb.toString().trim();
    }
}
