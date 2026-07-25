package com.pas.xcontrolwms.wms.service;

import com.pas.xcontrolwms.wms.client.XControlClient;
import com.pas.xcontrolwms.wms.enums.TipoMovimiento;
import com.pas.xcontrolwms.wms.model.ExplosiveBox;
import com.pas.xcontrolwms.wms.model.ExplosiveBoxFolio;
import com.pas.xcontrolwms.wms.model.ExplosiveProduct;
import com.pas.xcontrolwms.wms.model.ExplosiveRegistry;
import com.pas.xcontrolwms.wms.repository.ExplosiveRegistryRepository;
import com.pas.xcontrolwms.xcontrolmapping.XControlCajaDTO;
import com.pas.xcontrolwms.xcontrolmapping.XControlProductoDTO;
import com.pas.xcontrolwms.xcontrolmapping.XControlRequest;
import com.pas.xcontrolwms.xcontrolmapping.devoluciones.XControlDevolucionDTO;
import com.pas.xcontrolwms.xcontrolmapping.devoluciones.XControlDevolucionResponse;
import com.pas.xcontrolwms.xcontrolmapping.entradas.XControlEntradaDTO;
import com.pas.xcontrolwms.xcontrolmapping.entradas.XControlEntradasResponse;
import com.pas.xcontrolwms.xcontrolmapping.salidas.XControlSalidaDTO;
import com.pas.xcontrolwms.xcontrolmapping.salidas.XControlSalidasResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@Slf4j
public class ExplosiveRegistryService {

    private final XControlClient XControlClient;
    private final ExplosiveRegistryRepository explosiveRegistryRepository;

    public ExplosiveRegistryService(XControlClient xControlClient,
                                    ExplosiveRegistryRepository explosiveRegistryRepository) {
        this.XControlClient = xControlClient;
        this.explosiveRegistryRepository = explosiveRegistryRepository;
    }

    public void persistOut(XControlSalidasResponse xcontrolSalidasResponse) {
        for (XControlSalidaDTO salidaDTO : xcontrolSalidasResponse.getData()) {
            if (explosiveRegistryRepository.existsByXcontrolRegistryId(salidaDTO.getId())) {
                return;
            }
            ExplosiveRegistry explosiveRegistry = new ExplosiveRegistry();
            explosiveRegistry.setTipo(TipoMovimiento.SALIDA);
            explosiveRegistry.setXcontrolRegistryId(salidaDTO.getId());
            explosiveRegistry.setCompania(salidaDTO.getCompania());
            explosiveRegistry.setLugar(salidaDTO.getLugar());
            explosiveRegistry.setRepartidor(salidaDTO.getRepartidor());
            explosiveRegistry.setCargador(salidaDTO.getCargador());
            explosiveRegistry.setFecha(OffsetDateTime.now());
            persistProducts(explosiveRegistry, salidaDTO.getProductos());
        }

    }

    public void persistDevoluciones(XControlDevolucionResponse xcontrolDevolucionResponse) {
        for (XControlDevolucionDTO devolucionDTO : xcontrolDevolucionResponse.getData()) {
            if (explosiveRegistryRepository.existsByXcontrolRegistryId(devolucionDTO.getId())) {
                return;
            }
            ExplosiveRegistry explosiveRegistry = new ExplosiveRegistry();
            explosiveRegistry.setTipo(TipoMovimiento.DEVOLUCION);
            explosiveRegistry.setXcontrolRegistryId(devolucionDTO.getId());
            explosiveRegistry.setCompania(devolucionDTO.getCompania());
            explosiveRegistry.setLugar(devolucionDTO.getLugar());
            explosiveRegistry.setRepartidor(devolucionDTO.getRepartidor());
            explosiveRegistry.setCargador(devolucionDTO.getCargador());
            explosiveRegistry.setMotivo(devolucionDTO.getMotivo());
            explosiveRegistry.setTurno(devolucionDTO.getTurno());
            explosiveRegistry.setTipoVale(devolucionDTO.getTipo());
            explosiveRegistry.setFecha(OffsetDateTime.now());
            persistProducts(explosiveRegistry, devolucionDTO.getProductos());

        }
    }

