package br.com.calculosinvestimentos.controller

import br.com.calculosinvestimentos.dto.SelicDTO
import br.com.calculosinvestimentos.service.SelicService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/selic")
class SelicController {

    @Autowired
    lateinit var selicService: SelicService

    @GetMapping("/dadosDiarios")
    fun selicDadosDiarios(@RequestBody(required = false) selicDTO: SelicDTO?): ResponseEntity<Any> {
        val selicDTODadosDiarios = selicService.dadosDiariosSelic(selicDTO)
        return ResponseEntity.ok(selicDTODadosDiarios)
    }
}