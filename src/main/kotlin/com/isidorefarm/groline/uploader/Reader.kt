package com.isidorefarm.groline.uploader

import java.io.IOException
import java.io.FileReader
import com.opencsv.CSVReader
import com.isidorefarm.groline.uploader.entities.Reading
import java.io.File
import java.util.ArrayList


class Reader(filename: String = "./data/GLM0000A.CSV") {

    // NOTE: file path based on /build/libs/, needs to be changed if jar is moved outside build/libs dir
    val PATH = "./"
    val FULL_PATH = PATH + filename


    fun read(): ArrayList<Reading> {
        return getReadings()
    }

    private fun getReadings(): ArrayList<Reading> {
        var reader: CSVReader? = null
        val records = ArrayList<Reading>()

        try {

            val file = File(FULL_PATH)
            println("reading: " + file.absolutePath)

            // CSV file into object
            reader = CSVReader(FileReader(file.absolutePath))

            // skip header row
            reader.readNext()

            // each line
            var lineNum = 2
            for (line: Array<String> in reader.iterator()) {

                // skip empty reports
                if (line[0].isNullOrBlank()) {
                    println("no data in file: $FULL_PATH, skipping.")
                    continue
                }

                println(line[0])

                lineNum++
            }

        } finally {
            if (reader != null) try { reader.close() } catch (e: IOException) {}
        }

        return records
    }


}