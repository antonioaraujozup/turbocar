package br.com.zup.edu.turbocar.carro;

import javax.persistence.*;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_CARRO_PLACA", columnNames = {"placa"}),
        @UniqueConstraint(name = "UK_CARRO_CHASSI", columnNames = {"chassi"})
})
@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false, length = 7)
    private String placa;

    @Column(nullable = false, length = 17)
    private String chassi;

    public Carro(String marca, String modelo, Integer ano, String placa, String chassi) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.chassi = chassi;
    }

    /**
     * @deprecated Construtor para uso exclusivo do Hibernate.
     */
    @Deprecated
    public Carro() {
    }

    public Long getId() {
        return id;
    }
}
