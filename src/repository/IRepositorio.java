package repository;

import java.util.List;

public interface IRepositorio <T> {
    T guardar(T entidad);

    T buscarPorId(Long id);

    boolean eliminarPorId(Long id);

    T actualizar(T entidad);

    List<T> obtenerTodos();
}
