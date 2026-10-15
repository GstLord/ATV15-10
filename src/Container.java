import java.util.Objects;

// Classe de domínio (POJO) que representa um container (aplicação) hospedado em um servidor.
// A identidade do container é definida pelo ID (RN01). RA:25270970-2
public class Container {

    // Atributos privados (encapsulamento). RA:25270970-2
    private String id;
    private String nomeApp;
    private double ramNecessaria;

    // Construtor que recebe todos os dados do container. RA:25270970-2
    public Container(String id, String nomeApp, double ramNecessaria) {
        this.id = id;
        this.nomeApp = nomeApp;
        this.ramNecessaria = ramNecessaria;
    }

    // Getter do ID (sem setter, pois o ID é a identidade do objeto). RA:25270970-2
    public String getId() {
        return id;
    }

    // Getter do nome da aplicação. RA:25270970-2
    public String getNomeApp() {
        return nomeApp;
    }

    // Getter da RAM necessária; usado pela Stream em Servidor.calcularRamEmUso(). RA:25270970-2
    public double getRamNecessaria() {
        return ramNecessaria;
    }

    // equals sobrescrito: dois containers são iguais se tiverem o mesmo ID. RA:25270970-2
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Container outro = (Container) obj;
        return Objects.equals(id, outro.id);
    }

    // hashCode coerente com o equals, calculado a partir do ID. RA:25270970-2
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString para exibir o container de forma legível no console. RA:25270970-2
    @Override
    public String toString() {
        return "Container [id=" + id + ", nomeApp=" + nomeApp + ", ramNecessaria=" + ramNecessaria + "]";
    }
}
