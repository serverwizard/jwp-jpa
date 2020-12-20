package jpa.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StationRepositoryTest {
    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private LineRepository lineRepository;

    @DisplayName("StationRepository - save 테스트")
    @Test
    void saveTest() {
        Line sevenLine = lineRepository.save(Line.of("녹색", "7호선"));
        Line fiveLine = lineRepository.save(Line.of("보라색", "5호선"));

        Station savedStation = Station.of("군자역");
        savedStation.addLine(sevenLine);
        savedStation.addLine(fiveLine);

        // doesn't show mapping table insert query
        stationRepository.save(savedStation);
    }
}
