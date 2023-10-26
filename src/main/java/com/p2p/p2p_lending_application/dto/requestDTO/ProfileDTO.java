package com.p2p.p2p_lending_application.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.p2p.p2p_lending_application.models.User;
import com.p2p.p2p_lending_application.models.NextOfKin;
import com.p2p.p2p_lending_application.models.Telephone;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class ProfileDTO {

    private User user;
    @NotNull
    private List<Telephone> telephoneNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDateTime dateOfBirth;
    @NotNull
    @Length(min = 11, message = "digital format incorrect")
    private String digitalAddress;
    @NotNull
    private NextOfKin nextOfKin;
}
