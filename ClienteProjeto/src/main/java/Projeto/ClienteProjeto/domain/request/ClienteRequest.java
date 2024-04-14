package Projeto.ClienteProjeto.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

public class ClienteRequest {

    @JsonProperty("Prato")
    private String prato;
    @JsonProperty("Pais")
    private String pais;
}

