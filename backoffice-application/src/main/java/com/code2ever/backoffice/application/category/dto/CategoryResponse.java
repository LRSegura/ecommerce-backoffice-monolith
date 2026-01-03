package com.code2ever.backoffice.application.category.dto;

public record CategoryResponse(
        Long id,
        String name,
        boolean active
) {}
