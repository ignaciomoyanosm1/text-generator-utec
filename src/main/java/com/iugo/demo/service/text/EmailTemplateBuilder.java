package com.iugo.demo.service.text;

import com.iugo.demo.dto.request.ContextDTO;
import com.iugo.demo.dto.request.email.EmailProviderDTO;
import com.iugo.demo.dto.request.email.GenerateEmailProviderRequestDTO;

public class EmailTemplateBuilder {
    private EmailTemplateBuilder() {}

    public static String buildProviderEmail(GenerateEmailProviderRequestDTO req) {
        ContextDTO c = req.getContext();
        EmailProviderDTO e = req.getEmailProvider();

        String table = TableFormatter.monospaceTechnicalSpecsTable(e);

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
}
