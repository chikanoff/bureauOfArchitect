package com.bureau.mapper;

import com.bureau.model.dto.request.user.CreateUserRequest;
import com.bureau.model.dto.request.user.UpdateUserRequest;
import com.bureau.model.dto.response.UserInfoResponse;
import com.bureau.model.dto.response.UserResponse;
import com.bureau.model.dto.response.UserRoleResponse;
import com.bureau.model.entity.User;
import com.bureau.model.entity.User.UserBuilder;
import com.bureau.model.entity.UserRole;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-13T12:57:14+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.3.jar, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User createUserRequestToUser(CreateUserRequest req) {
        if ( req == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.fullName( req.getFullName() );
        user.username( req.getUsername() );
        user.email( req.getEmail() );
        user.password( req.getPassword() );

        return user.build();
    }

    @Override
    public void updateUserWithUpdateUserRequest(UpdateUserRequest req, User user) {
        if ( req == null ) {
            return;
        }

        user.setFullName( req.getFullName() );
        user.setUsername( req.getUsername() );
        user.setEmail( req.getEmail() );
    }

    @Override
    public UserInfoResponse userToUserInfoResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserInfoResponse userInfoResponse = new UserInfoResponse();

        userInfoResponse.setId( user.getId() );
        userInfoResponse.setFullName( user.getFullName() );
        userInfoResponse.setUsername( user.getUsername() );
        userInfoResponse.setEmail( user.getEmail() );
        userInfoResponse.setRole( userRoleToUserRoleResponse( user.getRole() ) );

        return userInfoResponse;
    }

    @Override
    public UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setFullName( user.getFullName() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setPassword( user.getPassword() );
        userResponse.setRole( userRoleToUserRoleResponse( user.getRole() ) );

        return userResponse;
    }

    protected UserRoleResponse userRoleToUserRoleResponse(UserRole userRole) {
        if ( userRole == null ) {
            return null;
        }

        UserRoleResponse userRoleResponse = new UserRoleResponse();

        userRoleResponse.setId( userRole.getId() );
        userRoleResponse.setName( userRole.getName() );

        return userRoleResponse;
    }
}
