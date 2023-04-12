package com.copsperf;

import com.copsperf.constants.EndpointConstants;
import com.copsperf.constants.PathConstants;
import com.copsperf.data.TowelsRequestData;
import com.copsperf.util.FileUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

@Slf4j
public class CopsPerformanceE2ETest {

    private String tibcoAddFolioRequest;

    @BeforeEach
    public void setup() {
        deleteDir(PathConstants.REPORTS_PATH);
      //  Map<String, Object> testDataMap = FileUtil.getTestDataMap("testData1");
      //  Map<String, Object> apiMap = FileUtil.getFileContents(PathConstants.TIBCO_ADD_UPDATE_FOLIO_JSON_PATH);

        // 1

        // 2
//        TibcoAddUpdateFolioRequestPojo tibcoAddFolioRequestData = TowelsRequestData.setTibcoAddUpdateFolioRequestData("31746");
//        tibcoAddFolioRequest = FileUtil.convertPojoToJsonString(tibcoAddFolioRequestData);
        System.out.println();
    }

    @Test
    @SneakyThrows
    public void copsE2ePerfTest() {
        log.info("CopsPerformanceE2ETest::copsE2ePerfTest");

        //${VoyageID}
        TestPlanStats stats = testPlan(
                threadGroup(1, 1,
                        httpSampler(EndpointConstants.GET_ALL_CUSTOMERS_URL)
                                .method(HTTPConstants.GET)
                                .children(responseAssertion().containsSubstrings("OK"))
                                .children(jsonExtractor("customerId", "S3GEL4876R")),

                        httpSampler(String.format(EndpointConstants.GET_CUSTOMER_BY_ID_URL, "S3GEL4876R"))
                                .method(HTTPConstants.GET)
                                .contentType(ContentType.APPLICATION_JSON)
                                .body(tibcoAddFolioRequest)
                                .children(responseAssertion().containsSubstrings("OK")),

                        httpSampler(EndpointConstants.ADD_CUSTOMER_URL)
                                .method(HTTPConstants.POST)
                                .contentType(ContentType.APPLICATION_JSON)
                                .body(tibcoAddFolioRequest)
                                .children(responseAssertion().containsSubstrings("OK"))),
                htmlReporter("target/reports")
        ).run();

        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }

    @SneakyThrows
    public static void deleteDir(String path) {
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

}
