package com.anonymousboard.dto.response;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class BoardResponseDtos {
    private Map<String, List<BoardResponseDto>> boardResponseDtos = new HashMap<>();
}

