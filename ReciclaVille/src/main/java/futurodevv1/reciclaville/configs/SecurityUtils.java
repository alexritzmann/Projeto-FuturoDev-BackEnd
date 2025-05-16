package futurodevv1.reciclaville.configs;

import futurodevv1.reciclaville.entities.User;
import futurodevv1.reciclaville.enums.UserRole;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils
{
    public User getCurrentUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User)
        {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    public Long getClientIdIfUser()
    {
        User user = getCurrentUser();
        if (user != null && user.getRole() == UserRole.USER && user.getClient() != null)
        {
            return user.getClient().getId();
        }
        return null;
    }
}
