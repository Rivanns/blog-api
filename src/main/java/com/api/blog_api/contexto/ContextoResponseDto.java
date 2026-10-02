package com.api.blog_api.contexto;

import java.time.ZoneId;
import java.util.Locale;

public record ContextoResponseDto(
        String locale,
        String timezone,
        String data,
        String moeda,
        String numero
) {}
