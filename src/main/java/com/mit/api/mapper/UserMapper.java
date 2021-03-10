package com.mit.api.mapper;

import com.mit.api.model.dto.ImageDto;
import com.mit.api.model.dto.UserDto;
import com.mit.api.model.dto.UserInfoDto;
import com.mit.api.model.entity.Image;
import com.mit.api.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toUser(UserDto userDto);

    Image toImage(ImageDto imageDto);

    UserInfoDto toUserInfo(User user);

    ImageDto toImageDto(Image image);
}
