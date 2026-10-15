import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Classe que funciona como o "banco de dados" principal do sistema,
// associando cada Cliente ao conjunto de Servidores que ele possui. RA:25270970-2
public class CloudManager {

    // RN02: o atributo é declarado pela interface Map e instanciado com HashMap
    // (implementação de alta performance baseada em Hash).
    // Chave: Cliente (identificado pelo CPF, graças ao equals/hashCode).
    // Valor: Set de Servidores (sem duplicação e sem ordem específica). RA:25270970-2
    private Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    // Construtor padrão: o mapa já foi inicializado na declaração. RA:25270970-2
    public CloudManager() {
    }

    // Registra um cliente no banco com um conjunto de servidores vazio.
    // putIfAbsent só insere se o cliente ainda não existir, evitando apagar
    // os servidores de um cliente já cadastrado. RA:25270970-2
    public void registrarCliente(Cliente c) {
        bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    // Associa um servidor a um cliente.
    // computeIfAbsent devolve o Set do cliente (criando um HashSet vazio caso o cliente
    // ainda não esteja registrado) e em seguida o servidor é adicionado ao Set.
    // Como é um HashSet, um servidor com o mesmo IP não é adicionado duas vezes. RA:25270970-2
    public void provisionarServidor(Cliente c, Servidor s) {
        bancoDeDados.computeIfAbsent(c, k -> new HashSet<>()).add(s);
    }

    // RN04: calcula a fatura ativa (soma do preço por hora dos servidores LIGADOS) de um cliente.
    // getOrDefault -> busca o Set do cliente; se ele não existir, usa um Set vazio (evita NullPointerException sem if);
    // stream() -> percorre o Set de forma funcional;
    // filter -> deixa passar apenas os servidores com statusLigado == true;
    // mapToDouble -> extrai o precoPorHora de cada servidor;
    // sum -> soma os valores e retorna o total. RA:25270970-2
    public double calcularFaturaAtiva(Cliente c) {
        return bancoDeDados.getOrDefault(c, Collections.emptySet())
                .stream()
                .filter(Servidor::isStatusLigado)
                .mapToDouble(Servidor::getPrecoPorHora)
                .sum();
    }

    // Getter do banco de dados. RA:25270970-2
    public Map<Cliente, Set<Servidor>> getBancoDeDados() {
        return bancoDeDados;
    }
}
