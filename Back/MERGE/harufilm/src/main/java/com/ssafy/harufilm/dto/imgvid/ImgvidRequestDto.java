package com.ssafy.harufilm.dto.imgvid;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImgvidRequestDto {
    
    private int imgvidnumber;
    private String imgvidlocation;
    private LocalDateTime imgviddate;
    private String articleimg;
    private String articlevid;
}
