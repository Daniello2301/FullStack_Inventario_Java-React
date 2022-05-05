package co.iudigital.backend_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.iudigital.backend_inventario.model.EstadoEquipo;

@Repository
public interface IEstadoRepository extends JpaRepository<EstadoEquipo, Long> {
}
