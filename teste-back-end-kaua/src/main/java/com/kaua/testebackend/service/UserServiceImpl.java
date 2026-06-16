package com.kaua.testebackend.service;

import com.kaua.testebackend.entity.Address;
import com.kaua.testebackend.entity.User;
import com.kaua.testebackend.exception.UserNotFoundException;
import com.kaua.testebackend.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ViaCepService viaCepService;

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("Usuário não encontrado"));
    }

    @Transactional
    @Override
    public User create(User user) {

        if (user.getAddresses() != null) {

            for (Address address : user.getAddresses()) {

                viaCepService.validateCep(address.getCep());

                address.setUser(user);
            }
        }

        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User update(Long id, User user){

        User existingUser = findById(id);

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());

        existingUser.getAddresses().clear();

        if (user.getAddresses() != null) {

            for (Address address : user.getAddresses()) {

                viaCepService.validateCep(address.getCep());

                address.setUser(existingUser);

                existingUser.getAddresses().add(address);
            }
        }

        return userRepository.save(existingUser);
    }

    @Transactional
    @Override
    public void delete(Long id){
        User user = findById(id);
        userRepository.delete(user);
    }

}
