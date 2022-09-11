package kurbanek.proxy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Table
public record Number(
    @Column @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long ordinalId,
    @Column Long value
) {}