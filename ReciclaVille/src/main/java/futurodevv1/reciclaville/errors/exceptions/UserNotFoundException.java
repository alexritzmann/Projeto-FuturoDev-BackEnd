package futurodevv1.reciclaville.errors.exceptions;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(Long id)
    {
      super("User with id '" + id + "' not found");
    }
}
