package com.ssafy.harufilm.dto.subscribe;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscribeUserlistResponseDto {
    List<SubscribeUserDto> list;
}
