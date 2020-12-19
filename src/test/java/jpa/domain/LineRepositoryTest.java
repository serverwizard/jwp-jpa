package jpa.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
// 애플리케이션 컨텍스트가 매번 생성되기 때문에 테스트 속도가 느려지는 단점이 있다.
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class LineRepositoryTest {

    @Autowired
    private LineRepository lineRepository;

    private Line savedLine;

    @BeforeEach
    void setUp() {
        savedLine = lineRepository.save(Line.of("green", "사가정역"));
    }

    @DisplayName("LineRepository - 조회 테스트")
    @Test
    public void findByIdTest() {
        Line line = lineRepository.findById(1L)
                .orElseThrow(IllegalArgumentException::new);

        assertThat(line).isEqualTo(savedLine);
    }

    @DisplayName("LineRepository - @Query 애노테이션을 이용한 삭제 테스트")
    @Test
    void deleteLineTest1() {
        lineRepository.deleteLineBy(1L);

        Line line = lineRepository.findLineById(1L);

        assertThat(line).isEqualTo(null);
    }

    @DisplayName("LineRepository - 쿼리 메소드를 이용한 삭제 테스트")
    @Test
    void deleteLineTest2() {
        lineRepository.deleteLineBy(1L);

        Line line = lineRepository.findById(1L)
                .orElseThrow(IllegalArgumentException::new);

        assertThat(line).isEqualTo(savedLine);
    }
}
