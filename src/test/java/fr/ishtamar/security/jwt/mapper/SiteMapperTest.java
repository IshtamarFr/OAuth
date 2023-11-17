package fr.ishtamar.security.jwt.mapper;

import fr.ishtamar.security.jwt.dto.SiteDto;
import fr.ishtamar.security.jwt.entity.Site;
import fr.ishtamar.security.jwt.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SiteMapperTest {
    @Autowired
    private SiteMapper siteMapper;

    @Test
    public void testTrucToDto() {
        UserInfo user= new UserInfo(106L,"TheOldMan","106@scp.com","123456","ROLE_USER");
        Site site = new Site(1L,"mockSite",user,"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        SiteDto siteDto = siteMapper.toDto(site);

        assertThat(siteDto.getName()).isEqualTo("mockSite");
        assertThat(siteDto.getOwner_id()).isEqualTo(106L);
    }
}
