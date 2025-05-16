package futurodevv1.reciclaville.errors.exceptions;

public class DeclarationNotFoundException extends RuntimeException
{
    public DeclarationNotFoundException(Long id)
    {
        super("Declaração com ID " + id + " não encontrada");
    }
}
