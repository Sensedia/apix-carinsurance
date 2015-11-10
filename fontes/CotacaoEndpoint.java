package com.sensedia.apix.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.sensedia.apix.server.model.CotacaoBean;
import com.sensedia.apix.server.model.SeguradoBean;
import com.sensedia.apix.server.service.CotacaoService;
import com.sensedia.apix.server.util.ConverterUtil;
import com.sensedia.apix.server.vo.Cotacao;
import com.sensedia.apix.server.vo.Segurado;

/**
 * WS Rest para manutenção de cotações.
 * 
 * @author Luiz César Cherri
 *
 */
@Api(name = "carinsuranceendpoint", version = "v1")
public class CotacaoEndpoint {

	CotacaoService cotacaoService = new CotacaoService();

	@ApiMethod(name = "listCotacao", httpMethod = "GET", path = "cotacoes")
	public List<Cotacao> listCotacao(
			@Nullable @Named("_offset") Integer offset,
			@Nullable @Named("_limit") Integer limit,
			@Nullable @Named("valorMinimo") Double valorMinimo,
			@Nullable @Named("valorMaximo") Double valorMaximo) {

		List<CotacaoBean> cotacoesBean = cotacaoService.listCotacao(offset,
				limit, valorMinimo, valorMaximo);

		List<Cotacao> cotacoesVO = new ArrayList<Cotacao>();
		for (CotacaoBean cotacaoBean : cotacoesBean) {
			cotacoesVO
					.add(ConverterUtil.getInstance().toCotacaoVO(cotacaoBean));
		}

		return cotacoesVO;

	}

	@ApiMethod(name = "getCotacao", httpMethod = "GET", path = "cotacoes/{id}")
	public Cotacao getCotacao(@Named("id") Long id) {
		return ConverterUtil.getInstance().toCotacaoVO(
				cotacaoService.getCotacao(id));
	}

	@ApiMethod(name = "requestCotacao", httpMethod = "POST", path = "cotacoes")
	public Cotacao requestCotacao(Segurado segurado) {

		SeguradoBean seguradoBean = ConverterUtil.getInstance().toSeguradoBean(
				segurado);

		return ConverterUtil.getInstance().toCotacaoVO(
				cotacaoService.requestCotacao(seguradoBean));

	}

	@ApiMethod(name = "updateCotacao", httpMethod = "PUT", path = "cotacoes/{id}")
	public Cotacao updateCotacao(@Named("id") Long id, Segurado segurado) {

		segurado.setId(id);
		
		SeguradoBean seguradoBean = ConverterUtil.getInstance().toSeguradoBean(
				segurado);

		return ConverterUtil.getInstance().toCotacaoVO(
				cotacaoService.updateCotacao(seguradoBean));
		
	}

	@ApiMethod(name = "removeCotacao", httpMethod = "DELETE", path = "cotacoes/{id}")
	public void removeCotacao(@Named("id") Long id) {

		cotacaoService.removeCotacao(id);

	}

}
