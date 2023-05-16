package apresentacao;

import java.util.List;

import com.mongodb.client.MongoCursor;

import negocio.Contato;
import negocio.Endereco;
import persistencia.ContatoDAO;

/**
 *
 * @author Renan Acosta
 */

public class Main {
    public static void main(String[] args) {
        ContatoDAO contatoDAO = new ContatoDAO();

        // contato 1
        Contato renan = new Contato();
        renan.setNome("Renan");
        renan.setEndereco(new Endereco("Rio Grande", "Mendes Neto", "Cohab II", "448", "casa"));
        renan.setTelefone(List.of("53997117933"));

        // contato 2
        Contato igor = new Contato();
        igor.setNome("Igor");
        igor.setEndereco(new Endereco("Rio Grande", "Presidente Vargas", "Cidade Nova", "1000", "Apto 101"));
        igor.setTelefone(List.of("53999999999"));

        // contato 3
        Contato betito = new Contato();
        betito.setNome("Betito");
        betito.setEndereco(new Endereco("Rio Grande", "Avenida Atlantica", "Cassino", "10", "casa"));
        betito.setTelefone(List.of("53988888888"));

        // contato 4
        Contato marcio = new Contato();
        marcio.setNome("Marcio");
        marcio.setEndereco(new Endereco("Pelotas", "Dom Joaquim", "Fragata", "338", "casa"));
        marcio.setTelefone(List.of("53977777777", "53987777788"));

        // contato 5
        Contato cibele = new Contato();
        cibele.setNome("Cibele");
        cibele.setEndereco(new Endereco("Pelotas", "Fernando Ferrari", "Areal", "249", "casa"));
        cibele.setTelefone(List.of("53997117944"));

        // #############################################################################

        //// inserir um contato

        contatoDAO.inserir(renan);
        contatoDAO.inserir(igor);
        contatoDAO.inserir(betito);
        contatoDAO.inserir(marcio);
        contatoDAO.inserir(cibele);

        // #############################################################################

        //// remover um contato

        // contatoDAO.deletarPorNome("Renan");
        // contatoDAO.deletarPorNome("Igor");
        // contatoDAO.deletarPorNome("Betito");
        // contatoDAO.deletarPorNome("Marcio");
        // contatoDAO.deletarPorNome("Cibele");

        // #############################################################################

        //// atualizar um contato

        // Contato contato = contatoDAO.buscarPorNome("Renan");
        // contato.setNome("Renan Acosta");
        // contatoDAO.update(contato);

        // Contato contato = contatoDAO.buscarPorNome("Renan Acosta");
        // Endereco endereco = new Endereco("Pelotas", "Rua dos Andradas", "Centro",
        // "123", "sala 101");
        // contato.setEndereco(endereco);
        // contatoDAO.update(contato);

        // Contato contato = contatoDAO.buscarPorNome("Renan Acosta");
        // contato.setTelefone(List.of("53999999999", "53988888888", "53977777777"));
        // contatoDAO.update(contato);

        // #############################################################################

        System.out.println("=========================");
        System.out.println("ACOSTA'S AGENDA - MongoDB");
        System.out.println("=========================");

        // listar contatos

        System.out.println("");
        System.out.println("===========================================================");
        System.out.println("");
        System.out.println(">>> LISTANDO TODOS OS CONTATOS <<<");
        System.out.println("");

        MongoCursor<Contato> contatos = contatoDAO.listar();

        while (contatos.hasNext()) {
            Contato c = contatos.next();
            System.out.println("");
            System.out.println(c);
        }

        // #############################################################################

        // listar contatos por cidade

        System.out.println("");
        System.out.println("===========================================================");
        System.out.println("");
        System.out.println(">>> LISTANDO CONTATOS POR CIDADE: [RIO GRANDE] <<<");
        System.out.println("");

        MongoCursor<Contato> contatosRioGrande = contatoDAO.listarPorCidade("Rio Grande");

        while (contatosRioGrande.hasNext()) {
            Contato c = contatosRioGrande.next();
            System.out.println("");
            System.out.println(c);
        }

        System.out.println("");
        System.out.println("===========================================================");
        System.out.println("");
        System.out.println(">>> LISTANDO CONTATOS POR CIDADE: [PELOTAS] <<<");
        System.out.println("");

        MongoCursor<Contato> contatosPelotas = contatoDAO.listarPorCidade("Pelotas");

        while (contatosPelotas.hasNext()) {
            Contato c = contatosPelotas.next();
            System.out.println("");
            System.out.println(c);
        }

        // #############################################################################

        // listar contatos com mais de um telefone
        System.out.println("");
        System.out.println("===========================================================");
        System.out.println("");
        System.out.println(">>> LISTANDO CONTATOS COM MAIS DE UM TELEFONE <<<");
        System.out.println("");

        List<Contato> maisTelefones = contatoDAO.listarContatosComMaisDeUmTelefone();

        for (Contato c : maisTelefones) {
            System.out.println("");

            System.out.println(c);
        }
        System.out.println("");
        System.out.println("===========================================================");
        System.out.println("");

        System.out.println("FIM DO PROGRAMA!");
    }
}
