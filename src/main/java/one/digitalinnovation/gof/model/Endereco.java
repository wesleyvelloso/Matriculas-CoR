package one.digitalinnovation.gof.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Os atributos desse modelo foram gerados automaticamente pelo site
 * jsonschema2pojo.org. Para isso, usamos o JSON de retorno da API do ViaCEP.
 * 
 * @see <a href="https://www.jsonschema2pojo.org">jsonschema2pojo.org</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 * 
 * @author falvojr
 */
@Data
@Entity
public class Endereco {

	@Id
	private String cep;

	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String ibge;
	private String gia;
	private String ddd;
	private String siafi;

}
