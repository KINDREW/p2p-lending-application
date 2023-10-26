package com.p2p.p2p_lending_application.dto.requestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LenderContractSignRequest {
    @NotNull
    private String lenderSignature;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private String paymentType;
}
