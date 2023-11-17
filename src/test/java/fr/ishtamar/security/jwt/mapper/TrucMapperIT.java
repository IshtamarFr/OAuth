package fr.ishtamar.security.jwt.mapper;

import fr.ishtamar.security.jwt.dto.TrucDto;
import fr.ishtamar.security.jwt.entity.Truc;
import fr.ishtamar.security.jwt.entity.UserInfo;
import fr.ishtamar.security.jwt.service.impl.UserInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TrucMapperIT {
    @Autowired
    private TrucMapper trucMapper;

    @MockBean
    private UserInfoServiceImpl userInfoService;

    @Test
    public void testTrucToEntity() {
        UserInfo user= new UserInfo(106L,"TheOldMan","106@scp.com","123456","ROLE_USER");
        TrucDto trucDto = new TrucDto(42L,"mockTrucDto",106L);

        when(userInfoService.getUserById(106L)).thenReturn(user);

        Truc truc = trucMapper.toEntity(trucDto);
        assertThat(truc.getName()).isEqualTo("mockTrucDto");
        assertThat(truc.getUser().getName()).isEqualTo("TheOldMan");
    }
}
