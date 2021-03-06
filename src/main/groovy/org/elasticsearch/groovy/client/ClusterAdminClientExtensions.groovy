/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.elasticsearch.groovy.client

import org.elasticsearch.action.ListenableActionFuture
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse
import org.elasticsearch.action.admin.cluster.node.hotthreads.NodesHotThreadsRequest
import org.elasticsearch.action.admin.cluster.node.hotthreads.NodesHotThreadsResponse
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoRequest
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoResponse
import org.elasticsearch.action.admin.cluster.node.restart.NodesRestartRequest
import org.elasticsearch.action.admin.cluster.node.restart.NodesRestartResponse
import org.elasticsearch.action.admin.cluster.node.shutdown.NodesShutdownRequest
import org.elasticsearch.action.admin.cluster.node.shutdown.NodesShutdownResponse
import org.elasticsearch.action.admin.cluster.node.stats.NodesStatsRequest
import org.elasticsearch.action.admin.cluster.node.stats.NodesStatsResponse
import org.elasticsearch.action.admin.cluster.repositories.delete.DeleteRepositoryRequest
import org.elasticsearch.action.admin.cluster.repositories.delete.DeleteRepositoryResponse
import org.elasticsearch.action.admin.cluster.repositories.get.GetRepositoriesRequest
import org.elasticsearch.action.admin.cluster.repositories.get.GetRepositoriesResponse
import org.elasticsearch.action.admin.cluster.repositories.put.PutRepositoryRequest
import org.elasticsearch.action.admin.cluster.repositories.put.PutRepositoryResponse
import org.elasticsearch.action.admin.cluster.reroute.ClusterRerouteRequest
import org.elasticsearch.action.admin.cluster.reroute.ClusterRerouteResponse
import org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsRequest
import org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsResponse
import org.elasticsearch.action.admin.cluster.shards.ClusterSearchShardsRequest
import org.elasticsearch.action.admin.cluster.shards.ClusterSearchShardsResponse
import org.elasticsearch.action.admin.cluster.snapshots.create.CreateSnapshotRequest
import org.elasticsearch.action.admin.cluster.snapshots.create.CreateSnapshotResponse
import org.elasticsearch.action.admin.cluster.snapshots.delete.DeleteSnapshotRequest
import org.elasticsearch.action.admin.cluster.snapshots.delete.DeleteSnapshotResponse
import org.elasticsearch.action.admin.cluster.snapshots.get.GetSnapshotsRequest
import org.elasticsearch.action.admin.cluster.snapshots.get.GetSnapshotsResponse
import org.elasticsearch.action.admin.cluster.snapshots.restore.RestoreSnapshotRequest
import org.elasticsearch.action.admin.cluster.snapshots.restore.RestoreSnapshotResponse
import org.elasticsearch.action.admin.cluster.snapshots.status.SnapshotsStatusRequest
import org.elasticsearch.action.admin.cluster.snapshots.status.SnapshotsStatusResponse
import org.elasticsearch.action.admin.cluster.state.ClusterStateRequest
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse
import org.elasticsearch.action.admin.cluster.stats.ClusterStatsRequest
import org.elasticsearch.action.admin.cluster.stats.ClusterStatsResponse
import org.elasticsearch.action.admin.cluster.tasks.PendingClusterTasksRequest
import org.elasticsearch.action.admin.cluster.tasks.PendingClusterTasksResponse
import org.elasticsearch.client.AdminClient
import org.elasticsearch.client.ClusterAdminClient
import org.elasticsearch.client.Requests

/**
 * {@code ClusterAdminClientExtensions} provides Groovy-friendly access to {@link ClusterAdminClient} features.
 * @see AdminClient#cluster()
 */
class ClusterAdminClientExtensions extends AbstractClientExtensions {

    // REQUEST/RESPONSE

