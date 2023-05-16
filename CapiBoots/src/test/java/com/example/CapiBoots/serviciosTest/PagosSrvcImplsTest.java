//package com.example.CapiBoots.serviciosTest;
//
//import com.example.CapiBoots.modelos.Pagos;
//import com.example.CapiBoots.repositorios.PagosRepositorio;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PagosSrvcImplsTest implements ifxPagosSrvcTest {
//
//    @Autowired
//    public PagosRepositorio pagorepo;
//
//
//    @Override
//    public Optional<Pagos> buscaId(Long id) {
//        return pagorepo.findById(id);
//    }
//    @Override
//    public List<Pagos> ListaPagos() {
//        return pagorepo.findAll();
//    }
//    @Override
//    public Pagos guardar(Pagos pago) {
//        return pagorepo.save(pago);
//    }
//    public void borrar(Long id) {
//        pagorepo.deleteById(id);
//    }
//}
//
