/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2015                  
 *                                                                                                                                 
 *  Creation Date: 05.02.2015                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.ui.dialog.classic.pricemodel.external;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Locale;
import java.util.UUID;

import javax.faces.component.UIViewRoot;
import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.oscm.billing.external.pricemodel.service.PriceModel;
import org.oscm.billing.external.pricemodel.service.PriceModelContent;
import org.oscm.internal.pricemodel.external.ExternalPriceModelException;
import org.oscm.internal.pricemodel.external.ExternalPriceModelService;
import org.oscm.internal.vo.VOPriceModel;
import org.oscm.internal.vo.VOServiceDetails;
import org.oscm.internal.vo.VOTechnicalService;
import org.oscm.ui.beans.ApplicationBean;
import org.oscm.ui.beans.PriceModelBean;
import org.oscm.ui.beans.SessionBean;
import org.oscm.ui.common.UiDelegate;
import org.oscm.ui.stubs.FacesContextStub;

/**
 * @author iversen
 * 
 */
public class ExternalPriceModelCtrlTest extends ExternalPriceModelTest {

    private ExternalPriceModelCtrl ctrl;
    private ExternalPriceModelModel model;
    private PriceModelBean priceModelBean;
    private ExternalPriceModelService externalPriceModelService;
    private final String BILLING_ID = "KILLBILL";
    private static final UUID PRICE_MODEL_UUID1 = UUID
            .fromString("392b159e-e60a-41c4-8495-4fd4b6252ed9");
    private Locale locale = Locale.ENGLISH;
    private ApplicationBean appbean;
    private SessionBean sessionBean;

    @Before
    public void beforeClass() {
        ctrl = Mockito.mock(ExternalPriceModelCtrl.class,
                Mockito.CALLS_REAL_METHODS);
        model = spy(new ExternalPriceModelModel());
        ctrl.setModel(model);
        ctrl.ui = mock(UiDelegate.class);

        priceModelBean = mock(PriceModelBean.class);
        doReturn(priceModelBean).when(ctrl).getPriceModelBean();

        appbean = mock(ApplicationBean.class);
        doReturn(appbean).when(ctrl).getAppBean();
        doReturn(locale).when(appbean).getDefaultLocale();

        externalPriceModelService = mock(ExternalPriceModelService.class);
        doReturn(externalPriceModelService).when(ctrl)
                .getExternalPriceModelService();

        sessionBean = mock(SessionBean.class);
        doReturn(sessionBean).when(ctrl).getSessionBean();

        UIViewRoot viewRoot = mock(UIViewRoot.class);
        given(viewRoot.getLocale()).willReturn(locale);
        new FacesContextStub(locale).setViewRoot(viewRoot);
    }

    @Test
    public void initializeShowPersistedPriceModel()
            throws ExternalPriceModelException {
        // given
        doReturn(new Boolean(true)).when(priceModelBean)
                .isExternalServiceSelected();
        doNothing().when(priceModelBean).updatePriceModel();
        VOServiceDetails selectedService = createVOServiceDetails();
        doReturn(selectedService).when(priceModelBean).getSelectedService();
        VOPriceModel priceModel = new VOPriceModel();
        doReturn(priceModel).when(priceModelBean).getPriceModel();
        doReturn(createExternalPriceModel(PRICE_MODEL_UUID1, new Locale("de")))
                .when(sessionBean).getSelectedExternalPriceModel();

        // when
        ctrl.initialize();

        // then
        verify(ctrl, times(1))
                .showPersistedPriceModel(Mockito.any(VOServiceDetails.class));
    }

    @Test
    public void initializeNativeBilling() throws ExternalPriceModelException {
        // given
        doReturn(new Boolean(false)).when(priceModelBean)
                .isExternalServiceSelected();

        // when
        ctrl.initialize();

        // then
        verify(ctrl, times(0)).showPersistedPriceModel(Mockito.any(VOServiceDetails.class));
    }

