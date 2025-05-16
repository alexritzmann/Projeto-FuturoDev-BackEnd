package futurodevv1.reciclaville.errors.exceptions;

public class MaterialNotFoundException extends RuntimeException
{
    public MaterialNotFoundException(Long id)
    {
        super("Material com ID " + id + " não encontrado");
    }
}
