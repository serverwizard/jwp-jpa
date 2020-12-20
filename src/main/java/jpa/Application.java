package jpa;

import jpa.domain.Line;
import jpa.domain.LineRepository;
import jpa.domain.Station;
import jpa.domain.StationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private StationRepository stationRepository;

    private LineRepository lineRepository;

    public Application(StationRepository stationRepository,
                       LineRepository lineRepository) {
        this.stationRepository = stationRepository;
        this.lineRepository = lineRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // hibernate-envers를 위한 코드
        Line sevenLine = lineRepository.save(Line.of("green", "7호선"));
        Line fiveLine = lineRepository.save(Line.of("보라색", "5호선"));

        Station savedStation = Station.of("군자역");
        savedStation.addLine(sevenLine);
        savedStation.addLine(fiveLine);

        stationRepository.save(savedStation);
    }
}
