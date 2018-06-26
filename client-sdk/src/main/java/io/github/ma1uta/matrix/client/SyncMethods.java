/*
 * Copyright sablintolya@gmail.com
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

import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.Page;
import io.github.ma1uta.matrix.client.api.SyncApi;
import io.github.ma1uta.matrix.client.model.sync.SyncResponse;

/**
 * Sync method.
 */
public class SyncMethods {

    private final MatrixClient matrixClient;

    SyncMethods(MatrixClient matrixClient) {
        this.matrixClient = matrixClient;
    }

    protected MatrixClient getMatrixClient() {
        return matrixClient;
    }

    /**
     * Sync events.
     *
     * @param filter    filter name.
     * @param since     next batch token.
     * @param fullState full state or not.
     * @param presence  offline presence or not.
     * @param timeout   timeout
     * @return sync data.
     */
    public SyncResponse sync(String filter, String since, boolean fullState, String presence, Long timeout) {
        RequestParams params = new RequestParams().queryParam("filter", filter)
            .queryParam("since", since)
            .queryParam("fullState", Boolean.toString(fullState))
            .queryParam("presence", presence);
        if (timeout != null) {
            params.queryParam("timeout", Long.toString(timeout));
        }
        return getMatrixClient().getRequestMethods().asyncGet(SyncApi.class, "sync", params, SyncResponse.class);
    }

    /**
     * This will listen for new events related to a particular room and return them to the caller. This will block until an event is
     * received, or until the timeout is reached.
     *
     * @param from    The token to stream from. This token is either from a previous request to this API or from the initial sync
     *                API.
     * @param timeout The maximum time in milliseconds to wait for an event.
     * @param roomId  The room ID for which events should be returned.
     * @return The events received, which may be none.
     */
    @SuppressWarnings("unchecked")
    public Page<Event> events(String from, Long timeout, String roomId) {
        RequestParams params = new RequestParams().queryParam("from", from).queryParam("roomId", roomId);
        if (timeout != null) {
            params.queryParam("timeout", Long.toString(timeout));
        }
        return getMatrixClient().getRequestMethods().get(SyncApi.class, "events", params, Page.class);
    }
}
