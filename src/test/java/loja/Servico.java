//Pacote
package loja;

//Bibliotecas
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

//Classe
public class Servico {

    //Estrutura para ler o aquivo
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
    public void ordemDaExecucao() throws IOException {
        incluirUsuario();
        consultarUsuario();
        alterarUsuario();
        excluirUsuario();
    }

    // Create / Incluir / POST
    @Test
    public void incluirUsuario() throws IOException {
        // Ler o conteúdo do arquivo user.json
        String jsonBody = lerJson("data/user.json");

        given()                                                         // Dado que
            .contentType("application/json")                            // Tipo de conteúdo da requisição
                                                                        // "text/xml" para web services comuns
                                                                        // "application/json" para APIs REST
            .log().all()                                                // Gerar um log completo da requisição
            .body(jsonBody)                                             // Conteúdo do corpo da requisição
        .when()                                                         // Quando
            .post("https://petstore.swagger.io/v2/user")           // Operação e endpoint
        .then()                                                         // Então
            .log().all()                                                // Gerar um log completo da resposta
            .statusCode(200)                                            // Validou o código de status da requisição como 200
            .body("code", is(200))                           // Valida o code como 200
            .body("message", is("4211"))                     // Valida a message como 4211
             ;
        System.out.print("Executou o serviço");
    }

    //Reach or Research / Consultar / Get
    @Test
    public void consultarUsuario(){
        String username = "MFERNANDES";

        given()                                                            // Dado que
            .contentType("application/json")                               // Tipo de conteúdo da requisição
            .log().all()                                                   // Mostrar tudo que foi enviado
        .when()                                                            // Quando
            .get("https://petstore.swagger.io/v2/user/" + username)   // Consulta pelo username
        .then()                                                            // Então
            .log().all()                                                   // Mostrar tudo que foi recebido
            .statusCode(200)                                               // Validou que a operação foi realizada
            .body("id", is(4211))                               // Validou o id do usuario
            .body("username", is("MFERNANDES"))                 // Validou o username do usuario
            .body("firstName", is("Mariana"))                   // Validou o firstName do usuario
            .body("lastName", is("Fernandes"))                  // Validou o lastName do usuario
            .body("email", is("mariana.fernandes@teste.com"))   // Validou o email do usuario
            .body("password", is("Teste##123"))                 // Validou o password do usuario
            .body("phone", is("(45)998822882"))                 // Validou o phone do usuario
        ;
    }

    // Update / Alterar / Put
    @Test
    public void alterarUsuario() throws IOException {
        // Ler o conteúdo do arquivo userput.json
        String jsonBody = lerJson("data/userput.json");

        given()                                                 // Dado que
            .contentType("application/json")                    // Tipo de conteúdo da requisição
                                                                // "text/xml" para web services comuns
                                                                // "application/json" para APIs REST
            .log().all()                                        // Gerar um log completo da requisição
            .body(jsonBody)                                     // Conteúdo do corpo da requisição
        .when()                                                 // Quando
            .put("https://petstore.swagger.io/v2/user")    // Altera o usuario
        .then()                                                 // Então
            .log().all()                                        // Mostrar tudo que foi recebido
            .statusCode(200)                                    // Validou que a operação foi realizada
            .body("code", is(200))                   // Valida o code como 200
            .body("message", is("4211"))             // Valida a message como 4211
        ;
    }
    //Delete / Excluir / Delete
    @Test
    public void excluirUsuario(){
        String username = "MFERNANDES1";

        given()                                                             // Dado que
            .contentType("application/json")                                // Tipo de conteúdo da requisição
            .log().all()                                                    // Mostrar tudo que foi enviado
        .when()                                                             // Quando
            .delete("https://petstore.swagger.io/v2/user/" + username) // Delete pelo username
        .then()                                                             // Então
            .log().all()                                                    // Mostrar tudo que foi recebido
            .statusCode(200)                                                // Validou que a operação foi realizada
            .body("code", is(200))
            .body("message", is(username))
        ;
    }

    @Test
    public void realizarVenda() throws IOException {
        // Ler o conteúdo do arquivo orderStore.json
        String jsonBody = lerJson("data/orderStore.json");

        given()                                                         // Dado que
            .contentType("application/json")                            // Tipo de conteúdo da requisição
                                                                        // "text/xml" para web services comuns
                                                                        // "application/json" para APIs REST
            .log().all()                                                // Gerar um log completo da requisição
            .body(jsonBody)                                             // Conteúdo do corpo da requisição
        .when()                                                         // Quando
            .post("https://petstore.swagger.io/v2/store/order")    // Operação e endpoint
        .then()                                                         // Então
            .log().all()                                                // Gerar um log completo da resposta
            .statusCode(200)                                            // Validou o código de status da requisição como 200
            .body("id", is(4211))                            // Valida o id como 4212
            .body("petId", is(1))                            // Valida a petId como
            .body("quantity", is(2))                         // Valida a quantity como 2
            .body("status", is("placed"))                    // Valida o status como placed
        ;
        System.out.print("Executou o serviço de venda");
    }


}
