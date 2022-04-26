package br.com.zup.edu.turbocar.carro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastrarNovoCarroController {

    private final CarroRepository repository;

    public CadastrarNovoCarroController(CarroRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/carros")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoCarroRequest request, UriComponentsBuilder uriComponentsBuilder) {

        if (repository.existsByPlaca(request.getPlaca())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe um carro cadastrado com a placa informada");
        }

        if (repository.existsByChassi(request.getChassi())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe um carro cadastrado com o chassi informado");
        }

        Carro carro = request.paraCarro();

        repository.save(carro);

        URI location = uriComponentsBuilder.path("/carros/{id}")
                .buildAndExpand(carro.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
