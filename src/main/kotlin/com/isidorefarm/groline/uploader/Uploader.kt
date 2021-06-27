package com.isidorefarm.groline.uploader


class Uploader {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            // load all readings from file
            Reader().read()

            println("upload complete!")
        }

    }

}