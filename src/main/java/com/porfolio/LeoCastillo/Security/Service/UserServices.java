package com.porfolio.LeoCastillo.Security.Service;

import com.porfolio.LeoCastillo.Security.Entity.User;
import com.porfolio.LeoCastillo.Security.Repository.IUserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServices {
    @Autowired
    IUserRepository iuserRepository;
    
    public Optional<User> getByUsername(String username)
    {
        return iuserRepository.findByUsername(username);
    }
    
    public boolean existsByUsername(String username)
    {
        return iuserRepository.existsByUsername(username);
    }
    
    public boolean existsByEmail(String email)
    {
        return iuserRepository.existsByEmail(email);
    }
    
    public void save(User user)
    {
        iuserRepository.save(user);
    }
}
