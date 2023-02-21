package groline.uploader.app

import groline.uploader.app.entities.*
import java.io.IOException
import java.io.FileReader
import com.opencsv.CSVReader
import java.io.File

data class Reader constructor(
    val deviceInfo: DeviceInfo,
    val phCalibrations: List<PhCalibration>,
    val ecCalibrations: List<EcCalibration>,
    val readings: List<Reading>
) {

    companion object {

        /**
         * NOTE: file path based on /build/libs/, needs to be changed if jar is moved outside build/libs dir
         */
        fun create(filename: String): Reader {
            var reader: CSVReader? = null

            val deviceInfo: DeviceInfo
            val phCalibrations = mutableListOf<PhCalibration>()
            val ecCalibrations = mutableListOf<EcCalibration>()
            val readings = mutableListOf<Reading>()

            try {

                val file = File(filename)
                println("reading: " + file.absolutePath)

                // CSV file into object
                reader = CSVReader(FileReader(file.absolutePath))

                // device info
                deviceInfo = DeviceInfo.create(reader)

                // skip blank line
                reader.readNext()

                // maintain state for current section
                val section = Section()

                // each line
                for (line: Array<String> in reader.iterator()) {

                    // skip blank rows or subheaders
                    if (line[0].contains("\u0000") || line[0] == "##")
                        continue

                    // inspect line, see if section has changed, skip if header line
                    if (section.isHeader(line))
                        continue

                    // parse data lines of each type
                    when (section.currentSectionId) {
                        Section.ID.PH_CALIBRATION -> phCalibrations.add(PhCalibration.create(line))
                        Section.ID.EC_CALIBRATION -> ecCalibrations.add(EcCalibration.create(line))
                        Section.ID.READING -> readings.add(Reading.create(line))
                        // do nothing otherwise
                        else -> println("no data on line, skipping")
                    }
                }

            } finally {
                if (reader != null) try { reader.close() } catch (_: IOException) {}
            }

            return Reader(
                deviceInfo = deviceInfo,
                phCalibrations = phCalibrations.toList(),
                ecCalibrations = ecCalibrations.toList(),
                readings = readings.toList()
            )
        }
    }

}