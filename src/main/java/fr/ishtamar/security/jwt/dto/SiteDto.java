package fr.ishtamar.security.jwt.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SiteDto {
    private Long id;

    @NotNull
    @Size(max=120)
    private String name;

    @NotNull
    private Long owner_id;
}
