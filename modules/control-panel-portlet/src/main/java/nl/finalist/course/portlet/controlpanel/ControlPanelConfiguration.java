package nl.finalist.course.portlet.controlpanel;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "finalist",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = Constants.CONFIGURATION_PID,
        localization = "content/Language",
        name = "finalist-course-control-panel-configuration"
)
public interface ControlPanelConfiguration {

    @Meta.AD(
            required = false,
            name = "api-url",
            description = "The base url for api.",
            deflt = "https://x.finalist.nl/"
    )
    String apiUrl();

    @Meta.AD(
            required = false,
            name = "group-id",
            description = "Site id waar we de content ophalen",
            deflt = "20118"
    )
    long groupId();

    @Meta.AD(
            required = false,
            name = "stucture-id",
            description = "Structure id voor het content type",
            deflt = "33968"
    )
    long structureId();

}
