package fr.ishtamar.security.jwt.mapper;

import fr.ishtamar.security.jwt.dto.SiteDto;
import fr.ishtamar.security.jwt.entity.Site;
import fr.ishtamar.security.jwt.service.UserInfoService;
import fr.ishtamar.security.jwt.service.impl.UserInfoServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "Spring")
public abstract class SiteMapper implements EntityMapper<SiteDto, Site> {

    @Autowired
    UserInfoService userInfoService=new UserInfoServiceImpl();

    @Mappings({
            @Mapping(target="owner", expression="java(this.userInfoService.getUserById(siteDto.getOwner_id()))")
    })
    public abstract Site toEntity(SiteDto siteDto);

    @Mappings({
            @Mapping(source= "site.owner.id",target="owner_id")
    })
    public abstract SiteDto toDto(Site site);
}
