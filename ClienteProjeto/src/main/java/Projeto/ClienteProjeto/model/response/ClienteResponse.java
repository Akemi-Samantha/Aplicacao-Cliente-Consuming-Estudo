package Projeto.ClienteProjeto.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private Long id;
    @JsonProperty("Prato")
    private String prato;
    @JsonProperty("Pais")
    private String pais;


}
