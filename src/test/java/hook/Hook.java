package hook;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
public class Hook {
    @BeforeAll
    public static void setUpBeforeClass() {
        System.out.println("Configuração global antes de todos os testes.");
        inicializarAmbiente();
    }
    @AfterAll
    public static void tearDownAfterClass() {
        System.out.println("Limpeza global após todos os testes.");
        limparAmbiente();
    }
    @Before
    public void setUp() {
        System.out.println("Iniciando um novo cenário de teste...");
        prepararDadosParaTeste();
    }
    @After
    public void tearDown() {
        System.out.println("Finalizando o cenário de teste...");
        limparDadosDepoisDoTeste();
    }
    private static void inicializarAmbiente() {
        System.out.println("Ambiente inicializado.");
    }
    private static void limparAmbiente() {
        System.out.println("Ambiente limpo.");
    }
    private void prepararDadosParaTeste() {
        System.out.println("Dados preparados para o cenário de teste.");
    }
    private void limparDadosDepoisDoTeste() {
        System.out.println("Dados limpos após o cenário de teste.");
    }
}
