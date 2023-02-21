package groline.uploader.app

import groline.uploader.app.entities.DeviceInfo
import groline.uploader.app.entities.EcCalibration
import groline.uploader.app.entities.PhCalibration
import groline.uploader.app.entities.Reading
import groline.uploader.app.entities.Reading.Item
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ReaderTest {

    @Test
    fun `reader loads sample file GLM0000A correctly`() {
        val reader = Reader.create("../data/GLM0000A.CSV")
        println(reader.toString())

        assertEquals(
            Reader(
                deviceInfo = getDeviceInfo(),
                phCalibrations = getPhCalibrations(),
                ecCalibrations = getEcCalibrations(),
                readings = getReadings()
            ),
            reader
        )
    }

    private fun getDeviceInfo(): DeviceInfo {
        return DeviceInfo(
            model = "HI981420",
            serial = "GA03480201",
            instrumentId = "0000",
            firmware = "1.02"
        )
    }

    private fun getPhCalibrations(): List<PhCalibration> {
        return listOf(
            PhCalibration(
                id = "1",
                date = "2021/04/15",
                time = "19:56:10",
                offset = "-4.6mV",
                slope = "91.8%",
                buffer1 = "4.01pH",
                buffer2 = "7.01pH"
            ),
            PhCalibration(
                id = "2",
                date = "---",
                time = "---",
                offset = "---",
                slope = "---",
                buffer1 = "---",
                buffer2 = "---"
            ),
            PhCalibration(
                id = "3",
                date = "---",
                time = "---",
                offset = "---",
                slope = "---",
                buffer1 = "---",
                buffer2 = "---"
            ),
            PhCalibration(
                id = "4",
                date = "---",
                time = "---",
                offset = "---",
                slope = "---",
                buffer1 = "---",
                buffer2 = "---"
            ), PhCalibration(
                id = "5",
                date = "---",
                time = "---",
                offset = "---",
                slope = "---",
                buffer1 = "---",
                buffer2 = "---"
            )
        )
    }

    private fun getEcCalibrations(): List<EcCalibration> {
        return listOf(
            EcCalibration(
                id = "1",
                date = "2021/04/15",
                time = "19:56:48",
                cellCoefficient = "90.2%",
                standard = "5.00mS/cm"
            ),
            EcCalibration(
                id = "2",
                date = "---",
                time = "---",
                cellCoefficient = "---",
                standard = "---"
            ),
            EcCalibration(
                id = "3",
                date = "---",
                time = "---",
                cellCoefficient = "---",
                standard = "---"
            ),
            EcCalibration(
                id = "4",
                date = "---",
                time = "---",
                cellCoefficient = "---",
                standard = "---"
            ),
            EcCalibration(
                id = "5",
                date = "---",
                time = "---",
                cellCoefficient = "---",
                standard = "---"
            ),
        )
    }

    private fun getReadings(): List<Reading> {
        return listOf(
            Reading(
                date = "2021/05/30",
                time = "12:36:00",
                ph = Item(min = "3.20", max = "pH", avg = "3.31", unit = "pH"),
                ec = Item(min = "3.27", max = "pH", avg="0.09", unit= "mS/cm"),
                temp = Item(min = "0.12", max = "mS/cm", avg="0.09", unit = "mS/cm")
            ),
            Reading(
                date = "2021/05/30",
                time = "12:51:00",
                ph = Item(min = "3.31", max = "pH", avg = "3.35", unit = "pH"),
                ec = Item(min = "3.32", max = "pH", avg = "0.10", unit = "mS/cm"),
                temp = Item(min = "0.13", max = "mS/cm", avg = "0.11", unit = "mS/cm")
            ),
            Reading(
                date = "2021/05/30",
                time = "13:06:00",
                ph = Item(min="3.33", max = "pH", avg = "3.34", unit = "pH"),
                ec = Item(min="3.33", max = "pH", avg = "0.11", unit = "mS/cm"),
                temp = Item(min="0.14", max = "mS/cm", avg = "0.12", unit = "mS/cm")
            )
        )
    }

}