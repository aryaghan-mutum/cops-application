package com.copsperf;

import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.Duration;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class PerfExamples {

    @Test
    public void jmeterTest1() throws IOException {
        TestPlanStats testPlanStats = testPlan(
                threadGroup(1, 10,
                        httpSampler("http://localhost:8081/users/getUsersList")),
                htmlReporter("target/reports")
        ).run();
        assertThat(testPlanStats.overall().errorsCount()).isEqualTo(0);
        assertThat(testPlanStats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }

    @Test
    public void jmeterTest2() throws IOException {
        TestPlanStats testPlanStats = testPlan(threadGroup(1, 5,
                        httpSampler("http://localhost:8081/users/getUserByEmailId/am@gmail.com")),
//                htmlReporter("target/reports"),
                jtlWriter("target/jtls")
        ).run();
        assertThat(testPlanStats.overall().errorsCount()).isEqualTo(0);
        assertThat(testPlanStats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }

    @Test
    public void g() throws IOException {
        testPlan(
                threadGroup(1, 10,
                        httpSampler("http://kong-api-kong.apps.dev2-ocp.aws-cup-dev.rccl.com/api/v1/voyage-data/voyage")
                                .children(
                                        jsr223PostProcessor("new File('traceFile') << \"${prev.sampleLabel}>>${prev.responseDataAsString}\\n\"")
                                )
                )
        ).showInGui();
    }

    @Test
    public void jmeterTestPost() throws IOException {
        TestPlanStats testPlanStats = testPlan(
                threadGroup(1, 1,
                        httpSampler("http://localhost:8081/users/signIn")
                                .method(HTTPConstants.POST)
                                .contentType(ContentType.APPLICATION_JSON)
                                .body("{\n" +
                                        "    \"email\": \"am@gmail.com\",\n" +
                                        "    \"password\": \"fdsdf32\"\n" +
                                        "}"),
                        httpSampler("http://localhost:8081/users/getUserByEmailId/am@gmail.com")
                                .method(HTTPConstants.GET)
                                .contentType(ContentType.APPLICATION_JSON),
                        httpSampler("http://localhost:8081/users/getUsersList")
                                .method(HTTPConstants.GET)
                                .contentType(ContentType.APPLICATION_JSON)),
                htmlReporter("target/reports")
        ).run();
//        assertThat(testPlanStats.overall().errorsCount()).isEqualTo(0);
        assertThat(testPlanStats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }
}
