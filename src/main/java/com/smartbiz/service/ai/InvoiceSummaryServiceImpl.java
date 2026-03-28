package com.smartbiz.service.ai;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceSummaryServiceImpl
        implements InvoiceSummaryService {

    private final OpenAiService claudeAI;

    private static final String SYSTEM = """
        You are a financial document assistant.
        Explain invoices in simple plain language.
        Highlight total, due date, key items.
        """;

    @Override
    public String summarizeInvoice(
            String invoiceData) {

        String msg = """
            Analyze this invoice in simple terms:
            %s
            
            Provide:
            1. Simple summary
            2. Items breakdown
            3. Payment details
            4. Important notes
            """.formatted(invoiceData);

        return claudeAI.chat(SYSTEM, msg);
    }
}