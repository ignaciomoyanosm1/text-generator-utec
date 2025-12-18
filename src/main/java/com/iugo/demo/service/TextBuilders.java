package com.iugo.demo.service;

import com.iugo.demo.dto.request.*;
import com.iugo.demo.dto.request.email.EmailProviderDTO;
import com.iugo.demo.dto.request.email.GenerateEmailProviderRequestDTO;
import com.iugo.demo.dto.request.email.TechnicalSpecificationDTO;
import com.iugo.demo.dto.request.memo.GenerateMemoRequestDTO;
import com.iugo.demo.dto.request.memo.OfferDTO;
import com.iugo.demo.dto.request.memo.RecommendationDTO;

import java.util.Comparator;

public class TextBuilders {

    private TextBuilders() {}

    public static String buildProviderEmail(GenerateEmailProviderRequestDTO req) {
        ContextDTO c = req.getContext();
        EmailProviderDTO e = req.getEmailProvider();

        String table = buildMonospaceTable(e);

        String notesBlock = (e.getNotes() != null && !e.getNotes().isBlank())
                ? "\n" + e.getNotes().trim() + "\n"
                : "";

        return ("""
                Buen día %s, ¿cómo estás?

                En esta oportunidad le contacto con el fin de solicitar cotización para el siguiente equipamiento:
                %s.

                A continuación se detallan las especificaciones técnicas solicitadas:

                %s

                Agradecemos se sirvan especificar en su propuesta:
                - Observaciones / descripción técnica del producto
                - Precios en UYU o USD, impuestos y modalidad (plaza o CIP/CIF)
                - Costos de envío si no están incluidos en el precio
                - Plazo de entrega en %s
                - Plazo de mantenimiento de la oferta
                - Razón social y RUT (indicar si la empresa está inscripta en RUPE)

                "Los pagos se realizan a %d días a partir de haber recibido y aceptado el producto.
                UTEC es agente de retención de impuestos en los casos que corresponda, de acuerdo a la normativa vigente."

                La cotización solicitada se requiere a más tardar el día %s.%s

                Saludos cordiales,
                %s
                %s
                """).formatted(
                e.getRecipientName(),
                c.getPurchaseItem(),
                table,
                e.getDeliveryLocation(),
                e.getPaymentDueDays(),
                e.getQuotationDeadline(),
                notesBlock,
                c.getRequester().getName(),
                c.getRequestingUnit()
        ).trim();
    }

    public static String buildStandardMemo(GenerateMemoRequestDTO req) {
        ContextDTO c = req.getContext();
        RecommendationDTO r = req.getRecommendation();

        String offersBlock = req.getOffers().stream()
                .sorted(Comparator.comparing(OfferDTO::getUnitPrice))
                .map(o -> "- %s | Modalidad: %s | Precio unitario: %s %s"
                        .formatted(o.getSupplier(), o.getModality(), o.getUnitPrice(), o.getCurrency()))
                .reduce("", (acc, line) -> acc + line + "\n")
                .trim();

        String aplica72 = Boolean.TRUE.equals(c.getAppliesArticle72()) ? "Sí" : "No";
        String comments = (c.getAdditionalComments() != null && !c.getAdditionalComments().isBlank())
                ? c.getAdditionalComments().trim()
                : "N/A";

        return ("""
                INFORME DE ANÁLISIS TÉCNICO DE OFERTAS

                Fecha: %s
                Número de procedimiento: %s
                Tipo de procedimiento: %s
                Objeto de compra: %s

                Unidad solicitante: %s
                Solicitante: %s (%s)
                Supervisor responsable: %s (%s)

                Se recibieron las siguientes ofertas:
                %s

                RECOMENDACIÓN:
                Se recomienda adjudicar la compra a %s, correspondiente al equipo %s,
                por un monto de %s %s.

                Fundamento legal aplicado: %s
                Aplicación del Artículo 72 del TOCAF: %s

                Comentarios adicionales:
                %s
                """).formatted(
                c.getDate(),
                c.getProcedureNumber(),
                c.getProcedureType(),
                c.getPurchaseItem(),
                c.getRequestingUnit(),
                c.getRequester().getName(), c.getRequester().getPosition(),
                c.getSupervisor().getName(), c.getSupervisor().getPosition(),
                offersBlock,
                r.getSupplier(),
                r.getEquipment(),
                r.getPrice(),
                r.getCurrency(),
                c.getLegalBasis(),
                aplica72,
                comments
        ).trim();
    }

    private static String buildMonospaceTable(EmailProviderDTO e) {
        int leftWidth = 69;
        int rightWidth = 11;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-" + leftWidth + "s | %-" + rightWidth + "s%n",
                "Especificación Técnica", "Puntaje"));
        sb.append("-".repeat(leftWidth)).append("-+-").append("-".repeat(rightWidth)).append("\n");

        for (TechnicalSpecificationDTO item : e.getTechnicalSpecifications()) {
            sb.append(String.format("%-" + leftWidth + "s | %-" + rightWidth + "s%n",
                    truncate(clean(item.getDescription()), leftWidth),
                    truncate(clean(item.getRating()), rightWidth)));
        }
        return sb.toString().trim();
    }

    private static String clean(String s) { return s == null ? "" : s.replace("\r", "").trim(); }

    private static String truncate(String s, int max) {
        if (s == null) return "";
        return s.length() <= max ? s : s.substring(0, Math.max(0, max - 1)) + "…";
    }
}
