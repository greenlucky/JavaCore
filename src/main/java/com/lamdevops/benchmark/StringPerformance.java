package com.lamdevops.benchmark;

import com.google.common.base.Splitter;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Measurement(batchSize = 10000, iterations = 10)
@Warmup(batchSize = 10000, iterations = 10)
@State(Scope.Thread)
public class StringPerformance {

    protected String baeldung = "baeldung";
    protected String longString = "Hello baeldung, I am a bit longer than other Strings";
    protected String formatString = "hello %s, nice to meet you";
    protected String formatDigit = "%d";
    protected String emptyString = " ";
    protected String result = "";

    protected int sampleNumber = 100;

    protected Pattern spacePattern = Pattern.compile(emptyString);
    protected Pattern longPattern = Pattern.compile(longString);
    protected List<String> stringSplit = new ArrayList<>();
    protected List<String> stringTokenizer = new ArrayList<>();

    @Benchmark
    public String benchmarkStringDynamicConcat() {
        result += baeldung;
        return result;
    }

    @Benchmark
    public StringBuilder  benchmarkStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder(result);
        stringBuilder.append(baeldung);
        return stringBuilder;
    }

    @Benchmark
    public List<String> benchmarkGuavaSplitter() {
        return Splitter.on(" ").trimResults()
                .omitEmptyStrings()
                .splitToList(longString);
    }

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(StringPerformance.class.getSimpleName()).threads(1)
                .forks(1).shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-server").build();
        new Runner(options).run();
    }
}
