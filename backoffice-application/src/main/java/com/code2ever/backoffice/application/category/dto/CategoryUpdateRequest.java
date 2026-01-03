package com.code2ever.backoffice.application.category.dto;

public record CategoryUpdateRequest(
        String name,
        Boolean active
) {}
