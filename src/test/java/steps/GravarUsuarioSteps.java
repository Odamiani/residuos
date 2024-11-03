package steps;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErrorMessageModel;
import org.junit.Assert;
import services.GravarUsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GravarUsuarioSteps {
    GravarUsuarioService gravarUsuarioService = new GravarUsuarioService();


    @Dado("que eu tenha os seguintes dados da entrega:")
    public void queEuTenhaOsSeguintesDadosDaEntrega(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows){
            gravarUsuarioService.setFieldsRecord(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de usuarios sem auth")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeUsuariosSemAuth(String endPoint) {
        gravarUsuarioService.createRecord(endPoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, gravarUsuarioService.response.statusCode());
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        JsonObject jsonResponse = JsonParser.parseString(gravarUsuarioService.response.jsonPath().prettify()).getAsJsonObject();

        Assert.assertTrue("Campo de erro 'email' não encontrado", jsonResponse.has("email"));
        Assert.assertEquals("Mensagem de erro no campo email incorreta", message, jsonResponse.get("email").getAsString());
    }

    @Dado("que eu recupere o ID do usuario criado no contexto")
    public void queEuRecupereOIDDoUsuarioCriadoNoContexto() {
        gravarUsuarioService.retrieveMoradorId();
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de entrega")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeEntrega(String endPoint) {
        gravarUsuarioService.deleteUser(endPoint);
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contract) throws IOException {
        gravarUsuarioService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException{
        Set<ValidationMessage> validateResponse = gravarUsuarioService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: "+ validateResponse, validateResponse.isEmpty());
    }
}
