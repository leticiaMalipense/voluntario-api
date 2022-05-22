package com.ifsp.api.queroservoluntario.converters;

import com.ifsp.api.queroservoluntario.entity.*;
import com.ifsp.api.queroservoluntario.enums.UserType;
import com.ifsp.api.queroservoluntario.rest.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserConverter {

    public UserEntity toEntity(UserModel userModel) {
        if(userModel instanceof IndividualModel) {
            IndividualEntity user = new IndividualEntity();
            user.setName(userModel.getName());
            user.setEmail(userModel.getEmail());
            user.setDocument(userModel.getDocument());
            user.setOcupation(((IndividualModel) userModel).getOcupation());
            user.setActive(Boolean.TRUE);

            if (userModel.getPassword() != null) {
                user.setPassword(userModel.getPassword());
            }

            return user;
        }

        if(userModel instanceof CompanyModel) {
            CompanyEntity user = new CompanyEntity();
            user.setName(userModel.getName());
            user.setEmail(userModel.getEmail());
            user.setDocument(userModel.getDocument());
            user.setDescription(((CompanyModel) userModel).getDescription());
            user.setOccupationArea(((CompanyModel) userModel).getOccupationArea());
            user.setActive(Boolean.FALSE);

            if (userModel.getPassword() != null) {
                user.setPassword(userModel.getPassword());
            }

            return user;
        }

        return null;
    }

    public UserModel toModel(Optional<UserEntity> userEntity, Optional<UserImageEntity> imageEntity) {
        if(!userEntity.isPresent()) {
            return null;
        }

        UserEntity user = userEntity.get();

        if(user instanceof IndividualEntity) {
            IndividualModel model = new IndividualModel();
            model.setId(user.getId());
            model.setName(user.getName());
            model.setEmail(user.getEmail());
            model.setDocument(user.getDocument());
            model.setPassword(user.getPassword());
            model.setType(UserType.findByName(user.getProfile().getType()));
            model.setOcupation(((IndividualEntity) user).getOcupation());
            model.setPhones(getPhones(user));
            model.setCreationDate(user.getCreationDate());
            model.setActive(user.getActive());

            if(imageEntity != null && imageEntity.isPresent()) {
                UserImageModel imageModel = new UserImageModel();
                imageModel.setTitle(imageEntity.get().getTitle());
                imageModel.setImage(imageEntity.get().getImage());
                model.setImage(imageModel);
            }

            return model;
        }

        if(user instanceof CompanyEntity) {
            CompanyModel model = new CompanyModel();
            model.setId(user.getId());
            model.setName(user.getName());
            model.setEmail(user.getEmail());
            model.setDocument(user.getDocument());
            model.setPassword(user.getPassword());
            model.setType(UserType.findByName(user.getProfile().getType()));
            model.setDescription(((CompanyEntity) user).getDescription());
            model.setOccupationArea(((CompanyEntity) user).getOccupationArea());
            model.setPhones(getPhones(user));
            model.setCreationDate(user.getCreationDate());
            model.setActive(user.getActive());

            if(imageEntity != null && imageEntity.isPresent()) {
                UserImageModel imageModel = new UserImageModel();
                imageModel.setTitle(imageEntity.get().getTitle());
                imageModel.setImage(imageEntity.get().getImage());
                model.setImage(imageModel);
            }

            return model;
        }

        if(user instanceof ManagerEntity) {
            ManagerModel model = new ManagerModel();
            model.setId(user.getId());
            model.setName(user.getName());
            model.setEmail(user.getEmail());
            model.setDocument(user.getDocument());
            model.setPassword(user.getPassword());
            model.setType(UserType.findByName(user.getProfile().getType()));
            return model;
        }

        return null;
    }

    private List<PhoneModel> getPhones(UserEntity user) {
        List<PhoneModel> phones = new ArrayList<>();
        user.getPhones().forEach(phone ->{
            PhoneModel phoneModel = new PhoneModel();
            phoneModel.setDdd(phone.getDdd());
            phoneModel.setNumber(phone.getNumber());
            phones.add(phoneModel);
        });

        return phones;
    }

    public UserEntity toEntityUpdate(UserEntity user, UserModel userModel) {
        if(user instanceof IndividualEntity) {
            IndividualEntity individual = (IndividualEntity) user;
            individual.setName(userModel.getName());
            individual.setDocument(userModel.getDocument());
            individual.setOcupation(((IndividualModel) userModel).getOcupation());

            return individual;
        }

        if(user instanceof CompanyEntity) {
            CompanyEntity company = (CompanyEntity) user;
            company.setName(userModel.getName());
            company.setDocument(userModel.getDocument());
            company.setDescription(((CompanyModel) userModel).getDescription());
            company.setOccupationArea(((CompanyModel) userModel).getOccupationArea());

            return company;
        }

        return null;
    }
}
