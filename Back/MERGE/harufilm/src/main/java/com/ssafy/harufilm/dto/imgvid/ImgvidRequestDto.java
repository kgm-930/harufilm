package com.ssafy.harufilm.dto.imgvid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImgvidRequestDto {
    
    private String userid;
    private String userpassword;
}
