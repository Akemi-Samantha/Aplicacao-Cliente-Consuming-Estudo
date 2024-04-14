package Projeto.ClienteProjeto.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseExceptions {

    private final String titulo;
    private final int status;
    private String detalhes;
    private String msgdev;
}
