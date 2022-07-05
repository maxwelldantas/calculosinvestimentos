package br.com.calculosinvestimentos.client

import br.com.calculosinvestimentos.dto.SelicDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "selic", url = "\${api.rest.selic}")
interface SelicDadosDiariosClient {

    @PostMapping(
        headers = ["Accept: application/json, text/plain, */*", "Content-Type: application/json"],
        produces = ["application/json"]
    )
    fun obterTaxaDiarias(@RequestBody selicDTO: SelicDTO?, @RequestParam("page") page: String): Map<String, Any>
}