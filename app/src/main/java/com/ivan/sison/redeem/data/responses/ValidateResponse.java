package com.ivan.sison.redeem.data.responses;

import com.ivan.sison.redeem.data.objects.Transaction;

public class ValidateResponse {

    private Response response;
    private Transaction transaction;

    public ValidateResponse(Response response, Transaction transaction) {
        this.response = response;
        this.transaction = transaction;
    }

    public Response getResponse() {
        return response;
    }

    public Transaction getTransaction() {
        return transaction;
    }

}
