package com.ifsp.api.queroservoluntario.service;

import com.ifsp.api.queroservoluntario.converters.AddressConverter;
import com.ifsp.api.queroservoluntario.converters.PhoneConverter;
import com.ifsp.api.queroservoluntario.converters.UserConverter;
import com.ifsp.api.queroservoluntario.entity.AddressEntity;
import com.ifsp.api.queroservoluntario.entity.PhoneEntity;
import com.ifsp.api.queroservoluntario.entity.UserEntity;
import com.ifsp.api.queroservoluntario.entity.UserImageEntity;
import com.ifsp.api.queroservoluntario.infra.exceptions.ConflictException;
import com.ifsp.api.queroservoluntario.infra.exceptions.NotFoundException;
import com.ifsp.api.queroservoluntario.repository.*;
import com.ifsp.api.queroservoluntario.rest.model.UserApproveModel;
import com.ifsp.api.queroservoluntario.rest.model.UserModel;
import com.ifsp.api.queroservoluntario.rest.model.UserPasswordModel;
import com.ifsp.api.queroservoluntario.service.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UserImageRepository userImageRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private PhoneConverter phoneConverter;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PhoneService phoneService;

    @Transactional
    public UserModel save(UserModel userModel) {
        String password = decodePassword(userModel.getPassword());

        Optional<UserEntity> userByEmail = userRepository.findByEmailOrDocument(userModel.getEmail(), userModel.getDocument());
        if(userByEmail.isPresent()) {
            throw new ConflictException();
        }

        UserEntity user = userConverter.toEntity(userModel);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setProfile(profileRepository.findByType(userModel.getType().name()));

        AddressEntity address = addressConverter.toEntity(userModel.getAddress());
        addressRepository.save(address);

        user.setAddress(address);
        UserEntity userSaved = userRepository.save(user);

        phoneService.save(userSaved.getId(), userModel.getPhones());

        String token = tokenService.authentication(userModel.getEmail(), password);
        userModel.setId(userSaved.getId());
        userModel.setToken("Bearer " + token);

        return userModel;
    }

    public String decodePassword(String password) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(password);
        return new String(decodedBytes);
    }

    @Transactional
    public void update(Long id, UserModel userModel) {
        UserEntity user = userRepository.findById(id).get();

        UserEntity userUpdated = userConverter.toEntityUpdate(user, userModel);
        AddressEntity addressUpdate = addressConverter.toEntityUpdate(user.getAddress(), userModel.getAddress());
        PhoneEntity phoneUpdate = phoneConverter.toEntityUpdate(user.getPhones(), userModel.getPhones());

        phoneRepository.save(phoneUpdate);
        addressRepository.save(addressUpdate);
        userRepository.save(userUpdated);

        if(userModel.getImage() != null) {
            Optional<UserImageEntity> image =  userImageRepository.findByUserId(id);

            if(image.isPresent()) {
                image.get().setTitle(userModel.getImage().getTitle());
                image.get().setImage(userModel.getImage().getImage());
                userImageRepository.save(image.get());
            } else {
                UserImageEntity newImage = new UserImageEntity();
                newImage.setUserId(userUpdated.getId());
                newImage.setTitle(userModel.getImage().getTitle());
                newImage.setImage(userModel.getImage().getImage());
                userImageRepository.save(newImage);
            }
        }

    }

    public UserModel findById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new NotFoundException();
        }
        UserModel model = userConverter.toModel(user, null);
        model.setAddress(addressConverter.toModel(user.get().getAddress()));
        return model;
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<UserEntity> findByEmailActive(String email) {
        return userRepository.findByEmailActive(email);
    }

    @Transactional
    public void updatePassword(Long id, UserPasswordModel userPasswordModel) {
        String password = new BCryptPasswordEncoder().encode(decodePassword(userPasswordModel.getNewPassword()));
        userRepository.updatePassword(id, password);
    }

    public UserModel findFillById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new NotFoundException();
        }

        Optional<UserImageEntity> userImage =  userImageRepository.findByUserId(id);

        UserModel model = userConverter.toModel(user, userImage);
        model.setAddress(addressConverter.toModel(user.get().getAddress()));
        return model;
    }

    @Transactional
    public String recoveryPassword(String emailToSend) {
        Optional<UserEntity> user = userRepository.findByEmail(emailToSend);

        if(!user.isPresent()) {
            throw new NotFoundException();
        }

        String newPassword = newPassword();
        user.get().setPassword(new BCryptPasswordEncoder().encode(newPassword));
        System.out.println("Senha -- " + newPassword);
        userRepository.save(user.get());
        return newPassword;
    }

    private String newPassword() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    @Transactional
    public void approve(Long userId, UserApproveModel model) throws RuntimeException {

        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(!userEntity.isPresent()) {
            throw new NotFoundException("Nao encontrado");
        }

        if(userEntity.get().getActive().booleanValue() != model.isApprove()) {
            UserEntity entity = userEntity.get();
            entity.setActive(model.isApprove());
            userRepository.save(entity);
        }
    }
}
