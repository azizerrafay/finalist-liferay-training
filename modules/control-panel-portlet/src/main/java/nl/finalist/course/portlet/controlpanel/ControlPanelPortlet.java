/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.finalist.course.portlet.controlpanel;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PortletApp;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.ServletContextPool;

import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.util.LocaleUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Liferay
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-controlpanel",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.render-weight=100",
		"javax.portlet.display-name=Control Panel Demo",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=nl_finalist_course_portlet_controlpanel_ControlPanelAppPortlet",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class,
		configurationPid = Constants.CONFIGURATION_PID
)
public class ControlPanelPortlet extends MVCPortlet {

	private static final Log LOG = LogFactoryUtil.getLog(MethodHandles.lookup().lookupClass());


	@Reference
	JournalArticleLocalService journalArticleLocalService;


	@SuppressWarnings("java:S3077")
	private volatile  ControlPanelConfiguration controlPanelConfiguration;

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {

		controlPanelConfiguration = ConfigurableUtil.createConfigurable(ControlPanelConfiguration.class, properties);

		LOG.info("Reload Configuration : " + controlPanelConfiguration.groupId() + " : " + controlPanelConfiguration.structureId());
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {


        long groupId = controlPanelConfiguration.groupId(); // SITE ID
		long ddmStructureId = 	controlPanelConfiguration.structureId(); // DEMO

		// Haal alle artikelen op van het type DEMO in de Site van het content type ( Structure ) DEMO
		List<JournalArticle> articles = journalArticleLocalService.getArticlesByStructureId(
		 groupId, ddmStructureId,-1 , -1, null);


		List<String> items = new ArrayList<>();
		// Voet alle artikel titels toe aan de lijst
		articles.forEach( a ->
			items.add(a.getTitle(LocaleUtil.getDefault()))
		);

		// Plaats de lijst me resultaten in het renderrequest als attribuut,
		// zodat ze beschikbaar zijn in de view.jsp
		renderRequest.setAttribute("items", items);
		super.doView(renderRequest, renderResponse);
	}

	@Reference
	UserLocalService userLocalService;

	@Reference
	RoleLocalService roleLocalService;

	@Override
	public void destroy() {
		PortletContext portletContext = getPortletContext();

		ServletContextPool.remove(portletContext.getPortletContextName());

		super.destroy();
	}

	@Override
	public void init(PortletConfig portletConfig) throws PortletException {
		super.init(portletConfig);

		LiferayPortletConfig liferayPortletConfig =
			(LiferayPortletConfig)portletConfig;

		com.liferay.portal.kernel.model.Portlet portlet =
			liferayPortletConfig.getPortlet();

		PortletApp portletApp = portlet.getPortletApp();

		ServletContextPool.put(
			portletApp.getServletContextName(), portletApp.getServletContext());
	}

}