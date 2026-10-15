// Exceção customizada do tipo CHECKED (RN03 - Regra de Capacidade).
// Por herdar de Exception (e não de RuntimeException), o compilador OBRIGA quem chama
// o método a tratá-la com try-catch ou declará-la com "throws".
// É lançada quando a RAM do servidor não comporta um novo container. RA:25270970-2
public class CapacidadeExcedidaException extends Exception {

    // Construtor que repassa a mensagem de erro para a superclasse (Exception). RA:25270970-2
    public CapacidadeExcedidaException(String msg) {
        super(msg);
    }
}
