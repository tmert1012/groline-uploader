package groline.uploader.app.entities

data class Reading constructor(
    val date: String,
    val time: String,
    val ph: Item,
    val ec: Item,
    val temp: Item
) {
    data class Item(
        val min: String,
        val max: String,
        val avg: String,
        val unit: String
    )

    companion object {
        fun create(line: Array<String>): Reading {
            try {
                var i = 0
                return Reading(
                    date = line[i++],
                    time = line[i++],
                    ph = Item(
                        min = line[i++],
                        max = line[i++],
                        avg = line[i++],
                        unit = line[i++]
                    ),
                    ec = Item(
                        min = line[i++],
                        max = line[i++],
                        avg = line[i++],
                        unit = line[i++]
                    ),
                    temp = Item(
                        min = line[i++],
                        max = line[i++],
                        avg = line[i++],
                        unit = line[i]
                    )
                )
            } catch (e: Exception) {
                println("Unable to parse Reading ${line[0]}")
                throw e
            }
        }
    }
}