    /**
     * Get the health of the cluster.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link ClusterHealthRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<ClusterHealthResponse> health(ClusterAdminClient self, Closure requestClosure) {
        doRequest(self, Requests.clusterHealthRequest(), requestClosure, self.&health)
    }

    /**
     * Get the state of the cluster.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link ClusterStateRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<ClusterStateResponse> state(ClusterAdminClient self, Closure requestClosure) {
        doRequest(self, Requests.clusterStateRequest(), requestClosure, self.&state)
    }

    /**
     * Update settings in the cluster.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link ClusterUpdateSettingsRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<ClusterUpdateSettingsResponse> updateSettings(ClusterAdminClient self,
                                                                                Closure requestClosure) {
        doRequest(self, Requests.clusterUpdateSettingsRequest(), requestClosure, self.&updateSettings)
    }

    /**
     * Reroute the allocation of shards in the cluster.
     * <p />
     * Note: This is an Advanced API and care should be taken before performing related operations.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link ClusterRerouteRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<ClusterRerouteResponse> reroute(ClusterAdminClient self, Closure requestClosure) {
        doRequest(self, Requests.clusterRerouteRequest(), requestClosure, self.&reroute)
    }

    /**
     * Get the cluster-wide aggregated stats.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link ClusterStatsRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<ClusterStatsResponse> clusterStats(ClusterAdminClient self, Closure requestClosure) {
        doRequest(self, Requests.clusterStatsRequest(), requestClosure, self.&clusterStats)
    }

    /**
     * Get the nodes info of the cluster.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link NodesInfoRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<NodesInfoResponse> nodesInfo(ClusterAdminClient self, Closure requestClosure) {
        doRequest(self, Requests.nodesInfoRequest(), requestClosure, self.&nodesInfo)
    }

    /**
     * Get the nodes stats of the cluster.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link NodesStatsRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<NodesStatsResponse> nodesStats(ClusterAdminClient self, Closure requestClosure) {
        doRequest(self, Requests.nodesStatsRequest(), requestClosure, self.&nodesStats)
    }

    /**
     * Get the hot threads details from nodes in the cluster.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link NodesHotThreadsRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<NodesHotThreadsResponse> nodesHotThreads(ClusterAdminClient self,
                                                                           Closure requestClosure) {
        doRequest(self, new NodesHotThreadsRequest(), requestClosure, self.&nodesHotThreads)
    }

    /**
     * Restart nodes in the cluster.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link NodesRestartRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<NodesRestartResponse> nodesRestart(ClusterAdminClient self, Closure requestClosure) {
        doRequest(self, Requests.nodesRestartRequest(), requestClosure, self.&nodesRestart)
    }

    /**
     * Shutdown nodes in the cluster.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link NodesShutdownRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<NodesShutdownResponse> nodesShutdown(ClusterAdminClient self,
                                                                       Closure requestClosure) {
        doRequest(self, Requests.nodesShutdownRequest(), requestClosure, self.&nodesShutdown)
    }

    /**
     * Determine the shards that the given search would be executed on within the cluster.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link ClusterSearchShardsRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<ClusterSearchShardsResponse> searchShards(ClusterAdminClient self,
                                                                            Closure requestClosure) {
        doRequest(self, Requests.clusterSearchShardsRequest(), requestClosure, self.&searchShards)
    }

    /**
     * Register a snapshot repository.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link PutRepositoryRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<PutRepositoryResponse> putRepository(ClusterAdminClient self,
                                                                       Closure requestClosure) {
        // closure is expected to set the repo name
        doRequest(self, Requests.putRepositoryRequest(null), requestClosure, self.&putRepository)
    }

    /**
     * Unregister a snapshot repository.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link DeleteRepositoryRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<DeleteRepositoryResponse> deleteRepository(ClusterAdminClient self,
                                                                             Closure requestClosure) {
        // closure is expected to set the repo name
        doRequest(self, Requests.deleteRepositoryRequest(null), requestClosure, self.&deleteRepository)
    }

    /**
     * Get snapshot repositories.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link GetRepositoriesRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<GetRepositoriesResponse> getRepositories(ClusterAdminClient self,
                                                                           Closure requestClosure) {
        doRequest(self, Requests.getRepositoryRequest(), requestClosure, self.&getRepositories)
    }

    /**
     * Create a new snapshot in a repository.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link CreateSnapshotRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<CreateSnapshotResponse> createSnapshot(ClusterAdminClient self,
                                                                         Closure requestClosure) {
        // closure is expected to set the repo and snapshot names
        doRequest(self, Requests.createSnapshotRequest(null, null), requestClosure, self.&createSnapshot)
    }

    /**
     * Get the snapshots status from a repository.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link SnapshotsStatusRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<SnapshotsStatusResponse> snapshotsStatus(ClusterAdminClient self,
                                                                           Closure requestClosure) {
        // closure is expected to set the repo name
        doRequest(self, Requests.snapshotsStatusRequest(null), requestClosure, self.&snapshotsStatus)
    }

    /**
     * Get snapshots from a repository.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link GetSnapshotsRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<GetSnapshotsResponse> getSnapshots(ClusterAdminClient self, Closure requestClosure) {
        // closure is expected to set the repo name
        doRequest(self, Requests.getSnapshotsRequest(null), requestClosure, self.&getSnapshots)
    }

    /**
     * Restore from a snapshot in a repository.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link RestoreSnapshotRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<RestoreSnapshotResponse> restoreSnapshot(ClusterAdminClient self,
                                                                           Closure requestClosure) {
        // closure is expected to set the repo and snapshot names
        doRequest(self, Requests.restoreSnapshotRequest(null, null), requestClosure, self.&restoreSnapshot)
    }

    /**
     * Delete snapshots from a repository.
     * <p />
     * Note: Deleting a snapshot should only be done after creating newer snapshots, which are themselves backed up, in
     * order to avoid data loss.
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link DeleteSnapshotRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<DeleteSnapshotResponse> deleteSnapshot(ClusterAdminClient self,
                                                                         Closure requestClosure) {
        // closure is expected to set the repo and snapshot names
        doRequest(self, Requests.deleteSnapshotRequest(null, null), requestClosure, self.&deleteSnapshot)
    }

    /**
     * Get a list of pending cluster tasks that are scheduled to be executed.
     * <p />
     * This includes operations that update the cluster state (e.g., a create index operation).
     *
     * @param self The {@code this} reference for the {@link ClusterAdminClient}.
     * @param requestClosure The map-like closure that configures the {@link PendingClusterTasksRequest}.
     * @return Never {@code null}.
     * @throws NullPointerException if any parameter is {@code null}
     */
    static ListenableActionFuture<PendingClusterTasksResponse> pendingClusterTasks(ClusterAdminClient self,
                                                                                   Closure requestClosure) {
        doRequest(self, new PendingClusterTasksRequest(), requestClosure, self.&pendingClusterTasks)
    }
}