package uk.tw.energy.generator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import uk.tw.energy.domain.ElectricityReading;

public class ElectricityReadingsGenerator {
    private final Random readingRandomiser;

    public ElectricityReadingsGenerator() {
        this.readingRandomiser = new Random();
    }

    public List<ElectricityReading> generate(int number) {
        List<ElectricityReading> readings = new ArrayList<>();
        Instant now = Instant.now();

        for (int i = 0; i < number; i++) {
            readings.add(generateReading(now.minusSeconds(i * 10L)));
        }

        readings.sort(Comparator.comparing(ElectricityReading::time));
        return readings;
    }

    private ElectricityReading generateReading(Instant time) {
        double positiveRandomValue = Math.abs(readingRandomiser.nextGaussian());
        BigDecimal randomReading = BigDecimal.valueOf(positiveRandomValue).setScale(4, RoundingMode.CEILING);
        return new ElectricityReading(time, randomReading);
    }
}
