package com.companny.pinponalv.shoptech.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record PermissionRequest(
        @Schema(example = "READ", description = "read para poder leer documentos")
        @NotBlank(message = "no puede estar vacio")
        String permission
) {}
