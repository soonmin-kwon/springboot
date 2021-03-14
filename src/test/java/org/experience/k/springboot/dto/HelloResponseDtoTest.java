package org.experience.k.springboot.dto;

import org.experience.k.springboot.web.dto.HelloResponseDto;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void TestLombbokFunc(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);
        //then
        assertThat(dto.getName()).isEqualTo(name); // assertj라는 테스트 검증 라이브러리의 검증 메소드, 검증하고 싶은 대상을 메소드 인자로 받는다.
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
