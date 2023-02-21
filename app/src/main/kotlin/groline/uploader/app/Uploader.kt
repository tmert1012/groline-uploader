package groline.uploader.app

class Uploader {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            // load all readings from file
            val reader = Reader.create("././data/GLM0000A.CSV")
            println(reader.toString())

            println("upload complete!")
        }
    }

}