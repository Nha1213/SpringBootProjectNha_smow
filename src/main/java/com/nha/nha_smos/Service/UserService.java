package com.nha.nha_smos.Service;

import com.nha.nha_smos.DTO.User.RegisterRequest;
import com.nha.nha_smos.DTO.User.RegisterResponse;
import com.nha.nha_smos.Exception.ResourceNotFoundException;
import com.nha.nha_smos.Model.RoleModel;
import com.nha.nha_smos.Model.UserModel;
import com.nha.nha_smos.Model.UserProfileModel;
import com.nha.nha_smos.Repository.RoleRepository;
import com.nha.nha_smos.Repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;
    private  final RoleRepository roleRepository;

    public UserModel registerUser(RegisterRequest req) {
        if((req.getEmail() == null || req.getEmail().isBlank())
                || (req.getPassword() == null || req.getPassword().isEmpty())
        ){
            throw  new ResourceNotFoundException("Invalid email or password");
        }
//        isPresent is mean  already exists
        if(req.getEmail() != null && this.userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new ResourceNotFoundException("Email already exists");
        }
        if(req.getPhone() != null && this.userRepository.findByPhone(req.getPhone()).isPresent()) {
            throw new ResourceNotFoundException("Phone already exists");
        }

//      set user optional
        UserModel user = new UserModel();

        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword()); // work encrypt
        user.setPhone(req.getPhone());

        //set role optional
        RoleModel role = this.roleRepository.findByName(req.getRoleName()).orElse(null);
        user.getRoles().add(role);

        //set  profile if providee
        if(req.getFirstName() != null && req.getLastName() != null ) {

            UserProfileModel profile = new UserProfileModel();

            profile.setFirstName(req.getFirstName());
            profile.setLastName(req.getLastName());
//            set with profile and user
            profile.setUser(user);
            profile.setGender(req.getGender());
            profile.setAddress(req.getAddress());
            profile.setDateOfBirth(req.getDateOfBirth());
            profile.setImage(req.getImage());

//            set with user
            user.setProfile(profile);
        }



        //Entity to DTO
//        RegisterResponse res = new RegisterResponse();
//        res.setEmail(user.getEmail());
//        res.setPassword(user.getPassword());
//        res.setPhone(user.getPhone());
//        res.setProfile(res.getProfile());
//        res.setCreatedAt(res.getCreatedAt());
//        res.setRoles(res.getRoles());
        return this.userRepository.save(user);
    }
}
