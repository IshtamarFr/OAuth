package fr.ishtamar.security.jwt.mapper;

import fr.ishtamar.security.jwt.dto.TrucDto;
import fr.ishtamar.security.jwt.entity.Truc;
import fr.ishtamar.security.jwt.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TrucMapperTest {
    @Autowired
    private TrucMapper trucMapper;

    @Test
    public void testTrucToDto() {
        UserInfo user= new UserInfo(106L,"TheOldMan","106@scp.com","123456","ROLE_USER");
        Truc truc = new Truc(1L,"mockTruc",user);
        TrucDto trucDto = trucMapper.toDto(truc);

        assertThat(trucDto.getName()).isEqualTo("mockTruc");
        assertThat(trucDto.getUser_id()).isEqualTo(106L);
    }
}
