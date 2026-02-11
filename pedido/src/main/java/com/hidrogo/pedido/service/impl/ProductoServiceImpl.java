package com.hidrogo.pedido.service.impl;

import com.hidrogo.pedido.entity.Producto;
import com.hidrogo.pedido.repository.ProductoRepository;
import com.hidrogo.pedido.service.ProductoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<Producto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Producto> search(String texto, Pageable pageable) {
        if (texto == null || texto.isBlank()) {
            return repository.findAll(pageable);
        }
        return repository.findByNombreContainingIgnoreCase(texto, pageable);
    }

    @Override
    public Producto findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Producto create(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public Producto update(Long id, Producto producto) {
        Producto existente=findById(id);
        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setCodigo(producto.getCodigo());
        existente.setPrecioUnitario(producto.getPrecioUnitario());
        return repository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }
}