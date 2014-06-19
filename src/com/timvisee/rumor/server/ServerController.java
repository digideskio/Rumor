package com.timvisee.rumor.server;

import com.timvisee.rumor.Core;
import com.timvisee.rumor.Defaults;
import com.timvisee.rumor.Profiler;
import com.timvisee.rumor.server.client.ClientAccepter;
import com.timvisee.rumor.server.client.ClientManager;

public class ServerController {

    private ClientManager conMan;
    private ClientAccepter clientAccepter;

    /**
     * Constructor
     */
    public ServerController() {
        // Profile the server start
        Profiler serverProf = new Profiler(true);

        // Starting server, show a status message
        CoreServer.getLogger().info("Starting " + Defaults.APP_NAME + " server...");

        // Set up the client manager
        this.conMan = new ClientManager();

        // Set up the client accepter and start accepting clients
        this.clientAccepter = new ClientAccepter(this.conMan);
        this.clientAccepter.start();

        // Stop the server start profiler
        serverProf.stop();

        // Server started, show a status message
        CoreServer.getLogger().info(Defaults.APP_NAME + " server started, took " + serverProf.getDurationString() + "!");
    }

    /**
     * Get the client manager instance
     * @return Connection manager instance
     */
    public ClientManager getConnectionManager() {
        return this.conMan;
    }

    /**
     * Get the client accepter instance
     * @return Client accepter instance
     */
    public ClientAccepter getClientAccepter() {
        return this.clientAccepter;
    }
}
