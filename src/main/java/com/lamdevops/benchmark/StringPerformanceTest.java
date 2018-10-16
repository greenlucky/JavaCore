package com.lamdevops.benchmark;

import org.junit.jupiter.api.Test;


class StringPerformanceTest {

    @Test
    public void testBenchmarkStringDynamicConcat() {
        StringPerformance stringPerformance = new StringPerformance();
        stringPerformance.benchmarkStringDynamicConcat();
    }
}