    @Test
    public void initBeans() throws ExternalPriceModelException {
        // given
        doReturn(null).when(ctrl).getAppBean();
        doReturn(null).when(ctrl).getPriceModelBean();
        doReturn(appbean).when(ctrl.ui).findBean(ctrl.APPBEAN);
        doReturn(priceModelBean).when(ctrl.ui).findBean(ctrl.PRICEMODELBEAN);

        // when
        ctrl.initBeans();

        // then
        verify(ctrl.ui, times(1)).findBean(ctrl.PRICEMODELBEAN);
        verify(ctrl.ui, times(1)).findBean(ctrl.APPBEAN);
    }

    @Test
    public void initBeansAppBeanIsNull() throws ExternalPriceModelException {
        // given
        doReturn(null).when(ctrl).getAppBean();
        doReturn(appbean).when(ctrl.ui).findBean(ctrl.APPBEAN);

        // when
        ctrl.initBeans();

        // then
        verify(ctrl.ui, times(0)).findBean(ctrl.PRICEMODELBEAN);
        verify(ctrl.ui, times(1)).findBean(ctrl.APPBEAN);
    }

    @Test
    public void initBeansPriceModelBeanIsNull()
            throws ExternalPriceModelException {
        // given
        doReturn(null).when(ctrl).getPriceModelBean();
        doReturn(priceModelBean).when(ctrl.ui).findBean(ctrl.PRICEMODELBEAN);

        // when
        ctrl.initBeans();

        // then
        verify(ctrl.ui, times(1)).findBean(ctrl.PRICEMODELBEAN);
        verify(ctrl.ui, times(0)).findBean(ctrl.APPBEAN);
    }

    @Test
    public void loadPriceModelContent() throws ExternalPriceModelException {

        // given
        PriceModelContent priceModelContent = createPriceModelContent();
        PriceModel priceModel = createPriceModel(priceModelContent,
                PRICE_MODEL_UUID1, this.locale);
        // when
        ctrl.loadPriceModelContent(priceModel);

        // then
        assertEquals(priceModelContent, model.getSelectedPriceModelContent());
        assertEquals(priceModel, model.getSelectedPriceModel());
        assertEquals(PRICE_MODEL_UUID1.toString(),
                model.getSelectedPriceModelId());

    }

    @Test
    public void showPersistedPriceModel() {
        // given
        VOServiceDetails selectedService = new VOServiceDetails();
        VOPriceModel priceModel = new VOPriceModel();
        String jsonPM = "{ \"plan name\": \"super-monthly\", \"phases\": [ { \"type\": \"TRIAL\", \"prices\": [] }, { \"type\": \"EVERGREEN\", \"prices\": [ { \"currency\": \"GBP\", \"value\": 750 }, { \"currency\": \"USD\", \"value\": 1000 } ] } ] }";
        priceModel.setPresentationDataType(MediaType.APPLICATION_JSON);
        priceModel.setPresentation(jsonPM.getBytes());
        priceModel.setExternal(true);
        priceModel.setUuid(PRICE_MODEL_UUID1);
        selectedService.setPriceModel(priceModel);

        // when
        ctrl.showPersistedPriceModel(selectedService);

        // then
        assertEquals(MediaType.APPLICATION_JSON,
                model.getSelectedPriceModelContent().getContentType());
        assertEquals(jsonPM,
                new String(model.getSelectedPriceModelContent().getContent()));

    }

    private VOServiceDetails createVOServiceDetails() {
        VOServiceDetails selectedService = new VOServiceDetails();
        selectedService.setBillingIdentifier(BILLING_ID);
        VOTechnicalService technicalService = new VOTechnicalService();
        technicalService.setExternalBilling(true);
        technicalService.setBillingIdentifier(BILLING_ID);
        selectedService.setTechnicalService(technicalService);
        return selectedService;

    }

}
