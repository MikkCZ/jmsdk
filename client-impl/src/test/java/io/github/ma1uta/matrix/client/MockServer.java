/*
 * Copyright Anatoliy Sablin tolya@sablin.xyz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.matrix.client;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class MockServer {

    public static final String ACCESS_TOKEN = "$ome_sEcreT_t0keN";

    private StandaloneClient matrixClient;
    public WireMockServer wireMockServer;

    public StandaloneClient getMatrixClient() {
        return matrixClient;
    }

    @BeforeEach
    public void setUp() {
        System.setProperty("jmsdk.resolver.homeserver.verification.disable", "true");
        matrixClient = new StandaloneClient("http://localhost:8089");
        wireMockServer = new WireMockServer(options().port(8089).notifier(new ConsoleNotifier(true)));
        wireMockServer.start();
    }

    @AfterEach
    public void shutdown() {
        matrixClient.close();
        wireMockServer.stop();
    }
}
