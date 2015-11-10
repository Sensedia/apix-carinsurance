package com.sensedia.apix.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.sensedia.apix.server.model.SeguradoBean;
import com.sensedia.apix.server.service.SeguradoService;
import com.sensedia.apix.server.util.ConverterUtil;
import com.sensedia.apix.server.vo.Segurado;

/**
 * WS Rest para manutenção de segurados.
 * 
 * @author Luiz César Cherri
 *
 */
@Api(name = "seguradoendpoint")
public class SeguradoEndpoint {

	SeguradoService seguradoService = new SeguradoService();

	@ApiMethod(name = "listSegurado", httpMethod = "GET")
	public List<Segurado> listSegurado(
			@Nullable @Named("_offset") Integer offset,
			@Nullable @Named("_limit") Integer limit) {

		List<SeguradoBean> seguradosBean = seguradoService.listSegurado(offset,
				limit);
		List<Segurado> seguradosVO = new ArrayList<Segurado>();

		for (SeguradoBean seguradoBean : seguradosBean) {
			seguradosVO.add(ConverterUtil.getInstance().toSeguradoVO(
					seguradoBean));
		}

		return seguradosVO;

	}

	@ApiMethod(name = "getSegurado", httpMethod = "GET")
	public Segurado getSegurado(@Named("id") Long id) {

		SeguradoBean seguradoBean = seguradoService.getSegurado(id);

		return ConverterUtil.getInstance().toSeguradoVO(seguradoBean);

	}

	@ApiMethod(name = "insertSegurado", httpMethod = "POST")
	public Segurado insertSegurado(Segurado segurado) {

		SeguradoBean seguradoBean = ConverterUtil.getInstance().toSeguradoBean(
				segurado);

		return ConverterUtil.getInstance().toSeguradoVO(
				seguradoService.insertSegurado(seguradoBean));

	}

	@ApiMethod(name = "updateSegurado", httpMethod = "PUT")
	public Segurado updateSegurado(Segurado segurado) {

		SeguradoBean seguradoBean = ConverterUtil.getInstance().toSeguradoBean(
				segurado);

		return ConverterUtil.getInstance().toSeguradoVO(
				seguradoService.updateSegurado(seguradoBean));

	}

	@ApiMethod(name = "removeSegurado", httpMethod = "DELETE")
	public void removeSegurado(@Named("id") Long id) {

		seguradoService.removeSegurado(id);

	}

}
