package com.cdac.trustvault.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.trustvault.custom_exceptions.ResourceNotFoundException;
import com.cdac.trustvault.dto.AuthRequest;
import com.cdac.trustvault.dto.PasswordResetRequest;
import com.cdac.trustvault.dto.UserRespDTO;
import com.cdac.trustvault.entity.UserEntity;
import com.cdac.trustvault.repository.UserRepository;

@Service
@Transactional
public class OTPUserServiceImpl implements OTPUserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;  // Corrected password encoding

    @Override
    public String addNewUser(UserEntity newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword())); // Encode password before saving
        UserEntity user = userRepo.save(newUser);
        return "New user added with ID=" + user.getId() + " " + user.getRole();
    }

    @Override
    public UserRespDTO authenticate(AuthRequest dto) {
        Optional<UserEntity> optional = userRepo.findByEmail(dto.getEmail());
        UserEntity userEntity = optional
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password !"));
        if (!passwordEncoder.matches(dto.getPassword(), userEntity.getPassword())) {
            throw new ResourceNotFoundException("Invalid email or password !!");
        }
        return mapper.map(userEntity, UserRespDTO.class);
    }

    @Override
    public void updatePassword(PasswordResetRequest passResDTO) {
        Optional<UserEntity> optional = userRepo.findByEmail(passResDTO.getEmail());
        UserEntity userEntity = optional.orElseThrow(() -> new ResourceNotFoundException("Invalid email or OTP!"));

        userEntity.setPassword(passwordEncoder.encode(passResDTO.getNewPassword())); // Encode new password
        userRepo.save(userEntity);
    }
}
