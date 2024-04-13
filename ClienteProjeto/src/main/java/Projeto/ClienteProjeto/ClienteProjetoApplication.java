package Projeto.ClienteProjeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClienteProjetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteProjetoApplication.class, args);
	}

}
