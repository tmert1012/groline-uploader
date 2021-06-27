package com.isidorefarm.groline.uploader

import java.io.IOException
import java.io.FileReader
import com.opencsv.CSVReader
import java.io.File

class Reader(filename: String = "./data/GLM0000A.CSV") {

    // NOTE: file path based on /build/libs/, needs to be changed if jar is moved outside build/libs dir
    val PATH = "./"
    val FULL_PATH = PATH + filename

    var currentSection = Section.UNDEFINED

    fun read() {
        var reader: CSVReader? = null

        try {

            val file = File(FULL_PATH)
            println("reading: " + file.absolutePath)

            // CSV file into object
            reader = CSVReader(FileReader(file.absolutePath))

            // device info
            val deviceInfo = DeviceInfo(
                reader.readNext()[1],
                reader.readNext()[1],
                reader.readNext()[1],
                reader.readNext()[1]
            )

            println(deviceInfo)

            // skip blank line
            reader.readNext()

            // each line
            for (line: Array<String> in reader.iterator()) {

                // skip blank rows or subheaders
                if (line[0] == "\u0000" || line[0] == "##")
                    continue

                // identify and skip header if appropriate
                if (identifySectionByHeader(line))
                    continue

                // parse data lines of each type
                when (currentSection) {
                    Section.PH_CALIBRATION -> parsePhCalibration(line)
                    Section.EC_CALIBRATION -> parseEcCalibration(line)
                    Section.READING -> parseReading(line)
                }

            }
        } finally {
            if (reader != null) try { reader.close() } catch (e: IOException) {}
        }

    }

    private fun parsePhCalibration(line: Array<String>) {
        try {
            var i = 0
            println(PhCalibration(line[i++], line[i++], line[i++], line[i++], line[i++], line[i++], line[i++]))
        } catch (e: Exception) {
            println("Unable to parse Ph from '$line[0]'")
        }
    }

    private fun parseEcCalibration(line: Array<String>) {
        try {
            var i = 0
            println(EcCalibration(line[i++], line[i++], line[i++], line[i++], line[i++]))
        } catch (e: Exception) {
            println("Unable to parse Ec from '$line[0]'")
        }
    }

    private fun parseReading(line: Array<String>) {
        try {
            var i = 0
            println(
                Reading(
                    line[i++], line[i++],
                    Item(line[i++], line[i++], line[i++], line[i++]),
                    Item(line[i++], line[i++], line[i++], line[i++]),
                    Item(line[i++], line[i++], line[i++], line[i++])
                )
            )
        } catch (e: Exception) {
            println("Unable to parse Reading '$line[0]'")
        }
    }

    private fun identifySectionByHeader(line: Array<String>): Boolean {
        var isHeader = false

        when (line[0]) {
            "pH Calibration" -> {
                currentSection = Section.PH_CALIBRATION
                isHeader = true
            }
            "EC Calibration" -> {
                currentSection = Section.EC_CALIBRATION
                isHeader = true
            }
            "Date" -> {
                currentSection = Section.READING
                isHeader = true
            }
        }

        return isHeader
    }

    enum class Section {
        UNDEFINED,
        PH_CALIBRATION,
        EC_CALIBRATION,
        READING,
    }

    data class DeviceInfo(
        val model: String,
        val serial: String,
        val instrumentId: String,
        val firmware: String
    )

    data class PhCalibration(
        val id: String,
        val date: String,
        val time: String,
        val offset: String,
        val slope: String,
        val buffer1: String,
        val buffer2: String
    )

    data class EcCalibration(
        val id: String,
        val date: String,
        val time: String,
        val cellCoefficient: String,
        val standard: String
    )

    data class Reading(
        val date: String,
        val time: String,
        val ph: Item,
        val ec: Item,
        val temp: Item
    )

    data class Item(
        val min: String,
        val max: String,
        val avg: String,
        val unit: String
    )


}