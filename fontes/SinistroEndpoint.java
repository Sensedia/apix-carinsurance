package com.sensedia.apix.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.sensedia.apix.server.model.SinistroBean;
import com.sensedia.apix.server.service.SinistroService;
import com.sensedia.apix.server.util.ConverterUtil;
import com.sensedia.apix.server.vo.Sinistro;

/**
 * WS Rest para manutenção de sinistros.
 * 
 * @author Sensedia
 *
 */
@Api(name = "carinsuranceendpoint", version = "v1")
public class SinistroEndpoint {

	SinistroService sinistroService = new SinistroService();

	@ApiMethod(name = "listSinistro", httpMethod = "GET", path = "sinistros")
	public List<Sinistro> listSinistro(
			@Nullable @Named("_offset") Integer offset,
			@Nullable @Named("_limit") Integer limit) {

		List<SinistroBean> sinistrosBean = sinistroService.listSinistro(offset,
				limit);
		List<Sinistro> sinistrosVO = new ArrayList<Sinistro>();

		for (SinistroBean sinistroBean : sinistrosBean) {
			sinistrosVO.add(ConverterUtil.getInstance().toSinistroVO(
					sinistroBean));
		}

		return sinistrosVO;

	}

	@ApiMethod(name = "getSinistro", httpMethod = "GET", path = "sinistros/{id}")
	public Sinistro getSinistro(@Named("id") Long id) {

		SinistroBean sinistroBean = sinistroService.getSinistro(id);

		return ConverterUtil.getInstance().toSinistroVO(sinistroBean);
	}

	@ApiMethod(name = "insertSinistro", httpMethod = "POST", path = "sinistros")
	public Sinistro insertSinistro(Sinistro sinistro) {

		SinistroBean sinistroBean = ConverterUtil.getInstance().toSinistroBean(
				sinistro);

		return ConverterUtil.getInstance().toSinistroVO(
				sinistroService.insertSinistro(sinistroBean));
		
	}

	@ApiMethod(name = "updateSinistro", httpMethod = "PUT", path = "sinistros/{id}")
	public Sinistro updateSinistro(@Named("id") Long id, Sinistro sinistro) {

		sinistro.setId(id);
		
		SinistroBean sinistroBean = ConverterUtil.getInstance().toSinistroBean(
				sinistro);

		return ConverterUtil.getInstance().toSinistroVO(
				sinistroService.updateSinistro(sinistroBean));

	}

	@ApiMethod(name = "removeSinistro", httpMethod = "DELETE", path = "sinistros/{id}")
	public void removeSinistro(@Named("id") Long id) {
		sinistroService.removeSinistro(id);
	}
}
