package com.codexmind.establishment.dto.asaas;

public record ResponseSaveCustomerAsaasDTO(

        String object,
        String id,
        String dateCreated,
        String name,
        String email,
        String company,
        String phone,
        String mobilePhone,
        String address,
        String addressNumber,
        String complement,
        String province,
        String postalCode,
        String cpfCnpj,
        String personType,
        boolean deleted,
        String additionalEmails,
        String externalReference,
        boolean notificationDisabled,
        String observations,
        String municipalInscription,
        String stateInscription,
        boolean canDelete,
        String cannotBeDeletedReason,
        boolean canEdit,
        String cannotEditReason,
        String city,
        String cityName,
        String state,
        String country
){
}
