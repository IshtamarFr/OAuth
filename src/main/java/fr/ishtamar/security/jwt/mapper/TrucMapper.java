package fr.ishtamar.security.jwt.mapper;

import fr.ishtamar.security.jwt.dto.TrucDto;
import fr.ishtamar.security.jwt.entity.Truc;
import fr.ishtamar.security.jwt.service.UserInfoService;
import fr.ishtamar.security.jwt.service.impl.UserInfoServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "Spring")
public abstract class TrucMapper implements EntityMapper<TrucDto, Truc> {

    @Autowired
    UserInfoService userInfoService=new UserInfoServiceImpl();

    @Mappings({
            @Mapping(target="user", expression="java(this.userInfoService.getUserById(trucDto.getUser_id()))")
    })
    public abstract Truc toEntity(TrucDto trucDto);

    @Mappings({
            @Mapping(source= "truc.user.id",target="user_id")
    })
    public abstract TrucDto toDto(Truc truc);
}
