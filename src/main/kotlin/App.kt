import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.int
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.event.ConnectionClosedEvent
import com.mongodb.event.ConnectionCreatedEvent
import com.mongodb.event.ConnectionPoolListener

class App : CliktCommand() {
    private val count: Int by option(help = "Query count").int().default(3)
    private val uri by option(help = "MongoDB connection string").required()

    override fun run() {
        val client = MongoClients.create(
            MongoClientSettings.builder()
                .applicationName("Alight test application")
                .applyConnectionString(ConnectionString(uri))
                .applyToConnectionPoolSettings {
                    it.addConnectionPoolListener(object : ConnectionPoolListener {
                        override fun connectionCreated(event: ConnectionCreatedEvent) {
                            echo("connection to ${event.connectionId.serverId.address} created")
                        }

                        override fun connectionClosed(event: ConnectionClosedEvent) {
                            echo("connection to ${event.connectionId.serverId.address} closed")
                        }
                    })
                }
                .build()
        )

        repeat(count) {
            val count = client
                .getDatabase("test")
                .getCollection("people")
                .countDocuments()
            echo("'db.test.people' count = $count")
            Thread.sleep(1000)
        }

        client.close()

    }
}

fun main(args: Array<String>) = App().main(args)