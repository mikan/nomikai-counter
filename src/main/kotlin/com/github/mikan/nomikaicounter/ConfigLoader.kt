package com.github.mikan.nomikaicounter

import com.github.mikan.nomikaicounter.web.ControllerUtil
import com.mongodb.Mongo
import com.mongodb.MongoClient
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.util.*
import java.util.logging.Logger

/**
 * DB and other configurations with local properties file.
 *
 * @author mikan
 * @since 2.0
 */
@Configuration
open class ConfigLoader : AbstractMongoConfiguration() {

    private final val DEFAULT_DATABASE = "nomikai"
    private final val DEFAULT_HOST = "localhost"
    private final val DEFAULT_PORT = 27017
    private final val database: String
    private final val host: String
    private final val port: Int

    init {
        val log = Logger.getLogger(ConfigLoader::class.qualifiedName)
        val dir = File(System.getProperty("user.home") + File.separator + ".nc")
        if (dir.mkdirs()) {
            log.info("Directory created: " + dir.absolutePath)
        }
        val file = File(dir.absolutePath + File.separator + "nc.properties")
        if (file.createNewFile()) {
            log.info("Properties created: " + file.absolutePath)
        }
        val props = Properties()
        try {
            props.load(FileReader(file))

            // MongoDB parameters
            database = props.getProperty("spring.data.mongodb.database", DEFAULT_DATABASE)
            host = props.getProperty("spring.data.mongodb.host", DEFAULT_HOST)
            val portValue = props.getProperty("spring.data.mongodb.port", Integer.toString(DEFAULT_PORT))
            try {
                port = Integer.parseInt(portValue)
            } catch (e: NumberFormatException) {
                log.severe("Invalid port number: " + portValue)
                port = DEFAULT_PORT
            }

            // View parameters
            ControllerUtil.copyright = props.getProperty("copyright.name", ControllerUtil.copyright)
            ControllerUtil.copyrightUrl = props.getProperty("copyright.url", ControllerUtil.copyrightUrl)
            ControllerUtil.copyrightYear = props.getProperty("copyright.year", ControllerUtil.copyrightYear)
        } catch (e: IOException) {
            log.warning("Failed to load nc.properties. Using default configuration.")
            database = DEFAULT_DATABASE
            host = DEFAULT_HOST
            port = DEFAULT_PORT
        }
    }

    override fun getDatabaseName(): String? {
        return database
    }

    override fun mongo(): Mongo? {
        return MongoClient(host, port)
    }
}