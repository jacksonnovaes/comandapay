package com.codexmind.establishment.converters;

import com.codexmind.establishment.domain.PixTransaction;
import com.codexmind.establishment.dto.PixTransactionDTO;
import com.codexmind.establishment.dto.asaas.*;

import java.util.List;


public class TransactionConverter {

    public static PixTransactionDTO toDTO(PixTransaction pixTransaction) {
        var calendario = new PixTransactionDTO.Calendario(
                "",
                pixTransaction.getExpiracao()
        );
        var valor = new PixTransactionDTO.Valor(
                pixTransaction.getValor()
        );
        var devedor = new PixTransactionDTO.Devedor(
                pixTransaction.getCpf(),
                pixTransaction.getDevedor()
        );

        var loc = new PixTransactionDTO.Loc(
                0,
                pixTransaction.getLocation(),
                pixTransaction.getTipoCob(),
                pixTransaction.getCriacao()

        );
        return new PixTransactionDTO(
                calendario,
                pixTransaction.getTxid(),
                pixTransaction.getRevisao(),
                pixTransaction.getStatus(),
                valor,
                pixTransaction.getChave(),
                devedor,
                pixTransaction.getSolicitacaoPagador(),
                loc,
                pixTransaction.getPixCopiaECola()
        );
    }

    public static PaymentResponseDTO toDTO(PaymentResponseDTO payment) {
        return new PaymentResponseDTO(
                payment.object(),
                payment.id(),
                payment.dateCreated(),
                payment.customer(),
                payment.subscription(),
                payment.installment(),
                payment.paymentLink(),
                payment.value(),
                payment.netValue(),
                payment.originalValue(),
                payment.interestValue(),
                payment.description(),
                payment.billingType(),
                payment.creditCard() != null ? new CreditCardDTO(
                        payment.creditCard().creditCardNumber(),
                        payment.creditCard().creditCardBrand(),
                        payment.creditCard().creditCardToken()
                ) : null,
                payment.canBePaidAfterDueDate(),
                payment.pixTransaction(),
                payment.pixQrCodeId(),
                payment.status(),
                payment.dueDate(),
                payment.originalDueDate(),
                payment.paymentDate(),
                payment.clientPaymentDate(),
                payment.installmentNumber(),
                payment.invoiceUrl(),
                payment.invoiceNumber(),
                payment.externalReference(),
                payment.deleted(),
                payment.anticipated(),
                payment.anticipable(),
                payment.creditDate(),
                payment.estimatedCreditDate(),
                payment.transactionReceiptUrl(),
                payment.nossoNumero(),
                payment.bankSlipUrl(),
                payment.discount() != null ? new DiscountDTO(
                        payment.discount().value(),
                        payment.discount().dueDateLimitDays(),
                        payment.discount().type()
                ) : null,
                payment.fine() != null ? new FineDTO(payment.fine().value()) : null,
                payment.interest() != null ? new InterestDTO(payment.interest().value()) : null,
                payment.split() != null ? payment.split().stream().map(split -> new SplitDTO(
                        split.id(),
                        split.walletId(),
                        split.fixedValue(),
                        split.percentualValue(),
                        split.totalValue(),
                        split.cancellationReason(),
                        split.status(),
                        split.externalReference(),
                        split.description()
                )).toList() : List.of(),
                payment.postalService(),
                payment.daysAfterDueDateToRegistrationCancellation(),
                payment.chargeback() != null ? new ChargebackDTO(
                        payment.chargeback().id(),
                        payment.chargeback().payment(),
                        payment.chargeback().installment(),
                        payment.chargeback().customerAccount(),
                        payment.chargeback().status(),
                        payment.chargeback().reason(),
                        payment.chargeback().disputeStartDate(),
                        payment.chargeback().value(),
                        payment.chargeback().paymentDate(),
                        payment.chargeback().creditCard() != null ? new CreditCardDTO(
                                payment.chargeback().creditCard().creditCardNumber(),
                                payment.chargeback().creditCard().creditCardBrand(),
                                null
                        ) : null,
                        payment.chargeback().disputeStatus(),
                        payment.chargeback().deadlineToSendDisputeDocuments()
                ) : null,
                payment.refunds() != null ? payment.refunds().stream().map(refund -> new RefundDTO(
                        refund.dateCreated(),
                        refund.status(),
                        refund.value(),
                        refund.endToEndIdentifier(),
                        refund.description(),
                        refund.effectiveDate(),
                        refund.transactionReceiptUrl(),
                        refund.refundedSplits() != null ? refund.refundedSplits().stream().map(refundedSplit -> new RefundedSplitDTO(
                                refundedSplit.id(),
                                refundedSplit.value(),
                                refundedSplit.done()
                        )).toList() : List.of()
                )).toList() : List.of()
        );
    }
}
