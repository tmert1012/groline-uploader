package groline.uploader.app.entities

class Section {

    var currentSectionId = ID.UNDEFINED

    /**
     * Returns true if the line is a header
     * Sets the [currentSectionId]
     */
    fun isHeader(line: Array<String>): Boolean {
        when (line[0]) {
            "pH Calibration" -> {
                currentSectionId = ID.PH_CALIBRATION
                return true
            }
            "EC Calibration" -> {
                currentSectionId = ID.EC_CALIBRATION
                return true
            }
            "Date" -> {
                currentSectionId = ID.READING
                return true
            }
            else -> {
                return false
            }
        }
    }

    enum class ID {
        UNDEFINED,
        PH_CALIBRATION,
        EC_CALIBRATION,
        READING
    }
}

