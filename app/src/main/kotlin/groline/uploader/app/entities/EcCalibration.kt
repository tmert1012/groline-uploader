package groline.uploader.app.entities

data class EcCalibration constructor(
    val id: String,
    val date: String,
    val time: String,
    val cellCoefficient: String,
    val standard: String
) {
    companion object {
        fun create(line: Array<String>): EcCalibration {
            try {
                var i = 0
                return EcCalibration(
                    id = line[i++],
                    date = line[i++],
                    time = line[i++],
                    cellCoefficient = line[i++],
                    standard = line[i]
                )
            } catch (e: Exception) {
                println("Unable to parse Ec from ${line[0]}")
                throw e
            }
        }
    }
}