package com.sensedia.apix.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.sensedia.apix.server.model.CotacaoBean;
import com.sensedia.apix.server.service.CotacaoService;
import com.sensedia.apix.server.util.ConverterUtil;
import com.sensedia.apix.server.vo.Cotacao;

/**
 * WS Rest para manutenção de cotações.
 * 
 * @author Luiz César Cherri
 *
 */
@Api(name = "cotacaoendpoint")
public class CotacaoEndpoint {

	CotacaoService cotacaoService = new CotacaoService();

	@ApiMethod(name = "listCotacao", httpMethod = "GET")
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

	@ApiMethod(name = "getCotacao", httpMethod = "GET")
	public Cotacao getCotacao(@Named("id") Long id) {
		return ConverterUtil.getInstance().toCotacaoVO(
				cotacaoService.getCotacao(id));
	}

	@ApiMethod(name = "insertCotacao", httpMethod = "POST")
	public Cotacao insertCotacao(Cotacao cotacao) {

		CotacaoBean cotacaoBean = ConverterUtil.getInstance().toCotacaoBean(
				cotacao);

		return ConverterUtil.getInstance().toCotacaoVO(
				cotacaoService.insertCotacao(cotacaoBean));

	}

	@ApiMethod(name = "updateCotacao", httpMethod = "PUT")
	public Cotacao updateCotacao(Cotacao cotacao) {

		CotacaoBean cotacaoBean = ConverterUtil.getInstance().toCotacaoBean(
				cotacao);

		return ConverterUtil.getInstance().toCotacaoVO(
				cotacaoService.insertCotacao(cotacaoBean));
	}

	@ApiMethod(name = "removeCotacao", httpMethod = "DELETE")
	public void removeCotacao(@Named("id") Long id) {

		cotacaoService.removeCotacao(id);

	}

}
