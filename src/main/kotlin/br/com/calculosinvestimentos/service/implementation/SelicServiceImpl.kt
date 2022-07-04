package br.com.calculosinvestimentos.service.implementation

import br.com.calculosinvestimentos.client.SelicDadosDiariosClient
import br.com.calculosinvestimentos.dto.SelicDTO
import br.com.calculosinvestimentos.service.SelicService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class SelicServiceImpl : SelicService {

    @Autowired
    lateinit var selicDadosDiariosClient: SelicDadosDiariosClient

    override fun dadosDiariosSelic(selicDTO: SelicDTO?): Any {
        if (selicDTO == null) {
            val localDate: LocalDate = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val data: String = formatter.format(localDate)
            val newSelicDTO = SelicDTO(data, data)
            return selicDadosDiariosClient.obterTaxaDiarias(newSelicDTO)
        }

        return selicDadosDiariosClient.obterTaxaDiarias(selicDTO)
    }
}