    private void persistProducts(ExplosiveRegistry explosiveRegistry, List<XControlProductoDTO> xControlProductoDTOList) {
        for (XControlProductoDTO productoDTO : xControlProductoDTOList) {

            ExplosiveProduct producto = new ExplosiveProduct();
            producto.setExplosiveRegistry(explosiveRegistry);
            producto.setProductoId(productoDTO.getId());
            producto.setNombre(productoDTO.getNombre());
            producto.setSegmento(productoDTO.getSegmento());
            producto.setCantidad(productoDTO.getCantidad());

            if (productoDTO.getCajas() != null) {

                for (XControlCajaDTO cajaDTO : productoDTO.getCajas()) {

                    ExplosiveBox caja = new ExplosiveBox();
                    caja.setExplosiveProduct(producto);
                    caja.setCodigo(cajaDTO.getCodigo());

                    if (cajaDTO.getFolios() != null) {

                        for (String folioStr : cajaDTO.getFolios()) {

                            ExplosiveBoxFolio folio = new ExplosiveBoxFolio();
                            folio.setBox(caja);
                            folio.setFolio(folioStr);

                            caja.getFolios().add(folio);
                        }
                    }

                    producto.getBoxes().add(caja);
                }
            }

            explosiveRegistry.getProducts().add(producto);
        }

        explosiveRegistryRepository.save(explosiveRegistry);

    }

    public void persistEntradas(XControlEntradasResponse xcontrolEntradasResponse) {
        for (XControlEntradaDTO entradaDTO : xcontrolEntradasResponse.getData()) {
            if (explosiveRegistryRepository.existsByXcontrolRegistryId(entradaDTO.getId())) {
                return;
            }
            ExplosiveRegistry explosiveRegistry = new ExplosiveRegistry();
            explosiveRegistry.setTipo(TipoMovimiento.ENTRADA);
            explosiveRegistry.setXcontrolRegistryId(entradaDTO.getId());
            explosiveRegistry.setEmbarqueId(entradaDTO.getEmbarqueId());
            explosiveRegistry.setObservaciones(entradaDTO.getObservaciones());
            LocalDate fecha = LocalDate.parse("2026-07-15");

            OffsetDateTime offsetDateTime = fecha.atStartOfDay().atOffset(ZoneOffset.ofHours(-6));
            explosiveRegistry.setFecha(offsetDateTime);
            persistProducts(explosiveRegistry, entradaDTO.getProductos());

        }
    }

    public XControlSalidasResponse getSalidas(XControlRequest xControlRequest) {
        try {

            XControlSalidasResponse xcontrolSalidasResponse = XControlClient.salidasXControl(xControlRequest);
            persistOut(xcontrolSalidasResponse);
            return xcontrolSalidasResponse;
        } catch (Exception e) {
            log.error("error uploadDocument");
            throw new RuntimeException(e);
        }
    }

    public XControlDevolucionResponse getDevoluciones(XControlRequest xControlRequest) {
        try {

            XControlDevolucionResponse xcontrolDevolucionResponse = XControlClient.devolucionXControl(xControlRequest);
            persistDevoluciones(xcontrolDevolucionResponse);
            return xcontrolDevolucionResponse;
        } catch (Exception e) {
            log.error("error uploadDocument");
            throw new RuntimeException(e);
        }
    }

    //URL DE COMPRAS
    public XControlEntradasResponse getEntradas(XControlRequest xControlRequest) {
        try {

            XControlEntradasResponse xcontrolEntradasResponse = XControlClient.entradasXControl(xControlRequest);
            persistEntradas(xcontrolEntradasResponse);
            return xcontrolEntradasResponse;
        } catch (Exception e) {
            log.error("error uploadDocument");
            throw new RuntimeException(e);
        }
    }

}
