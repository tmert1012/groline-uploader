package groline.uploader.app.entities

import com.opencsv.CSVReader

data class DeviceInfo constructor(
    val model: String,
    val serial: String,
    val instrumentId: String,
    val firmware: String
) {
    companion object {

        /**
         * Create a [DeviceInfo] object from a [CSVReader].
         * Note: This will consume the first 4 lines of the CSVReader
         */
        fun create(reader: CSVReader): DeviceInfo {
            try {
                return DeviceInfo(
                    model = reader.readNext()[1],
                    serial = reader.readNext()[1],
                    instrumentId = reader.readNext()[1],
                    firmware = reader.readNext()[1]
                )
            } catch (e: Exception) {
                println("Unable to read deviceInfo.")
                throw e
            }
        }
    }
}