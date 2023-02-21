package groline.uploader.app.entities

data class PhCalibration constructor(
    val id: String,
    val date: String,
    val time: String,
    val offset: String,
    val slope: String,
    val buffer1: String,
    val buffer2: String
) {
    companion object {
        fun create(line: Array<String>): PhCalibration {
            try {
                var i = 0
                return PhCalibration(
                    id = line[i++],
                    date = line[i++],
                    time = line[i++],
                    offset = line[i++],
                    slope = line[i++],
                    buffer1 = line[i++],
                    buffer2 = line[i]
                )
            } catch (e: Exception) {
                println("Unable to parse Ph from ${line[0]}")
                throw e
            }
        }
    }
}
