// Exceção customizada do tipo UNCHECKED (RN03 - Regra de Status).
// Por herdar de RuntimeException, o compilador NÃO obriga a usar try-catch nem "throws".
// É lançada quando se tenta alocar um container em um servidor desligado. RA:25270970-2
public class ServidorOfflineException extends RuntimeException {

    // Construtor que repassa a mensagem de erro para a superclasse (RuntimeException). RA:25270970-2
    public ServidorOfflineException(String msg) {
        super(msg);
    }
}
