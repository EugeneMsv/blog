package com.nast.domain.specifications;

import com.nast.domain.entities.security.UserProfile;
import com.nast.domain.services.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {

    private static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);

    @Autowired
    private UserProfileService userProfileService;

    @Override
    public UserProfile convert(Object source) {
        UserProfile profile = userProfileService.findOne(Long.parseLong(String.valueOf(source)));
        logger.info("Profile : {}", profile);
        return profile;
    }
}
