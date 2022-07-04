package br.com.calculosinvestimentos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class CalculosDeInvestimentosApplication

fun main(args: Array<String>) {
    runApplication<CalculosDeInvestimentosApplication>(*args)
}
