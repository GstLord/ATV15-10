import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Classe de domínio que representa um servidor virtual da NexusCloud.
// A identidade do servidor é definida pelo IP (RN01). RA:25270970-2
public class Servidor {

    // Atributos privados (encapsulamento). RA:25270970-2
    private String ip;
    private double precoPorHora;
    private double ramTotal;

    // O servidor "nasce" desligado: boolean tem valor padrão false,
    // mas deixamos explícito para facilitar a leitura. RA:25270970-2
    private boolean statusLigado = false;

    // RN02: lista cronológica (mantém a ordem de inserção) dos containers do servidor.
    // Inicializada já na declaração com ArrayList para evitar NullPointerException. RA:25270970-2
    private List<Container> containers = new ArrayList<>();

    // Construtor conforme o UML: IP, RAM total e preço por hora. RA:25270970-2
    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = ip;
        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
    }

    // Liga o servidor, permitindo que containers sejam alocados nele. RA:25270970-2
    public void ligar() {
        this.statusLigado = true;
    }

    // Desliga o servidor. RA:
    public void desligar() {
        this.statusLigado = false;
    }

    // RN03: aloca um container no servidor aplicando a política Fail-Fast,
    // ou seja, valida todas as regras ANTES de alterar a lista.
    // O "throws" é obrigatório porque CapacidadeExcedidaException é checked. RA:25270970-2
    public void alocarContainer(Container c) throws CapacidadeExcedidaException {
        // Regra de Status (unchecked): se o servidor estiver desligado, interrompe a operação. RA:25270970-2
        if (!statusLigado) {
            throw new ServidorOfflineException("Erro: Não é possível alocar containers em um servidor desligado.");
        }

        // Regra de Capacidade (checked): RAM em uso + RAM do novo container não pode passar da RAM total. RA:25270970-2
        if (calcularRamEmUso() + c.getRamNecessaria() > ramTotal) {
            throw new CapacidadeExcedidaException("Falha Crítica: Memória RAM insuficiente no servidor " + ip + ".");
        }

        // Se passou pelas duas validações, o container é adicionado ao final da lista. RA:25270970-2
        containers.add(c);
    }

    // RN04: calcula a RAM em uso somando a RAM de todos os containers alocados.
    // stream() -> percorre a lista de forma funcional (sem for);
    // mapToDouble -> transforma cada Container no seu valor de ramNecessaria;
    // sum -> soma todos os valores (retorna 0.0 se a lista estiver vazia). RA:25270970-2
    public double calcularRamEmUso() {
        return containers.stream()
                .mapToDouble(Container::getRamNecessaria)
                .sum();
    }

    // Getter do IP (sem setter, pois o IP é a identidade do servidor). RA:25270970-2
    public String getIp() {
        return ip;
    }

    // Getter do preço por hora; usado pela Stream em CloudManager.calcularFaturaAtiva(). RA:25270970-2
    public double getPrecoPorHora() {
        return precoPorHora;
    }

    // Getter da RAM total do servidor. RA:
    public double getRamTotal() {
        return ramTotal;
    }

    // Getter do status (para boolean a convenção é "is"); usado no filter da fatura. RA:25270970-2
    public boolean isStatusLigado() {
        return statusLigado;
    }

    // Getter da lista de containers. RA:
    public List<Container> getContainers() {
        return containers;
    }

    // equals sobrescrito: dois servidores são iguais se tiverem o mesmo IP.
    // Isso garante que o HashSet do CloudManager não aceite servidores duplicados. RA:25270970-2
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Servidor outro = (Servidor) obj;
        return Objects.equals(ip, outro.ip);
    }

    // hashCode coerente com o equals, calculado a partir do IP. RA:25270970-2
    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }

    // toString para exibir o servidor de forma legível no console. RA:25270970-2
    @Override
    public String toString() {
        return "Servidor [ip=" + ip + ", ramTotal=" + ramTotal + ", precoPorHora=" + precoPorHora
                + ", statusLigado=" + statusLigado + "]";
    }
}
