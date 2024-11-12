package kr.co.bootcal.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TestBoardVO {
    String title;
    String content;
    String author;
    String regDate;
    String imageFile;
}
