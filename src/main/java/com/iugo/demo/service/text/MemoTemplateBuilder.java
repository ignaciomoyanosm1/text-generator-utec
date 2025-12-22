package com.iugo.demo.service.text;
import com.iugo.demo.dto.request.ContextDTO;
import com.iugo.demo.dto.request.memo.GenerateMemoRequestDTO;
import com.iugo.demo.dto.request.memo.OfferDTO;
import com.iugo.demo.dto.request.memo.RecommendationDTO;

import java.util.Comparator;

public final class MemoTemplateBuilder {

    private MemoTemplateBuilder() {
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
}
