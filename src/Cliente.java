import java.util.Objects;

// Classe de domínio (POJO) que representa um cliente da NexusCloud.
// A identidade do cliente é definida pelo CPF, e não pelo endereço de memória (RN01). RA:25270970-2
public class Cliente {

    // Atributos privados para garantir o encapsulamento: só podem ser acessados pelos getters. RA:25270970-2
    private String cpf;
    private String nome;

    // Construtor que obriga a informar CPF e nome no momento da criação do objeto. RA:25270970-2
    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    // Getter do CPF. Não há setter porque o CPF é a identidade do objeto;
    // alterá-lo depois de inserido no HashMap "perderia" a chave. RA:25270970-2
    public String getCpf() {
        return cpf;
    }

    // Getter do nome do cliente. RA:25270970-2
    public String getNome() {
        return nome;
    }

    // equals sobrescrito: dois clientes são iguais se tiverem o mesmo CPF.
    // 1) Se for a mesma referência, já é igual.
    // 2) Se o outro objeto for nulo ou de outra classe, não é igual.
    // 3) Caso contrário, compara os CPFs com Objects.equals (seguro contra null). RA:25270970-2
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente outro = (Cliente) obj;
        return Objects.equals(cpf, outro.cpf);
    }

    // hashCode sobrescrito usando o mesmo campo do equals (CPF).
    // Isso é obrigatório para o Cliente funcionar como chave do HashMap. RA:25270970-2
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    // toString para exibir o cliente de forma legível no console. RA:25270970-2
    @Override
    public String toString() {
        return "Cliente [cpf=" + cpf + ", nome=" + nome + "]";
    }
}
