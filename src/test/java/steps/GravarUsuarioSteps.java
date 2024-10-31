package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.GravarUsuarioService;

import java.util.List;
import java.util.Map;

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
}
