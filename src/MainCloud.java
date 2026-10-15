// Classe executável com o roteiro de testes do "Campo de Testes". RA:
public class MainCloud {

    // O "throws CapacidadeExcedidaException" é necessário porque alocarContainer declara essa exceção checked;
    // assim os passos 6 e 8 podem chamar o método sem try-catch, como pede o roteiro. RA:25270970-2
    public static void main(String[] args) throws CapacidadeExcedidaException {

        // Passo 1: instancia o gerenciador (banco de dados principal). RA:25270970-2
        CloudManager manager = new CloudManager();

        // Passo 2: instancia um cliente (CPF e nome). RA:25270970-2
        Cliente cliente = new Cliente("111.222.333-44", "Wayne Enterprises");

        // Passo 3: instancia um servidor (IP, RAM total de 16 GB, R$ 5.50 por hora).
        // Lembrando que o servidor nasce DESLIGADO. RA:25270970-2
        Servidor servidor = new Servidor("192.168.0.1", 16.0, 5.50);

        // Passo 4: registra o cliente no banco e associa o servidor a ele. RA:25270970-2
        manager.registrarCliente(cliente);
        manager.provisionarServidor(cliente, servidor);

        // Passo 5: instancia dois containers diferentes (10 GB e 8 GB de RAM). RA:25270970-2
        Container c1 = new Container("C-01", "App Batcomputador", 10.0);
        Container c2 = new Container("C-02", "App Bat-sinal", 8.0);

        // Passo 6 - Teste Falha Unchecked: tenta alocar com o servidor desligado.
        // Sem try-catch de propósito: a ServidorOfflineException "estoura" no console (mensagem vermelha),
        // provando que a regra funciona.
        // Passo 7: após o teste, a linha foi comentada para o programa poder prosseguir. RA:25270970-2
        // servidor.alocarContainer(c1);

        // Passo 7: liga o servidor. RA:25270970-2
        servidor.ligar();

        // Passo 8: aloca o container 1, agora com sucesso (servidor ligado e 10 GB <= 16 GB). RA:25270970-2
        servidor.alocarContainer(c1);
        System.out.println("Sucesso: container " + c1.getId() + " alocado no servidor " + servidor.getIp() + ".");
        System.out.println("RAM em uso: " + servidor.calcularRamEmUso() + " GB de " + servidor.getRamTotal() + " GB.");

        // Passo 9 - Teste Falha Checked: tenta alocar o container 2 (10 + 8 = 18 GB > 16 GB).
        // O catch captura a CapacidadeExcedidaException e imprime o erro de forma elegante,
        // sem derrubar a aplicação. RA:25270970-2
        try {
            servidor.alocarContainer(c2);
            System.out.println("Sucesso: container " + c2.getId() + " alocado no servidor " + servidor.getIp() + ".");
        } catch (CapacidadeExcedidaException e) {
            System.out.println("[ALERTA] Não foi possível alocar o container " + c2.getId() + " -> " + e.getMessage());
        }

        // Passo 10: imprime a fatura ativa do cliente (apenas servidores ligados). Valor esperado: 5.50. RA:25270970-2
        System.out.printf("Fatura ativa de %s: R$ %.2f por hora%n", cliente.getNome(), manager.calcularFaturaAtiva(cliente));
    }
}
