package com.copsperf;

import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.blazemeter.BlazeMeterEngine;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import us.abstracta.jmeter.javadsl.dashboard.DashboardVisualizer;
import us.abstracta.jmeter.javadsl.octoperf.OctoPerfEngine;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class PerformanceTest {


    @Test
    public void jmeterTest() throws IOException {
        System.out.println("jmeterTest");
        testPlan(threadGroup(1, 10,
                        httpSampler("http://opencart.abstracsta.us")
                                .children(
                                        uniformRandomTimer(1000, 5000)
                                )
                ),
//                jtlWriter("test.txt")
                DashboardVisualizer.dashboardVisualizer()
        ).run();
    }

    @Test
    public void jmeterTest2() throws IOException {
        System.out.println("jmeterTest");
        testPlan(threadGroup(1, 10,
                        httpSampler("http://opencart.abstracsta.us")
                                .children(
                                        uniformRandomTimer(1000, 5000)
                                )
                ),
                htmlReporter("target/reports")
        ).run();
    }

//    @Test
//    public void jmeterTest3() throws IOException {
//        System.out.println("jmeterTest2");
//        testPlan(threadGroup(1, 1,
//                httpSampler("http://opencart.abstracta.us"))
//        ).saveAsJmx(EndpointConstantse.JMX_PATH);
//    }

    @Test
    public void jmeterTest4() throws IOException, InterruptedException, TimeoutException {
        System.out.println("jmeterTest4");
        testPlan(threadGroup(1, 10,
                        httpSampler("http://opencart.abstracsta.us")
                )
        ).runIn(new BlazeMeterEngine(System.getenv("BZ_TOKEN")));
    }

    @Test
    public void jmeterTest5() throws IOException, InterruptedException, TimeoutException {
        System.out.println("jmeterTest5");
        testPlan(threadGroup(1, 10,
                        httpSampler("http://opencart.abstracsta.us")
                )
        ).runIn(new OctoPerfEngine(System.getenv("OCTOPERF_TOKEN")));
    }

    @Test
    public void jmeterTest6() throws IOException {
        System.out.println("jmeterTest6");
        TestPlanStats stats = testPlan(threadGroup(1, 3,
                httpSampler("http://opencart.abstracsta.us")
                        .children(responseAssertion().containsSubstrings("test")))
        ).run();
        assertThat(stats.overall().sampleTimePercentile99()).isLessThanOrEqualTo(Duration.ofSeconds(5));
    }
}
