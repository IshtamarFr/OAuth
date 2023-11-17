package fr.ishtamar.security.jwt.mapper;

import fr.ishtamar.security.jwt.dto.SiteDto;
import fr.ishtamar.security.jwt.entity.Site;
import fr.ishtamar.security.jwt.entity.UserInfo;
import fr.ishtamar.security.jwt.service.impl.UserInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SiteMapperIT {
    @Autowired
    private SiteMapper siteMapper;

    @MockBean
    private UserInfoServiceImpl userInfoService;

    @Test
    public void testTrucToEntity() {
        UserInfo user= new UserInfo(106L,"TheOldMan","106@scp.com","123456","ROLE_USER");
        SiteDto siteDto = new SiteDto(42L,"mockSiteDto",106L);

        when(userInfoService.getUserById(106L)).thenReturn(user);

        Site site = siteMapper.toEntity(siteDto);
        assertThat(site.getName()).isEqualTo("mockSiteDto");
        assertThat(site.getOwner().getName()).isEqualTo("TheOldMan");
    }
}
